package com.perisic.beds.peripherals;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

/**
 * Admin_login UI
 * in here user has to input their user name and password in relevant fields
 * @author Nimesh Mendis
 */
public class AdminLogin extends JFrame {
	
	/**
	 * Assigning and initializing the variables
	 */
	
	private static final long serialVersionUID = -4039190054916137576L;
	private JPanel contentPane;				//content pane
	private JPasswordField passwordField;	//password field
	private JTextField usernameField;		//user name field

	/**
	 * Launch the application.
	 * @param args not used
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Window event method implemented in here
	 */
	
	private void systemExit() {
		WindowEvent winClose = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
	}
	
	/**
	 * Create the frame.
	 */
	
	public AdminLogin() {
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel.setBounds(0, 0, 665, 413);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1.setBackground(new Color(100, 149, 237));
		panel_1.setBounds(20, 21, 624, 98);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label1 = new JLabel("Welcome to Sri Lanka Telecom");
		label1.setFont(new Font("Tahoma", Font.BOLD, 35));
		label1.setBounds(41, 30, 551, 37);
		panel_1.add(label1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 15));
		passwordField.setBounds(224, 246, 311, 30);
		panel.add(passwordField);
		
		JLabel label3 = new JLabel("Password:");
		label3.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label3.setBounds(64, 243, 115, 30);
		panel.add(label3);
		
		/**
		 * Once user click the login button action listener listen to the button click
		 * and performing the action
		 */
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			
			//This is the login function
			//Single factor authentication
			//This is the validation part of the login 
			
			@SuppressWarnings("deprecation")//For security reasons, this method is deprecated. password field
			public void actionPerformed(ActionEvent e) {
				String user = usernameField.getText();	//user_admin name is getting from the text field and assign to a variable
				String pass = passwordField.getText();	//user_admin password is getting from the password field and assign to a variable
				
				//Once you try login with empty fields
				if(user.isEmpty() && pass.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please enter Login Credentials", "Login Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else {
					//Once you type the credential and new admin_Login_Process object will calling the method
					AdminLoginProcess lp = new AdminLoginProcess();
					lp.setUsername(user);
		            lp.setPassword(pass);
		            
		            //Once it is true you can proceed forward to Admin_Dash
		            try {
		            	if(lp.userAuthentication() == true) {
		            		
		            		JOptionPane.showMessageDialog(null, "Hello Admin, Welcome back!!!");
		            		AdminDash fdb = new AdminDash();
		    				fdb.setVisible(true);
		    				fdb.setLocationRelativeTo(null);
		    				fdb.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    				AdminLogin.this.dispose();
		    				
		            	}
		            	//Showing an error message once you entered wrong credentials
		            	else if(lp.userAuthentication() == false)
		                {
		            		
		                    JOptionPane.showMessageDialog(null, "Invalid Username and Password!!! Re-try");
		                    passwordField.setText("");
		                    
		                }
		                else
		                	//this will pop-up is there any database connection error or any other server connection error
		                {
		                	
		                    JOptionPane.showMessageDialog(null, "Something went wrong!!! please try again");
		                    
		                }
		            }
		            catch (SQLException ex) {
		                
		                JOptionPane.showMessageDialog(null, ex.getMessage());
		                
		            }
		            
				}
			}
			
		});
		
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnLogin.setBounds(178, 322, 133, 45);
		panel.add(btnLogin);
		
		JLabel label2 = new JLabel("User Name:");
		label2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label2.setBounds(47, 184, 126, 30);
		panel.add(label2);
		
		/**
		 * once you click the button 
		 * it clear all the fields 
		 */
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				usernameField.setText("");
				passwordField.setText("");
			}
			
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnReset.setBounds(402, 322, 133, 45);
		panel.add(btnReset);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.BOLD, 15));
		usernameField.setBounds(225, 184, 310, 30);
		panel.add(usernameField);
		usernameField.setColumns(10);
		
		/**
		 * Once you click the back button 
		 * it will direct to the main dash UI
		 */
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				MainDash fdb = new MainDash();
				fdb.setVisible(true);
				fdb.setLocationRelativeTo(null);
				fdb.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				AdminLogin.this.dispose();
				systemExit();	//Calling the window event action
			}
			
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setBounds(30, 130, 89, 27);
		panel.add(btnBack);
	}
	
}

