	package btltw_04.vnitstar.Configs;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectSQL {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://LAPTOP-7D271VJA:1433;databaseName=database_tuan02;user=sa;password=123456;encrypt=true;TrustServerCertificate=true";
        return DriverManager.getConnection(url);
    }
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Connection conn = getConnection();
         if (conn != null) {
             DatabaseMetaData metaData = conn.getMetaData();
             System.out.println("Kết nối thành công với CSDL: " + metaData.getDatabaseProductName());
	}
}
}