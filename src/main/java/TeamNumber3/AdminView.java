package TeamNumber3;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import java.awt.Component;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import org.eclipse.wb.swing.FocusTraversalOnArray;

public class AdminView {

	JFrame frmFileSearchSystem;
	private JLabel txtSearchHeading;
	private JLabel txtFileName;
	private JLabel txtStatus;
	private JButton btnResetWindows;
	private JTable tblFiles;
	private JLabel lblNumberOfFiles;

	/**
	 * Launch the application.
	 
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
		frmFileSearchSystem.getContentPane().setBackground(UIManager.getColor("Button.background"));
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
		btnRemoveSelectedFiles.setToolTipText("Remove files that are selected in the table");
		btnRemoveSelectedFiles.setMnemonic(KeyEvent.VK_R);
		btnRemoveSelectedFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Method to be added when btnRemoveSelectedFiles is clicked.
			}
		});
		btnRemoveSelectedFiles.setBounds(243, 344, 170, 23);
		frmFileSearchSystem.getContentPane().add(btnRemoveSelectedFiles);
		
		JButton btnRebuild = new JButton("Rebuild");
		btnRebuild.setToolTipText("Re-index modified files");
		btnRebuild.setMnemonic(KeyEvent.VK_B);
		btnRebuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Method to be added when btnRebuild is clicked.
			}
		});
		btnRebuild.setBounds(140, 344, 80, 23);
		frmFileSearchSystem.getContentPane().add(btnRebuild);
		
		JButton btnAddFile = new JButton("Add File...");
		btnAddFile.setToolTipText("Add files to be indexed");
		btnAddFile.setMnemonic(KeyEvent.VK_A);
		btnAddFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Method to be added when btnAddFile is clicked.
				JFileChooser fileChooser = new JFileChooser( "." );
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
				fileChooser.setFileFilter(filter);
				int status = fileChooser.showOpenDialog( frmFileSearchSystem );
				if ( status == JFileChooser.APPROVE_OPTION ) { 
					File selectedFile = fileChooser.getSelectedFile();
					persistenceFile pf = new persistenceFile();
					pf.name = selectedFile.getName();
					pf.filepath = selectedFile.getAbsolutePath();
					pf.dateModified = selectedFile.lastModified();
					PersistenceData.addFileToIndex(pf);
					lblNumberOfFiles.setText("Number of files indexed: " + PersistenceData.getNumFilesIndexed());
					
					}
			}
		});
		btnAddFile.setBounds(25, 344, 95, 23);
		frmFileSearchSystem.getContentPane().add(btnAddFile);
		
		btnResetWindows = new JButton("Reset Windows");
		btnResetWindows.setToolTipText("Reset both windows to their defaults");
		btnResetWindows.setMnemonic(KeyEvent.VK_W);
		btnResetWindows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Method to be added when btnResetWindows is clicked.
			}
		});
		btnResetWindows.setBounds(25, 378, 140, 23);
		frmFileSearchSystem.getContentPane().add(btnResetWindows);
		
		lblNumberOfFiles = new JLabel("Number of files indexed: 0");
		lblNumberOfFiles.setBounds(175, 382, 186, 14);
		frmFileSearchSystem.getContentPane().add(lblNumberOfFiles);
		
		JLabel lblVersion = new JLabel("version 0.2");
		lblVersion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVersion.setBounds(365, 406, 75, 14);
		frmFileSearchSystem.getContentPane().add(lblVersion);
		
		tblFiles = new JTable();
		tblFiles.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tblFiles.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"File Name", "Status"
			}
		));
		tblFiles.getColumnModel().getColumn(0).setPreferredWidth(125);
		tblFiles.getColumnModel().getColumn(1).setPreferredWidth(91);
		tblFiles.setBounds(10, 42, 412, 291);
		frmFileSearchSystem.getContentPane().add(tblFiles);
		frmFileSearchSystem.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnAddFile, btnRebuild, btnRemoveSelectedFiles, btnResetWindows}));
	}
}