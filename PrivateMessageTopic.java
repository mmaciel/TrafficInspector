// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.



public class PrivateMessageTopic {
  
  // instance variables
  public long leastSignificantBitsVehicleId;
  public long mostSignificantBitsVehicleId;
  public long leastSignificantBitsGatewayId;
  public long mostSignificantBitsGatewayId;
  public int groupId;
  public byte[] message;
  
  // constructors
  public PrivateMessageTopic() {}
  public PrivateMessageTopic( long __f1, long __f2, long __f3, long __f4, int __f5, byte[] __f6 ) {
    leastSignificantBitsVehicleId = __f1;
    mostSignificantBitsVehicleId = __f2;
    leastSignificantBitsGatewayId = __f3;
    mostSignificantBitsGatewayId = __f4;
    groupId = __f5;
    message = __f6;
  }
  
  public void clear() {
    message = null;
  }
  
  public void copy( PrivateMessageTopic from ) {
    this.leastSignificantBitsVehicleId = from.leastSignificantBitsVehicleId;
    this.mostSignificantBitsVehicleId = from.mostSignificantBitsVehicleId;
    this.leastSignificantBitsGatewayId = from.leastSignificantBitsGatewayId;
    this.mostSignificantBitsGatewayId = from.mostSignificantBitsGatewayId;
    this.groupId = from.groupId;
    this.message = from.message;
  }
  
}; // PrivateMessageTopic
