import com.toc.coredx.DDS.*;

public class SubTest {
	public static void main(String[] args) {
		class TestDataReaderListener implements DataReaderListener {
			
			public long get_nil_mask() { return 0; }
			
			public void on_requested_deadline_missed(DataReader dr, RequestedDeadlineMissedStatus status) { 
				System.out.println("--- REQUESTED DEADLINE MISSED ---"); 
				System.out.println("");
			};

			public void on_requested_incompatible_qos(DataReader dr, RequestedIncompatibleQosStatus status) { 
				System.out.println("--- REQUESTED INCOMPAT QOS ---"); 
				System.out.println("");
			};

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
				System.out.println(""); 
			}

			public void on_sample_lost(DataReader dr, SampleLostStatus status) { };
			
			public void on_data_available(DataReader dr) { 
				TopicDescription td = dr.get_topicdescription();
				System.out.println("--- DATA AVAILABLE ---"); 
				System.out.println("--- topic = " + td.get_name() + " (type: " + td.get_type_name() + ")");
				
				TruckInformationTopicDataReader string_dr = (TruckInformationTopicDataReader)dr;
				TruckInformationTopicSeq     samples = new TruckInformationTopicSeq();
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
							if (si.value[i].valid_data) System.out.println("--- msg: " + samples.value[i].tracker);
						}
					}
					
					string_dr.return_loan(samples, si);
				}
				
				else {}
	
				System.out.println("");				
			};
		};
		
		
		
		
		
		System.out.println ("Initializing");
		DomainParticipantFactory dpf = DomainParticipantFactory.get_instance();
    
		System.out.println("Creating domain participant");
		DomainParticipant dp = dpf.create_participant ( 0, null, null, 0 );
		
		System.out.println("Creating Subscriber");
		Subscriber sub = dp.create_subscriber ( null, null, 0 );
		
		System.out.println("Registering Type");
		TruckInformationTopicTypeSupport ts = new TruckInformationTopicTypeSupport();
		ReturnCode_t retval = ts.register_type( dp, "TruckInformationTopic" );
		
		System.out.println("Creating Topic");
		Topic top = dp.create_topic ( "TruckInformationTopic", "TruckInformationTopic", null, null, 0 );
		
		System.out.println("Creating Datareader");
		TestDataReaderListener dl = new TestDataReaderListener();
		TruckInformationTopicDataReader dr = (TruckInformationTopicDataReader) sub.create_datareader ( top, null, dl, coredx.getDDS_ALL_STATUS() );		
		
		System.out.println ( "Tipo do valor armazenado: " + ts.get_type_name() );
		System.out.println ( "Tipo do tópico enviado: " + top.get_type_name() );
		System.out.println ( "Nome do tópico: " + top.get_name() );
		
		
		/*while ( true ) {
			try {
				Thread.currentThread().sleep(100);  
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		
	}
};