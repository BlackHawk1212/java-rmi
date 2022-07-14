package com.perisic.beds.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.perisic.beds.rmiinterface.Question;
import com.perisic.beds.rmiinterface.RemoteQuestions;

/**
 * Implementation of the questionnaire. Note that chosen answers are collected in this
 * object as well. That means that if the object is destroyed, for instance server restart
 * the collected data is all gone. 
 * To do: make data persistent, for instance link collected data to a database or save data
 * in a text file.  
 * @author Marc Conrad
 *
 */
public class QuestionServerImplementation extends UnicastRemoteObject implements RemoteQuestions{
	private static final long serialVersionUID = -3763231206310491048L;
	Vector<Question> myQuestions = new Vector<Question>(); 
	
	/**
	 * All questions and answers are initialized in the constructor of this class. 
	 * To do: read questions and options from an external data file.
	 * @throws RemoteException throwing
	 */
	
	QuestionServerImplementation() throws RemoteException {

		super();
		System.out.println("QuestionServerImplementation Created");

		DBConnection db = new DBConnection();
		Connection cn = db.dbConnection();

		//Retrieving All questions and options available in the database after successful service start
		
		/*Since there is different counts of options in feedback questionnaire. I took the select
		 * count query to retrieve specific options form the database
		 */
		
		int count = 0;
		String sql1  = "SELECT COUNT(*) AS cnt FROM options";
		ResultSet rs;
		Statement stat;
		try {
			stat = cn.createStatement();
			rs = stat.executeQuery(sql1);
			while(rs.next()) {
				count = rs.getInt("cnt");
			}
			stat.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] answer = new String[count];

		String sql = "SELECT * FROM `options`";

		int i=0;
		try {

			stat = cn.createStatement();
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				if(i<count) {
					answer[i] = rs.getString("answer");
					i++;
				}
				else {
					break;
				}
			}
			i=0;
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());

		}

		String sql2 = "SELECT * FROM `questions`";
		Statement stat2;
		try {
			stat2 = cn.createStatement();
			rs = stat2.executeQuery(sql2);

			while (rs.next()) {
				Question questions = new Question(rs.getString("question"), answer);
				myQuestions.add(questions);
			}

			cn.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} 
	} 

	/**
	 * Implementation of remote interface method. 
	 */
	@Override
	public int getNumberOfQuestions() throws RemoteException {
		return myQuestions.size();
	}
	/**
	 * Implementation of remote interface method. 
	 */
	@Override
	public Question getQuestion(int i) throws RemoteException {
		return myQuestions.elementAt(i);
	}
	/**
	 * Implementation of remote interface method. 
	 */	
	@Override
	public void submitAnswer(int i, String answer) throws RemoteException {
		myQuestions.elementAt(i).addAnswer(answer);
	}
	/**
	 * Implementation of remote interface method. 
	 */	
	@Override
	public Vector<Question> getData() { 
		return myQuestions; 
	}

}
