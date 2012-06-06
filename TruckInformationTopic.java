// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.



public class TruckInformationTopic {
  
  // instance variables
  public long leastSignificantBitsGatewayId;
  public long mostSignificantBitsGatewayId;
  public long leastSignificantBitsVehicleId;
  public long mostSignificantBitsVehicleId;
  public String tracker;
  public TruckTrackingInformationTopic[] information;
  public int[] informationGroups;
  
  // constructors
  public TruckInformationTopic() {}
  public TruckInformationTopic( long __f1, long __f2, long __f3, long __f4, String __f5, TruckTrackingInformationTopic[] __f6, int[] __f7 ) {
    leastSignificantBitsGatewayId = __f1;
    mostSignificantBitsGatewayId = __f2;
    leastSignificantBitsVehicleId = __f3;
    mostSignificantBitsVehicleId = __f4;
    tracker = __f5;
    information = __f6;
    informationGroups = __f7;
  }
  
  public void clear() {
    tracker = null;
    information = null;
    informationGroups = null;
  }
  
  public void copy( TruckInformationTopic from ) {
    this.leastSignificantBitsGatewayId = from.leastSignificantBitsGatewayId;
    this.mostSignificantBitsGatewayId = from.mostSignificantBitsGatewayId;
    this.leastSignificantBitsVehicleId = from.leastSignificantBitsVehicleId;
    this.mostSignificantBitsVehicleId = from.mostSignificantBitsVehicleId;
    this.tracker = from.tracker;
    this.information = from.information;
    this.informationGroups = from.informationGroups;
  }
  
}; // TruckInformationTopic
