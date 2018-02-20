package TeamNumber3;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class AdminView {

	JFrame frmFileSearchSystem;
	private JLabel txtSearchHeading;
	private JLabel txtFileName;
	private JLabel txtStatus;
	private JButton btnResetWindows;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
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
		frmFileSearchSystem = new JFrame();
		frmFileSearchSystem.setTitle("File Search System");
		frmFileSearchSystem.setResizable(false);
		frmFileSearchSystem.getContentPane().setBackground(Color.WHITE);
		frmFileSearchSystem.setBounds(100, 100, 448, 448);
		frmFileSearchSystem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmFileSearchSystem.getContentPane().setLayout(null);
		
		txtSearchHeading = new JLabel();
		txtSearchHeading.setBounds(0, 0, 440, 23);
		txtSearchHeading.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtSearchHeading.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearchHeading.setText("File Search System Administration");
		frmFileSearchSystem.getContentPane().add(txtSearchHeading);
		
		txtFileName = new JLabel();
		txtFileName.setBounds(0, 23, 220, 20);
		txtFileName.setHorizontalAlignment(SwingConstants.CENTER);
		txtFileName.setText("File Name");
		frmFileSearchSystem.getContentPane().add(txtFileName);
		
		txtStatus = new JLabel();
		txtStatus.setBounds(220, 23, 220, 20);
		txtStatus.setHorizontalAlignment(SwingConstants.CENTER);
		txtStatus.setText("Status");
		frmFileSearchSystem.getContentPane().add(txtStatus);
		
		JButton btnRemoveSelectedFiles = new JButton("Remove Selected Files");
		btnRemoveSelectedFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Method to be added when btnRemoveSelectedFiles is clicked.
			}
		});
		btnRemoveSelectedFiles.setBounds(253, 344, 151, 23);
		frmFileSearchSystem.getContentPane().add(btnRemoveSelectedFiles);
		
		JButton btnRebuild = new JButton("Rebuild");
		btnRebuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Method to be added when btnRebuild is clicked.
			}
		});
		btnRebuild.setBounds(139, 344, 89, 23);
		frmFileSearchSystem.getContentPane().add(btnRebuild);
		
		JButton btnAddFile = new JButton("Add File...");
		btnAddFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Method to be added when btnAddFile is clicked.
			}
		});
		btnAddFile.setBounds(25, 344, 89, 23);
		frmFileSearchSystem.getContentPane().add(btnAddFile);
		
		btnResetWindows = new JButton("Reset Windows");
		btnResetWindows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Method to be added when btnResetWindows is clicked.
			}
		});
		btnResetWindows.setBounds(33, 375, 107, 23);
		frmFileSearchSystem.getContentPane().add(btnResetWindows);
		
		JLabel lblNumberOfFiles = new JLabel("Number of files indexed: 0");
		lblNumberOfFiles.setBounds(150, 378, 146, 14);
		frmFileSearchSystem.getContentPane().add(lblNumberOfFiles);
		
		JLabel lblVersion = new JLabel("version 0.1");
		lblVersion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVersion.setBounds(346, 384, 75, 14);
		frmFileSearchSystem.getContentPane().add(lblVersion);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"File Name", "Status"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(125);
		table.getColumnModel().getColumn(1).setPreferredWidth(91);
		table.setBounds(10, 42, 412, 291);
		frmFileSearchSystem.getContentPane().add(table);
	}
}
