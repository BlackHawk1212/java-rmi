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
 * Edit_admin class
 * @author Nimesh Mendis
 *
 */

public class EditAdmin extends JFrame {

	//Assigning and initializing the variables
	private static final long serialVersionUID = 2610531770839416568L;
	private JPanel contentPane;
	private JTextField aId;
	private JTextField name;
	private JTextField pwd;
	private JTable table;

	DBConnection db = new DBConnection(); //Creating new DBConnection object

	/**
	 * Launch the application.
	 * @param args not used
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditAdmin frame = new EditAdmin();
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

	/**
	 * Implementing the CRUD functions
	 */

	//Create function
	private void InsertData() {

		Connection cn = connection();		//Creating new connection object
		try {
			String query = "INSERT INTO `login` values (?,?,?)";
			PreparedStatement ps = cn.prepareStatement(query);			//executing the query and retrieving the data 
			ps.setString(1, aId.getText());
			ps.setString(2, name.getText());		//Inserting the values which taking from the text field IDs
			ps.setString(3, pwd.getText());
			ps.execute();

			JOptionPane.showMessageDialog(null, "Admin data Inserted Successfully...");		//Displaying successful message
			ViewData();				//refresh the table
			aId.setText("");
			name.setText("");		//Clearing the fields
			pwd.setText("");


		}catch(Exception ex) {
			System.out.println("error: " + ex);		//catching any exception errors
		}

	}

	/**
	 * Retrieve function
	 */

	private void ViewData() {

		Connection cn = connection();						//Creating new connection object
		DefaultTableModel model = new DefaultTableModel();		//creating a new default table model
		model.addColumn("Admin ID");
		model.addColumn("Admin Name");		//Creating table columns to the table
		model.addColumn("Password");
		try {					
			String query = "SELECT * FROM `login`";		//Getting data from the database
			Statement stat = cn.createStatement();			//Creating new SQL statement
			ResultSet rs = stat.executeQuery(query);		//executing the query and retrieving the data 
			while(rs.next()) {
				model.addRow(new Object[]{
						rs.getInt("adminId"),		//Getting text data from database table and showing in the UI table
						rs.getString("adminName"),
						rs.getString("adminPw")
				});

			}
			rs.close();			//closing the query
			stat.close();		//closing the created statement
			cn.close();		//Closing the connection

			table.setModel(model);			//setting the table model
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(100);
			table.getColumnModel().getColumn(1).setPreferredWidth(200);		//Setting the table column width sizes
			table.getColumnModel().getColumn(2).setPreferredWidth(300);

		}catch(Exception ex) {
			System.out.println("Error: " + ex);		//catching any exception errors
		}

	}

	/**
	 * Update function
	 * @param adminId
	 */
	
	private void Update(String adminId) {

		Connection cn = connection();		//Creating new connection object
		try {
			String query = "UPDATE `login` SET adminName = ?, adminPw = ? WHERE adminId = ?";		//Updating the table 
			PreparedStatement ps = cn.prepareStatement(query);			//executing the query and retrieving the data 

			ps.setString(1, name.getText());		//getting the text from relevant text field IDs
			ps.setString(2, pwd.getText());			
			ps.setString(3, adminId);
			ps.executeUpdate();			//executing update method

			ps.close();		//closing the prepared statement
			cn.close();		//closing the connection

			JOptionPane.showMessageDialog(null, "Admin data Updated Successfully...");		//Showing successful message
			ViewData();																	//refresh the table
			aId.setText("");
			name.setText("");															//resetting and clear the fields
			pwd.setText("");

		}catch (Exception ex) {
			System.out.println("Error: " + ex);				//catching exception errors
		}

	}

	/**
	 * Delete function
	 * @param adminId
	 */
	
