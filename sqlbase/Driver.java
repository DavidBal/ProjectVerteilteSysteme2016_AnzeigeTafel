package sqlbase;

import java.sql.*;
import javax.swing.JOptionPane;

public class Driver {
	Connection conn = null;
	public static Connection dbConnector(){
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:Datenbank\\messages.sqlite");
			JOptionPane.showMessageDialog(null, "Verbindung hergestellt");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
}
