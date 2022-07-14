package com.perisic.beds.peripherals;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.perisic.beds.questionnaire.QuestionSet;

/**
 * Implemented the feedback class
 * @author Nimesh Mendis
 *
 */

public class Feedback extends JFrame {

	private QuestionSet questionnaire = new QuestionSet();

	private static final long serialVersionUID = -8428590640233967435L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField email;
	private JButton finishfb;
	private JButton ansQs;
	private JComboBox options;
	private String empStatus [] = {"Select an option", "Permanent Employee", "Trainee Employee"};

	/**
	 * Launch the application.
	 * @param args not used
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Feedback frame = new Feedback();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creating the database connection
	 * @return connection
	 */
	
	private static Connection connection() {

		Connection c;
		try {
			//load the driver
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost/" + "sltdb", "root", "");

		} catch (Exception e) {
			c = null;
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return c;

	}

	/*
	 * Implementing the CRUD functions
	 */
	
	//Create function
	private void InsertData() {

		Connection cn = connection();			//Creating new connection object
		try {
			String query = "INSERT INTO `employee` (name,empStatus,email) values (?,?,?)";		
			PreparedStatement ps = cn.prepareStatement(query);				//creating a new prepare statement
			ps.setString(1, name.getText());
			ps.setString(2, options.getSelectedItem().toString());			//Inserting the values which taking from the text field IDs
			ps.setString(3, email.getText());
			ps.executeUpdate();					//executing the update method

			JOptionPane.showMessageDialog(null, "Employee Data Stored Successfully...");		//Displaying successful message
			name.setText("");
			options.setSelectedItem("Select an option");			//Clearing and reseting the fields
			javaMailUtil.sendMail(email.getText());			//Calling the javaMailUtil function to send confirmation mail
			email.setText("");

		}catch(Exception ex) {
			System.out.println("error: " + ex);			//catching any exception errors
		}

	}


	/**
	 * Create the frame.
	 */

	public Feedback() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1203, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(0, 0, 1187, 564);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1.setBackground(new Color(100, 149, 237));
		panel_1.setBounds(21, 179, 1143, 361);
		panel.add(panel_1);

		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(24, 21, 88, 31);
		panel_1.add(lblNewLabel_1);

		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		name.setColumns(10);
		name.setBounds(269, 21, 401, 33);
		panel_1.add(name);

		JLabel lblNewLabel_2 = new JLabel("Employee Status:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_2.setBounds(24, 78, 222, 32);
		panel_1.add(lblNewLabel_2);

		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.PLAIN, 20));
		email.setColumns(10);
		email.setBounds(269, 142, 602, 33);
		panel_1.add(email);

		JLabel lblNewLabel_2_2 = new JLabel("Email:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_2_2.setBounds(24, 142, 222, 32);
		panel_1.add(lblNewLabel_2_2);

		/*
		 * Once this method is called. After successful server connection,
		 * ansQs action listener requesting stub (client side RMI interface) to get questions from the RMI server
		 * then stub pass the message to skeleton (server side RMI interface) to get the questions from the remote server
		 * then server looking for the questions in the database
		 * After successful founded result from the database server retrieve and pass to skeleton and passes to stub and stub passes to the client 
		 * then client display questions through UI
		 */
		
		JButton ansQs = new JButton("Answer Questions");
		ansQs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource().equals(ansQs)) { 

					for(int i = 0; i <= questionnaire.numberOfQuestions(); i++) {			//Getting the number of questions
						if(i == questionnaire.numberOfQuestions()) {
							int selectedValue = JOptionPane.showConfirmDialog(rootPane, "Submit Questions");		//confirmation message once the questionnaire completed 
							if(selectedValue == 0) {
								questionnaire.reportAnswers();
								JOptionPane.showMessageDialog(rootPane, "Survey Result Submitted");		//successful submission questionnaire to the database
							}else {

							}

						}else {
							String message = questionnaire.getQuestion(i); 		//Display the question
							String [] options = questionnaire.getOptions(i); 		//Display the answers with the question

							//questions will display until all the questions available in the database table
							int selectedValue = JOptionPane.showOptionDialog(rootPane, message, "Question "+(i+1), JOptionPane.DEFAULT_OPTION, 
									JOptionPane.QUESTION_MESSAGE, null, options, options[0]);  

							questionnaire.submitAnswer(i, options[selectedValue]);	//submitting the question with the relevant option selected by the employee
						}
					}
				}
			}
			
		});
		ansQs.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ansQs.setBounds(925, 231, 189, 31);
		panel_1.add(ansQs);

		JButton finishfb = new JButton("Finished");
		finishfb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				InsertData();					//Calling the Create function
				
			}
		});
		finishfb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		finishfb.setBounds(387, 304, 363, 29);
		panel_1.add(finishfb);

		options = new JComboBox();
		options.setFont(new Font("Tahoma", Font.PLAIN, 20));
		options.setModel(new DefaultComboBoxModel(new String[] {"Select an option", "Permanent Employee", "Trainee Employee"}));
		options.setBounds(269, 84, 351, 33);
		panel_1.add(options);

		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setLayout(null);
		panel_1_1_2.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1_1_2.setBackground(new Color(100, 149, 237));
		panel_1_1_2.setBounds(21, 24, 1143, 107);
		panel.add(panel_1_1_2);

		JLabel headlbl = new JLabel("Sri Lanka Telecom");
		headlbl.setFont(new Font("Tahoma", Font.BOLD, 45));
		headlbl.setBounds(396, 11, 416, 54);
		panel_1_1_2.add(headlbl);

		JLabel subheadlbl = new JLabel("Feel free to fill this questionnaire to improve our employee satisfaction");
		subheadlbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		subheadlbl.setBounds(350, 60, 507, 21);
		panel_1_1_2.add(subheadlbl);

		/**
		 * once user click the back button 
		 * mouse click listener listen to the action and performing the action 
		 */
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainDash fdb = new MainDash();
				fdb.setVisible(true);
				fdb.setLocationRelativeTo(null);
				fdb.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				Feedback.this.dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setBounds(35, 141, 88, 27);
		panel.add(btnBack);
	}

}
