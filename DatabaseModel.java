import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.lang.Long.*;

public class DatabaseModel {
	private static Connection dbConnection;
	private static String dbConnectionString = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)}; DBQ=TrafficInspectorDB.mdb";
	
	public DatabaseModel () {}
	
	public static boolean connectToDatabase () {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			dbConnection = DriverManager.getConnection(dbConnectionString, "", "");
			return true;
		}
		catch (SQLException msg) {
			return false;
		}
		catch (ClassNotFoundException msg) {
			return false;
		}
	}
	
	public static boolean disconnectFromDatabase () {
		try {
			dbConnection.close();
			return true;
		}
		catch (SQLException msg) {
			return false;
		}		
	}
	
	private static ResultSet queryResult (String sqlQuery) {
		PreparedStatement statement;
		try {
			statement = dbConnection.prepareStatement(sqlQuery);
			return statement.executeQuery();
		}
		catch (SQLException msg) {			
		}
		return null;
	}
	
	public static void recordTruckInformation (TruckModel recordTruck) throws SQLException {
		try {
			if (connectToDatabase()) {
				System.out.println("Connected to database and attempting to write data");
				PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO TRUCK_INFORMATION (LEAST_BITS_VEHICLE_ID, MOST_BITS_VEHICLE_ID, LEAST_BITS_GATEWAY_ID, MOST_BITS_GATEWAY_ID, GROUPS, TRACKER, LATITUDE, LONGITUDE, SPEED, MESSAGE_TIME) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				
				statement.setString (1, Long.toString(recordTruck.getLeastBitsVehicleId()));
				statement.setString (2, Long.toString(recordTruck.getMostBitsVehicleId()));
				statement.setString (3, Long.toString(recordTruck.getLeastBitsGatewayId()));
				statement.setString (4, Long.toString(recordTruck.getMostBitsGatewayId()));
				statement.setInt    (5, recordTruck.getGroups());
				statement.setString (6, recordTruck.getTracker());
				statement.setDouble (7, recordTruck.getLatitude());
				statement.setDouble (8, recordTruck.getLongitude());
				statement.setDouble (9, recordTruck.getSpeed());
				statement.setString (10, Long.toString(recordTruck.getTime()));
				
				statement.executeUpdate();
				dbConnection.commit();
				dbConnection.close();
			}
		}
		catch (SQLException msg) {
			System.out.println(msg.toString());
		}		
	}		
}