import com.toc.coredx.DDS.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.event.*; 
import java.util.AbstractList.*;
import java.util.AbstractCollection.*;
import java.lang.Object.*;
import java.lang.Long.*;
import java.sql.SQLException;

public class ContextDataReaderListener implements DataReaderListener {
	
	private DomainParticipantFactory dpf = null;
	private DomainParticipant dp = null;
	private Subscriber sub = null;
	
	private TruckInformationTopicTypeSupport truckInformationTopicTS = null;
	private ReturnCode_t truckInformationRetval = null;
	private Topic truckInformationTop = null;
	private ContextDataReaderListener dl1 = null;
	private TruckInformationTopicDataReader truckInformationDr = null;
	
	public ArrayList<String> truckLeastId = new ArrayList<String>();
	public ArrayList<String> truckMostId = new ArrayList<String>();
	
	private ArrayList<TruckModel> truckInformationList = new ArrayList<TruckModel>();
	public static ArrayList<TruckModel> truckMessages = new ArrayList<TruckModel>();
	private ArrayList<Integer> truckGroups = new ArrayList<Integer>();
	private static ArrayList<GatewayModel> gatewayInformationList = new ArrayList<GatewayModel>();
	public static ArrayList<StringBuffer> truckInformationMessages = new ArrayList<StringBuffer>();
	public static StringBuffer truckInformationGlobalMessages = new StringBuffer();
	public static StringBuffer gatewayGlobalMessages = new StringBuffer();
	
	public int vehicleCounter = 0;
	public int gatewayCounter = 0;
	
	public long get_nil_mask() { return 0; }			
	
	public void on_requested_deadline_missed(DataReader dr, RequestedDeadlineMissedStatus status) { 
		System.out.println("--- REQUESTED DEADLINE MISSED ---"); 
		System.out.println("");
	}

	public void on_requested_incompatible_qos(DataReader dr, RequestedIncompatibleQosStatus status) { 
		System.out.println("--- REQUESTED INCOMPAT QOS ---"); 
		System.out.println("");
	}

	public void on_sample_rejected(DataReader dr, SampleRejectedStatus status) {  };

	public void on_liveliness_changed(DataReader dr, LivelinessChangedStatus status) {
		TopicDescription   td = dr.get_topicdescription();
		System.out.println("--- LIVELINESS CHANGED ---"); 
		System.out.println("");
	}

	public void on_subscription_matched(DataReader dr, SubscriptionMatchedStatus status) { 
		TopicDescription   td = dr.get_topicdescription();
		System.out.println("--- SUBSCRIPTION MATCHED ---"); 
		System.out.println("--- topic   = " + td.get_name() + " (type: " + td.get_type_name() + ")");
		System.out.println("--- current = " + status.get_current_count());
		
		if (td.get_name().equals("TruckGroupInfoTopic")) {
			//truckGroupInformationMessages.append("--- Group #" + status.get_current_count() + " has been created.\n");
			//MonitorWindow.updateGroupTextArea(truckGroupInformationMessages.toString());
			//MonitorWindow.updateGroupComboBox("Group #" + status.get_current_count());
		}
		
		System.out.println(""); 
	}

	public void on_sample_lost(DataReader dr, SampleLostStatus status) { };
	
