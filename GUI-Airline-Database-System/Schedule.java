import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Schedule extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private JTextField flightCode_txtField;
	private JTextField DepartDate_txtField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Schedule schedule = new Schedule();
					schedule.setVisible(true);
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
	public Schedule() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 99, 375, 151);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblFlightCode = new JLabel("Flight code");
		lblFlightCode.setBounds(30, 39, 74, 14);
		contentPane.add(lblFlightCode);
		
		flightCode_txtField = new JTextField();
		flightCode_txtField.setBounds(102, 36, 86, 20);
		contentPane.add(flightCode_txtField);
		flightCode_txtField.setColumns(10);
		
		JLabel lblDepartDate = new JLabel("Depart date");
		lblDepartDate.setBounds(30, 67, 74, 14);
		contentPane.add(lblDepartDate);
		
		DepartDate_txtField = new JTextField();
		DepartDate_txtField.setBounds(102, 64, 86, 20);
		contentPane.add(DepartDate_txtField);
		DepartDate_txtField.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose(); // hide the current frame
				Options opt = new Options();
				opt.setVisible(true);
			}
		});
		btnBack.setBounds(316, 65, 89, 23);
		contentPane.add(btnBack);
		
		JButton FindBtn = new JButton("Find");
		FindBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String fc2query = flightCode_txtField.getText();
					String dd2query = DepartDate_txtField.getText();
					String query = "SELECT passenger_id, first_name, last_name, miles FROM Passenger "
							+ "WHERE passenger_id IN (SELECT passenger_id FROM Booking "
							+ "WHERE flight_code = '" + fc2query + "' AND depart_date = '" + dd2query + "');";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					if (rs.next()) {
						table.setModel(DbUtils.resultSetToTableModel(rs));	
						query = "SELECT available_seats FROM Flight_instance "
							+ "WHERE flight_code = '" + fc2query + "' AND depart_date = '" + dd2query +"';";
						pst = connection.prepareStatement(query);
						rs = pst.executeQuery();
						if (rs.next()){
							JOptionPane.showMessageDialog(null, "Available Seats: " + rs.getInt("available_seats"));
						}
					}else {
						JOptionPane.showMessageDialog(null, "No such data found in the database");
					}
					rs.close();
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		FindBtn.setBounds(316, 35, 89, 20);
		contentPane.add(FindBtn);
	
	}
}
