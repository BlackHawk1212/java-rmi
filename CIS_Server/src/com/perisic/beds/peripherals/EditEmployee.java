package com.perisic.beds.peripherals;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

/**
 * Edit employee class
 * @author Nimesh Mendis
 *
 */

public class EditEmployee extends JFrame {

	
	//Assigning and initializing the variables
	private static final long serialVersionUID = 7323617194678504623L;
	private JPanel contentPane;
	private JTextField eId;
	private JTextField eName;
	private JTextField eEmail;
	private JTable table;
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
					EditEmployee frame = new EditEmployee();
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
	 * used Abstraction. Because user_admin does not need to add or delete Employees
	 * Because in abstraction we only displaying relevant functions only 
	 */
	
	//Retrieve function
	private void ViewData() {

		Connection cn = connection();							//Creating new connection object
		DefaultTableModel model = new DefaultTableModel();		//creating a new default table model
		model.addColumn("ID");
		model.addColumn("Name");				//Adding columns to the table
		model.addColumn("Employee Status");
		model.addColumn("Email");
		try {					
			String query = "SELECT * FROM `employee`";			//Creating new connection object
			Statement stat = cn.createStatement();		//Creating new SQL statement
			ResultSet rs = stat.executeQuery(query);		//executing the query and retrieving the data 
			while(rs.next()) {
				model.addRow(new Object[]{
						rs.getInt("empId"),
						rs.getString("name"),				//Getting text data from database table and showing in the UI table
						rs.getString("empStatus"),
						rs.getString("email")	
				});

			}
			rs.close();				//closing the execution of the query
			stat.close();			//closing the created statement
			cn.close();				//Closing the connection

			table.setModel(model);			//setting the table model
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(30);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
			table.getColumnModel().getColumn(2).setPreferredWidth(150);			//Setting the table column width sizes
			table.getColumnModel().getColumn(3).setPreferredWidth(245);

		}catch(Exception ex) {
			System.out.println("Error: " + ex);		//catching any exception errors
		}

	}

	/**
	 * Update function
	 * @param empId stored in database
	 */
	
	private void Update(String empId) {

		Connection cn = connection();			//Creating new connection object
		try {
			String query = "UPDATE `employee` SET name = ?, empStatus = ?, email = ? WHERE empId = ?";		//Updating the table 
			PreparedStatement ps = cn.prepareStatement(query);			//preparing a query statement

			ps.setString(1, eName.getText());
			ps.setString(2, options.getSelectedItem().toString());			//getting the text from relevant text field IDs
			ps.setString(3, eEmail.getText());
			ps.setString(4, empId);
			ps.executeUpdate();			//executing the update method of prepared statement

			ps.close();			//closing the prepare statement
			cn.close();			//closing the connection

			JOptionPane.showMessageDialog(null, "Employee information Updated Successfully...");		//Showing successful message
			ViewData();						//refresh the table
			eId.setText("");
			eName.setText("");
			options.setSelectedItem("Select an option");							//resetting and clear the fields
			eEmail.setText("");

		}catch (Exception ex) {
			System.out.println("Error: " + ex);				//catching exception errors
		}

	}

	/**
	 * Create the frame.
	 */
	
	public EditEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1236, 823);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel.setBackground(new Color(100, 149, 237));
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1.setBackground(new Color(100, 149, 237));
		panel_1.setBounds(25, 160, 554, 499);
		panel.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Employee Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(24, 21, 238, 31);
		panel_1.add(lblNewLabel_1);
		
		eId = new JTextField();
		eId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		eId.setColumns(10);
		eId.setBounds(272, 19, 251, 33);
		panel_1.add(eId);
		
		JLabel lblNewLabel_2 = new JLabel("Employee Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_2.setBounds(24, 78, 208, 32);
		panel_1.add(lblNewLabel_2);
		
		eName = new JTextField();
		eName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		eName.setColumns(10);
		eName.setBounds(24, 121, 501, 39);
		panel_1.add(eName);
		
		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Update(eId.getText());	//Calling Update function
				
			}
		});
		update.setFont(new Font("Tahoma", Font.PLAIN, 16));
		update.setBounds(368, 419, 104, 39);
		panel_1.add(update);
		
		JLabel lblNewLabel_2_1 = new JLabel("Employee Email");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_2_1.setBounds(24, 285, 208, 32);
		panel_1.add(lblNewLabel_2_1);
		
		eEmail = new JTextField();
		eEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		eEmail.setColumns(10);
		eEmail.setBounds(22, 328, 501, 39);
		panel_1.add(eEmail);
		
		JButton view = new JButton("View");
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewData();	//Calling Retrieve function
				
			}
		});
		view.setFont(new Font("Tahoma", Font.PLAIN, 16));
		view.setBounds(105, 419, 104, 39);
		panel_1.add(view);
		
		options = new JComboBox();
		options.setFont(new Font("Tahoma", Font.PLAIN, 20));
		options.setModel(new DefaultComboBoxModel(new String[] {"Select an option", "Permanent Employee", "Trainee Employee"}));
		options.setBounds(24, 223, 351, 33);
		panel_1.add(options);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Employee Satus");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_2_1_1.setBounds(24, 180, 208, 32);
		panel_1.add(lblNewLabel_2_1_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1_1.setBackground(new Color(100, 149, 237));
		panel_1_1.setBounds(25, 670, 1162, 82);
		panel.add(panel_1_1);
		
		JButton reset = new JButton("Clear");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				eId.setText("");
				eName.setText("");			//Resetting the fields 
				eEmail.setText("");
				options.setSelectedItem("Select an option");
				
			}
		});
		reset.setFont(new Font("Tahoma", Font.PLAIN, 16));
		reset.setBounds(509, 21, 104, 39);
		panel_1_1.add(reset);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1_1_1.setBackground(new Color(100, 149, 237));
		panel_1_1_1.setBounds(589, 160, 598, 499);
		panel.add(panel_1_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 578, 477);
		panel_1_1_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				//Once you click the the table row table values displaying in relevant fields
				
				eId.setText(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
				eName.setText(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
				options.setSelectedItem(table.getModel().getValueAt(table.getSelectedRow(), 2).toString());
				eEmail.setText(table.getModel().getValueAt(table.getSelectedRow(), 3).toString());
				
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setLayout(null);
		panel_1_1_2.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1_1_2.setBackground(new Color(100, 149, 237));
		panel_1_1_2.setBounds(25, 24, 1162, 82);
		panel.add(panel_1_1_2);
		
		JLabel lblNewLabel = new JLabel("Employee Managing System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblNewLabel.setBounds(258, 11, 664, 54);
		panel_1_1_2.add(lblNewLabel);
		
		/**
		 * once user click the back button 
		 * action listener listen to the action and performing the action 
		 */
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminDash fdb = new AdminDash();
				fdb.setVisible(true);
				fdb.setLocationRelativeTo(null);
				fdb.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				EditEmployee.this.dispose();
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setBounds(35, 117, 88, 27);
		panel.add(btnBack);
	}
}
