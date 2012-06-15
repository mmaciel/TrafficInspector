package DDSTopics;

// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.



public class GroupAdvertisementTopic {
  
  // instance variables
  public long leastSignificantBitsGatewayId;
  public long mostSignificantBitsGatewayId;
  public long leastSignificantBitsVehicleId;
  public long mostSignificantBitsVehicleId;
  public int groupType;
  public int[] groupOperationCollection;
  
  // constructors
  public GroupAdvertisementTopic() {}
  public GroupAdvertisementTopic( long __f1, long __f2, long __f3, long __f4, int __f5, int[] __f6 ) {
    leastSignificantBitsGatewayId = __f1;
    mostSignificantBitsGatewayId = __f2;
    leastSignificantBitsVehicleId = __f3;
    mostSignificantBitsVehicleId = __f4;
    groupType = __f5;
    groupOperationCollection = __f6;
  }
  
  public void clear() {
    groupOperationCollection = null;
  }
  
  public void copy( GroupAdvertisementTopic from ) {
    this.leastSignificantBitsGatewayId = from.leastSignificantBitsGatewayId;
    this.mostSignificantBitsGatewayId = from.mostSignificantBitsGatewayId;
    this.leastSignificantBitsVehicleId = from.leastSignificantBitsVehicleId;
    this.mostSignificantBitsVehicleId = from.mostSignificantBitsVehicleId;
    this.groupType = from.groupType;
    this.groupOperationCollection = from.groupOperationCollection;
  }
  
}; // GroupAdvertisementTopic
