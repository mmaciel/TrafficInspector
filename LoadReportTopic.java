// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.



public class LoadReportTopic {
  
  // instance variables
  public long leastSignificantBitsParticipantId;
  public long mostSignificantBitsParticipantId;
  public byte participantType;
  public byte cpuUsage;
  public int freeMemory;
  public int numberOfConnectedVehicles;
  
  // constructors
  public LoadReportTopic() {}
  public LoadReportTopic( long __f1, long __f2, byte __f3, byte __f4, int __f5, int __f6 ) {
    leastSignificantBitsParticipantId = __f1;
    mostSignificantBitsParticipantId = __f2;
    participantType = __f3;
    cpuUsage = __f4;
    freeMemory = __f5;
    numberOfConnectedVehicles = __f6;
  }
  
  public void clear() {
  }
  
  public void copy( LoadReportTopic from ) {
    this.leastSignificantBitsParticipantId = from.leastSignificantBitsParticipantId;
    this.mostSignificantBitsParticipantId = from.mostSignificantBitsParticipantId;
    this.participantType = from.participantType;
    this.cpuUsage = from.cpuUsage;
    this.freeMemory = from.freeMemory;
    this.numberOfConnectedVehicles = from.numberOfConnectedVehicles;
  }
  
}; // LoadReportTopic
