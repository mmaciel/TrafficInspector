// Author: Matheus Maciel
// Class: AppManager
// Last update: 15/06/2012
// Reasoning for last update: Comments

// Package 
package Controller;

// Imports
import Model.GatewayModel;
import Model.GroupModel;
import Model.TruckModel;
import View.TrafficInspectorMainWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JCheckBox;

// AppManager Class 
// Responsable for listening the interface events and connecting it's methods to their proper structures
// This class is responsible for most, if not all, interface updates related to new data found by the ContextDataReaderListener class
public class AppManager {

    // Table headers: It's necessary that they are equal to those defined in the interface
    private Object[] truckInformationHeaders = {"Truck Least Significative Bits", "Truck Most Significative Bits", "Gateway Least Significative Bits", "Gateway Most Significative Bits", "Group", "Tracker", "Latitude", "Longitude", "Speed", "Time"};
    private Object[] gatewayInformationHeaders = {"Gateway Least Significative Bits", "Gateway Most Significative Bits", "Participant Type", "CPU Usage", "Free Memory", "Connected Vehicles"};
    private Object[] groupInformationHeaders = {"Truck Least Significative Bits", "Truck Most Significative Bits", "Gateway Least Significative Bits", "Gateway Most Significative Bits", "Group"};
    
    // Data structures to contain model used by the application
    // <editor-fold defaultstate="collapsed" desc="Data Structures">
    private ArrayList<TruckModel> truckInformationList = new ArrayList<TruckModel>();
    private ArrayList<GatewayModel> truckGatewayInformationList = new ArrayList<GatewayModel>();
    private ArrayList<TruckModel> truckMessages = new ArrayList<TruckModel>();
    private ArrayList<Integer> truckGroups = new ArrayList<Integer>();
    private ArrayList<GroupModel> groupMessages = new ArrayList<GroupModel>();
    private ArrayList<Integer> groupInformationList = new ArrayList<Integer>();
    private ArrayList<GatewayModel> gatewayInformationList = new ArrayList<GatewayModel>();
    private ArrayList<GatewayModel> gatewayMessages = new ArrayList<GatewayModel>();
    private ArrayList<String> truckSavedFilters = new ArrayList<String>();
    private ArrayList<String> groupSavedFilters = new ArrayList<String>();
    private ArrayList<String> gatewaySavedFilters = new ArrayList<String>();
    private ArrayList<ArrayList<TruckModel>> truckCustomFilterMessages = new ArrayList<ArrayList<TruckModel>>();
    private ArrayList<ArrayList<GroupModel>> groupCustomFilterMessages = new ArrayList<ArrayList<GroupModel>>();
    private ArrayList<ArrayList<GatewayModel>> gatewayCustomFilterMessages = new ArrayList<ArrayList<GatewayModel>>();    
    private int truckCounter = 0;
    private int truckGatewayCounter = 0;
    private int gatewayCounter = 0;
    private int truckCustomFilterCounter = 0;
    private int groupCustomFilterCounter = 0;
    private int gatewayCustomFilterCounter = 0;
    private TrafficInspectorMainWindow MAIN_WINDOW;
    private ContextDataReaderListener DATA_READER_LISTENER;
    // </editor-fold>

    // Constructor for AppManager class
    public AppManager(TrafficInspectorMainWindow mainWindow, ContextDataReaderListener contextListener) {
        MAIN_WINDOW = mainWindow;
        DATA_READER_LISTENER = contextListener;
    }
    
    // Constructor for AppManager class
    public AppManager(TrafficInspectorMainWindow mainWindow) {
        MAIN_WINDOW = mainWindow;
    }
    
    // Method to update the datareader that will be used by the application manager
    public void updateNewDataReader(ContextDataReaderListener contextListener) {
        DATA_READER_LISTENER = contextListener;
    }
    
    // Method to find groups in GroupAdvertisementTopic context
    public void identifyGroupOnGroups(GroupModel newGroup) {
        if (!groupInformationList.isEmpty()) {
            for (int i = 0; i < newGroup.getInformationGroups().length; i++) {
                groupInformationList.add(newGroup.getInformationGroups()[i]);
                MAIN_WINDOW.updateNotificationAreas("Group " + newGroup.getGroup(i) + " has been identified in Group topic.");
                MAIN_WINDOW.updateGroupCombo(Integer.toString(newGroup.getGroup(i)));
            }
        } else {
            for (int j = 0; j < newGroup.getInformationGroups().length; j++) {
                if (!groupInformationList.contains(newGroup.getGroup(j))) {
                    groupInformationList.add(newGroup.getGroup(j));
                    MAIN_WINDOW.updateNotificationAreas("Group " + newGroup.getGroup(j) + " has been identified in Group topic.");
                    MAIN_WINDOW.updateGroupCombo(Integer.toString(newGroup.getGroup(j)));
                }
            }
        }
    }

