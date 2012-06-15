// Author: Matheus Maciel
// Class: GatewayModel
// Last update: 15/06/2012
// Reasoning for last update: Comments

// Package
package Model;

// TruckInformationTopic Model 
// Must be updated to reflect topic changes
public class GatewayModel {
	public GatewayModel (long leastGatewayId, long mostGatewayId) {
		LEAST_BITS_GATEWAY_ID = leastGatewayId;
		MOST_BITS_GATEWAY_ID = mostGatewayId;
	}
	
        public GatewayModel (long leastGatewayId, long mostGatewayId, byte participantType, byte cpuUsage, int freeMemory, int numberOfConnectedVehicles) {
		LEAST_BITS_GATEWAY_ID = leastGatewayId;
		MOST_BITS_GATEWAY_ID = mostGatewayId;
                PARTICIPANT_TYPE = participantType;
                CPU_USAGE = cpuUsage;
                FREE_MEMORY = freeMemory;
                NUMBER_OF_CONNECTED_VEHICLES = numberOfConnectedVehicles;                
	}
        
	public long getLeastBitsGatewayId() { return LEAST_BITS_GATEWAY_ID; }
	public void setLeastBitsGatewayId(long value) { LEAST_BITS_GATEWAY_ID = value; } 
	
	public long getMostBitsGatewayId() { return MOST_BITS_GATEWAY_ID; }
	public void setMostBitsGatewayId(long value) { MOST_BITS_GATEWAY_ID = value; } 

	public int getGatewayId() { return GATEWAY_ID; }
	public void setGatewayId(int value) { GATEWAY_ID = value; }
		
	public int getConnectedVehicles() { return CONNECTED_VEHICLES; }
	public void setConnectedVehicles(int value) { CONNECTED_VEHICLES = value; }
        
        public byte getParticipantType() { return PARTICIPANT_TYPE; }
        public void setParticipantType(byte value) { PARTICIPANT_TYPE = value; }
        
        public byte getCpuUsage() { return CPU_USAGE; }
        public void setCpuUsage(byte value) { CPU_USAGE = value; }
        
        public int getNumberOfConnectedVehicles() { return NUMBER_OF_CONNECTED_VEHICLES; }
	public void setnumberOfConnectedVehicles(int value) { NUMBER_OF_CONNECTED_VEHICLES = value; }
        
        public int getFreeMemory() { return FREE_MEMORY; }
	public void setFreeMemory(int value) { FREE_MEMORY = value; }
        
	private long LEAST_BITS_GATEWAY_ID;
	private long MOST_BITS_GATEWAY_ID;
	private int CONNECTED_VEHICLES;
	private int GATEWAY_ID;
        private byte PARTICIPANT_TYPE;
        private byte CPU_USAGE;
        private int FREE_MEMORY;
        private int NUMBER_OF_CONNECTED_VEHICLES;
}