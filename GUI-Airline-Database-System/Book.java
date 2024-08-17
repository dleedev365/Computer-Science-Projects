import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class Book extends JFrame {

	private JPanel contentPane;
	private JTextField pid_txtField;
	private JTextField A_flightcode_txtField;
	private JTextField A_departdate_txtField;
	private JTextField B_flightcode_txtField;
	private JTextField B_departdate_txtField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book frame = new Book();
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
	public Book() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPassengerId = new JLabel("Passenger ID");
		lblPassengerId.setBounds(22, 47, 72, 14);
		contentPane.add(lblPassengerId);
		
		pid_txtField = new JTextField();
		pid_txtField.setBounds(104, 44, 86, 20);
		contentPane.add(pid_txtField);
		pid_txtField.setColumns(10);
		
		JRadioButton SingleTripbtn = new JRadioButton("Single trip");
		SingleTripbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A_flightcode_txtField.setEnabled(true);
				A_departdate_txtField.setEnabled(true);
				B_flightcode_txtField.setEnabled(false);
				B_departdate_txtField.setEnabled(false);
			}
		});
		SingleTripbtn.setBounds(104, 71, 86, 23);
		contentPane.add(SingleTripbtn);
		
		JRadioButton RoundTripbtn = new JRadioButton("Round trip");
		RoundTripbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A_flightcode_txtField.setEnabled(true);
				A_departdate_txtField.setEnabled(true);
				B_flightcode_txtField.setEnabled(true);
				B_departdate_txtField.setEnabled(true);
			}
		});
		RoundTripbtn.setBounds(195, 71, 101, 23);
		contentPane.add(RoundTripbtn);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(SingleTripbtn);
		buttonGroup.add(RoundTripbtn);
		
		JLabel lblNewLabel = new JLabel("Type of Travel");
		lblNewLabel.setBounds(22, 72, 72, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblFlightCode = new JLabel("Flight code");
		lblFlightCode.setBounds(22, 126, 72, 14);
		contentPane.add(lblFlightCode);
		
		JLabel lblDepartdate = new JLabel("Depart_date");
		lblDepartdate.setBounds(202, 126, 72, 14);
		contentPane.add(lblDepartdate);
		
		A_flightcode_txtField = new JTextField();
		A_flightcode_txtField.setBounds(104, 123, 86, 20);
		contentPane.add(A_flightcode_txtField);
		A_flightcode_txtField.setColumns(10);
		A_flightcode_txtField.setEnabled(false);
		
		A_departdate_txtField = new JTextField();
		A_departdate_txtField.setBounds(284, 123, 86, 20);
		contentPane.add(A_departdate_txtField);
		A_departdate_txtField.setColumns(10);
		A_departdate_txtField.setEnabled(false);
		
		JLabel label = new JLabel("Flight code");
		label.setBounds(22, 176, 72, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Depart_date");
		label_1.setBounds(202, 176, 72, 14);
		contentPane.add(label_1);
		
		B_flightcode_txtField = new JTextField();
		B_flightcode_txtField.setBounds(104, 173, 86, 20);
		contentPane.add(B_flightcode_txtField);
		B_flightcode_txtField.setColumns(10);
		B_flightcode_txtField.setEnabled(false);
		
		B_departdate_txtField = new JTextField();
		B_departdate_txtField.setBounds(284, 176, 86, 20);
		contentPane.add(B_departdate_txtField);
		B_departdate_txtField.setColumns(10);
		B_departdate_txtField.setEnabled(false);
		
		JLabel lblDestinationA = new JLabel("<Destination A>");
		lblDestinationA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDestinationA.setBounds(22, 101, 107, 14);
		contentPane.add(lblDestinationA);
		
		JLabel lblDestinationB = new JLabel("<Destination B>");
		lblDestinationB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDestinationB.setBounds(22, 151, 107, 14);
		contentPane.add(lblDestinationB);
		
		JButton btnBook = new JButton("Book");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean is_id_valid = false;
					boolean is_seat_valid = false;
					boolean is_flight_A_valid = false;
					boolean is_flight_B_valid = false;
					boolean is_date_valid = false;
					int pid = Integer.parseInt( pid_txtField.getText() );
					String depart_date1 = A_departdate_txtField.getText();
					String depart_date2 = B_departdate_txtField.getText();
					String flight_code1 = A_flightcode_txtField.getText();
					String flight_code2 = B_flightcode_txtField.getText();
				
					
					// check for passenger_id 
					String query = "SELECT passenger_id FROM Passenger WHERE passenger_id = " + pid + ";";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()) {
						is_id_valid = true;
						System.out.println("Passenger_id: " + rs.getInt("passenger_id") );
					}else {
						is_id_valid = false;
					}
					
					// check if flight_instance exists
					query = "SELECT flight_code FROM Flight_Instance " + 
							"WHERE flight_code = '" + flight_code1 + "' AND depart_date = '" + depart_date1 + "';";
					pst = connection.prepareStatement(query);
					rs = pst.executeQuery();
					
					if(rs.next()) {
						is_flight_A_valid = true;
					}
					else {
						is_flight_A_valid = false;
					}
				
					// check availability of seats for the flight instances
					// available seats > 0
					// if false, message "The tickets are sold out for this flight"
	
					query = "SELECT available_seats FROM Flight_Instance " + 
							"WHERE flight_code = '" + flight_code1 + "' AND depart_date = '" + depart_date1 + "';";
					pst = connection.prepareStatement(query);
					rs = pst.executeQuery();
					
					if(rs.next()) {
						is_seat_valid = true;
						System.out.println("Available seats: " + rs.getInt("available_seats") );
						if (rs.getInt("available_seats") == 0) {
							JOptionPane.showMessageDialog(null, "The tickets are sold out for " + flight_code1 + " flight");
							is_seat_valid = false;
						}
					}
					else {
						is_seat_valid = false;
					}
						
					// for round trip
					if (RoundTripbtn.isSelected()) {
						// check if flight_instance exists
						query = "SELECT flight_code FROM Flight_Instance " + 
								"WHERE flight_code = '" + flight_code2 + "' AND depart_date = '" + depart_date2 + "';";
						pst = connection.prepareStatement(query);
						rs = pst.executeQuery();
						
						if(rs.next()) {
							is_flight_B_valid = true;
						}
						else {
							is_flight_B_valid = false;
						}
						
						
						query = "SELECT available_seats FROM Flight_Instance " + 
								"WHERE flight_code = '" + flight_code2 + "' AND depart_date = '" + depart_date2 + "';";
						pst = connection.prepareStatement(query);
						rs = pst.executeQuery();
						
						if(rs.next()) {
							is_seat_valid = true;
							System.out.println("Available seats: " + rs.getInt("available_seats") );
							if (rs.getInt("available_seats") == 0) {
								JOptionPane.showMessageDialog(null, "The tickets are sold out for " + flight_code2 + " flight");
								is_seat_valid = false;
							}
						}else {
							is_seat_valid = false;
						}
						
						// check depart date of round trip
						// first leg depart date should be earlier than depart date of the second leg
						// For a round trip, insertion of two legs must be performed in one transaction
						// both succeed or fail
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
						
						java.util.Date A_departdate = format.parse(depart_date1);
						java.util.Date B_departdate = format.parse(depart_date2);
						
						// B_departdate is no earlier than A_departdate
						if (  B_departdate.after(A_departdate) || B_departdate.equals(A_departdate)){
							if (A_departdate.before(B_departdate) || A_departdate.equals(A_departdate)){
								is_date_valid = true;
							}
						}
					} // closure for round trip condition loop
			
					
					System.out.println("ID valid: " + is_id_valid + "/Flight A valid: " + is_flight_A_valid 
							 + "/Flight B valid: " + is_flight_B_valid + "/Seat valid: " + is_seat_valid + "/Date valid: " + is_date_valid);
					

					if ( is_date_valid == true && is_id_valid == true && is_seat_valid == true && is_flight_A_valid == true && is_flight_B_valid == true && RoundTripbtn.isSelected()	) {
						System.out.print(flight_code1 + "-" + depart_date1 + "," + flight_code2 + "-" + depart_date2); 
						// insert a round trip to db {flight_code, depart_date, passenger_id}
						query = "INSERT INTO Booking VALUES('" + flight_code1 + "','" + depart_date1 + "'," + pid + ");";
						pst = connection.prepareStatement(query);
						pst.execute();
						query = "INSERT INTO Booking VALUES('" + flight_code2 + "','" + depart_date2 + "'," + pid + ");";
						pst = connection.prepareStatement(query);
						pst.execute();
						
						pst.close();
						rs.close();
						dispose();
						Options opt = new Options();
						opt.setVisible(true);
						
					}else if (is_id_valid == true && is_seat_valid == true && is_flight_A_valid == true && SingleTripbtn.isSelected()){
						System.out.print(flight_code1 + "-" + depart_date1);
						// insert a single trip to db
						query = "INSERT INTO Booking VALUES('" + flight_code1 + "','" + depart_date1 + "'," + pid + ");";
						pst = connection.prepareStatement(query);
						pst.execute();
						
						pst.close();
						rs.close();
						dispose();
						Options opt = new Options();
						opt.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "The passenger or flight instance does not exist");
						pid_txtField.setText("");
						A_departdate_txtField.setText("");
						B_departdate_txtField.setText("");
						A_flightcode_txtField.setText("");
						B_flightcode_txtField.setText("");
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBook.setBounds(236, 227, 89, 23);
		contentPane.add(btnBook);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Options opt = new Options();
				opt.setVisible(true);
			}
		});
		btnCancel.setBounds(335, 227, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblDateFormat = new JLabel("* date format should be \"yyyy-mm-dd\"");
		lblDateFormat.setBounds(15, 11, 259, 25);
		contentPane.add(lblDateFormat);
	}
}