    // Method to find gateways in LoadReportTopic context
    public void identifyGatewayOnGateway(GatewayModel newGateway) {
        boolean foundGateway = false;
        for (int i = 0; i < gatewayInformationList.size(); i++) {
            if ((newGateway.getLeastBitsGatewayId() == gatewayInformationList.get(i).getLeastBitsGatewayId()) && (newGateway.getMostBitsGatewayId() == gatewayInformationList.get(i).getMostBitsGatewayId())) {
                return;
            }
        }

        if (foundGateway == false) {
            newGateway.setGatewayId(gatewayCounter);
            gatewayInformationList.add(newGateway);
            MAIN_WINDOW.updateNotificationAreas("A new gateway has been detected in Gateway topic.");
            MAIN_WINDOW.updateGatewayCombo("#" + gatewayCounter);
            gatewayCounter++;
        }
    }

    // Method to find trucks in TruckInformationTopic context
    public void identifyTruck(TruckModel newTruck) {
        boolean foundTruck = false;
        for (int i = 0; i < truckInformationList.size(); i++) {
            if ((truckInformationList.get(i).getLeastBitsVehicleId() == newTruck.getLeastBitsVehicleId()) && (truckInformationList.get(i).getMostBitsVehicleId() == newTruck.getMostBitsVehicleId())) {
                return;
            }
        }

        if (foundTruck == false) {
            truckInformationList.add(newTruck);
            MAIN_WINDOW.updateNotificationAreas("New vehicle found!");
            MAIN_WINDOW.updateTruckCombo("Truck #" + truckCounter);
            truckCounter++;
        }
    }

    // Method to find gateways in TruckInformationTopic context
    public void identifyGateway(TruckModel newTruck) {
        boolean foundGateway = false;
        for (int i = 0; i < truckGatewayInformationList.size(); i++) {
            if ((newTruck.getLeastBitsGatewayId() == truckGatewayInformationList.get(i).getLeastBitsGatewayId()) && (newTruck.getMostBitsGatewayId() == truckGatewayInformationList.get(i).getMostBitsGatewayId())) {
                return;
            }
        }

        if (foundGateway == false) {
            GatewayModel newGateway = new GatewayModel(newTruck.getLeastBitsGatewayId(), newTruck.getMostBitsGatewayId());
            newGateway.setGatewayId(truckGatewayCounter);
            truckGatewayInformationList.add(newGateway);
            MAIN_WINDOW.updateNotificationAreas("A new gateway has been detected.");
            MAIN_WINDOW.updateTruckGatewayCombo("#" + truckGatewayCounter);
            truckGatewayCounter++;
        }
    }

    // Method to find groups in TruckInformationTopic context
    public void identifyGroup(TruckModel newTruck) {
        ArrayList<Integer> newGroupsFound = new ArrayList<Integer>();
        if (truckGroups.size() == 0) {
            for (int j1 = 0; j1 < newTruck.getGroups().length; j1++) {
                for (int k1 = 0; k1 < newTruck.getGroups()[j1].getInformationGroups().length; k1++) {
                    if (!newGroupsFound.contains(newTruck.getGroups()[j1].getGroup(k1))) {
                        newGroupsFound.add(newTruck.getGroups()[j1].getGroup(k1));
                    }
                }
            }
        } else {
            for (int i = 0; i < truckGroups.size(); i++) {
                for (int j = 0; j < newTruck.getGroups().length; j++) {
                    for (int k = 0; k < newTruck.getGroups()[j].getInformationGroups().length; k++) {
                        if (truckGroups.get(i) == newTruck.getGroups()[j].getGroup(k)) {
                            continue;
                        } else {
                            if (!newGroupsFound.contains(newTruck.getGroups()[j].getGroup(k))) {
                                newGroupsFound.add(newTruck.getGroups()[j].getGroup(k));
                            }
                        }
                    }
                }
            }
        }

        if (!newGroupsFound.isEmpty()) {
            for (int m = 0; m < newGroupsFound.size(); m++) {
                if (!truckGroups.contains(newGroupsFound.get(m))) {
                    truckGroups.add(newGroupsFound.get(m));
                    MAIN_WINDOW.updateNotificationAreas("Group " + newGroupsFound.get(m) + " has been identified.");
                    MAIN_WINDOW.updateTruckGroupCombo(Integer.toString(newGroupsFound.get(m)));
                }
            }
        }
    }

    // Method to add truck messages to the program
    public void addTruckMessage(TruckModel newTruck) {
        truckMessages.add(newTruck);
    }

