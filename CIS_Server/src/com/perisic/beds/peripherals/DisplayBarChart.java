package com.perisic.beds.peripherals;

import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.perisic.beds.questionnaire.Answers;
import com.perisic.beds.rmiinterface.Question;


/**
 * Implementation of display bar chart
 * @author Nimesh Mendis
 */

public class DisplayBarChart {

	/**
	 * implementing the show method
	 * @param answers responded from the employees
	 */

	public static void show(Answers answers) {

		//Getting number of questions available in the database
		int dataSize = answers.getMyData().size();
		System.out.println("My Data Size is "+dataSize);
		for(int i = 0; i<dataSize; i++) {

			Map<String,Integer> map = new HashMap<String, Integer>(); //using hash_mapping collecting both questions and answers without duplicate values
			Question q = answers.getMyData().elementAt(i);
			String qsn = q.getQuestionText();
			String[] ans = q.getAnswers();

			for(String an : ans) {
				map.put(an, q.getFrequency(an));	//Getting the size frequency of the answers
			}

			//Displaying the question and the answers relating to the question in the console
			System.out.println("Qsn is : "+qsn);
			System.out.println("Ans is  : "+map);

			//creating and displaying the bar chart
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			JFreeChart chart = ChartFactory.createBarChart(qsn,	//Displaying the question on the title
					"Options",			//Y Axis name
					"Responds Count", 	//X axis name
					dataset, 
					PlotOrientation.VERTICAL, 
					true, 			//Include legend
					true, 
					false);
			
			NumberAxis domainAxis = new NumberAxis();
			domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			CategoryPlot plot = (CategoryPlot)chart.getPlot();
			BarRenderer renderer = (BarRenderer)plot.getRenderer();
			renderer = new BarRenderer();
			renderer.getMaximumBarWidth();
			ChartFrame frame = new ChartFrame(qsn,chart);

			for(String key : map.keySet()) {
				dataset.setValue(map.get(key), //Answer frequency
						key, 		//Number of bars
						key		//include the options
						);
			}

			//Bar chart is fit to the graph and its set visible to the user
			frame.pack();
			frame.setVisible(true); 

		}

	}

}
