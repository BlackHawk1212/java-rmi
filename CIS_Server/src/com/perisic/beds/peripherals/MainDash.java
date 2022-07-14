package com.perisic.beds.peripherals;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

/**
 * Main dash class
 * @author Nimesh Mendis
 *
 */

public class MainDash extends JFrame {

	private static final long serialVersionUID = 2787715264581114470L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @param args not used
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainDash frame = new MainDash();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public MainDash() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("MainUI");
		setBounds(100, 100, 842, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 826, 356);
		panel.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel.setBackground(new Color(100, 149, 237));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setBounds(21, 20, 766, 107);
		panel_1_1_2.setLayout(null);
		panel_1_1_2.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1_1_2.setBackground(new Color(100, 149, 237));
		panel.add(panel_1_1_2);
		
		JLabel lblFeedback = new JLabel("Sri Lanka Telecom");
		lblFeedback.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblFeedback.setBounds(193, 11, 416, 54);
		panel_1_1_2.add(lblFeedback);
		
		JLabel lblNewLabel = new JLabel("Feel free to fill this questionnaire to improve our employee satisfaction");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(155, 65, 507, 21);
		panel_1_1_2.add(lblNewLabel);
		
		/**
		 * once user click the back button 
		 * mouse listener listen to the action and performing the action 
		 */
		
		JButton login = new JButton("Admin Login");
		login.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminLogin adminlog = new AdminLogin();
				adminlog.setVisible(true);
				adminlog.setLocationRelativeTo(null);
				adminlog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				MainDash.this.dispose();
			}
			
		});
		login.setFont(new Font("Tahoma", Font.PLAIN, 30));
		login.setBounds(475, 248, 221, 45);
		panel.add(login);
		
		/**
		 * once user click the back button 
		 * action listener listen to the action and performing the action 
		 */
		
		JButton feedback = new JButton("Feedback");
		feedback.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Feedback fdb = new Feedback();
				fdb.setVisible(true);
				fdb.setLocationRelativeTo(null);
				fdb.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				MainDash.this.dispose();
			}
			
		});
		feedback.setFont(new Font("Tahoma", Font.PLAIN, 30));
		feedback.setBounds(102, 248, 181, 45);
		panel.add(feedback);
	}
}