    // Method to add groups messages to the program
    public void addGroupMessage(GroupModel newGroup) {
        groupMessages.add(newGroup);
    }

    // Method to add gateway messages to the program
    public void addGatewayMessage(GatewayModel newGateway) {
        gatewayMessages.add(newGateway);
    }
    
    // Method to add custom truck messages to the program
    public void addTruckCustomMessage(int index, TruckModel newTruck) {
        truckCustomFilterMessages.get(index).add(newTruck);
    }
    
    // Method to add custom group messages to the program
    public void addGroupCustomMessage(int index, GroupModel newGroup) {
        groupCustomFilterMessages.get(index).add(newGroup);
    }
    
    // Method to add custom gateway messages to the program
    public void addGatewayCustomMessage(int index, GatewayModel newGateway) {
        gatewayCustomFilterMessages.get(index).add(newGateway);
    }

    // Method to filter truck messages for the truck index filter option
    public Object[][] filterTruckMessagesByIndex(long truckLeastBitsId, long truckMostBitsId, ArrayList<TruckModel> TruckStoredInformation) {
        int messagesFound = 0;
        for (int i = 0; i < TruckStoredInformation.size(); i++) {
            if ((TruckStoredInformation.get(i).getLeastBitsVehicleId() == truckLeastBitsId) && (TruckStoredInformation.get(i).getMostBitsVehicleId() == truckMostBitsId)) {
                messagesFound++;
            }
        }

        Object[][] returnObject = new Object[messagesFound][10];
        int dataController = 0;
        String groupList = "";
        for (int j = 0; j < TruckStoredInformation.size(); j++) {
            if ((TruckStoredInformation.get(j).getLeastBitsVehicleId() == truckLeastBitsId) && (TruckStoredInformation.get(j).getMostBitsVehicleId() == truckMostBitsId)) {
                returnObject[dataController][0] = TruckStoredInformation.get(j).getLeastBitsVehicleId();
                returnObject[dataController][1] = TruckStoredInformation.get(j).getMostBitsVehicleId();
                returnObject[dataController][2] = TruckStoredInformation.get(j).getLeastBitsGatewayId();
                returnObject[dataController][3] = TruckStoredInformation.get(j).getMostBitsGatewayId();
                if (TruckStoredInformation.get(j).getGroups() != null) {
                    for (int j1 = 0; j1 < TruckStoredInformation.get(j).getGroups().length; j1++) {
                        for (int k1 = 0; k1 < TruckStoredInformation.get(j).getGroups()[j1].getInformationGroups().length; k1++) {
                            groupList = groupList + TruckStoredInformation.get(j).getGroups()[j1].getInformationGroups()[k1] + " ";
                        }
                    }
                }
                returnObject[dataController][4] = groupList;
                returnObject[dataController][5] = TruckStoredInformation.get(j).getTracker();
                returnObject[dataController][6] = TruckStoredInformation.get(j).getLatitude();
                returnObject[dataController][7] = TruckStoredInformation.get(j).getLongitude();
                returnObject[dataController][8] = TruckStoredInformation.get(j).getSpeed();
                Timestamp ts = new Timestamp(TruckStoredInformation.get(j).getTime());
                returnObject[dataController][9] = ts;
                groupList = "";
                dataController++;
            }
        }

        return returnObject;
    }

