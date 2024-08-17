import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Profile extends JFrame {

	private JPanel contentPane;
	private JTextField fname_txtField;
	private JTextField lname_txtField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	
	/**
	 * Create the frame.
	 */
	public Profile() {
		connection=sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setBounds(84, 101, 70, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setBounds(84, 145, 70, 14);
		contentPane.add(lblLastName);
		
		fname_txtField = new JTextField();
		fname_txtField.setBounds(164, 98, 111, 20);
		contentPane.add(fname_txtField);
		fname_txtField.setColumns(10);
		
		lname_txtField = new JTextField();
		lname_txtField.setBounds(164, 142, 111, 20);
		contentPane.add(lname_txtField);
		lname_txtField.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					// pid, fname, lname, miles
					
					// find the largest pid, return 1 row only
					String query = "SELECT P1.passenger_id FROM Passenger P1 " + 
							"WHERE P1.passenger_id IN (SELECT MAX(P2.passenger_id) FROM Passenger P2);";
					
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();

					String fname = fname_txtField.getText();
					String lanme = lname_txtField.getText();
					
					if (rs.next()) {
						int current_pid = rs.getInt("passenger_id");
						System.out.println(rs.getInt("passenger_id"));
						int new_pid = current_pid + 1;
						query = "INSERT INTO Passenger VALUES(" + new_pid + ",'" + fname + "','" + lanme + "'," + 0 +");";
						pst = connection.prepareStatement(query);
						pst.execute();
						System.out.println("New passenger saved to db");
					}else{
						// there is no passenger in db, give pid = 0
						System.out.println("No rows returned/ No passenger data in db");
						query = "INSERT INTO Passenger VALUES(" + 0 + ",'" + fname + "','" + lanme + "'," + 0 +");";
						pst = connection.prepareStatement(query);
						pst.execute();
						System.out.println("New passenger saved to db");	
					}
					pst.close();
					rs.close();
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSubmit.setBounds(203, 214, 91, 23);
		contentPane.add(btnSubmit);
		
		JButton cancelBtn = new JButton("cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Options opt = new Options();
				opt.setVisible(true);
			}
		});
		cancelBtn.setBounds(308, 214, 91, 23);
		contentPane.add(cancelBtn);
		
		JLabel lblPassengerProfile = new JLabel("Passenger Profile");
		lblPassengerProfile.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblPassengerProfile.setBounds(33, 23, 270, 48);
		contentPane.add(lblPassengerProfile);
	}
}