	public void on_data_available(DataReader dr) { 
		TopicDescription td = dr.get_topicdescription();
		System.out.println("--- DATA AVAILABLE ---"); 
		System.out.println("--- topic = " + td.get_name() + " (type: " + td.get_type_name() + ")");
		
		// TruckInformationTopic
		if (td.get_type_name().equals("TruckInformationTopic")) {
			TruckInformationTopicDataReader string_dr = (TruckInformationTopicDataReader)dr;
			TruckInformationTopicSeq     samples = new TruckInformationTopicSeq();
			SampleInfoSeq si      = new SampleInfoSeq();
			ReturnCode_t  retval  = string_dr.take(samples, si, 100, coredx.DDS_ANY_SAMPLE_STATE, coredx.DDS_ANY_VIEW_STATE, coredx.DDS_ANY_INSTANCE_STATE);
			System.out.println(" --- DR.read() ===> " + retval);

			if (retval == ReturnCode_t.RETCODE_OK) {
				if (samples.value == null) System.out.println("--- samples.value = null");
				else {
					if (samples.value.length > 0) {
					
					TruckModel newTruck = new TruckModel(samples.value[0].leastSignificantBitsVehicleId, samples.value[0].mostSignificantBitsVehicleId, samples.value[0].leastSignificantBitsGatewayId, samples.value[0].mostSignificantBitsGatewayId, samples.value[0].informationGroups[0], samples.value[0].tracker, samples.value[0].information[0].latitude, samples.value[0].information[0].longitude, samples.value[0].information[0].speed, samples.value[0].information[0].time);						
					
					boolean foundTruck = false;
					int truckIndex = -1;
					for (int i = 0; i < truckInformationList.size(); i++) {
						if ((newTruck.getLeastBitsVehicleId() == truckInformationList.get(i).getLeastBitsVehicleId()) && (newTruck.getMostBitsVehicleId() ==truckInformationList.get(i).getMostBitsVehicleId())) {
							foundTruck = true;
							truckIndex = i;
							break;
						}
						else {
							continue;
						}						
					}
					
					if (foundTruck == false) {
						truckInformationList.add(newTruck);
						System.out.println("New truck found using new way to sort");
						
						StringBuffer truckBuffer = new StringBuffer();
						truckInformationMessages.add(truckBuffer);
						TrafficInspectorMainWindow.updateNotificationAreas("New vehicle found!");
						TrafficInspectorMainWindow.updateTruckCombo("Truck #" + vehicleCounter);
						vehicleCounter++;
						truckInformationMessages.get(truckInformationList.size() - 1).append("--- #" + (truckInformationList.size() - 1) + " -- " + samples.value[0].information[0].latitude + " -- " + samples.value[0].information[0].longitude + " -- " + samples.value[0].information[0].speed + "\n");
						
					}
					
					else {
						truckInformationMessages.get(truckIndex).append("--- #" + truckIndex + " -- " + samples.value[0].information[0].latitude + " -- " + samples.value[0].information[0].longitude + " -- " + samples.value[0].information[0].speed + "\n");						
					}
					
					truckInformationGlobalMessages.append("--- #" + truckIndex + " -- " + samples.value[0].information[0].latitude + " -- " + samples.value[0].information[0].longitude + " -- " + samples.value[0].information[0].speed + "\n");
					
					boolean foundGateway = false;
					int gatewayIndex = -1;
					for (int i = 0; i < gatewayInformationList.size(); i++) {
						if ((newTruck.getLeastBitsGatewayId() == gatewayInformationList.get(i).getLeastBitsGatewayId()) && (newTruck.getMostBitsGatewayId() ==gatewayInformationList.get(i).getMostBitsGatewayId())) {
							foundGateway = true;
							gatewayIndex = i;
							break;
						}
						else {							
							continue;
						}					
					}
					
					if (foundGateway == false) {
						GatewayModel newGateway = new GatewayModel(newTruck.getLeastBitsGatewayId(), newTruck.getMostBitsGatewayId());
						newGateway.setGatewayId(gatewayCounter);
						TrafficInspectorMainWindow.updateNotificationAreas("A new gateway has been detected.");
						gatewayInformationList.add(newGateway);
						TrafficInspectorMainWindow.updateTruckGatewayCombo(Integer.toString(newGateway.getGatewayId()));
						TrafficInspectorMainWindow.updateGatewayCombo(Integer.toString(newGateway.getGatewayId()));
						gatewayCounter++;
					}
					
					for (int i = 0; i < samples.value[0].informationGroups.length; i++) {
						TruckModel dataTruck = new TruckModel(samples.value[0].leastSignificantBitsVehicleId, samples.value[0].mostSignificantBitsVehicleId, samples.value[0].leastSignificantBitsGatewayId, samples.value[0].mostSignificantBitsGatewayId, samples.value[0].informationGroups[i], samples.value[0].tracker, samples.value[0].information[0].latitude, samples.value[0].information[0].longitude, samples.value[0].information[0].speed, samples.value[0].information[0].time);						
						truckMessages.add(dataTruck);
						if (!truckGroups.contains(samples.value[0].informationGroups[i])) {
							truckGroups.add(samples.value[0].informationGroups[i]);
							TrafficInspectorMainWindow.updateNotificationAreas("Group " + samples.value[0].informationGroups[i] + " has been detected.");
							TrafficInspectorMainWindow.updateTruckGroupCombo(Integer.toString(samples.value[0].informationGroups[i]));
							TrafficInspectorMainWindow.updateGroupCombo(Integer.toString(samples.value[0].informationGroups[i]));
						}						
					}
					
					// Update text area according to the selected truck.
					if (TrafficInspectorMainWindow.truckTrackingFilterSelected()) {
						if (truckIndex == TrafficInspectorMainWindow.truckGetSelectedTruck()) {
							TrafficInspectorMainWindow.updateTruckInformationArea(truckInformationMessages.get(truckIndex).toString());
						}
					}
					
					// Checks if this truck already sent any messages. If not, create an entry for it.
					/* 
					if (!truckMostId.contains(Long.toString(samples.value[0].mostSignificantBitsVehicleId)) && !truckLeastId.contains(Long.toString(samples.value[0].leastSignificantBitsVehicleId))) {
						truckMostId.add(Long.toString(samples.value[0].mostSignificantBitsVehicleId));
						truckLeastId.add(Long.toString(samples.value[0].leastSignificantBitsVehicleId));
						
						// Save the information about the so-called truck inside his StringBuffer.
						StringBuffer truckBuffer = new StringBuffer();
						truckInformationMessages.add(truckBuffer);
						
						// Inform that a new truck has been detected.
						// System.out.println("--- New Vehicle detected");
						TrafficInspectorMainWindow.updateNotificationAreas("New vehicle detected!");

						// Updates number of trucks connected and updates the GUI.					
						TrafficInspectorMainWindow.updateTruckCombo("Truck #" + vehicleCounter);
						vehicleCounter++;
					}
					*/
					
					/*
					int truckId = 0;
					if (truckMostId.indexOf(Long.toString(samples.value[0].mostSignificantBitsVehicleId)) == truckLeastId.indexOf(Long.toString(samples.value[0].leastSignificantBitsVehicleId))) {
						truckId = truckMostId.indexOf(Long.toString(samples.value[0].mostSignificantBitsVehicleId));					
					}				
					else {
						System.out.println("--- Id error!");
					}
					*/
					
					/*
					System.out.println(samples.value[0].information[0].latitude);
					System.out.println(samples.value[0].information[0].longitude);
					System.out.println(samples.value[0].information[0].speed);
					*/
					
					// Update that truck information, the global messages and update the window.
					
					// Insert message in the database (test purposes)
					//String dbInfo = Long.toString(samples.value[0].leastSignificantBitsVehicleId) + ", " + Long.toString(samples.value[0].mostSignificantBitsVehicleId) + ", " + Long.toString(samples.value[0].leastSignificantBitsGatewayId) + ", " + Long.toString(samples.value[0].mostSignificantBitsGatewayId) + ", " + samples.value[0].informationGroups[0] + ", '" + samples.value[0].tracker + "', " + samples.value[0].information[0].latitude + ", " + samples.value[0].information[0].longitude + ", " + samples.value[0].information[0].speed + ", " + Long.toString(samples.value[0].information[0].time);
					//System.out.println(dbInfo);
										
					try { 
						DatabaseModel.recordTruckInformation(newTruck);
						System.out.println("Database Write");
					}
					catch (SQLException msg) {
						System.out.println("Database error");
					}						
					
					//System.out.println("--- samples.value.length= " + samples.value.length);
					/* for (int i = 0; i < samples.value.length; i++) {
						System.out.println("--- State       : " + (si.value[i].instance_state == coredx.DDS_ALIVE_INSTANCE_STATE?"ALIVE":"NOT ALIVE") );
						System.out.println("--- TimeStamp   : " + si.value[i].source_timestamp.sec + "." + si.value[i].source_timestamp.nanosec);
						System.out.println("--- Handle      : " + si.value[i].instance_handle.value);
						System.out.println("--- WriterHandle: " + si.value[i].publication_handle.value);
						System.out.println("--- SampleRank  : " + si.value[i].sample_rank);
						if (si.value[i].valid_data) System.out.println("--- msg: " + samples.value[i].tracker);
					}
					*/
				}
				}
				
				string_dr.return_loan(samples, si);			
			}
			
			else {}
		}

		// LoadReportTopic
		if (td.get_type_name().equals("LoadReportTopic")) {
		
			LoadReportTopicDataReader string_dr = (LoadReportTopicDataReader)dr;
			LoadReportTopicSeq     samples = new LoadReportTopicSeq();
			SampleInfoSeq si      = new SampleInfoSeq();
			ReturnCode_t  retval  = string_dr.take(samples, si, 100, coredx.DDS_ANY_SAMPLE_STATE, coredx.DDS_ANY_VIEW_STATE, coredx.DDS_ANY_INSTANCE_STATE);
			System.out.println(" --- DR.read() ===> " + retval);

			if (retval == ReturnCode_t.RETCODE_OK) {
				if (samples.value == null) System.out.println("--- samples.value = null");
				else {
					System.out.println("--- samples.value.length= " + samples.value.length);
					for (int i = 0; i < samples.value.length; i++) {
						System.out.println("--- State       : " + (si.value[i].instance_state == coredx.DDS_ALIVE_INSTANCE_STATE?"ALIVE":"NOT ALIVE") );
						System.out.println("--- TimeStamp   : " + si.value[i].source_timestamp.sec + "." + si.value[i].source_timestamp.nanosec);
						System.out.println("--- Handle      : " + si.value[i].instance_handle.value);
						System.out.println("--- WriterHandle: " + si.value[i].publication_handle.value);
						System.out.println("--- SampleRank  : " + si.value[i].sample_rank);
						if (si.value[i].valid_data) System.out.println("--- msg: " + samples.value[i].numberOfConnectedVehicles);					
					}
				}
				
				string_dr.return_loan(samples, si);			
			}
			
			else {}
			
			gatewayGlobalMessages.append("-- " + Long.toString(samples.value[0].leastSignificantBitsParticipantId) + " -- " + Long.toString(samples.value[0].mostSignificantBitsParticipantId) + " -- " + samples.value[0].participantType + " -- " + samples.value[0].cpuUsage + " -- " + samples.value[0].freeMemory + " -- " + samples.value[0].numberOfConnectedVehicles + "\n");
			TrafficInspectorMainWindow.updateGatewayMessageArea(gatewayGlobalMessages.toString());
		}
		
		// PingTopic
		if (td.get_type_name().equals("PingTopic")) {
		
			PingTopicDataReader string_dr = (PingTopicDataReader)dr;
			PingTopicSeq     samples = new PingTopicSeq();
			SampleInfoSeq si      = new SampleInfoSeq();
			ReturnCode_t  retval  = string_dr.take(samples, si, 100, coredx.DDS_ANY_SAMPLE_STATE, coredx.DDS_ANY_VIEW_STATE, coredx.DDS_ANY_INSTANCE_STATE);
			System.out.println(" --- DR.read() ===> " + retval);

			if (retval == ReturnCode_t.RETCODE_OK) {
				if (samples.value == null) System.out.println("--- samples.value = null");
				else {
					System.out.println("--- samples.value.length= " + samples.value.length);
					for (int i = 0; i < samples.value.length; i++) {
						System.out.println("--- State       : " + (si.value[i].instance_state == coredx.DDS_ALIVE_INSTANCE_STATE?"ALIVE":"NOT ALIVE") );
						System.out.println("--- TimeStamp   : " + si.value[i].source_timestamp.sec + "." + si.value[i].source_timestamp.nanosec);
						System.out.println("--- Handle      : " + si.value[i].instance_handle.value);
						System.out.println("--- WriterHandle: " + si.value[i].publication_handle.value);
						System.out.println("--- SampleRank  : " + si.value[i].sample_rank);
						if (si.value[i].valid_data) System.out.println("--- msg: " + samples.value[i].pingId);					
					}
				}
				
				string_dr.return_loan(samples, si);			
			}
			
			else {}
		}
		
		// TruckGroupInfoTopic
		if (td.get_type_name().equals("TruckGroupInfoTopic")) {
		
			TruckGroupInfoTopicDataReader string_dr = (TruckGroupInfoTopicDataReader)dr;
			TruckGroupInfoTopicSeq     samples = new TruckGroupInfoTopicSeq();
			SampleInfoSeq si      = new SampleInfoSeq();
			ReturnCode_t  retval  = string_dr.take(samples, si, 100, coredx.DDS_ANY_SAMPLE_STATE, coredx.DDS_ANY_VIEW_STATE, coredx.DDS_ANY_INSTANCE_STATE);
			System.out.println(" --- DR.read() ===> " + retval);

			if (retval == ReturnCode_t.RETCODE_OK) {
				if (samples.value == null) System.out.println("--- samples.value = null");
				else {
					System.out.println("--- samples.value.length= " + samples.value.length);
					for (int i = 0; i < samples.value.length; i++) {
						System.out.println("--- State       : " + (si.value[i].instance_state == coredx.DDS_ALIVE_INSTANCE_STATE?"ALIVE":"NOT ALIVE") );
						System.out.println("--- TimeStamp   : " + si.value[i].source_timestamp.sec + "." + si.value[i].source_timestamp.nanosec);
						System.out.println("--- Handle      : " + si.value[i].instance_handle.value);
						System.out.println("--- WriterHandle: " + si.value[i].publication_handle.value);
						System.out.println("--- SampleRank  : " + si.value[i].sample_rank);
						if (si.value[i].valid_data) System.out.println("--- msg: " + samples.value[i].informationGroups.length);					
					}
				}
				
				string_dr.return_loan(samples, si);			
			}
			
			else {}
		}			
	}
	