    // Method to filter truck messages for the group filter option
    public Object[][] filterTruckMessagesByGroup(int group, ArrayList<TruckModel> TruckStoredInformation) {
        int messagesFound = 0;

        for (int i = 0; i < TruckStoredInformation.size(); i++) {
            if (TruckStoredInformation.get(i).getGroups() == null) {
                continue;
            }
            for (int j = 0; j < TruckStoredInformation.get(i).getGroups().length; j++) {
                for (int k = 0; k < TruckStoredInformation.get(i).getGroups()[j].getInformationGroups().length; k++) {
                    if (group == TruckStoredInformation.get(i).getGroups()[j].getInformationGroups()[k]) {
                        messagesFound++;
                    }
                }
            }
        }

        Object[][] returnObject = new Object[messagesFound][10];
        int dataController = 0;
        String groupList = "";
        for (int i1 = 0; i1 < TruckStoredInformation.size(); i1++) {
            if (TruckStoredInformation.get(i1).getGroups() == null) {
                continue;
            }
            for (int j1 = 0; j1 < TruckStoredInformation.get(i1).getGroups().length; j1++) {
                for (int k1 = 0; k1 < TruckStoredInformation.get(i1).getGroups()[j1].getInformationGroups().length; k1++) {
                    if (group == TruckStoredInformation.get(i1).getGroups()[j1].getInformationGroups()[k1]) {

                        returnObject[dataController][0] = TruckStoredInformation.get(i1).getLeastBitsVehicleId();
                        returnObject[dataController][1] = TruckStoredInformation.get(i1).getMostBitsVehicleId();
                        returnObject[dataController][2] = TruckStoredInformation.get(i1).getLeastBitsGatewayId();
                        returnObject[dataController][3] = TruckStoredInformation.get(i1).getMostBitsGatewayId();

                        if (TruckStoredInformation.get(i1).getGroups() != null) {
                            for (int l1 = 0; l1 < TruckStoredInformation.get(i1).getGroups().length; l1++) {
                                for (int m1 = 0; m1 < TruckStoredInformation.get(i1).getGroups()[j1].getInformationGroups().length; m1++) {
                                    groupList = groupList + TruckStoredInformation.get(i1).getGroups()[j1].getInformationGroups()[m1] + " ";
                                }
                            }
                        }
                        returnObject[dataController][4] = groupList;
                        returnObject[dataController][5] = TruckStoredInformation.get(i1).getTracker();
                        returnObject[dataController][6] = TruckStoredInformation.get(i1).getLatitude();
                        returnObject[dataController][7] = TruckStoredInformation.get(i1).getLongitude();
                        returnObject[dataController][8] = TruckStoredInformation.get(i1).getSpeed();
                        Timestamp ts = new Timestamp(TruckStoredInformation.get(i1).getTime());
                        returnObject[dataController][9] = ts;
                        groupList = "";
                        dataController++;
                    }
                }
            }
        }

        return returnObject;
    }

    // Method to filter truck messages for the gateway index filter option
    public Object[][] filterTruckMessagesByGateway(long gatewayLeastBitsId, long gatewayMostBitsId, ArrayList<TruckModel> TruckStoredInformation) {
        int messagesFound = 0;
        for (int i = 0; i < TruckStoredInformation.size(); i++) {
            if ((TruckStoredInformation.get(i).getLeastBitsGatewayId() == gatewayLeastBitsId) && (TruckStoredInformation.get(i).getMostBitsGatewayId() == gatewayMostBitsId)) {
                messagesFound++;
            }
        }

        Object[][] returnObject = new Object[messagesFound][10];
        int dataController = 0;
        String groupList = "";
        for (int j = 0; j < TruckStoredInformation.size(); j++) {
            if ((TruckStoredInformation.get(j).getLeastBitsGatewayId() == gatewayLeastBitsId) && (TruckStoredInformation.get(j).getMostBitsGatewayId() == gatewayMostBitsId)) {
                returnObject[dataController][0] = TruckStoredInformation.get(j).getLeastBitsVehicleId();
                returnObject[dataController][1] = TruckStoredInformation.get(j).getMostBitsVehicleId();
                returnObject[dataController][2] = TruckStoredInformation.get(j).getLeastBitsGatewayId();
                returnObject[dataController][3] = TruckStoredInformation.get(j).getMostBitsGatewayId();

                if (TruckStoredInformation.get(j).getGroups() != null) {
                    for (int j1 = 0; j1 < TruckStoredInformation.get(j).getGroups().length; j1++) {
                        for (int k1 = 0; k1 < TruckStoredInformation.get(j).getGroups()[j1].getInformationGroups().length; k1++) {
                            groupList = groupList + TruckStoredInformation.get(j).getGroups()[j1].getInformationGroups()[k1] + " ";
                        }
                    }
                }

                returnObject[dataController][4] = groupList;
                returnObject[dataController][5] = TruckStoredInformation.get(j).getTracker();
                returnObject[dataController][6] = TruckStoredInformation.get(j).getLatitude();
                returnObject[dataController][7] = TruckStoredInformation.get(j).getLongitude();
                returnObject[dataController][8] = TruckStoredInformation.get(j).getSpeed();
                Timestamp ts = new Timestamp(TruckStoredInformation.get(j).getTime());
                returnObject[dataController][9] = ts;
                groupList = "";
                dataController++;
            }
        }

        return returnObject;
    }

    // Method to filter group messages for the group filter option
    public Object[][] filterGroupMessagesByGroup(int group, ArrayList<GroupModel> groupMessages) {
        int messagesFound = 0;

        for (int i = 0; i < groupMessages.size(); i++) {
            for (int j = 0; j < groupMessages.get(i).getInformationGroups().length; j++) {
                if (groupMessages.get(i).getGroup(j) == group) {
                    messagesFound++;
                }
            }
        }

        Object[][] returnObject = new Object[messagesFound][5];
        int dataController = 0;
        for (int k = 0; k < groupMessages.size(); k++) {
            for (int l = 0; l < groupMessages.get(k).getInformationGroups().length; l++) {
                if (groupMessages.get(k).getGroup(l) == group) {
                    returnObject[dataController][0] = groupMessages.get(k).getLeastBitsVehicleId();
                    returnObject[dataController][1] = groupMessages.get(k).getMostBitsVehicleId();
                    returnObject[dataController][2] = groupMessages.get(k).getLeastBitsGatewayId();
                    returnObject[dataController][3] = groupMessages.get(k).getMostBitsGatewayId();
                    returnObject[dataController][4] = groupMessages.get(k).getGroup(l);
                    dataController++;
                }
            }
        }

        return returnObject;
    }

