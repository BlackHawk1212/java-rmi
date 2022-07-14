package com.perisic.beds.rmiserver;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.perisic.beds.rmiinterface.RemoteQuestions;

/**
 * Start server class
 * @author Nimesh Mendis
 */

public class StartServer {

	/**
	 * main method
	 * @param args not used
	 */
	
	public static void main(String[] args) {
		
		System.out.println("Attempting to start the Question Server..."); 
		try {
			
			//POLYMORPHISM 
			
			RemoteQuestions questions = new QuestionServerImplementation();
			Registry reg = LocateRegistry.createRegistry(1099);		//TCP port number(default is 1099).
			reg.rebind("QuestionService1819",questions);

			System.out.println("Service started. Welcome to the RMI Question Service!");

		} catch (RemoteException e1) {
			
			System.out.println("An error occured: "+e1.toString()); 
			e1.printStackTrace();
			
		}
		
	}
	
}
