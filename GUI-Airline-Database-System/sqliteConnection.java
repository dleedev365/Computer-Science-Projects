import java.sql.*;
import javax.swing.*;

public class sqliteConnection {
	private static Connection con = null;
	public static Connection dbConnector()
	{
		try{
			String sUsername = // your username
			String sPassword= // your password
	        String connectionUrl = // your connection info 
	        Connection conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connection Successful");
	        return conn;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}	
	}
}