    // Method to filter gateway messages for the gateway index filter option
    public Object[][] filterGatewayMessagesByGateway(long gatewayLeastBitsId, long gatewayMostBitsId, ArrayList<GatewayModel> gatewayStoredInformation) {
        int messagesFound = 0;
        for (int i = 0; i < gatewayStoredInformation.size(); i++) {
            if ((gatewayStoredInformation.get(i).getLeastBitsGatewayId() == gatewayLeastBitsId) && (gatewayStoredInformation.get(i).getMostBitsGatewayId() == gatewayMostBitsId)) {
                messagesFound++;
            }
        }

        Object[][] returnObject = new Object[messagesFound][6];
        int dataController = 0;
        for (int j = 0; j < gatewayStoredInformation.size(); j++) {
            if ((gatewayStoredInformation.get(j).getLeastBitsGatewayId() == gatewayLeastBitsId) && (gatewayStoredInformation.get(j).getMostBitsGatewayId() == gatewayMostBitsId)) {
                returnObject[dataController][0] = gatewayStoredInformation.get(j).getLeastBitsGatewayId();
                returnObject[dataController][1] = gatewayStoredInformation.get(j).getMostBitsGatewayId();
                returnObject[dataController][2] = gatewayStoredInformation.get(j).getParticipantType();
                returnObject[dataController][3] = gatewayStoredInformation.get(j).getCpuUsage();
                returnObject[dataController][4] = gatewayStoredInformation.get(j).getFreeMemory();
                returnObject[dataController][5] = gatewayStoredInformation.get(j).getNumberOfConnectedVehicles();
                dataController++;
            }
        }

        return returnObject;
    }

    // Method to grab a truck from the information list using an index
    // This method is used to determine what index in the interface selects which truck in the application logic
    public TruckModel getTruckByIndex(int index) {
        return truckInformationList.get(index);
    }

    // Method to grab a truck index using their logic values
    // This method is used to determine what truck in the logic selects which truck in the interface
    public int getTruckId(TruckModel newTruck) {
        boolean foundTruck = false;
        int truckIndex = 0;
        for (int i = 0; i < truckInformationList.size(); i++) {
            if ((truckInformationList.get(i).getLeastBitsVehicleId() == newTruck.getLeastBitsVehicleId()) && (truckInformationList.get(i).getMostBitsVehicleId() == newTruck.getMostBitsVehicleId())) {
                foundTruck = true;
                truckIndex = i;
                break;
            }
        }

        if (foundTruck == false) {
            return -1;
        } else {
            return truckIndex;
        }
    }

    // Method to turn truck messages into a matrix
    // Used to prepare data for the interface when dealing with global messages or custom filters
    public Object[][] truckMessagesToMatrix(ArrayList<TruckModel> TruckStoredInformation) {
        Object[][] returnObject = new Object[TruckStoredInformation.size()][10];
        int dataController = 0;
        String groupList = "";
        for (int j = 0; j < TruckStoredInformation.size(); j++) {

            returnObject[dataController][0] = TruckStoredInformation.get(j).getLeastBitsVehicleId();
            returnObject[dataController][1] = TruckStoredInformation.get(j).getMostBitsVehicleId();
            returnObject[dataController][2] = TruckStoredInformation.get(j).getLeastBitsGatewayId();
            returnObject[dataController][3] = TruckStoredInformation.get(j).getMostBitsGatewayId();

            if (TruckStoredInformation.get(j).getGroups() != null) {
                for (int j1 = 0; j1 < TruckStoredInformation.get(j).getGroups().length; j1++) {
                    for (int k1 = 0; k1 < TruckStoredInformation.get(j).getGroups()[j1].getInformationGroups().length; k1++) {
                        groupList = groupList + TruckStoredInformation.get(j).getGroups()[j1].getInformationGroups()[k1] + " ";
                    }
                }
            }


            returnObject[dataController][4] = groupList;
            returnObject[dataController][5] = TruckStoredInformation.get(j).getTracker();
            returnObject[dataController][6] = TruckStoredInformation.get(j).getLatitude();
            returnObject[dataController][7] = TruckStoredInformation.get(j).getLongitude();
            returnObject[dataController][8] = TruckStoredInformation.get(j).getSpeed();
            returnObject[dataController][9] = TruckStoredInformation.get(j).getTime();
            groupList = "";
            dataController++;

        }

        return returnObject;
    }

