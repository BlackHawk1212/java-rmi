package com.perisic.beds.rmiserver;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

/**
 * Server side database connection class
 * @author Nimesh Mendis
 *
 */

public class DBConnection {

	private final String server = "jdbc:mysql://localhost/";
	private final String database = "sltdb";
	private final String user_name = "root";
	private final String pass_word = "";

	private final String driver = "com.mysql.jdbc.Driver";

	/**
	 * return the successful connection
	 * @return connection
	 */
	
	public Connection dbConnection() {
		Connection c;
		try {
			//load the driver
			Class.forName(driver);
			c = DriverManager.getConnection(server + database, user_name, pass_word);

		} catch (Exception e) {
			c = null;
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return c;
	}
	
}
