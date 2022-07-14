package com.perisic.beds.peripherals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Admin_login process class implemented in here
 * @author Nimesh Mendis
 *
 */
public class AdminLoginProcess {

	/**
	 * Assigning and initializing the variables
	 */
	
	private int adminId;
	private String username;
	private String password;
	private int n = 0;

	//Getters and Setters

	/**
	 * what is the admin_id?
	 * @return adminId from the database
	 */

	public int getAdminId() {
		return adminId;
	}

	/**
	 * set your admin_id
	 * @param adminId to the client
	 */
	
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	/**
	 * what is your user name?
	 * @return user_name from the database
	 */
	
	public String getUsername() {
		return username;
	}

	/**
	 * set your user name
	 * @param username to the client
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * what is your password?
	 * @return password get from the database
	 */
	
	public String getPassword() {
		return password;
	}

	/**
	 * set your password
	 * @param password set to the client
	 */
	
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Implementing user authentication method
	 * @return boolean value
	 * @throws SQLException from the authentication
	 */
	
	public boolean userAuthentication() throws SQLException{

		//Initiating the DB connection and select all data from the login table
		
		new DBConnection();
		Connection cn = DBConnection.DBConnection();		//Opening a new database connection
		Statement stmt = cn.createStatement();			//Creating a new SQL statement
		String SQL = "SELECT * FROM login";			//SQL statement to select all the admin_login data from the database
		ResultSet rs = stmt.executeQuery(SQL);			//Executing the query and retrieving the data_set

		while (rs.next() == true) {
			String un = rs.getString("adminName");	//column adminName from login table and assigning to a variable
			String pwd = rs.getString("adminPw");	//column adminPw from login table and assigning to a variable

			if(username.equals(un) && password.equals(pwd)) {
				n = 1;
				break;
			}
			else {
				n = 0;
			}
		} 
		if (n == 1) {		//once the user_name and password which is valid credentials. this returns true
			return true;
		} else {
			return false;
		}
	}
	
}
