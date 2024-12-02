package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

	private static final  String database = "jdbc:postgresql://localhost:5432/postgres";
	private static final String username ="postgres";
	private static final String password = "post";
	
	
	static {
	    try {
	        Class.forName("org.postgresql.Driver");
	    } catch (ClassNotFoundException e) {
	        System.out.println("PostgreSQL JDBC Driver not found!");
	        e.printStackTrace();
	    }
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(database, username, password);
	}
	
	public static void closeConnection(Connection connection ) {
	if (connection != null) {
		try {
			connection.close();
			System.out.println("connection closed!");

		}catch (Exception e) {
			System.out.println("error closing the connection!");
		}
	}
	}
}
