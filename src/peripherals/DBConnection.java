

package com.perisic.beds.peripherals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


/**
 * client side Database connection class
 * @author Nimesh Mendis
 */

public final class DBConnection {

	/**
	 * Assigning and initializing the variables
	 */
	
	private final static String server = "jdbc:mysql://localhost/";
	private final static String database = "sltdb";
	private final static String user_name = "root";
	private final static String pass_word = "";

	private final static String driver = "com.mysql.jdbc.Driver";

	/**
	 * connection class
	 * @return connection
	 */
	
	public static Connection DBConnection() {
		
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
	
	//Pre_defined SQL Query methods 
	
	/**
	 * retrieving all data from a particular table and view to the user
	 * @param table_name stored in database
	 */

	public void getDB(String table_name) {

		String selectQuery = "SELECT * FROM " + table_name+ ";"; 

		Connection db = DBConnection.DBConnection();
		Statement stat;
		try {
			stat = db.createStatement();
			stat.execute(selectQuery);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally{
			try{
				db.close();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
		
	}
	
	/**
	 * Data retrieve from any table 
	 * where id value is an integer value
	 * @param table_name stored in database
	 * @param idName stored in database
	 * @param idValue stored in database
	 */

	public void getFieldDB(String table_name, String idName, int idValue) {

		String selectQuery2 = "SELECT FROM " + table_name + " where " + idName + " = "+idValue+ ";"; 

		Connection db = DBConnection.DBConnection();
		Statement stat;
		try {
			stat = db.createStatement();
			stat.execute(selectQuery2);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally{
			try{
				db.close();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
		
	}

	/**
	 * Data retrieve from any table 
	 * where id value is an String value
	 * @param table_name stored in database
	 * @param idName stored in database
	 * @param idValue stored in database
	 */
	
	public void getFieldDB(String table_name, String idName, String idValue) {

		String selectQuery3 = "SELECT FROM " + table_name + " where " + idName + " = "+idValue+ ";"; 

		Connection db = DBConnection.DBConnection();
		Statement stat;
		try {
			stat = db.createStatement();
			stat.execute(selectQuery3);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally{
			try{
				db.close();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
		
	}

	/**
	 * Inserting data to table
	 * @param table_name stored in database
	 * @param fields stored in database
	 * @param values stored in database
	 */
	
	public void insertDB(String table_name, String[] fields, String[] values) {

		String field_list = "", value_list = "";

		for (int i = 0; i < fields.length; i++) {
			field_list = field_list + fields[i];
			if (i < fields.length - 1) {
				field_list += ", ";
			}
		}
		for (int i = 0; i < values.length; i++) {
			value_list = value_list + values[i];
			if (i < values.length - 1) {
				value_list += "', '";
			}
		}
		String sql = "INSERT INTO " + table_name + " ("
				+ field_list + ") VALUES ('" + value_list + "')";
		Connection db = DBConnection.DBConnection();
		Statement stat;
		try {
			stat = db.createStatement();
			stat.execute(sql);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally{
			try{db.close();}
			catch(Exception ex)
			{JOptionPane.showMessageDialog(null, ex.getMessage());}
		}

	}

	/**
	 * Update tables
	 * @param table_name stored in database
	 * @param fields stored in database
	 * @param values stored in database
	 */
	
	public void updateAll(String table_name, String[] fields, String[] values) {

		String field_values = "";
		for (int i = 0; i < fields.length; i++) {
			field_values = field_values + fields[i] + "='" + values[i] + "'";
			if (i < fields.length - 1) {
				field_values += ", ";
			}
		}
		String updateQuery = "Update " + table_name + " Set " + field_values + " ;";

		Connection db = DBConnection.DBConnection();
		Statement stat;
		try {
			stat = db.createStatement();
			stat.execute(updateQuery);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally{
			try{
				db.close();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
		
	}

	/**
	 * Update data fields where id value is a String
	 * @param table_name stored in database
	 * @param fields stored in database
	 * @param values stored in database
	 * @param idName stored in database
	 * @param idValue stored in database
	 */
	public void updateField(String table_name, String[] fields, String[] values, String idName, String idValue) {

		String field_values = "";
		for (int i = 0; i < fields.length; i++) {
			field_values = field_values + fields[i] + "='" + values[i] + "'";
			if (i < fields.length - 1) {
				field_values += ", ";
			}
		}
		String updateQuery2 = "Update " + table_name + " Set " + field_values + " where " + idName +"="+  idValue+ ";";

		Connection db = DBConnection.DBConnection();
		Statement stat;
		try {
			stat = db.createStatement();
			stat.execute(updateQuery2);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally{
			try{
				db.close();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
		
	}

	/**
	 * Update data fields where id value is an Integer
	 * @param table_name stored in database
	 * @param fields stored in database
	 * @param values stored in database
	 * @param idName stored in database
	 * @param idValue stored in database
	 */
	
	public void updateField(String table_name, String[] fields, String[] values, String idName, int idValue) {

		String field_values = "";
		for (int i = 0; i < fields.length; i++) {
			field_values = field_values + fields[i] + "='" + values[i] + "'";
			if (i < fields.length - 1) {
				field_values += ", ";
			}
		}
		String updateQuery2 = "Update " + table_name + " Set " + field_values + " where " + idName +"="+ '"' +idValue+'"' +";";

		Connection db = DBConnection.DBConnection();
		Statement stat;
		try {
			stat = db.createStatement();
			stat.execute(updateQuery2);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally{
			try{
				db.close();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
		
	}
	
	/**
	 * Delete all records from table
	 * @param table_name stored in database
	 */
	
	public void deleteAll(String table_name) {

		String deleteQuery = "DELETE FROM " + table_name+ ";"; 

		Connection db = DBConnection.DBConnection();
		Statement stat;
		try {
			stat = db.createStatement();
			stat.execute(deleteQuery);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally{
			try{
				db.close();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
	}

	/**
	 * Delete any field from any table
	 * where id value is an integer 
	 * @param table_name stored in database
	 * @param idName stored in database
	 * @param idValue stored in database
	 */
	
	public void deleteField(String table_name, String idName, int idValue) {

		String deleteQuery2 = "DELETE FROM " + table_name + " where " + idName + " = "+idValue+ ";"; 

		Connection db = DBConnection.DBConnection();
		Statement stat;
		try {
			stat = db.createStatement();
			stat.execute(deleteQuery2);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally{
			try{
				db.close();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
	}

	/**
	 * Delete any field from any table
	 * where id value is a String
	 * @param table_name stored in database
	 * @param idName stored in database
	 * @param idValue stored in database
	 */
	
	public void deleteField(String table_name, String idName, String idValue) {            

		String deleteQuery3 = "DELETE FROM " + table_name + " where " + idName + " = "+'"'+idValue+'"' + ";"; 

		Connection db = DBConnection.DBConnection();
		Statement stat;
		try {
			stat = db.createStatement();
			stat.execute(deleteQuery3);
			db.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally{
			try{
				db.close();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
		
	}

}