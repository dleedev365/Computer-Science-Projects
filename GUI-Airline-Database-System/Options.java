import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Options extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Options window = new Options();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;

	/**
	 * Initialize the contents of the frame.
	 */
	public Options() {
		setTitle("Airline Database System");
		connection=sqliteConnection.dbConnector();
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JButton btnCreateAProfile = new JButton("Create a profile");
		btnCreateAProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose(); // hide the current frame
				Profile profile = new Profile();
				profile.setVisible(true);
			}
		});
		
		btnCreateAProfile.setBounds(110, 47, 231, 55);
		contentPane.add(btnCreateAProfile);
		
		JButton btnNewButton = new JButton("View Flights");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Schedule schedule = new Schedule();
				schedule.setVisible(true);
			}
		});
		btnNewButton.setBounds(110, 113, 231, 55);
		contentPane.add(btnNewButton);
		
		JButton btnBookAFlight = new JButton("Book a flight");
		btnBookAFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Book book = new Book();
				book.setVisible(true);
			}
		});
		btnBookAFlight.setBounds(110, 179, 231, 55);
		contentPane.add(btnBookAFlight);
	}
}