    // Method to turn group messages into a matrix
    // Used to prepare data for the interface when dealing with global messages or custom filters
    public Object[][] gatewayMessagesToMatrix(ArrayList<GatewayModel> gatewayStoredInformation) {
        Object[][] returnObject = new Object[gatewayStoredInformation.size()][6];
        int dataController = 0;
        for (int j = 0; j < gatewayStoredInformation.size(); j++) {
            returnObject[dataController][0] = gatewayStoredInformation.get(j).getLeastBitsGatewayId();
            returnObject[dataController][1] = gatewayStoredInformation.get(j).getMostBitsGatewayId();
            returnObject[dataController][2] = gatewayStoredInformation.get(j).getParticipantType();
            returnObject[dataController][3] = gatewayStoredInformation.get(j).getCpuUsage();
            returnObject[dataController][4] = gatewayStoredInformation.get(j).getFreeMemory();
            returnObject[dataController][5] = gatewayStoredInformation.get(j).getNumberOfConnectedVehicles();
            dataController++;
        }
        return returnObject;
    }

    // Method to turn gateway messages into a matrix
    // Used to prepare data for the interface when dealing with global messages or custom filters
    public Object[][] groupMessagesToMatrix(ArrayList<GroupModel> groupStoredInformation) {
        Object[][] returnObject = new Object[groupStoredInformation.size()][5];
        int dataController = 0;
        for (int k = 0; k < groupStoredInformation.size(); k++) {

            returnObject[dataController][0] = groupStoredInformation.get(k).getLeastBitsVehicleId();
            returnObject[dataController][1] = groupStoredInformation.get(k).getMostBitsVehicleId();
            returnObject[dataController][2] = groupStoredInformation.get(k).getLeastBitsGatewayId();
            returnObject[dataController][3] = groupStoredInformation.get(k).getMostBitsGatewayId();
            for (int l = 0; l < groupStoredInformation.get(k).getInformationGroups().length; l++) {
                returnObject[dataController][4] = groupStoredInformation.get(k).getGroup(l);
            }
            dataController++;
        }

        return returnObject;
    }

    // Add a new line to truck table
    // This method is not used
    public void addNewTruckInformation(TruckModel newTruck) {
        Object[] parameters = {newTruck.getLeastBitsVehicleId(), newTruck.getMostBitsVehicleId(), newTruck.getLeastBitsGatewayId(), newTruck.getMostBitsGatewayId(), newTruck.getGroups(), newTruck.getTracker(), newTruck.getLatitude(), newTruck.getLongitude(), newTruck.getSpeed(), newTruck.getTime()};
        MAIN_WINDOW.addRowTruckInformation(parameters);
    }

    // Method used to update the interface according to the selected filter related to trucks  
    public void updateTruckInformationTable() {
        if (MAIN_WINDOW.truckTrackingFilterSelected()) {
            TruckModel newTruck = getTruckByIndex(MAIN_WINDOW.truckGetSelectedTruck());
            MAIN_WINDOW.truckInformationTableUpdated(filterTruckMessagesByIndex(newTruck.getLeastBitsVehicleId(), newTruck.getMostBitsVehicleId(), truckMessages), truckInformationHeaders);
        }
        if (MAIN_WINDOW.truckGroupFilterSelected()) {
            int newGroup = MAIN_WINDOW.truckGetSelectedGroup();
            MAIN_WINDOW.truckInformationTableUpdated(filterTruckMessagesByGroup(newGroup, truckMessages), truckInformationHeaders);
        }
        if (MAIN_WINDOW.truckGatewayFilterSelected()) {
            GatewayModel newGateway = getGatewayByIndex(MAIN_WINDOW.truckGetSelectedGateway());
            MAIN_WINDOW.truckInformationTableUpdated(filterTruckMessagesByGateway(newGateway.getLeastBitsGatewayId(), newGateway.getMostBitsGatewayId(), truckMessages), truckInformationHeaders);
        }
        if (MAIN_WINDOW.truckCustomFilterSelected()) {
            MAIN_WINDOW.truckInformationTableUpdated(truckMessagesToMatrix(truckCustomFilterMessages.get(MAIN_WINDOW.truckGetCustomSelected())), truckInformationHeaders);
        }
    }

