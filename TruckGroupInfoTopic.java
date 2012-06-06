// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.



public class TruckGroupInfoTopic {
  
  // instance variables
  public long leastSignificantBitsGatewayId;
  public long mostSignificantBitsGatewayId;
  public long leastSignificantBitsVehicleId;
  public long mostSignificantBitsVehicleId;
  public int[] informationGroups;
  
  // constructors
  public TruckGroupInfoTopic() {}
  public TruckGroupInfoTopic( long __f1, long __f2, long __f3, long __f4, int[] __f5 ) {
    leastSignificantBitsGatewayId = __f1;
    mostSignificantBitsGatewayId = __f2;
    leastSignificantBitsVehicleId = __f3;
    mostSignificantBitsVehicleId = __f4;
    informationGroups = __f5;
  }
  
  public void clear() {
    informationGroups = null;
  }
  
  public void copy( TruckGroupInfoTopic from ) {
    this.leastSignificantBitsGatewayId = from.leastSignificantBitsGatewayId;
    this.mostSignificantBitsGatewayId = from.mostSignificantBitsGatewayId;
    this.leastSignificantBitsVehicleId = from.leastSignificantBitsVehicleId;
    this.mostSignificantBitsVehicleId = from.mostSignificantBitsVehicleId;
    this.informationGroups = from.informationGroups;
  }
  
}; // TruckGroupInfoTopic
