package cs341project1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

import javax.swing.SwingUtilities;

import Storage.DB;
import WindowsPack.LoginFrame;

public class TheMain {

	public static void main(String[] args) throws SQLException {
		//commented out code was a test to see if the database actually exists
		//make sure the buildpath is a downloaded copy of sqlite-jdbc-3.50.30.jar on the classpath (see slide for download details)
		// dev/test users:  username:kratos password:boy      username:masterchief  password:360
		
		//	DB db;
		//	String DB_FILE = "data/app.db";
		// String URL = "jdbc:sqlite:" + DB_FILE;
		//makes sure the table/database is made properly
		//try {
		 //db = DB.getInstance();
		 //	db.initialize();
	//	} catch (SQLException e) {
		//	e.printStackTrace();
		//}
		//System.out.println("DB Path: " + new java.io.File("data/app.db").getAbsolutePath());
		
			//	   try (Connection conn = DriverManager.getConnection(URL);
				   //	             Statement st = conn.createStatement();
				   //    ResultSet rs = st.executeQuery("SELECT name FROM sqlite_master WHERE type='table';")) {

			   //while (rs.next()) {
		            	//System.out.println("Table: " + rs.getString("name"));
		                //}
		            //} catch (SQLException e) {
		        	//e.printStackTrace();
					//System.out.println("error");
					//	}
		
		
		   //FINAL VERSION AFTER TESTING IS DONE IS BELOW
		  DB db;
		 try {
			db = DB.getInstance();
			db.initialize();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true)); //after database is doublechecked, start up the login process
	}

}
