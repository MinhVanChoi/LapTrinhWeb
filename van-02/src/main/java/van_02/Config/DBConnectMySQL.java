package van_02.Config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;	



public class DBConnectMySQL {

	private static String USERNAME = "root";
	private static String PASSWORD = "123456789";
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/van_02?autoReconnect=true&useSSL=false";
	
		
	public static Connection getDatabaseConnection() throws ClassNotFoundException {

		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		try {
			new DBConnectMySQL();
			System.out.println(DBConnectMySQL.getDatabaseConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}	
