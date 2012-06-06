// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.



public class PingTopic {
  
  // instance variables
  public byte pingId;
  public long leastSignificantBitsGatewayId;
  public long mostSignificantBitsGatewayId;
  public long leastSignificantBitsVehicleId;
  public long mostSignificantBitsVehicleId;
  public long timestamp;
  public boolean pingCore;
  public boolean ping;
  
  // constructors
  public PingTopic() {}
  public PingTopic( byte __f1, long __f2, long __f3, long __f4, long __f5, long __f6, boolean __f7, boolean __f8 ) {
    pingId = __f1;
    leastSignificantBitsGatewayId = __f2;
    mostSignificantBitsGatewayId = __f3;
    leastSignificantBitsVehicleId = __f4;
    mostSignificantBitsVehicleId = __f5;
    timestamp = __f6;
    pingCore = __f7;
    ping = __f8;
  }
  
  public void clear() {
  }
  
  public void copy( PingTopic from ) {
    this.pingId = from.pingId;
    this.leastSignificantBitsGatewayId = from.leastSignificantBitsGatewayId;
    this.mostSignificantBitsGatewayId = from.mostSignificantBitsGatewayId;
    this.leastSignificantBitsVehicleId = from.leastSignificantBitsVehicleId;
    this.mostSignificantBitsVehicleId = from.mostSignificantBitsVehicleId;
    this.timestamp = from.timestamp;
    this.pingCore = from.pingCore;
    this.ping = from.ping;
  }
  
}; // PingTopic
