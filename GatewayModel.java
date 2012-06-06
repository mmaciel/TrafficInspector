public class GatewayModel {
	public GatewayModel (long leastGatewayId, long mostGatewayId) {
		LEAST_BITS_GATEWAY_ID = leastGatewayId;
		MOST_BITS_GATEWAY_ID = mostGatewayId;
	}
	
	public long getLeastBitsGatewayId() { return LEAST_BITS_GATEWAY_ID; }
	public void setLeastBitsGatewayId(long value) { LEAST_BITS_GATEWAY_ID = value; } 
	
	public long getMostBitsGatewayId() { return MOST_BITS_GATEWAY_ID; }
	public void setMostBitsGatewayId(long value) { MOST_BITS_GATEWAY_ID = value; } 

	public int getGatewayId() { return GATEWAY_ID; }
	public void setGatewayId(int value) { GATEWAY_ID = value; }
		
	public int getConnectedVehicles() { return CONNECTED_VEHICLES; }
	public void setConnectedVehicles(int value) { CONNECTED_VEHICLES = value; }
	
	private long LEAST_BITS_GATEWAY_ID;
	private long MOST_BITS_GATEWAY_ID;
	private int CONNECTED_VEHICLES;
	private int GATEWAY_ID;
}