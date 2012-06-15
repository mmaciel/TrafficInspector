// Author: Matheus Maciel
// Class: DataBaseModel
// Last update: 15/06/2012
// Reasoning for last update: Comments
// Package 
package Model;

// Imports
import java.sql.*;

public class DatabaseModel {

    private static Connection dbConnection;
    private static Connection mySQLConnection;
    private static String dbConnectionString = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)}; DBQ=TrafficInspectorDB.mdb";

    public DatabaseModel() {
    }

    // Method to connect to a database
    // Whenever the used database changes, this method must use it's connector
    // As it is displayed here, it uses MS Access
    public static boolean connectToDatabase() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            dbConnection = DriverManager.getConnection(dbConnectionString, "", "");
            return true;
        } catch (SQLException msg) {
            return false;
        } catch (ClassNotFoundException msg) {
            return false;
        }
    }

    // Method to connect to a database
    // Whenever the used database changes, this method must use it's connector
    // As it is displayed here, it uses MySQL
    public static boolean connectToMySQLDatabase() {
        try {
            System.out.println("Verifing Class...");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver OK");
            mySQLConnection = DriverManager.getConnection("jdbc:mysql://localhost/trafficinspector", "root", "mmaciel");
            System.out.println("Connection OK");
            return true;
        } catch (SQLException msg) {
            return false;
        } catch (ClassNotFoundException msg) {
            return false;
        }
    }

    // Method to record a truck message to the database
    // Whenever the used table or truck model changes, this method must be updated
    // As it is displayed here, it uses MS Access
    public static void recordTruckInformation(TruckModel recordTruck) throws SQLException {
        try {
            if (connectToDatabase()) {
                System.out.println("Connected to database and attempting to write data");
                PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO TRUCK_INFORMATION (LEAST_BITS_VEHICLE_ID, MOST_BITS_VEHICLE_ID, LEAST_BITS_GATEWAY_ID, MOST_BITS_GATEWAY_ID, GROUPS, TRACKER, LATITUDE, LONGITUDE, SPEED, MESSAGE_TIME) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                statement.setString(1, Long.toString(recordTruck.getLeastBitsVehicleId()));
                statement.setString(2, Long.toString(recordTruck.getMostBitsVehicleId()));
                statement.setString(3, Long.toString(recordTruck.getLeastBitsGatewayId()));
                statement.setString(4, Long.toString(recordTruck.getMostBitsGatewayId()));
//				statement.setInt    (5, recordTruck.getGroups());
                statement.setString(6, recordTruck.getTracker());
                statement.setDouble(7, recordTruck.getLatitude());
                statement.setDouble(8, recordTruck.getLongitude());
                statement.setDouble(9, recordTruck.getSpeed());
                statement.setString(10, Long.toString(recordTruck.getTime()));

                statement.executeUpdate();
                dbConnection.commit();
                dbConnection.close();
            }
        } catch (SQLException msg) {
            System.out.println(msg.toString());
        }
    }

    // Method to record a truck message to the database
    // Whenever the used table or truck model changes, this method must be updated
    // As it is displayed here, it uses MySQL
    public static void recordTruckInformationToMySQL(TruckModel recordTruck) throws SQLException {
        try {
            if (connectToMySQLDatabase()) {
                if (recordTruck.getGroups() == null) {
                    PreparedStatement statement = mySQLConnection.prepareStatement("INSERT INTO truck_information (LEAST_BITS_VEHICLE_ID, MOST_BITS_VEHICLE_ID, LEAST_BITS_GATEWAY_ID, MOST_BITS_GATEWAY_ID, GROUPS, TRACKER, LATITUDE, LONGITUDE, SPEED, MESSAGE_TIME) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                    statement.setLong(1, recordTruck.getLeastBitsVehicleId());
                    statement.setLong(2, recordTruck.getMostBitsVehicleId());
                    statement.setLong(3, recordTruck.getLeastBitsGatewayId());
                    statement.setLong(4, recordTruck.getMostBitsGatewayId());
                    statement.setInt(5, 0);
                    statement.setString(6, recordTruck.getTracker());
                    statement.setDouble(7, recordTruck.getLatitude());
                    statement.setDouble(8, recordTruck.getLongitude());
                    statement.setDouble(9, recordTruck.getSpeed());
                    Timestamp ts = new Timestamp(recordTruck.getTime());
                    statement.setTimestamp(10, ts);

                    statement.executeUpdate();
                } else {
                    for (int i = 0; i < recordTruck.getGroups().length; i++) {
                        for (int j = 0; j < recordTruck.getGroups()[i].getInformationGroups().length; j++) {
                            PreparedStatement statement = mySQLConnection.prepareStatement("INSERT INTO truck_information (LEAST_BITS_VEHICLE_ID, MOST_BITS_VEHICLE_ID, LEAST_BITS_GATEWAY_ID, MOST_BITS_GATEWAY_ID, GROUPS, TRACKER, LATITUDE, LONGITUDE, SPEED, MESSAGE_TIME) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                            statement.setLong(1, recordTruck.getLeastBitsVehicleId());
                            statement.setLong(2, recordTruck.getMostBitsVehicleId());
                            statement.setLong(3, recordTruck.getLeastBitsGatewayId());
                            statement.setLong(4, recordTruck.getMostBitsGatewayId());
                            statement.setInt(5, recordTruck.getGroups()[i].getGroup(j));
                            statement.setString(6, recordTruck.getTracker());
                            statement.setDouble(7, recordTruck.getLatitude());
                            statement.setDouble(8, recordTruck.getLongitude());
                            statement.setDouble(9, recordTruck.getSpeed());
                            Timestamp ts = new Timestamp(recordTruck.getTime());
                            statement.setTimestamp(10, ts);

                            statement.executeUpdate();                            
                        }
                    }
                }
                mySQLConnection.close();
            }
        } catch (SQLException msg) {
            System.out.println(msg.toString());
        }
    }

    // Method to record a truck message to the database
    // Whenever the used table or truck model changes, this method must be updated
    // As it is displayed here, it uses MySQL
    public static void recordGroupInformationToMySQL(GroupModel recordGroup) throws SQLException {
        try {
            if (connectToMySQLDatabase()) {
                if (recordGroup.getInformationGroups() == null) {
                } else {
                    for (int i = 0; i < recordGroup.getInformationGroups().length; i++) {
                        PreparedStatement statement = mySQLConnection.prepareStatement("INSERT INTO group_messages (LEAST_BITS_VEHICLE_ID, MOST_BITS_VEHICLE_ID, LEAST_BITS_GATEWAY_ID, MOST_BITS_GATEWAY_ID, GROUP_VALUE) VALUES(?,?,?,?,?)");

                        statement.setLong(1, recordGroup.getLeastBitsVehicleId());
                        statement.setLong(2, recordGroup.getMostBitsVehicleId());
                        statement.setLong(3, recordGroup.getLeastBitsGatewayId());
                        statement.setLong(4, recordGroup.getMostBitsGatewayId());
                        statement.setInt(5, recordGroup.getGroup(i));

                        statement.executeUpdate();                        
                    }
                }
                mySQLConnection.close();
            }
        } catch (SQLException msg) {
            System.out.println(msg.toString());
        }
    }

    // Method to record a truck message to the database
    // Whenever the used table or truck model changes, this method must be updated
    // As it is displayed here, it uses MySQL
    public static void recordGatewayInformationToMySQL(GatewayModel recordGateway) throws SQLException {
        try {
            if (connectToMySQLDatabase()) {
                PreparedStatement statement = mySQLConnection.prepareStatement("INSERT INTO gateway_messages (LEAST_BITS_GATEWAY_ID, MOST_BITS_GATEWAY_ID, PARTICIPANT_TYPE, CPU_USAGE, FREE_MEMORY, NUMBER_OF_VEHICLES) VALUES(?, ?, ?, ?, ?, ?)");

                statement.setLong(1, recordGateway.getLeastBitsGatewayId());
                statement.setLong(2, recordGateway.getMostBitsGatewayId());
                statement.setInt(3, recordGateway.getParticipantType());
                statement.setInt(4, recordGateway.getCpuUsage());
                statement.setInt(5, recordGateway.getFreeMemory());
                statement.setInt(6, recordGateway.getNumberOfConnectedVehicles());
                statement.executeUpdate();
                mySQLConnection.close();
            }
        } catch (SQLException msg) {
            System.out.println(msg.toString());
        }
    }
}