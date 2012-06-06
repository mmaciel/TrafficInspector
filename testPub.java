import com.toc.coredx.DDS.*;

public class testPub {
	public static void main ( String[] args ) {
		System.out.println("Initializing");
		DomainParticipantFactory dpf = DomainParticipantFactory.get_instance();
		
		System.out.println("Creating domain participant");
		DomainParticipant dp = dpf.create_participant ( 0, null, null, 0 );
		
		System.out.println("Creating Publisher");
		Publisher pub = dp.create_publisher ( null, null, 0 );
		
		System.out.println("Registering Type");
		LoadReportTopicTypeSupport ts = new LoadReportTopicTypeSupport();
		ReturnCode_t retval = ts.register_type( dp, "LoadReportTopic" );
		
		System.out.println("Creating Topic");
		Topic top = dp.create_topic ( "LoadReportTopic", "LoadReportTopic", null, null, 0 );
		
		System.out.println("Creating Datawriter");
		LoadReportTopicDataWriter dw = (LoadReportTopicDataWriter) pub.create_datawriter ( top, null, null, 0 );
		
		while ( true ) {
			LoadReportTopic information = new LoadReportTopic();
			information.leastSignificantBitsParticipantId = 0;
			information.mostSignificantBitsParticipantId = 0;
			information.participantType = 0;
			information.cpuUsage = 0;
			information.freeMemory = 0;
			information.numberOfConnectedVehicles = 0;
					
			retval = dw.write ( information, null );
			if ( retval != ReturnCode_t.RETCODE_OK ) System.out.println( "   ====  DDS_DataWriter_write() error... ");
			else System.out.println("Message posted"); 
			
			try {
				Thread.currentThread().sleep(5000);  
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}			