	public static GatewayModel getGatewayById (int index) {
		for (int i = 0; i < gatewayInformationList.size(); i++) {
			if (gatewayInformationList.get(i).getGatewayId() == index) {
				return gatewayInformationList.get(i);
			}
		}
		
		return null;
	}
	
	public static String sortTrucksByGroup (ArrayList<TruckModel> informationList, int groupArg) {
		StringBuffer resultString = new StringBuffer();
		for (int i = 0; i < informationList.size(); i++) {
			if (informationList.get(i).getGroups() == groupArg) {
				resultString.append(Long.toString(informationList.get(i).getLeastBitsVehicleId()) + " " + Long.toString(informationList.get(i).getMostBitsVehicleId()) + " " +  informationList.get(i).getLatitude() + " " + informationList.get(i).getLongitude() + " " + informationList.get(i).getSpeed() + "\n");
			}
		}
		return resultString.toString();
	}
	
	public static String sortTrucksByGateway (ArrayList<TruckModel> informationList, int gatewayId) {
		StringBuffer resultString = new StringBuffer();
		GatewayModel sortGateway = getGatewayById(gatewayId);
		for (int i = 0; i < informationList.size(); i++) {
			if ((informationList.get(i).getLeastBitsGatewayId() == sortGateway.getLeastBitsGatewayId()) && (informationList.get(i).getMostBitsGatewayId() == sortGateway.getMostBitsGatewayId())) {
				resultString.append(Long.toString(informationList.get(i).getLeastBitsVehicleId()) + " " + Long.toString(informationList.get(i).getMostBitsVehicleId()) + " " +  informationList.get(i).getLatitude() + " " + informationList.get(i).getLongitude() + " " + informationList.get(i).getSpeed() + "\n");
			}
		}
		
		return resultString.toString();
	}
	
