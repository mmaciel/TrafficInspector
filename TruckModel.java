public class TruckModel {
	public TruckModel (long leastVehicleId, long mostVehicleId, long leastGatewayId, long mostGatewayId, int groups, String tracker, double latitude, double longitude, double speed, long time) {
		LEAST_BITS_VEHICLE_ID = leastVehicleId;
		MOST_BITS_VEHICLE_ID = mostVehicleId;
		LEAST_BITS_GATEWAY_ID = leastGatewayId;
		MOST_BITS_GATEWAY_ID = mostGatewayId;
		GROUPS = groups;
		TRACKER = tracker;
		LATITUDE = latitude;
		LONGITUDE = longitude;
		SPEED = speed;
		TIME = time;
	}
	
	public TruckModel () {}
	
	public long getLeastBitsVehicleId() { return LEAST_BITS_VEHICLE_ID; }
	public void setLeastBitsVehicleId(long value) { LEAST_BITS_VEHICLE_ID = value; } 
	
	public long getMostBitsVehicleId() { return MOST_BITS_VEHICLE_ID; }
	public void setMostBitsVehicleId(long value) { MOST_BITS_VEHICLE_ID = value; } 
	
	public long getLeastBitsGatewayId() { return LEAST_BITS_GATEWAY_ID; }
	public void setLeastBitsGatewayId(long value) { LEAST_BITS_GATEWAY_ID = value; } 
	
	public long getMostBitsGatewayId() { return MOST_BITS_GATEWAY_ID; }
	public void setMostBitsGatewayId(long value) { MOST_BITS_GATEWAY_ID = value; } 
	
	public int getGroups() { return GROUPS; }
	public void setGroups(int value) { GROUPS = value; }
	
	public String getTracker() { return TRACKER; }
	public void setTracker(String value) { TRACKER = value; }
	
	public double getLatitude() { return LATITUDE; }
	public void setLatitude(double value) { LATITUDE = value; }
	
	public double getLongitude() { return LONGITUDE; }
	public void setLongitude(double value) { LONGITUDE = value; }
	
	public double getSpeed() { return SPEED; }
	public void setSpeed(double value) { SPEED = value; }
	
	public long getTime() { return TIME; }
	public void setTime(long value) { TIME = value; }
	
	private long LEAST_BITS_VEHICLE_ID;
	private long MOST_BITS_VEHICLE_ID;
	private long LEAST_BITS_GATEWAY_ID;
	private long MOST_BITS_GATEWAY_ID;
	private int GROUPS;
	private String TRACKER;
	private double LATITUDE;
	private double LONGITUDE;
	private double SPEED;
	private long TIME;
}
	