    // Method used to update the interface according to the selected filter related to groups
    public void updateGroupInformationTable() {
        if (MAIN_WINDOW.groupFilterSelected()) {
            int newGroup = MAIN_WINDOW.groupGetSelectedGroup();
            MAIN_WINDOW.groupInformationTableUpdated(filterGroupMessagesByGroup(newGroup, groupMessages), groupInformationHeaders);
        }
        
        if (MAIN_WINDOW.groupCustomFilterSelected()) {
            MAIN_WINDOW.groupInformationTableUpdated(groupMessagesToMatrix(groupCustomFilterMessages.get(MAIN_WINDOW.groupGetCustomSelected())), groupInformationHeaders);
        }
    }

    // Method used to update the interface according to the selected filter related to gateways
    public void updateGatewayInformationTable() {
        if (MAIN_WINDOW.gatewayFilterSelected()) {
            GatewayModel newGateway = getGatewayByIndex(MAIN_WINDOW.gatewayGetSelectedGateway());
            MAIN_WINDOW.gatewayInformationTableUpdated(filterGatewayMessagesByGateway(newGateway.getLeastBitsGatewayId(), newGateway.getMostBitsGatewayId(), gatewayMessages), gatewayInformationHeaders);
        }
        if (MAIN_WINDOW.gatewayCustomFilterSelected()) {
            MAIN_WINDOW.gatewayInformationTableUpdated(gatewayMessagesToMatrix(gatewayCustomFilterMessages.get(MAIN_WINDOW.gatewayGetCustomSelected())), gatewayInformationHeaders);
        }
    }

    // Method to get a truck in the application logic using their logic information
    // Used for obtaining full truck model whenever in possession of an vehicle ID
    public TruckModel getTruckById(long truckLeastBitsId, long truckMostBitsId) {
        for (int i = 0; i < truckInformationList.size(); i++) {
            if ((truckInformationList.get(i).getLeastBitsVehicleId() == truckLeastBitsId) && (truckInformationList.get(i).getMostBitsVehicleId() == truckMostBitsId)) {
                return truckInformationList.get(i);
            }
        }
        return null;
    }

    // Method to get a gateway index in the application logic using a truck logic information
    // Used for obtaining a gateway index in the TruckInformationTopic context
    public int getGatewayIndexByTruck(TruckModel newTruck) {
        boolean foundGateway = false;
        int gatewayIndex = 0;
        for (int i = 0; i < truckGatewayInformationList.size(); i++) {
            if ((newTruck.getLeastBitsGatewayId() == truckGatewayInformationList.get(i).getLeastBitsGatewayId()) && (newTruck.getMostBitsGatewayId() == truckGatewayInformationList.get(i).getMostBitsGatewayId())) {
                foundGateway = true;
                gatewayIndex = i;
                break;
            }

            if (foundGateway == false) {
                return -1;
            }
        }

        return gatewayIndex;
    }

    // Method to get a gateway information in the application logic using an index
    // Used for obtaining a gateway data in the TruckInformationTopic context
    public GatewayModel getGatewayByIndex(int index) {
        return truckGatewayInformationList.get(index);
    }

    // Method to organize the message displayed in the Custom Filtering tab
    public void organizeCustomFilters () {
        StringBuilder customFilters = new StringBuilder();
        customFilters.append ("Truck Saved Filters: \n");
        for (int i = 0; i < truckSavedFilters.size(); i++) {
            customFilters.append ("#" + i + ": " + truckSavedFilters.get(i) + "\n");
        }        
        customFilters.append ("\nGroup Saved Filters: \n");
        for (int j = 0; j < groupSavedFilters.size(); j++) {
            customFilters.append ("#" + j + ": " + groupSavedFilters.get(j) + "\n");
        }
        
        customFilters.append ("\nGateway Saved Filters: \n");
        for (int k = 0; k < gatewaySavedFilters.size(); k++) {
            customFilters.append ("#" + k + ": " + gatewaySavedFilters.get(k) + "\n");
        }
        
        MAIN_WINDOW.updateCustomFilterArea(customFilters.toString());
    }
    
