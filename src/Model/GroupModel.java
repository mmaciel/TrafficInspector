// Author: Matheus Maciel
// Class: GroupModel
// Last update: 15/06/2012
// Reasoning for last update: Comments

// Package 
package Model;

// TruckGroupInfoTopic Model 
// Must be updated to reflect changes in GroupAdvertisementTopic
public class GroupModel {

    public GroupModel(long leastBitsGatewayId, long mostBitsGatewayId, long leastBitsVehicleId, long mostBitsVehicleId, int[] informationGroups) {
        LEAST_BITS_GATEWAY_ID = leastBitsGatewayId;
        MOST_BITS_GATEWAY_ID = mostBitsGatewayId;
        LEAST_BITS_VEHICLE_ID = leastBitsVehicleId;
        MOST_BITS_VEHICLE_ID = mostBitsVehicleId;
        INFORMATION_GROUPS = informationGroups;
    }

    public long getLeastBitsGatewayId() {
        return LEAST_BITS_GATEWAY_ID;
    }

    public void setLeastBitsGatewayId(long value) {
        LEAST_BITS_GATEWAY_ID = value;
    }

    public long getMostBitsGatewayId() {
        return MOST_BITS_GATEWAY_ID;
    }

    public void setMostBitsGatewayId(long value) {
        MOST_BITS_GATEWAY_ID = value;
    }

    public long getLeastBitsVehicleId() {
        return LEAST_BITS_VEHICLE_ID;
    }

    public void setLeastBitsVehicleId(long value) {
        LEAST_BITS_VEHICLE_ID = value;
    }

    public long getMostBitsVehicleId() {
        return MOST_BITS_VEHICLE_ID;
    }

    public void setMostBitsVehicleId(long value) {
        MOST_BITS_VEHICLE_ID = value;
    }

    public int[] getInformationGroups() {
        return INFORMATION_GROUPS;
    }   

    public void setInformationGroups(int[] value) {
        INFORMATION_GROUPS = value;
    }
    
    public int getGroup (int index) {
        return INFORMATION_GROUPS[index];
    }
            
    private long LEAST_BITS_GATEWAY_ID;
    private long MOST_BITS_GATEWAY_ID;
    private long LEAST_BITS_VEHICLE_ID;
    private long MOST_BITS_VEHICLE_ID;
    private int[] INFORMATION_GROUPS;
}
