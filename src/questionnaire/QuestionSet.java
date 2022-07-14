package com.perisic.beds.questionnaire;

import java.rmi.Naming;
import java.rmi.RemoteException;

import com.perisic.beds.peripherals.DisplayBarChart;
import com.perisic.beds.peripherals.DisplayPieChart;
import com.perisic.beds.rmiinterface.RemoteQuestions; 
/**
 * Represents the questionnaire locally. All requests from peripherals will be 
 * through this class. 
 * @author Marc Conrad
 *
 */
public class QuestionSet {

	RemoteQuestions myQuestions; 

	/**
	 * implementing the question set
	 */
	
	public QuestionSet() {
		super();
		try {
			myQuestions = (RemoteQuestions) Naming.lookup("rmi://localhost/QuestionService1819");
		} catch (Exception e) {
			System.out.println("A problem occured: "+e.toString());
			e.printStackTrace();
			System.out.println("Is your server running?");
		} 
	}
	
	/**
	 * number of questions method
	 * @return number of questions
	 */
	
	public int numberOfQuestions() { 
		try {
			return myQuestions.getNumberOfQuestions();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0; 
		} 
	}
	
	/**
	 * get question
	 * @param i question number
	 * @return question text
	 */
	
	public String getQuestion(int i) { 
		try {
			return myQuestions.getQuestion(i).getQuestionText();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "no connection to server"; 
		} 
	}
	
	/**
	 * get options 
	 * @param i options
	 * @return null 
	 */
	
	public String [] getOptions(int i) { 
		try {
			return myQuestions.getQuestion(i).getAnswers();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; 
		} 
	}
	
	/**
	 * submit answers
	 * @param i number of answers
	 * @param answer text
	 */
	
	public void submitAnswer(int i, String answer) { 
		try {
			myQuestions.submitAnswer(i, answer);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * report answer method
	 */
	public void reportAnswers() { 

		try {
			Answers myAnswers = new Answers(myQuestions.getData());

			System.out.println("Basic analysis:");
			System.out.println(myAnswers.basicAnalysis());

			//ShowImageFromURL.show(myAnswers.getBarChartURL());
		} catch (RemoteException e) {
			System.out.println("Something went wrong: "+e.toString());
			e.printStackTrace();
		}  
	}

	/**
	 * this method is running once the user select the bar chart option in admin_dash
	 */
	
	public void reportBarChart() {
		// TODO Auto-generated method stub
		try {
			Answers myAnswers = new Answers(myQuestions.getData());

			System.out.println("Basic analysis:");
			System.out.println(myAnswers.basicAnalysis());

			DisplayBarChart.show(myAnswers);
			
			//DisplayPieChart.show(myAnswers);
		} catch (RemoteException e) {
			System.out.println("Something went wrong: "+e.toString());
			e.printStackTrace();
		} 
	}

	/**
	 * this method is running once the user select the pie chart option in admin_dash
	 */
	
	public void reportPieChart() {
		// TODO Auto-generated method stub
		try {
			Answers myAnswers = new Answers(myQuestions.getData());

			System.out.println("Basic analysis:");
			System.out.println(myAnswers.basicAnalysis());

			//ShowImageFromURL.show(myAnswers.getBarChartURL());
			
			DisplayPieChart.show(myAnswers);
		} catch (RemoteException e) {
			System.out.println("Something went wrong: "+e.toString());
			e.printStackTrace();
		} 
	}


}
