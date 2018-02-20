package TeamNumber3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Component;
import javax.swing.Box;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class AdminView {

	private JFrame frame;
	private JTextField txtSearchHeading;
	private JTextField txtFileName;
	private JTextField txtStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminView window = new AdminView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 456, 451);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		txtSearchHeading = new JTextField();
		txtSearchHeading.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtSearchHeading.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearchHeading.setText("File Search System Administration");
		frame.getContentPane().add(txtSearchHeading, BorderLayout.NORTH);
		txtSearchHeading.setColumns(10);
		
		txtFileName = new JTextField();
		txtFileName.setHorizontalAlignment(SwingConstants.CENTER);
		txtFileName.setText("File Name");
		frame.getContentPane().add(txtFileName, BorderLayout.WEST);
		txtFileName.setColumns(10);
		
		txtStatus = new JTextField();
		txtStatus.setHorizontalAlignment(SwingConstants.CENTER);
		txtStatus.setText("Status");
		frame.getContentPane().add(txtStatus, BorderLayout.EAST);
		txtStatus.setColumns(10);
	}

}
