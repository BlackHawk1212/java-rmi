package com.perisic.beds.peripherals;

import java.util.concurrent.ThreadLocalRandom;
import com.perisic.beds.questionnaire.QuestionSet;
/** 
 * A simple class that simulates answering questions to test the infrastructure.
 * @author Marc Conrad
 */
public class Tester {
	
	/**
	 * Simulates howMany participants filling in the questionnaire. 
	 * @param questions from the question set stored in database
	 * @param howMany questions
	 */
	
	public static void doQuestionnaire(QuestionSet questions, int howMany) { 

		System.out.println("Generating random answers...");	
		for(int k = 0; k < howMany; k++) { 
			for(int i=0; i < questions.numberOfQuestions(); i++) { 
				String [] options = questions.getOptions(i); 
				int randomNum = ThreadLocalRandom.current().nextInt(0, options.length);
				questions.submitAnswer(i, options[randomNum]);
			}
		}

	}
	
	/** 
	 * The tests starts here.
	 * @param args not used.
	 */
	public static void main(String [] args) {
		
		//Generating random answers for 10 questions
		System.out.println("Test 1: Answer 10 Questions.");
		QuestionSet questions1 = new QuestionSet();
		doQuestionnaire(questions1, 10); 
		questions1.reportAnswers(); 
		System.out.println(); 

		//Generating random answers for another 50 questions
		System.out.println("Test 2: Answer 50 more Questions.");
		doQuestionnaire(questions1, 50); 
		QuestionSet questions2 = new QuestionSet(); 
		questions2.reportAnswers(); 
		
	}
}
