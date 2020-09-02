package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtils {
    
	private static final String driverClass = "com.mysql.cj.jdbc.Driver",
                            url="jdbc:mysql://localhost:3306/hotelmanagementsystem_kot?useUnicode=yes&characterEncoding=UTF-8",
			username = "root",
			password = "papaji";

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driverClass);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
        public static void closeConn(Connection c){
		try {
			if(c!=null)
				c.close();    
		}
		catch (Exception ex) {
			System.out.println (ex);
		}
	}//closeConn
          public static String getConnectionURL() {
		String con = "";
		try {
			
			con = "http://localhost:8080/Ecommerce/";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
          public static void main(String[] args) {
              System.err.println(getConnection());
    }
}
