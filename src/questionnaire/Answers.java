package com.perisic.beds.questionnaire;

import java.util.Vector;

import com.perisic.beds.rmiinterface.Question;

/**
 * This class provides represents a set of answered questions and methods are provided 
 * to display these answers in various formats: raw data, bar chart, JSON. 
 * To do: add more methods to systematically analyze the answers according to the needs 
 * of the project. 
 * @author Marc Conrad
 */

public class Answers {

	private Vector<Question> myData; 
	
	/** 
	 * Initializes the object with the data to be analyzed. 
	 * @param myData a vector with answered questions.
	 */
	
	public Answers(Vector<Question> myData) {
		super();
		this.myData = myData;
	}
	
	/**
	 * get questions from the database
	 * @return myData a vector with answered questions.
	 */

	public Vector<Question> getMyData() {
		return myData;
	}

	/**
	 * set questions
	 * @param myData a vector with answered questions.
	 */

	public void setMyData(Vector<Question> myData) {
		this.myData = myData;
	}

	/**
	 * Provides a very basic summary of the answers. 
	 * @return basic statistics. 
	 */

	public String basicAnalysis() {	
		StringBuffer report = new StringBuffer(); 
		for(int i = 0; i < myData.size(); i++ ) { 
			Question qq = myData.elementAt(i); 

			report.append(qq.getQuestionText()); 
			String [] answers = qq.getAnswers(); 			
			for(int j = 0; j < answers.length; j++ ) { 
				report.append(" "+answers[j]+" - "+ qq.getFrequency(answers[j])+";");
			}
			report.append(System.lineSeparator()); 
		}
		return report.toString(); 


	}

	/** 
	 * translates the data into JSON format. 
	 * @param get respondents report
	 * @return JSON report
	 */

	public void saveJSON() {
		String report = "{"; 
		for(int i = 0; i < myData.size(); i++ ) { 
			Question qq = myData.elementAt(i);
			if( i > 0 ) { report += ","; }
			report += "\"" + qq.getQuestionText() +"\": {"; 

			String [] answers = qq.getAnswers(); 			
			for(int j = 0; j < answers.length; j++ ) { 
				if( j > 0 ) { report += ","; } 
				report += "\""+answers[j]+"\" : "+ qq.getFrequency(answers[j]);
			}
			report += "}";
		}
		report += "}"; 
		
		JsonManipulation jm = new JsonManipulation();
		jm.setJsonData(report);
		jm.saveJson();
		System.out.println("json is: "+report);
	}
	
	/**
	 * get JSON values from the database
	 * @return report
	 */
	public String getJSON() {
		JsonManipulation jm = new JsonManipulation();
		String report = jm.getJsonDataFromDB();
		return report; 
	}
}