    // Application Listeners
    // Whenever the interface needs a new listener, it should be added here
    // <editor-fold defaultstate="collapsed" desc="Application Listeners">
    public void truckTrackingComboListener() {
        MAIN_WINDOW.getTruckTrackingCombo().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                updateTruckInformationTable();
            }
        });
    }

    public void truckGatewayComboListener() {
        MAIN_WINDOW.getTruckGatewayCombo().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                updateTruckInformationTable();
            }
        });
    }

    public void truckGroupComboListener() {
        MAIN_WINDOW.getTruckGroupCombo().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                updateTruckInformationTable();
            }
        });
    }

    public void groupComboListener() {
        MAIN_WINDOW.getGroupCombo().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                updateGroupInformationTable();
            }
        });
    }

    public void gatewayComboListener() {
        MAIN_WINDOW.getGatewayCombo().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                updateGatewayInformationTable();
            }
        });
    }

    public void truckCheckActionListener() {
        MAIN_WINDOW.getTruckConnectCheck().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JCheckBox cb = (JCheckBox) e.getSource();
                if (cb.isSelected() == true) {
                    System.out.println("Connected to truck topic!");
                }
            }
        });
    }

    public void truckToggleGlobalButtonActionListener() {
        MAIN_WINDOW.getTruckToggleGlobalButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                MAIN_WINDOW.clearTruckFilterGroup();
                MAIN_WINDOW.truckInformationTableUpdated(truckMessagesToMatrix(truckMessages), truckInformationHeaders);
            }
        });
    }

    public void groupToggleGlobalButtonActionListener() {
        MAIN_WINDOW.getGroupToggleGlobalButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                MAIN_WINDOW.clearGroupFilterGroup();
                MAIN_WINDOW.groupInformationTableUpdated(groupMessagesToMatrix(groupMessages), groupInformationHeaders);
            }
        });
    }

    public void gatewayToggleGlobalButtonActionListener() {
        MAIN_WINDOW.getGatewayToggleGlobalButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                MAIN_WINDOW.clearGatewayFilterGroup();
                MAIN_WINDOW.gatewayInformationTableUpdated(gatewayMessagesToMatrix(gatewayMessages), gatewayInformationHeaders);
            }
        });
    }

    public void saveFilterActionListener() {
        MAIN_WINDOW.getSaveFilterButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Truck Tracking Information
                if (MAIN_WINDOW.getCustomFilterSelection() == 1) {
                    truckSavedFilters.add(MAIN_WINDOW.getExpressionField().getText());                    
                    ArrayList<TruckModel> newTruckModelList = new ArrayList<TruckModel>();
                    truckCustomFilterMessages.add(truckCustomFilterCounter, newTruckModelList);
                    MAIN_WINDOW.updateTruckCustomCombo("#" + truckCustomFilterCounter);
                    DATA_READER_LISTENER.createCustomTruckFilter(truckSavedFilters.get(truckCustomFilterCounter));
                    truckCustomFilterCounter++;           
                }

                // Group Information
                if (MAIN_WINDOW.getCustomFilterSelection() == 2) {
                    groupSavedFilters.add(MAIN_WINDOW.getExpressionField().getText());
                    ArrayList<GroupModel> newGroupModelList = new ArrayList<GroupModel>();
                    groupCustomFilterMessages.add(groupCustomFilterCounter, newGroupModelList);
                    MAIN_WINDOW.updadeGroupCustomCombo("#" + groupCustomFilterCounter);
                    DATA_READER_LISTENER.createCustomGroupFilter(groupSavedFilters.get(groupCustomFilterCounter));
                    groupCustomFilterCounter++;
                }

                // Gateway Information
                if (MAIN_WINDOW.getCustomFilterSelection() == 3) {
                    gatewaySavedFilters.add(MAIN_WINDOW.getExpressionField().getText());
                    ArrayList<GatewayModel> newGatewayModelList = new ArrayList<GatewayModel>();
                    gatewayCustomFilterMessages.add(groupCustomFilterCounter, newGatewayModelList);
                    MAIN_WINDOW.updateGatewayCustomCombo("#" + gatewayCustomFilterCounter);
                    DATA_READER_LISTENER.createCustomGatewayFilter(gatewaySavedFilters.get(gatewayCustomFilterCounter));
                    gatewayCustomFilterCounter++;
                }
                
                organizeCustomFilters();
            }
        });
    }

    public void truckCustomComboListener() {
        MAIN_WINDOW.getTruckCustomCombo().addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                updateTruckInformationTable();
            }
        });
    }
    
    public void groupCustomComboListener() {
        MAIN_WINDOW.getGroupCustomCombo().addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                updateGroupInformationTable();
            }
        });
    }
    
    public void gatewayCustomComboListener() {
        MAIN_WINDOW.getGatewayCustomCombo().addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                updateGatewayInformationTable();
            }
        });
    }
    // </editor-fold>

    // Method to initialize all interface listeners
    public void initializeListener() {
        truckTrackingComboListener();
        truckGatewayComboListener();
        truckGroupComboListener();
        truckCustomComboListener();
        groupComboListener();
        groupCustomComboListener();
        gatewayComboListener();
        gatewayCustomComboListener();
        truckCheckActionListener();        
        truckToggleGlobalButtonActionListener();
        groupToggleGlobalButtonActionListener();
        gatewayToggleGlobalButtonActionListener();
        saveFilterActionListener();
    }
    
    
}
