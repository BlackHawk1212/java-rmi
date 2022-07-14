package com.perisic.beds.peripherals;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.perisic.beds.questionnaire.QuestionSet;

/**
 * Admin_dash board. This interface responsible for 
 * Edit_question, Edit_admin, Edit_employees in database 
 * also you can generate and view the bar chart and pie chart
 * @author Nimesh Mendis
 *
 */
public class AdminDash extends JFrame {

	private QuestionSet questionnaire = new QuestionSet(); //creating new question set object

	/**
	 * Assigning and initializing the variables
	 */
	
	private static final long serialVersionUID = 1130033892317089076L;
	protected static final AbstractButton errorStatus = null;
	private JPanel contentPane;		//content pane

	/**
	 * Launch the application.
	 * @param args not used
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDash frame = new AdminDash();
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
	
	public AdminDash() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1056, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel.setForeground(new Color(0, 0, 139));
		panel.setBounds(0, 0, 1040, 611);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(0, 0, 139));
		panel_1.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1.setBackground(new Color(100, 149, 237));
		panel_1.setBounds(21, 27, 998, 87);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel headlbl = new JLabel("Hello Admin, Welcome back!");
		headlbl.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 40));
		headlbl.setBounds(229, 11, 560, 58);
		panel_1.add(headlbl);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setForeground(new Color(0, 0, 139));
		panel_1_1.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1_1.setBackground(new Color(100, 149, 237));
		panel_1_1.setBounds(21, 160, 348, 429);
		panel.add(panel_1_1);
		panel_1_1.setLayout(null);

		/**
		 * Edit_question window event is handle in here. 
		 * Once you click the edit questions button, mouse clicked listen to the action and directing to the Edit_question UI
		 */
		
		JButton btnQuestionnaire = new JButton("Edit Questions");	
		btnQuestionnaire.addMouseListener(new MouseAdapter() {		

			@Override
			public void mouseClicked(MouseEvent e) {
				EditQuestion q = new EditQuestion();
				q.setVisible(true);
				q.setLocationRelativeTo(null);
				q.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				AdminDash.this.dispose();
			}

		});
		btnQuestionnaire.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnQuestionnaire.setBounds(64, 274, 231, 45);
		panel_1_1.add(btnQuestionnaire);

		JLabel lblNewLabel_1_1 = new JLabel("DATABASE");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(30, 233, 102, 26);
		panel_1_1.add(lblNewLabel_1_1);

		/**
		 * Generating and viewing feedback charts handle in here
		 * once you click the button action listener listen to the button click and displaying a dialog box
		 * we can choose either bar chart or pie chart since there are two options available
		 * once you click any type of chart selected value calling relevant method to display the charts
		 */
		
		JButton btnViewFeedback = new JButton("View Feedback");
		btnViewFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource().equals(btnViewFeedback)) {
					String[] options = {"Bar Chart", "Pie Chart"};
					int selectedValue = JOptionPane.showOptionDialog(rootPane, "Which graph do you want to display?", "Select Graph Type", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(selectedValue == 0) {
						questionnaire.reportBarChart(); 
					}else {
						questionnaire.reportPieChart(); 
					}
				}
			}

		});
		btnViewFeedback.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnViewFeedback.setBounds(51, 346, 258, 45);
		panel_1_1.add(btnViewFeedback);

		JLabel lblNewLabel_1_1_1 = new JLabel("ADMIN");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(30, 40, 102, 26);
		panel_1_1.add(lblNewLabel_1_1_1);
		
		/**
		 * Edit_admin window event is handle in here. 
		 * Once you click the edit_admin button, action listener listen to the action and directing to the Edit_admin UI
		 * since this is an restricted section UI showing an warning message to proceed
		 */

		JButton adminpanel = new JButton("Edit Admin");
		adminpanel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if(JOptionPane.showConfirmDialog(adminpanel, "Restricted Section, Do you want to proceed", "Security Warning", 
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					EditAdmin q = new EditAdmin();
					q.setVisible(true);
					q.setLocationRelativeTo(null);
					q.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					AdminDash.this.dispose();
				}

			}

		});
		adminpanel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		adminpanel.setBounds(51, 77, 258, 45);
		panel_1_1.add(adminpanel);

		/**
		 * Edit_employee window event is handle in here. 
		 * Once you click the edit employee button, action listener listen to the action and directing to the Edit_employee UI
		 * In here user_admin only can view and update available employee information 
		 */
		
		JButton btnEditEmployees = new JButton("Edit Employees");
		btnEditEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				EditEmployee fdb = new EditEmployee();
				fdb.setVisible(true);
				fdb.setLocationRelativeTo(null);
				fdb.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				AdminDash.this.dispose();

			}
		});
		btnEditEmployees.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnEditEmployees.setBounds(51, 149, 258, 45);
		panel_1_1.add(btnEditEmployees);

		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setForeground(new Color(0, 0, 139));
		panel_1_1_1.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(0, 0, 139)));
		panel_1_1_1.setBackground(new Color(100, 149, 237));
		panel_1_1_1.setBounds(379, 160, 640, 429);
		panel.add(panel_1_1_1);

		/**
		 * Once you finish, you have to logout from the dash board. 
		 * Once you click the logout button mouse clicked listen to the action and directing to the main dash UI
		 * once you logout you have to login once again 
		 */
		
		JButton logout = new JButton("Logout");
		logout.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if(JOptionPane.showConfirmDialog(adminpanel, "Do you want to Logout", "SLT admin dashboard", 
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					MainDash md = new MainDash();
					md.setVisible(true);
					md.setLocationRelativeTo(null);
					md.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					AdminDash.this.dispose();

				}
			}

		});
		logout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		logout.setBounds(31, 125, 88, 27);
		panel.add(logout);
	}
}