	public void prepare () {
		System.out.println ("Initializing");
		DomainParticipantFactory dpf = DomainParticipantFactory.get_instance();
    
		System.out.println("Creating domain participant");
		DomainParticipant dp = dpf.create_participant ( 0, null, null, 0 );
		
		System.out.println("Creating Subscriber");
		Subscriber sub = dp.create_subscriber ( null, null, 0 );
		
		truckInformationTopicTS = new TruckInformationTopicTypeSupport();
		truckInformationRetval = truckInformationTopicTS.register_type( dp, "TruckInformationTopic" );
		truckInformationTop = dp.create_topic ( "TruckInformationTopic", "TruckInformationTopic", null, null, 0 );
		dl1 = new ContextDataReaderListener();
		truckInformationDr = (TruckInformationTopicDataReader) sub.create_datareader ( truckInformationTop, null, dl1, coredx.getDDS_ALL_STATUS() );
		
		TruckGroupInfoTopicTypeSupport truckGroupInfoTS = new TruckGroupInfoTopicTypeSupport();
		ReturnCode_t TruckGroupInfoRetval = truckGroupInfoTS.register_type( dp, "TruckGroupInfoTopic" );
		ContextDataReaderListener dl5 = new ContextDataReaderListener();
		Topic truckGroupInfoTop = dp.create_topic ( "TruckGroupInfoTopic", "TruckGroupInfoTopic", null, null, 0 );
		TruckGroupInfoTopicDataReader truckGroupInfoDr = (TruckGroupInfoTopicDataReader) sub.create_datareader ( truckGroupInfoTop, null, dl5, coredx.getDDS_ALL_STATUS() );
		
		LoadReportTopicTypeSupport loadReportTopicTS = new LoadReportTopicTypeSupport();
		ReturnCode_t LoadReportRetval = loadReportTopicTS.register_type( dp, "LoadReportTopic" );
		Topic loadReportTop = dp.create_topic ( "LoadReportTopic", "LoadReportTopic", null, null, 0 );
		ContextDataReaderListener dl3 = new ContextDataReaderListener();
		LoadReportTopicDataReader loadReportDr = (LoadReportTopicDataReader) sub.create_datareader ( loadReportTop, null, dl3, coredx.getDDS_ALL_STATUS());	
	}
}


	