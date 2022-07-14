package com.perisic.beds.questionnaire;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.perisic.beds.peripherals.DBConnection;

/**
 * JSON manipulation class
 * @author Nimesh Mendis
 *
 */

public class JsonManipulation {

	private String jsonData;

	/**
	 * get JSON data
	 * @return returning JSON data
	 */
	
	public String getJsonData() {
		return jsonData;
	}

	/**
	 * set JSON data
	 * @param jsonData stored in database
	 */
	
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	/**
	 * Save JSON data to database
	 */
	
	public void saveJson() {
		
		String fields[]={"json_respond"};
        String values[]={jsonData};
        DBConnection db = new DBConnection();
        db.updateField("responds_json", fields, values, "respond_Id", 1);

	}

	/**
	 * get JSON data from database
	 * @return result from the database
	 */
	
	public String getJsonDataFromDB() {
		String result = "";
		new DBConnection();
		Connection cn = DBConnection.DBConnection();

		String sql = "SELECT * FROM `responds_json`";
		Statement stat;
		try {
			stat = cn.createStatement();
			ResultSet rs = stat.executeQuery(sql);

			while (rs.next()) {
				result = rs.getString("json_respond");
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}
}
