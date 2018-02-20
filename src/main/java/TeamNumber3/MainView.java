package TeamNumber3;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.MatteBorder;

public class MainView {

	private JFrame frmFileSearchSystem;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frmFileSearchSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFileSearchSystem = new JFrame();
		frmFileSearchSystem.setTitle("File Search System");
		frmFileSearchSystem.setBounds(100, 100, 600, 400);
		frmFileSearchSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblSearch = new JLabel("File Search System");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		
		JButton btnMaintenance = new JButton("Maintenance");
		
		JButton btnAbout = new JButton("About");
		
		JList list = new JList();
		list.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(frmFileSearchSystem.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(list, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(btnMaintenance)
							.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
							.addComponent(lblSearch)
							.addGap(143)
							.addComponent(btnAbout))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
							.addGap(22)
							.addComponent(btnSearch)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnMaintenance)
							.addComponent(btnAbout))
						.addComponent(lblSearch, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(list, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
					.addContainerGap())
		);
		frmFileSearchSystem.getContentPane().setLayout(groupLayout);
	}
}
