package project1;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import javax.swing.JTextPane;
import java.awt.Panel;

public class selectionscreen extends JPanel{

	private JFrame tittle;
	int max_value;
	int total_samples;
	int totalFramesRead;
	int numBytesRead;
	int numFramesRead;
	byte[] audioBytes;
	int frameLength;
	int bytesPerFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					selectionscreen window = new selectionscreen();
					window.tittle.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public selectionscreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		tittle = new JFrame();
		tittle.setTitle("Audio File Waveform Generator");
		tittle.setBounds(100, 100, 450, 300);
		tittle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tittle.getContentPane().setLayout(null);

		JLabel lblTotalNumberOf = new JLabel("Total number of samples");
		lblTotalNumberOf.setBounds(211, 219, 142, 16);
		tittle.getContentPane().add(lblTotalNumberOf);

		JLabel sample_text = new JLabel("0");
		sample_text.setBounds(363, 219, 61, 16);
		tittle.getContentPane().add(sample_text);
		
		Panel panel = new Panel();
		panel.setForeground(Color.RED);
		panel.setBackground(Color.BLACK);
		panel.setBounds(10, 10, 414, 203);
		tittle.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
				
		JLabel lblMaximumValue = new JLabel("Maximum Value");
		lblMaximumValue.setBounds(211, 238, 135, 16);
		tittle.getContentPane().add(lblMaximumValue);

		JLabel max_text = new JLabel("0");
		max_text.setBounds(363, 238, 61, 16);
		tittle.getContentPane().add(max_text);
		
		JButton btnNewButton = new JButton("Open file");
		btnNewButton.setBounds(10, 219, 94, 35);
		tittle.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.repaint();
			}
		});
		btnNewButton_1.setBounds(107, 219, 94, 35);
		tittle.getContentPane().add(btnNewButton_1);
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent arg0) {
				// Create a file chooser
				final JFileChooser fileChooser = new JFileChooser();
				// set current directory
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				// show up the dialog
				int result = fileChooser.showOpenDialog(null);
				// check if the user selects a file or not
				if (result == JFileChooser.APPROVE_OPTION) {
					// user selects a file
					File selectedFile = fileChooser.getSelectedFile();
					String filepath = selectedFile.getAbsolutePath();
					System.out.println("Selected file: " + filepath);
					
					totalFramesRead = 0;

					// 1. get an audio stream from a file
					File file = new File(filepath);
					AudioInputStream audioInputStream = null;
					try {
						audioInputStream = AudioSystem.getAudioInputStream(file);
					} catch (UnsupportedAudioFileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 2. Create a byte array in which you'll store successive chunks of data from
					// the file.
					frameLength = (int) audioInputStream.getFrameLength();
					bytesPerFrame = (int) audioInputStream.getFormat().getFrameSize();
					audioBytes = new byte[frameLength * bytesPerFrame];
					try {
						numBytesRead = 0;
						numFramesRead = 0;

						// 3. Repeatedly read bytes from the audio input stream into the array.
						// On each iteration, do something useful with the bytes in the array
						// Try to read numBytes bytes from the file.
						while ((numBytesRead = audioInputStream.read(audioBytes)) != -1) {
							// Calculate the number of frames actually read.
							numFramesRead = numBytesRead / bytesPerFrame;
							totalFramesRead += numFramesRead;

							// Here, do something useful with the audio data that's
							// now in the audioBytes array...

							max_value = 0;
							total_samples = audioBytes.length / 2;
							Graphics g = panel.getGraphics();
							
							g.drawLine(0, 0, 0, panel.getHeight());//y-axis
						    g.drawLine(0, panel.getHeight() / 2, panel.getWidth(), panel.getHeight() / 2);//x-axis
						   
						    
						    //g.drawLine(20, 80, 20, 200);//y-axis
					        //g.drawLine(20, 200, 140, 200);//x-axis
						    //g.drawLine(x1 + 20, 200 - y1, x2 + 20, 200 - y2);//offset by coordinate system
							
							for (int counter = 1; counter < audioBytes.length; counter++) {
								if (audioBytes[counter] > max_value) {
									max_value = audioBytes[counter];
								}

								// draw line for each sample
								// x1, y1, x2, y2,
								g.drawLine(counter, panel.getHeight() / 2 - audioBytes[counter-1], counter+1, panel.getHeight() / 2 - audioBytes[counter]);
								panel.paintComponents(g);  
				
							}

							System.out.println("Max value = " + max_value);
							System.out.println("Total # of samples = " + total_samples);
							System.out.println("Total # of frames read = " + totalFramesRead);

							sample_text.setText("" + totalFramesRead);
							max_text.setText("" + max_value);	// max value = 127, # of samples/frames 29200, 63945
							
							
						} // closure for while loop
					} catch (Exception ex) {
						// Handle the error...
					}
				}
			}
		});
	}
}
