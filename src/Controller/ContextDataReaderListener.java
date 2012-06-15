// Author: Matheus Maciel
//  Class: ContextDataReaderListener
//  Last update: 15/06/2012
//  Reasoning for last update: Comments
// Package 
package Controller;

// Imports 
import DDSTopics.*;
import Model.DatabaseModel;
import Model.GatewayModel;
import Model.GroupModel;
import Model.TruckModel;
import View.TrafficInspectorMainWindow;
import com.toc.coredx.DDS.*;
import java.sql.SQLException;

// Traffic Inspector Data Reader / Listener Class
// This class is responsible for treating all the data received from the SDDL
// Whenever there's an update in SDDL, prepare and on_data_available methods must be updated to match topic names
public class ContextDataReaderListener implements DataReaderListener {

    // <editor-fold defaultstate="collapsed" desc="Private properties of ContextDataReaderListener">
    private DomainParticipantFactory dpf = DomainParticipantFactory.get_instance();
    private DomainParticipant dp = dpf.create_participant(0, null, null, 0);
    private Subscriber sub = dp.create_subscriber(null, null, 0);
    private TruckInformationTopicDataReader truckInformationDr;
    private GroupAdvertisementTopicDataReader GroupAdvertisementTopicDr;
    private LoadReportTopicDataReader loadReportDr;
    private AppManager APPLICATION_MANAGER;
    private TrafficInspectorMainWindow MAIN_WINDOW;
    // Numeric controllers for custom topic management
    private static int customTruckDataReaderCounter = 0;
    private static int customGroupDataReaderCounter = 0;
    private static int customGatewayDataReaderCounter = 0;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Default class constructor">
    public ContextDataReaderListener(AppManager applicationManager, TrafficInspectorMainWindow mainWindow) {
        APPLICATION_MANAGER = applicationManager;
        MAIN_WINDOW = mainWindow;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="DataReaderListener interface methods">
    @Override
    public long get_nil_mask() {
        return 0;
    }

    @Override
    public void on_requested_deadline_missed(DataReader dr, RequestedDeadlineMissedStatus status) {
        System.out.println("--- REQUESTED DEADLINE MISSED ---");
        System.out.println("");
    }

    @Override
    public void on_requested_incompatible_qos(DataReader dr, RequestedIncompatibleQosStatus status) {
        System.out.println("--- REQUESTED INCOMPAT QOS ---");
        System.out.println("");
    }

    @Override
    public void on_sample_rejected(DataReader dr, SampleRejectedStatus status) {
    }

    ;

    @Override
    public void on_liveliness_changed(DataReader dr, LivelinessChangedStatus status) {
        TopicDescription td = dr.get_topicdescription();
        System.out.println("--- LIVELINESS CHANGED ---");
        System.out.println("");
    }

    @Override
    public void on_subscription_matched(DataReader dr, SubscriptionMatchedStatus status) {
        TopicDescription td = dr.get_topicdescription();
        System.out.println("--- SUBSCRIPTION MATCHED ---");
        System.out.println("--- topic   = " + td.get_name() + " (type: " + td.get_type_name() + ")");
        System.out.println("--- current = " + status.get_current_count());
    }

    @Override
    public void on_sample_lost(DataReader dr, SampleLostStatus status) {
    }

    ;

    // This is where the magic happens
    // Whenever a given topic is identified, this method handles how data should be used and what elements should be updated */
    // This class is connected to AppManager so updates on that controller must be taken in account when this class uses AppManager methods */
    @Override
    public void on_data_available(DataReader dr) {
        TopicDescription td = dr.get_topicdescription();
        System.out.println("--- DATA AVAILABLE ---");
        System.out.println("--- topic = " + td.get_name() + " (type: " + td.get_type_name() + ")");

        // <editor-fold defaultstate="collapsed" desc="Custom TruckInformationTopic Reader implementation">
        for (int truckCustomTopicIterator = 0; truckCustomTopicIterator < customTruckDataReaderCounter; truckCustomTopicIterator++) {
            if (td.get_name().equals("TruckInformationFilteredTopic" + truckCustomTopicIterator)) {
                TruckInformationTopicDataReader string_dr = (TruckInformationTopicDataReader) dr;
                TruckInformationTopicSeq samples = new TruckInformationTopicSeq();
                SampleInfoSeq si = new SampleInfoSeq();
                ReturnCode_t retval = string_dr.take(samples, si, 100, coredx.DDS_ANY_SAMPLE_STATE, coredx.DDS_ANY_VIEW_STATE, coredx.DDS_ANY_INSTANCE_STATE);
                //System.out.println(" --- DR.read() ===> " + retval);


                // If everything went ok when fetching available information...
                if (retval == ReturnCode_t.RETCODE_OK) {
                    if (samples.value == null) {
                        System.out.println("--- samples.value = null");
                    } else {
                        // Loop thru available samples
                        for (int truckSampleCounter = 0; truckSampleCounter < samples.value.length; truckSampleCounter++) {

                            TruckModel newTruck = null;

                            // Check if the found node has a defined group already
                            // If they don't, add them to the list without groups anyway
                            if (samples.value[truckSampleCounter].groupCollection.length == 0) {
                                newTruck = new TruckModel(samples.value[truckSampleCounter].leastSignificantBitsVehicleId, samples.value[truckSampleCounter].mostSignificantBitsVehicleId, samples.value[truckSampleCounter].leastSignificantBitsGatewayId, samples.value[truckSampleCounter].mostSignificantBitsGatewayId, null, samples.value[truckSampleCounter].tracker, samples.value[truckSampleCounter].information[0].latitude, samples.value[truckSampleCounter].information[truckSampleCounter].longitude, samples.value[truckSampleCounter].information[0].speed, samples.value[0].information[0].time);
                                APPLICATION_MANAGER.addTruckCustomMessage(truckCustomTopicIterator, newTruck);
                            } // If they had a defined group...
                            else {
                                GroupModel truckGroupInformation = null;
                                GroupModel[] truckGroups = null;
                                // Loop thru the groups that sample has
                                for (int i = 0; i < samples.value[truckSampleCounter].groupCollection.length; i++) {
                                    truckGroups = new GroupModel[samples.value[truckSampleCounter].groupCollection.length];
                                    truckGroupInformation = new GroupModel(samples.value[0].leastSignificantBitsVehicleId, samples.value[truckSampleCounter].mostSignificantBitsVehicleId, samples.value[0].leastSignificantBitsGatewayId, samples.value[truckSampleCounter].mostSignificantBitsGatewayId, samples.value[truckSampleCounter].groupCollection[i].groupIDCollection);
                                    truckGroups[i] = truckGroupInformation;
                                }

                                // Add the node custom message to the list using the groups it belong to
                                newTruck = new TruckModel(samples.value[truckSampleCounter].leastSignificantBitsVehicleId, samples.value[truckSampleCounter].mostSignificantBitsVehicleId, samples.value[truckSampleCounter].leastSignificantBitsGatewayId, samples.value[truckSampleCounter].mostSignificantBitsGatewayId, truckGroups, samples.value[truckSampleCounter].tracker, samples.value[truckSampleCounter].information[0].latitude, samples.value[truckSampleCounter].information[0].longitude, samples.value[truckSampleCounter].information[0].speed, samples.value[truckSampleCounter].information[0].time);
                                APPLICATION_MANAGER.addTruckCustomMessage(truckCustomTopicIterator, newTruck);
                            }
                        }
                    }
                    string_dr.return_loan(samples, si);
                } else {
                }
            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Custom GroupAdvertisementTopic Reader implementation">
        for (int groupCustomTopicIterator = 0; groupCustomTopicIterator < customGroupDataReaderCounter; groupCustomTopicIterator++) {
            if (td.get_name().equals("GroupAdvertisementFilteredTopic" + groupCustomTopicIterator)) {
                GroupAdvertisementTopicDataReader string_dr = (GroupAdvertisementTopicDataReader) dr;
                GroupAdvertisementTopicSeq samples = new GroupAdvertisementTopicSeq();
                SampleInfoSeq si = new SampleInfoSeq();
                ReturnCode_t retval = string_dr.take(samples, si, 100, coredx.DDS_ANY_SAMPLE_STATE, coredx.DDS_ANY_VIEW_STATE, coredx.DDS_ANY_INSTANCE_STATE);
                //System.out.println(" --- DR.read() ===> " + retval);

                // If everything went ok when fetching available information...
                if (retval == ReturnCode_t.RETCODE_OK) {
                    if (samples.value == null) {
                        System.out.println("--- samples.value = null");
                    } else {
                        // Loop thru available samples
                        for (int groupSampleCounter = 0; groupSampleCounter < samples.value.length; groupSampleCounter++) {
                            // Add Group custom message where it belongs and update the interface
                            GroupModel newGroup = new GroupModel(samples.value[groupSampleCounter].leastSignificantBitsGatewayId, samples.value[groupSampleCounter].mostSignificantBitsGatewayId, samples.value[groupSampleCounter].leastSignificantBitsVehicleId, samples.value[groupSampleCounter].mostSignificantBitsVehicleId, samples.value[groupSampleCounter].groupOperationCollection);
                            APPLICATION_MANAGER.addGroupCustomMessage(groupCustomTopicIterator, newGroup);
                            APPLICATION_MANAGER.updateGroupInformationTable();
                        }
                    }
                    string_dr.return_loan(samples, si);
                }
            }
        }

        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Custom LoadReportTopic implementation">
        for (int gatewayCustomTopicIterator = 0; gatewayCustomTopicIterator < customGatewayDataReaderCounter; gatewayCustomTopicIterator++) {
            if (td.get_name().equals("LoadReportFilteredTopic" + gatewayCustomTopicIterator)) {
                LoadReportTopicDataReader string_dr = (LoadReportTopicDataReader) dr;
                LoadReportTopicSeq samples = new LoadReportTopicSeq();
                SampleInfoSeq si = new SampleInfoSeq();
                ReturnCode_t retval = string_dr.take(samples, si, 100, coredx.DDS_ANY_SAMPLE_STATE, coredx.DDS_ANY_VIEW_STATE, coredx.DDS_ANY_INSTANCE_STATE);
                //System.out.println(" --- DR.read() ===> " + retval);

                // If everything went ok when fetching data...
                if (retval == ReturnCode_t.RETCODE_OK) {
                    if (samples.value == null) {
                        //System.out.println("--- samples.value = null");
                    } else {
                        // Loop thru available samples
                        for (int gatewaySampleCounter = 0; gatewaySampleCounter < samples.value.length; gatewaySampleCounter++) {
                            // Add the gateway custom message where it belongs and update the interface
                            GatewayModel newGateway = new GatewayModel(samples.value[gatewaySampleCounter].leastSignificantBitsParticipantId, samples.value[gatewaySampleCounter].mostSignificantBitsParticipantId, samples.value[gatewaySampleCounter].participantType, samples.value[gatewaySampleCounter].cpuUsage, samples.value[gatewaySampleCounter].freeMemory, samples.value[gatewaySampleCounter].numberOfConnectedVehicles);
                            APPLICATION_MANAGER.addGatewayCustomMessage(gatewayCustomTopicIterator, newGateway);
                            APPLICATION_MANAGER.updateGatewayInformationTable();
                        }
                    }
                    string_dr.return_loan(samples, si);
                } else {
                }
            }
        }
        // </editor-fold>        

        // <editor-fold defaultstate="collapsed" desc="Normal TruckInformationTopic Reader Implementation">
        if (td.get_name().equals("TruckInformationTopic")) {
            TruckInformationTopicDataReader string_dr = (TruckInformationTopicDataReader) dr;
            TruckInformationTopicSeq samples = new TruckInformationTopicSeq();
            SampleInfoSeq si = new SampleInfoSeq();
            ReturnCode_t retval = string_dr.take(samples, si, 100, coredx.DDS_ANY_SAMPLE_STATE, coredx.DDS_ANY_VIEW_STATE, coredx.DDS_ANY_INSTANCE_STATE);
            //System.out.println(" --- DR.read() ===> " + retval);

            TruckModel newTruck = null;
            
            // If everything went ok when fetching data...
            if (retval == ReturnCode_t.RETCODE_OK) {
                if (samples.value == null) {
                    //System.out.println("--- samples.value = null");
                } else {
                    // Loop thru available samples
                    for (int truckSampleCounter = 0; truckSampleCounter < samples.value.length; truckSampleCounter++) {
                        // Check if node belongs to a valid group
                        if (samples.value[truckSampleCounter].groupCollection.length == 0) {
                            // Add node using no groups and update the interface
                            newTruck = new TruckModel(samples.value[truckSampleCounter].leastSignificantBitsVehicleId, samples.value[truckSampleCounter].mostSignificantBitsVehicleId, samples.value[truckSampleCounter].leastSignificantBitsGatewayId, samples.value[truckSampleCounter].mostSignificantBitsGatewayId, null, samples.value[truckSampleCounter].tracker, samples.value[truckSampleCounter].information[0].latitude, samples.value[truckSampleCounter].information[truckSampleCounter].longitude, samples.value[truckSampleCounter].information[0].speed, samples.value[0].information[0].time);
                            APPLICATION_MANAGER.identifyTruck(newTruck);
                            APPLICATION_MANAGER.identifyGateway(newTruck);
                            APPLICATION_MANAGER.addTruckMessage(newTruck);
                            if (MAIN_WINDOW.truckTrackingFilterSelected() && (MAIN_WINDOW.truckGetSelectedTruck() == APPLICATION_MANAGER.getTruckId(newTruck))) {
                                APPLICATION_MANAGER.updateTruckInformationTable();
                            }

                            if (MAIN_WINDOW.truckGatewayFilterSelected() && (MAIN_WINDOW.truckGetSelectedGateway() == APPLICATION_MANAGER.getGatewayIndexByTruck(newTruck))) {
                                APPLICATION_MANAGER.updateTruckInformationTable();
                            }

                        } // If it belongs to a valid group...
                        else {
                            // Organize the groups
                            GroupModel truckGroupInformation = null;
                            GroupModel[] truckGroups = null;
                            for (int i = 0; i < samples.value[truckSampleCounter].groupCollection.length; i++) {
                                truckGroups = new GroupModel[samples.value[truckSampleCounter].groupCollection.length];
                                truckGroupInformation = new GroupModel(samples.value[0].leastSignificantBitsVehicleId, samples.value[truckSampleCounter].mostSignificantBitsVehicleId, samples.value[0].leastSignificantBitsGatewayId, samples.value[truckSampleCounter].mostSignificantBitsGatewayId, samples.value[truckSampleCounter].groupCollection[i].groupIDCollection);
                                truckGroups[i] = truckGroupInformation;
                            }

                            // Add node message to where it belongs and update the interface
                            newTruck = new TruckModel(samples.value[truckSampleCounter].leastSignificantBitsVehicleId, samples.value[truckSampleCounter].mostSignificantBitsVehicleId, samples.value[truckSampleCounter].leastSignificantBitsGatewayId, samples.value[truckSampleCounter].mostSignificantBitsGatewayId, truckGroups, samples.value[truckSampleCounter].tracker, samples.value[truckSampleCounter].information[0].latitude, samples.value[truckSampleCounter].information[0].longitude, samples.value[truckSampleCounter].information[0].speed, samples.value[truckSampleCounter].information[0].time);
                            APPLICATION_MANAGER.identifyTruck(newTruck);
                            APPLICATION_MANAGER.identifyGateway(newTruck);
                            APPLICATION_MANAGER.identifyGroup(newTruck);
                            APPLICATION_MANAGER.addTruckMessage(newTruck);

                            if (MAIN_WINDOW.truckTrackingFilterSelected() && (MAIN_WINDOW.truckGetSelectedTruck() == APPLICATION_MANAGER.getTruckId(newTruck))) {
                                APPLICATION_MANAGER.updateTruckInformationTable();
                            }

                            if (MAIN_WINDOW.truckGatewayFilterSelected() && (MAIN_WINDOW.truckGetSelectedGateway() == APPLICATION_MANAGER.getGatewayIndexByTruck(newTruck))) {
                                APPLICATION_MANAGER.updateTruckInformationTable();
                            }
                        }
                    }
                }
                string_dr.return_loan(samples, si);
            } else {
            }
            try { DatabaseModel.recordTruckInformationToMySQL(newTruck); }
            catch (SQLException except) { System.out.println("Erro ao gravar o truck no MySQL"); }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Normal LoadReportTopic Reader Implementation">
        if (td.get_name().equals("LoadReportTopic")) {
            GatewayModel newGateway = null;
            
            LoadReportTopicDataReader string_dr = (LoadReportTopicDataReader) dr;
            LoadReportTopicSeq samples = new LoadReportTopicSeq();
            SampleInfoSeq si = new SampleInfoSeq();
            ReturnCode_t retval = string_dr.take(samples, si, 100, coredx.DDS_ANY_SAMPLE_STATE, coredx.DDS_ANY_VIEW_STATE, coredx.DDS_ANY_INSTANCE_STATE);
            //System.out.println(" --- DR.read() ===> " + retval);

            // if everything went ok when fetching data...
            if (retval == ReturnCode_t.RETCODE_OK) {
                if (samples.value == null) {
                    //System.out.println("--- samples.value = null");
                } else {
                    // Loop thru samples
                    for (int gatewaySampleCounter = 0; gatewaySampleCounter < samples.value.length; gatewaySampleCounter++) {
                        // Add gateway message where it belongs and update the interface
                        newGateway = new GatewayModel(samples.value[gatewaySampleCounter].leastSignificantBitsParticipantId, samples.value[gatewaySampleCounter].mostSignificantBitsParticipantId, samples.value[gatewaySampleCounter].participantType, samples.value[gatewaySampleCounter].cpuUsage, samples.value[gatewaySampleCounter].freeMemory, samples.value[gatewaySampleCounter].numberOfConnectedVehicles);
                        APPLICATION_MANAGER.identifyGatewayOnGateway(newGateway);
                        APPLICATION_MANAGER.addGatewayMessage(newGateway);
                        APPLICATION_MANAGER.updateGatewayInformationTable();
                    }
                }
                string_dr.return_loan(samples, si);
            } else {
            }
            
            try { DatabaseModel.recordGatewayInformationToMySQL(newGateway); }
            catch (SQLException except) { System.out.println("Erro ao gravar o truck no MySQL"); }
            
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Normal GroupAdvertisementTopic Reader Implementation">        
        if (td.get_name().equals("GroupAdvertisementTopic")) {
            GroupModel newGroup = null;
            
            GroupAdvertisementTopicDataReader string_dr = (GroupAdvertisementTopicDataReader) dr;
            GroupAdvertisementTopicSeq samples = new GroupAdvertisementTopicSeq();
            SampleInfoSeq si = new SampleInfoSeq();
            ReturnCode_t retval = string_dr.take(samples, si, 100, coredx.DDS_ANY_SAMPLE_STATE, coredx.DDS_ANY_VIEW_STATE, coredx.DDS_ANY_INSTANCE_STATE);
            //System.out.println(" --- DR.read() ===> " + retval);

            // If everything went ok when fetching data...
            if (retval == ReturnCode_t.RETCODE_OK) {
                if (samples.value == null) {
                    //System.out.println("--- samples.value = null");
                } else {
                    // Loop thru samples
                    for (int groupSampleCounter = 0; groupSampleCounter < samples.value.length; groupSampleCounter++) {
                        // Add group message where it belongs and update the interface
                        newGroup = new GroupModel(samples.value[groupSampleCounter].leastSignificantBitsGatewayId, samples.value[groupSampleCounter].mostSignificantBitsGatewayId, samples.value[groupSampleCounter].leastSignificantBitsVehicleId, samples.value[groupSampleCounter].mostSignificantBitsVehicleId, samples.value[groupSampleCounter].groupOperationCollection);                        
                        APPLICATION_MANAGER.identifyGroupOnGroups(newGroup);
                        APPLICATION_MANAGER.addGroupMessage(newGroup);
                        try {DatabaseModel.recordGroupInformationToMySQL(newGroup);}
                        catch (SQLException except) { System.out.println("Erro ao gravar o grupo no MySQL"); }
                        APPLICATION_MANAGER.updateGroupInformationTable();
                    }
                }

                string_dr.return_loan(samples, si);
            }
        }
        // </editor-fold>
    }
    // </editor-fold>
    
    // Method responsible for subscribing in every topic traffic inspector is responsable of inspecting
    // Any new topics should be added here
    public void prepare() {
        TruckInformationTopicTypeSupport truckInformationTopicTS = new TruckInformationTopicTypeSupport();
        ReturnCode_t truckInformationRetval = truckInformationTopicTS.register_type(dp, "TruckInformationTopic");
        Topic truckInformationTop = dp.create_topic("TruckInformationTopic", "TruckInformationTopic", null, null, 0);
        ContextDataReaderListener dl1 = new ContextDataReaderListener(APPLICATION_MANAGER, MAIN_WINDOW);
        truckInformationDr = (TruckInformationTopicDataReader) sub.create_datareader(truckInformationTop, null, dl1, coredx.getDDS_ALL_STATUS());

        GroupAdvertisementTopicTypeSupport GroupAdvertisementTopicTS = new GroupAdvertisementTopicTypeSupport();
        ReturnCode_t GroupAdvertisementTopicRetval = GroupAdvertisementTopicTS.register_type(dp, "GroupAdvertisementTopic");
        ContextDataReaderListener dl6 = new ContextDataReaderListener(APPLICATION_MANAGER, MAIN_WINDOW);
        Topic GroupAdvertisementTopicTop = dp.create_topic("GroupAdvertisementTopic", "GroupAdvertisementTopic", null, null, 0);
        GroupAdvertisementTopicDr = (GroupAdvertisementTopicDataReader) sub.create_datareader(GroupAdvertisementTopicTop, null, dl6, coredx.getDDS_ALL_STATUS());

        LoadReportTopicTypeSupport loadReportTopicTS = new LoadReportTopicTypeSupport();
        ReturnCode_t LoadReportRetval = loadReportTopicTS.register_type(dp, "LoadReportTopic");
        Topic loadReportTop = dp.create_topic("LoadReportTopic", "LoadReportTopic", null, null, 0);
        ContextDataReaderListener dl3 = new ContextDataReaderListener(APPLICATION_MANAGER, MAIN_WINDOW);
        loadReportDr = (LoadReportTopicDataReader) sub.create_datareader(loadReportTop, null, dl3, coredx.getDDS_ALL_STATUS());
    }

    // This method creates a custom topic and a datareader for that topic
    // The filter determinates how information will be available in that topic
    // It uses a counter to indicate to diffentiate filtered topics about trucks
    public void createCustomTruckFilter(String filter) {
        TruckInformationTopicTypeSupport truckInformationFilteredTopicTS = new TruckInformationTopicTypeSupport();
        ReturnCode_t truckInformationFilteredRetval = truckInformationFilteredTopicTS.register_type(dp, null);
        Topic truckInformationFilteredTop = dp.create_topic("TruckInformationTopic", "TruckInformationTopic", null, null, 0);
        ContentFilteredTopic filteredTopic = dp.create_contentfilteredtopic("TruckInformationFilteredTopic" + customTruckDataReaderCounter, truckInformationFilteredTop, filter, null);
        customTruckDataReaderCounter++;
        ContextDataReaderListener dl = new ContextDataReaderListener(APPLICATION_MANAGER, MAIN_WINDOW);
        TruckInformationTopicDataReader truckInformationFilteredDr = (TruckInformationTopicDataReader) sub.create_datareader(filteredTopic, null, dl, coredx.getDDS_ALL_STATUS());
    }

    // This method creates a custom topic and a datareader for that topic
    // The filter determinates how information will be available in that topic
    // It uses a counter to indicate to diffentiate filtered topics about groups
    public void createCustomGroupFilter(String filter) {
        GroupAdvertisementTopicTypeSupport GroupAdvertisementFilteredTopicTS = new GroupAdvertisementTopicTypeSupport();
        ReturnCode_t GroupAdvertisementFilteredTopicRetval = GroupAdvertisementFilteredTopicTS.register_type(dp, null);
        Topic GroupAdvertisementFilteredTop = dp.create_topic("GroupAdvertisementTopic", "GroupAdvertisementTopic", null, null, 0);
        ContentFilteredTopic filteredTopic = dp.create_contentfilteredtopic("GroupAdvertisementFilteredTopic" + customGroupDataReaderCounter, GroupAdvertisementFilteredTop, filter, null);
        customGroupDataReaderCounter++;
        ContextDataReaderListener dl = new ContextDataReaderListener(APPLICATION_MANAGER, MAIN_WINDOW);
        GroupAdvertisementTopicDataReader groupAdvertisementFilteredDr = (GroupAdvertisementTopicDataReader) sub.create_datareader(filteredTopic, null, dl, coredx.getDDS_ALL_STATUS());
    }

    // This method creates a custom topic and a datareader for that topic
    // The filter determinates how information will be available in that topic
    // It uses a counter to indicate to diffentiate filtered topics about gateways
    public void createCustomGatewayFilter(String filter) {
        LoadReportTopicTypeSupport loadReportFilteredTopicTS = new LoadReportTopicTypeSupport();
        ReturnCode_t LoadReportRetval = loadReportFilteredTopicTS.register_type(dp, null);
        Topic loadReportFilteredTop = dp.create_topic("LoadReportTopic", "LoadReportTopic", null, null, 0);
        ContentFilteredTopic filteredTopic = dp.create_contentfilteredtopic("LoadReportFilteredTopic" + customGatewayDataReaderCounter, loadReportFilteredTop, filter, null);
        customGatewayDataReaderCounter++;
        ContextDataReaderListener dl = new ContextDataReaderListener(APPLICATION_MANAGER, MAIN_WINDOW);
        LoadReportTopicDataReader loadReportFilteredDr = (LoadReportTopicDataReader) sub.create_datareader(filteredTopic, null, dl, coredx.getDDS_ALL_STATUS());
    }    
}
