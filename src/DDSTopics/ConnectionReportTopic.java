package DDSTopics;

// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.



public class ConnectionReportTopic {
  
  // instance variables
  public long leastSignificantBitsVehicleId;
  public long mostSignificantBitsVehicleId;
  public long leastSignificantBitsGatewayId;
  public long mostSignificantBitsGatewayId;
  public boolean connected;
  
  // constructors
  public ConnectionReportTopic() {}
  public ConnectionReportTopic( long __f1, long __f2, long __f3, long __f4, boolean __f5 ) {
    leastSignificantBitsVehicleId = __f1;
    mostSignificantBitsVehicleId = __f2;
    leastSignificantBitsGatewayId = __f3;
    mostSignificantBitsGatewayId = __f4;
    connected = __f5;
  }
  
  public void clear() {
  }
  
  public void copy( ConnectionReportTopic from ) {
    this.leastSignificantBitsVehicleId = from.leastSignificantBitsVehicleId;
    this.mostSignificantBitsVehicleId = from.mostSignificantBitsVehicleId;
    this.leastSignificantBitsGatewayId = from.leastSignificantBitsGatewayId;
    this.mostSignificantBitsGatewayId = from.mostSignificantBitsGatewayId;
    this.connected = from.connected;
  }
  
}; // ConnectionReportTopic
