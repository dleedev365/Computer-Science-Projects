package project2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class selectionscreen extends JPanel {

	private JFrame frmUmcompressedbmpImage;
	private JTextField R_field;
	private JTextField G_field;
	private JTextField B_field;
	BufferedImage img;
	int x_pixel, y_pixel;
	int R_value, G_value, B_value, A_value;
	File file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					selectionscreen window = new selectionscreen();
					window.frmUmcompressedbmpImage.setVisible(true);
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
		frmUmcompressedbmpImage = new JFrame();
		frmUmcompressedbmpImage.setTitle("Umcompressed .bmp Image File Reader");
		frmUmcompressedbmpImage.setBounds(100, 100, 622, 495);
		frmUmcompressedbmpImage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUmcompressedbmpImage.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 55, 456, 390);
		frmUmcompressedbmpImage.getContentPane().add(scrollPane);

		JLabel lblR = new JLabel("R:");
		lblR.setBounds(20, 24, 32, 20);
		frmUmcompressedbmpImage.getContentPane().add(lblR);

		R_field = new JTextField();
		R_field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				R_value = Integer.parseInt(R_field.getText());
			}
		});
		R_field.setBounds(51, 24, 86, 20);
		frmUmcompressedbmpImage.getContentPane().add(R_field);
		R_field.setColumns(10);

		JLabel lblG = new JLabel("G:");
		lblG.setBounds(147, 24, 32, 20);
		frmUmcompressedbmpImage.getContentPane().add(lblG);

		G_field = new JTextField();
		G_field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				G_value = Integer.parseInt(G_field.getText());
			}
		});
		G_field.setBounds(171, 24, 86, 20);
		frmUmcompressedbmpImage.getContentPane().add(G_field);
		G_field.setColumns(10);

		JLabel lblB = new JLabel("B:");
		lblB.setBounds(267, 24, 26, 20);
		frmUmcompressedbmpImage.getContentPane().add(lblB);

		B_field = new JTextField();
		B_field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				B_value = Integer.parseInt(B_field.getText());
			}
		});
		B_field.setBounds(291, 24, 86, 20);
		frmUmcompressedbmpImage.getContentPane().add(B_field);
		B_field.setColumns(10);

		JButton btnRgb = new JButton("RGB");
		btnRgb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int x = 0; x < x_pixel; x++) {
					for (int y = 0; y < y_pixel; y++) {

						// Get RGB Color on each pixel
						int p = img.getRGB(x, y);

						int a = (p >> 24) & 0xFF; // alpha
						int r = (p >> 16) & 0xFF; // red
						int g = (p >> 8) & 0xFF; // green
						int b = (p >> 0) & 0xFF; // blue

						//System.out.println("Original RGBA = (" + r + "," + g + "," + b + "," + a + ")");

						if (R_field.getText().equals("")) {
							r = R_value;
						}
						if (G_field.getText().equals("")) {
							g = G_value;
						}
						if (B_field.getText().equals("")) {
							b = B_value;
						}

						//System.out.println("RGBA = (" + r + "," + g + "," + b + "," + a + ")");
						// Set new RGB on each pixel
						p = (a << 24) | (r << 16) | (g << 8) | b;
						img.setRGB(x, y, p);

						// set icon
						JLabel wIcon = new JLabel(new ImageIcon(img));
						// alignment
						wIcon.setHorizontalAlignment(JLabel.CENTER);
						scrollPane.setViewportView(wIcon);
					}
				}

			}
		});
		btnRgb.setBounds(492, 212, 105, 50);
		frmUmcompressedbmpImage.getContentPane().add(btnRgb);

		JButton btnOpenFile = new JButton("Open file");
		btnOpenFile.addActionListener(new ActionListener() {
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

					// 1. get a .bmp image file
					file = new File(filepath);

					img = null;
					try {
						img = ImageIO.read(file);
					} catch (IOException e) {
						// handle error
						System.out.print("Image can't be read");
					}

					// pixels of the image
					y_pixel = img.getHeight();
					x_pixel = img.getWidth();

					// set icon
					JLabel wIcon = new JLabel(new ImageIcon(img));
					// alignment
					wIcon.setHorizontalAlignment(JLabel.CENTER);
					scrollPane.setViewportView(wIcon);
				}
			}
		});
		btnOpenFile.setBounds(492, 55, 105, 85);
		frmUmcompressedbmpImage.getContentPane().add(btnOpenFile);

		JButton btnBrightness = new JButton("Brightness");
		btnBrightness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double avg = 0;
				double count = 0;
				for (int x = 0; x < x_pixel; x++) {
					for (int y = 0; y < y_pixel; y++) {
						// Get RGB Color on each pixel
						Color c = new Color(img.getRGB(x, y));
						int r = c.getRed();
						int g = c.getGreen();
						int b = c.getBlue();
						int a = c.getAlpha();

						// change brightness 1.5 times brighter
						// from Oracle
						// luminance of each pixel
						float lum = (0.2126f * r) + (0.7152f * g) + ((0.0722f + 0.015f) * b);
						avg += lum;
						count++;

						float hue = 0.2126f;
						// 0.2126f;
						float sat = 0.7152f;
						// 0.7152f;
						float bri = 0.0722f + 0.015f;

						float[] hsv = { hue, sat, bri };

						// original brightness for sample1.bmp is 93.38455
						float[] newC = Color.RGBtoHSB(r, g, b, hsv);
						int newRGB = Color.HSBtoRGB(newC[0] % 255f, newC[1] % 255f, newC[2] % 255f);

						img.setRGB(x, y, c.getRGB() + newRGB);

					}
				}
				avg = avg / count;
				System.out.println("The average luminacne:" + avg);

				// set icon
				JLabel wIcon = new JLabel(new ImageIcon(img));

				// alignment
				wIcon.setHorizontalAlignment(JLabel.CENTER);
				scrollPane.setViewportView(wIcon);

			}
		});
		btnBrightness.setBounds(492, 273, 105, 50);
		frmUmcompressedbmpImage.getContentPane().add(btnBrightness);

		JButton btnGrayscale = new JButton("Grayscale");
		btnGrayscale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int x = 0; x < x_pixel; x++) {
					for (int y = 0; y < y_pixel; y++) {
						// Get RGB Color on each pixel
						Color c = new Color(img.getRGB(x, y));
						int r = c.getRed();
						int g = c.getGreen();
						int b = c.getBlue();
						int a = c.getAlpha();

						// grayscaling
						int gr = (r + g + b) / 3;

						// Create graycolor
						Color gColor = new Color(gr, gr, gr, a);
						img.setRGB(x, y, gColor.getRGB());
					}
				}

				// set icon
				JLabel wIcon = new JLabel(new ImageIcon(img));
				// alignment
				wIcon.setHorizontalAlignment(JLabel.CENTER);
				scrollPane.setViewportView(wIcon);

			}
		});
		btnGrayscale.setBounds(492, 334, 105, 50);
		frmUmcompressedbmpImage.getContentPane().add(btnGrayscale);

		JButton btnDithering = new JButton("Dithering");
		btnDithering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ordered diethering matrix
				/*
				// 4x4
				int[][] ditherM = { 
						{ 0, 8, 2, 10 }, 
						{ 12, 4, 14, 6 }, 
						{ 3, 11, 1, 9 }, 
						{ 15, 7, 13, 5 } 
						};
				*/
				/*
				// 3x 3
				int[][] ditherM = { 
						{ 0, 7, 3}, 
						{ 6, 5, 2}, 
						{ 4, 1, 8} 
						};
				*/
				
				// 2 x 2
				int[][] ditherM = {
						{0,2},
						{3,1}
				};
				
				/*
				// 8 x 8
				int[][] ditherM = { 
						{0,48,12,60,3,51,15,63},
						{32,16,44,28,35,19,47,31},
						{8,56,4,52,11,59,7,55},
						{40,24,36,20,43,27,39,23},
						{2,50,14,62,1,49,13,61},
						{34,18,46,30,33,17,45,29},
						{10,58,6,54,9,57,5,53},
						{42,26,38,22,41,25,37,21}
					};
				*/
				
				int i;
				int j;
				
				int[][] inputM = new int[x_pixel][y_pixel];
				int[][] outputM = new int[x_pixel][y_pixel];
				
				//initialize input
				for(int x =0; x < x_pixel; x++) { //columns
					for(int y=0; y<y_pixel; y++) { //rows
						//copy current img rgb
						inputM[x][y] = img.getRGB(x, y);
					}
				}
				
				
				for(int x =0; x < x_pixel; x++) { //columns
					for(int y=0; y < y_pixel; y++) { //rows
						i = x % 2; // n x n matrix
						j = y % 2; 
						
						//intputM(x,y) is the input, outputM(x,y) is the output
						// ditherM is the dither matrix
						
						if (  inputM[x][y] > ditherM[i][j]) {
							outputM[x][y] = 1;
						}else {
							outputM[x][y] = 0;
						}
						
						Color c = new Color(outputM[x][y]);
						int cr = c.getRed();
						int cg = c.getGreen();
						int cb = c.getBlue();
						int ca = c.getAlpha();
						//original
						Color oc = new Color(inputM[x][y]);
						int r = oc.getRed();
						int g = oc.getGreen();
						int b = oc.getBlue();
						int a = oc.getAlpha();

						img.setRGB(x, y, oc.getRGB() + c.getRGB());
						

						// set icon
						JLabel wIcon = new JLabel(new ImageIcon(img));
						// alignment
						wIcon.setHorizontalAlignment(JLabel.CENTER);
						scrollPane.setViewportView(wIcon);
					}
				}
				System.out.println(inputM.toString());
				System.out.println(outputM.toString());
			}
		});
		btnDithering.setBounds(492, 395, 105, 50);
		frmUmcompressedbmpImage.getContentPane().add(btnDithering);

		JButton btnNewButton = new JButton("Original");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 1. get a .bmp image file
				img = null;
				try {
					img = ImageIO.read(file);
				} catch (IOException e1) {
					// handle error
					System.out.print("Image can't be read");
				}

				// set icon
				JLabel wIcon = new JLabel(new ImageIcon(img));
				// alignment
				wIcon.setHorizontalAlignment(JLabel.CENTER);
				scrollPane.setViewportView(wIcon);

			}
		});
		btnNewButton.setBounds(492, 151, 105, 50);
		frmUmcompressedbmpImage.getContentPane().add(btnNewButton);

	}
}
