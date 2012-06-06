// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.



public class UnsentMessageTopic {
  
  // instance variables
  public long leastSignificantBitsVehicleId;
  public long mostSignificantBitsVehicleId;
  public long leastSignificantBitsGatewayId;
  public long mostSignificantBitsGatewayId;
  public MessageStructureTopic[] unsentMessages;
  
  // constructors
  public UnsentMessageTopic() {}
  public UnsentMessageTopic( long __f1, long __f2, long __f3, long __f4, MessageStructureTopic[] __f5 ) {
    leastSignificantBitsVehicleId = __f1;
    mostSignificantBitsVehicleId = __f2;
    leastSignificantBitsGatewayId = __f3;
    mostSignificantBitsGatewayId = __f4;
    unsentMessages = __f5;
  }
  
  public void clear() {
    unsentMessages = null;
  }
  
  public void copy( UnsentMessageTopic from ) {
    this.leastSignificantBitsVehicleId = from.leastSignificantBitsVehicleId;
    this.mostSignificantBitsVehicleId = from.mostSignificantBitsVehicleId;
    this.leastSignificantBitsGatewayId = from.leastSignificantBitsGatewayId;
    this.mostSignificantBitsGatewayId = from.mostSignificantBitsGatewayId;
    this.unsentMessages = from.unsentMessages;
  }
  
}; // UnsentMessageTopic
