package com.perisic.beds.peripherals;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.perisic.beds.questionnaire.Answers;
import com.perisic.beds.rmiinterface.Question;

/**
 * Implementation of display bar chart
 * @author Nimesh Mendis
 */

public class DisplayPieChart {
	
	/**
	 * implementing the show method
	 * @param answers responded from the employees
	 */
	
	public static void show(Answers answers) { 
		
		//Getting number of questions available in the database
		int dataSize = answers.getMyData().size();
		System.out.println("My Data Size is "+dataSize);
		for(int i = 0; i<dataSize; i++) {
			
			Map<String,Integer> map = new HashMap<String, Integer>();	//using hash_mapping collecting both questions and answers without duplicate values
			Question q = answers.getMyData().elementAt(i);
			String qsn = q.getQuestionText();
			String[] ans = q.getAnswers();
			for(String an : ans) {
				map.put(an, q.getFrequency(an));	//Getting the size frequency of the answers
			}
			
			//Displaying the question and the answers relating to the question in the console
			System.out.println("Qsn is : "+qsn);
			System.out.println("Ans is  : "+map);
			
			//Displaying the Pie chart to the user
			JPanel panel = createDemoPanel(map,qsn);
			JFrame frame = new JFrame();
			frame.setTitle("Question");
			frame.getContentPane().add(panel, BorderLayout.CENTER);
			frame.pack();
			frame.setVisible(true);
			
		}

	}
	
	/**
	 * Creating pie chart data set
	 * @param map frequency size
	 * @return
	 */
	
	 private static PieDataset createDataset(Map<String, Integer> map) {
		 
	      DefaultPieDataset dataset = new DefaultPieDataset( );
	      for(String key : map.keySet()) {
	    	  
	    	  dataset.setValue(key, map.get(key));
	    	  
	      }  
	      return dataset;  
	      
	   }
	 
	 /**
	  * creating chart method
	  * @param dataset question data
	  * @param qsn question title
	  * @return chart
	  */
	 
	 private static JFreeChart createChart( PieDataset dataset, String qsn ) {
		 
	      JFreeChart chart = ChartFactory.createPieChart(      
	         qsn,   // chart title 
	         dataset,          // data    
	         true,             // include legend   
	         true, 
	         false);

	      return chart;
	      
	   }
	   
	 /**
	  * creating the chart in a demo panel
	  * @param map answer displaying
	  * @param qsn question title
	  * @return chart panel
	  */
	 
	   public static JPanel createDemoPanel(Map<String,Integer>map, String qsn) {
		   
	      JFreeChart chart = createChart(createDataset(map), qsn);  
	      return new ChartPanel( chart );
	      
	   }

}
