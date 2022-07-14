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
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Edit question class
 * @author Nimesh Mendis
 *
 */

public class EditQuestion extends JFrame {

	/**
	 * Assigning and initializing the variables
	 */
	
	DBConnection db = new DBConnection();			//Creating new DBConnection object

	
	private static final long serialVersionUID = 4804698217967843090L;
	private static final Object [][] rowdata = {};
	private static final Object [] columnnames = {"ID", "Question"};

	public static final String email = null;
	private JFrame frame;
	private JPanel contentPane;
	private JTextField qsId;
	private JTextField question;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 * @param args not used
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditQuestion frame = new EditQuestion();
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
	
	private Connection connection() {

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

		Connection cn = connection();							//Creating new connection object
		try {
			String query = "INSERT INTO `questions` values (?,?)";
			PreparedStatement ps = cn.prepareStatement(query);		//creating a new query statement
			ps.setString(1, qsId.getText());
			ps.setString(2, question.getText());						//Inserting the values which taking from the text field IDs
			ps.execute();					//executing the prepared statement method

			JOptionPane.showMessageDialog(null, "Qustion Inserted Successfully...");		//Displaying successful message
			ViewData();							//refresh the table
			qsId.setText("");
			question.setText("");						//Clearing the fields

		}catch(Exception ex) {
			System.out.println("error: " + ex);				//catching any exception errors
		}

	}

	/**
	 * Retrieve function
	 */
	
	private void ViewData() {

		Connection cn = connection();						//Creating new connection object
		DefaultTableModel model = new DefaultTableModel();						//creating a new default table model
		model.addColumn("ID");									//Creating table columns to the table
		model.addColumn("Question");						
		try {					
			String query = "SELECT * FROM `questions`";			//Getting data from the database
			Statement stat = cn.createStatement();		//creating a new statement
			ResultSet rs = stat.executeQuery(query);			//executing the prepared query statement
			while(rs.next()) {
				model.addRow(new Object[]{
						rs.getInt("qId"),
						rs.getString("Question")			//Getting text data from database table 
				});

			}
			rs.close();		//closing the execution
			stat.close();		//closing the created statement
			cn.close();						//Closing the connection

			table.setModel(model);			//setting the table model
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(50);
			table.getColumnModel().getColumn(1).setPreferredWidth(525);			//Setting the table column width sizes

		}catch(Exception ex) {
			System.out.println("Error: " + ex);				//catching any exception errors
		}

	}

	/**
	 * Update function
	 * @param qId stored in database
	 */
	
	private void Update(String qId) {

		Connection cn = connection();						//Creating new connection object
		try {
			String query = "UPDATE `questions` SET question = ? WHERE qId = ?";			//Updating the table
			PreparedStatement ps = cn.prepareStatement(query);				//creating a new prepare statement method

			ps.setString(1, question.getText());				//getting the text from relevant text field IDs
			ps.setString(2, qId);
			ps.executeUpdate();				//executing the update method

			ps.close();			//closing the query statement		
			cn.close();			//closing the connection

			JOptionPane.showMessageDialog(null, "Qustion Updated Successfully...");			//Showing successful message
			ViewData();									//refresh the table
			qsId.setText("");
			question.setText("");										//resetting and clear the fields

		}catch (Exception ex) {
			System.out.println("Error: " + ex);					//catching exception errors
		}

	}

	/**
	 * Delete function
	 * @param qId stored in database
	 */
	
	private void Delete(String qId) {

		Connection cn = connection();				//Creating new connection object
		try {
			String query = "DELETE FROM `questions` WHERE qId = ?";		//Delete data from the database
			PreparedStatement ps = cn.prepareStatement(query);		//creating a new prepare statement
			ps.setString(1, qId);
			ps.execute();			//executing the prepare statement

			ps.close();				//closing the query statement
			cn.close();						//closing the connection

			JOptionPane.showMessageDialog(null, "Qustion Deleted Successfully...");				//Showing successful message
			ViewData();									//refresh the table
			qsId.setText("");
			question.setText("");									//resetting and clear the fields

		}catch (Exception e) {
			System.out.println("error: " + e);			//catching exception errors
		}

	}

	/**
	 * Create the frame
	 */
	
	public EditQuestion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1226, 807);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel.setBounds(0, 0, 1210, 768);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1.setBackground(new Color(100, 149, 237));
		panel_1.setBounds(25, 160, 554, 499);
		panel.add(panel_1);

		JLabel lblNewLabel_1 = new JLabel("Question Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(24, 21, 220, 31);
		panel_1.add(lblNewLabel_1);

		qsId = new JTextField();
		qsId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		qsId.setBounds(272, 19, 251, 33);
		panel_1.add(qsId);
		qsId.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Question");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_2.setBounds(24, 78, 112, 32);
		panel_1.add(lblNewLabel_2);

		question = new JTextField();
		question.setFont(new Font("Tahoma", Font.PLAIN, 16));
		question.setColumns(10);
		question.setBounds(24, 121, 501, 39);
		panel_1.add(question);

		JButton add = new JButton("Add");
		add.setBounds(32, 419, 104, 39);
		panel_1.add(add);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				InsertData();			//Calling the Create function

			}
		});

		add.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JButton delete = new JButton("Delete");
		delete.setBounds(229, 419, 104, 39);
		panel_1.add(delete);
		delete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Delete(qsId.getText());			//Calling the Delete function

			}
		});


		JButton update = new JButton("Update");
		update.setBounds(419, 419, 104, 39);
		panel_1.add(update);
		update.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1_1.setBackground(new Color(100, 149, 237));
		panel_1_1.setBounds(25, 670, 1162, 76);
		panel.add(panel_1_1);
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Update(qsId.getText());			//Calling the Update function

			}
		});

		JButton reset = new JButton("Clear");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				qsId.setText("");
				question.setText("");			//Clearing fields once you perform this action

			}
		});

		reset.setFont(new Font("Tahoma", Font.PLAIN, 16));
		reset.setBounds(321, 19, 104, 39);
		panel_1_1.add(reset);

		JButton view = new JButton("View");
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ViewData();			//Calling Retrieve function

			}

		});
		view.setFont(new Font("Tahoma", Font.PLAIN, 16));
		view.setBounds(128, 19, 104, 39);
		panel_1_1.add(view);

		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1_1_1.setBackground(new Color(100, 149, 237));
		panel_1_1_1.setBounds(589, 160, 598, 499);
		panel.add(panel_1_1_1);
		panel_1_1_1.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 578, 477);
		panel_1_1_1.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				//Once you click the the table row table values displaying in relevant fields
				
				qsId.setText(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
				question.setText(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
			}
			
		});

		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setLayout(null);
		panel_1_1_2.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1_1_2.setBackground(new Color(100, 149, 237));
		panel_1_1_2.setBounds(25, 24, 1162, 82);
		panel.add(panel_1_1_2);

		JLabel lblNewLabel = new JLabel("Questionnaire Managing System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblNewLabel.setBounds(202, 11, 736, 54);
		panel_1_1_2.add(lblNewLabel);

		/**
		 * once user click the back button 
		 * action listener listen to the action and performing the action 
		 */
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				AdminDash fdb = new AdminDash();
				fdb.setVisible(true);
				fdb.setLocationRelativeTo(null);
				fdb.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				EditQuestion.this.dispose();

			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setBounds(35, 117, 88, 27);
		panel.add(btnBack);
	}
}
