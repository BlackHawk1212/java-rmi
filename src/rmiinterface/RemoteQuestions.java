package com.perisic.beds.rmiinterface;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;
/**
 * RMI interface to enable to retrieve questions from the server and to submit data
 * to the server. 
 * @author Marc Conrad
 *
 */
public interface RemoteQuestions extends Remote {
	/**
	 * Number of questions on the server.
	 * @return number of questions
	 * @throws RemoteException throws
	 */
	public int getNumberOfQuestions() throws RemoteException; 
	/**
	 * Retrieve specific question from the server. 
	 * @param i number of the question. 
	 * @return the Question. 
	 * @throws RemoteException throws
	 */
	public Question getQuestion(int i) throws RemoteException; 
	/**
	 * Submit the answer to the question number i.
	 * @param i question where the answer belongs to.
	 * @param answer the answer given to this question. 
	 * @throws RemoteException throws
	 */
	void submitAnswer(int i, String answer) throws RemoteException;  
	/**
	 * Returns the answers to the questions given. 
	 * @return answers to the questions. 
	 * @throws RemoteException throws
	 */
	public Vector<Question> getData() throws RemoteException; 
}
