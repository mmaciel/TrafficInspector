// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.



public class TruckTrackingInformationTopic {
  
  // instance variables
  public double longitude;
  public double latitude;
  public float speed;
  public long time;
  
  // constructors
  public TruckTrackingInformationTopic() {}
  public TruckTrackingInformationTopic( double __f1, double __f2, float __f3, long __f4 ) {
    longitude = __f1;
    latitude = __f2;
    speed = __f3;
    time = __f4;
  }
  
  public void clear() {
  }
  
  public void copy( TruckTrackingInformationTopic from ) {
    this.longitude = from.longitude;
    this.latitude = from.latitude;
    this.speed = from.speed;
    this.time = from.time;
  }
  
}; // TruckTrackingInformationTopic