	private void Delete(String adminId) {

		Connection cn = connection();		//Creating new connection object
		try {
			String query = "DELETE FROM `login` WHERE adminId = ?";			//Delete data from the database
			PreparedStatement ps = cn.prepareStatement(query);		//executing the query and retrieving the data 
			ps.setString(1, adminId);
			ps.execute();			//execute delete method

			ps.close();			//closing the prepared statement
			cn.close();			//closing the connection

			JOptionPane.showMessageDialog(null, "Admin Deleted Successfully...");			//Showing successful message
			ViewData();																	//refresh the table
			aId.setText("");
			name.setText("");														//resetting and clear the fields
			pwd.setText("");

		}catch (Exception e) {
			System.out.println("error: " + e);				//catching exception errors
		}

	}

	/**
	 * Create the frame.
	 */
	
	public EditAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1227, 805);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(0, 0, 1210, 768);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1.setBackground(new Color(100, 149, 237));
		panel_1.setBounds(25, 160, 554, 499);
		panel.add(panel_1);

		JLabel lblNewLabel_1 = new JLabel("Admin Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(24, 21, 220, 31);
		panel_1.add(lblNewLabel_1);

		aId = new JTextField();
		aId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		aId.setColumns(10);
		aId.setBounds(239, 19, 251, 33);
		panel_1.add(aId);

		JLabel lblNewLabel_2 = new JLabel("Admin Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_2.setBounds(24, 78, 176, 32);
		panel_1.add(lblNewLabel_2);

		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		name.setColumns(10);
		name.setBounds(24, 121, 501, 39);
		panel_1.add(name);

		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				InsertData();	//Calling the Create function

			}
		});
		add.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add.setBounds(32, 419, 104, 39);
		panel_1.add(add);

		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Delete(aId.getText());	//Calling the Delete function

			}
		});
		delete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		delete.setBounds(229, 419, 104, 39);
		panel_1.add(delete);

		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Update(aId.getText());	//Calling the Update function

			}
		});
		update.setFont(new Font("Tahoma", Font.PLAIN, 16));
		update.setBounds(419, 419, 104, 39);
		panel_1.add(update);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1_1.setBackground(new Color(100, 149, 237));
		panel_1_1.setBounds(25, 670, 1162, 76);
		panel.add(panel_1_1);

		JButton reset = new JButton("Clear");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				aId.setText("");
				name.setText("");		//Clearing fields once you perform this action
				pwd.setText("");

			}
		});
		reset.setFont(new Font("Tahoma", Font.PLAIN, 16));
		reset.setBounds(321, 19, 104, 39);
		panel_1_1.add(reset);

		JButton exit = new JButton("Exit");
		exit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		exit.setBounds(1027, 23, 89, 31);
		panel_1_1.add(exit);

		JButton view = new JButton("View");
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ViewData();		//Calling Retrieve function

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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 578, 477);
		panel_1_1_1.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				//Once you click the the table row table values displaying in relevant fields

				aId.setText(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
				name.setText(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
				pwd.setText(table.getModel().getValueAt(table.getSelectedRow(), 2).toString());

			}
		});
		scrollPane.setViewportView(table);

		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setLayout(null);
		panel_1_1_2.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1_1_2.setBackground(new Color(100, 149, 237));
		panel_1_1_2.setBounds(25, 24, 1162, 82);
		panel.add(panel_1_1_2);

		JLabel lblAdminManagingSystem = new JLabel("Admin Management Section");
		lblAdminManagingSystem.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblAdminManagingSystem.setBounds(273, 11, 686, 54);
		panel_1_1_2.add(lblAdminManagingSystem);

		/**
		 * once user click the back button 
		 * action listener listen to the action and performing the action 
		 */
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				AdminDash q = new AdminDash();
				q.setVisible(true);
				q.setLocationRelativeTo(null);
				q.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				EditAdmin.this.dispose();

			}

		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setBounds(35, 117, 88, 27);
		panel.add(btnBack);

		JLabel lblNewLabel_2_1 = new JLabel("Password");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_2_1.setBounds(24, 171, 176, 32);
		panel_1.add(lblNewLabel_2_1);

		pwd = new JTextField();
		pwd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pwd.setColumns(10);
		pwd.setBounds(24, 214, 501, 39);
		panel_1.add(pwd);
	}
}
