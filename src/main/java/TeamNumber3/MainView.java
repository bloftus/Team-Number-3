// Program by Brian Loftus, Sean Thompson, Kevin Broyles, and Shawn Broyles

package TeamNumber3;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class MainView {

	private JFrame frmFileSearchSystem;
	private JTextField txtSearchBar;
	private final ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton rdbtnOr;
    private JRadioButton rdbtnAnd;
    private JRadioButton rdbtnPhrase;
    private Map<String, List<Pair>> wordMap = PersistenceData.getWordMap();
	// AdminView window2 = new AdminView();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frmFileSearchSystem.setVisible(true);
					
					PersistenceData.readIndexFromFile();
					
					JSONObject index = PersistenceData.getIndex();
					
					if (index != null) {
						System.out.println(index.get("name"));
						System.out.println(index.get("version"));
						System.out.println(index.get("indexedFiles"));
						
						// Testing
						Map<String, List<Pair>> wordMap = PersistenceData.getWordMap();
						String word = "apple";
						if (wordMap.containsKey(word)) {
							List<Pair> Pairs = wordMap.get(word);
							for(int i = 0; i < Pairs.size(); i++) {
								Pair p = Pairs.get(i);
								System.out.println(word + " is located at " + 
										p.getPositionNum() + " in file " + p.getFileNum() );
							}
						}
						else
							System.out.println("\"" + word + "\" is not indexed");
					}
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
		frmFileSearchSystem.setResizable(false);
		frmFileSearchSystem.setTitle("File Search System");
		frmFileSearchSystem.setBounds(100, 100, 598, 442);
		frmFileSearchSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblSearch = new JLabel("File Search System");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtSearchBar = new JTextField();
		txtSearchBar.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setToolTipText("Search for text in the indexed files");
		btnSearch.setMnemonic(KeyEvent.VK_S);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnOr.isSelected()) {
					IndexSearch.orSearch(txtSearchBar.getText(), wordMap);
				} else if(rdbtnAnd.isSelected()) {
					IndexSearch.andSearch(txtSearchBar.getText(), wordMap);
				} else if(rdbtnPhrase.isSelected() ) {
					IndexSearch.phraseSearch(txtSearchBar.getText(), wordMap);
				}
			}
		});
		
		JButton btnMaintenance = new JButton("Maintenance");
		btnMaintenance.setToolTipText("Display the maintenace window");
		btnMaintenance.setMnemonic(KeyEvent.VK_M);
		btnMaintenance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AdminView window2 = new AdminView();
							window2.frmFileSearchSystem.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		JButton btnAbout = new JButton("About");
		btnAbout.setToolTipText("About File Search System");
		btnAbout.setMnemonic(KeyEvent.VK_B);
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "File Search System Version 0.2\nContributors:\nBrian Loftus\nSean Thompson\nKevin Broyles\nShawn Broyles");
			}
		});
		
		JList lstOutput = new JList();
		lstOutput.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JRadioButton rdbtnOr = new JRadioButton("OR");
		rdbtnOr.setMnemonic(KeyEvent.VK_O);
		buttonGroup.add(rdbtnOr);
		rdbtnOr.setSelected(true);
		
		JRadioButton rdbtnAnd = new JRadioButton("AND");
		rdbtnAnd.setMnemonic(KeyEvent.VK_A);
		buttonGroup.add(rdbtnAnd);
		
		JRadioButton rdbtnPhrase = new JRadioButton("PHRASE");
		rdbtnPhrase.setMnemonic(KeyEvent.VK_P);
		buttonGroup.add(rdbtnPhrase);
		GroupLayout groupLayout = new GroupLayout(frmFileSearchSystem.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rdbtnOr)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnAnd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnPhrase))
						.addComponent(lstOutput, GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnMaintenance)
							.addPreferredGap(ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
							.addComponent(lblSearch)
							.addGap(143)
							.addComponent(btnAbout))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtSearchBar, GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
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
						.addComponent(txtSearchBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnOr)
						.addComponent(rdbtnAnd)
						.addComponent(rdbtnPhrase))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addComponent(lstOutput, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frmFileSearchSystem.getContentPane().setLayout(groupLayout);
		frmFileSearchSystem.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnMaintenance, btnAbout, txtSearchBar, btnSearch, rdbtnOr, rdbtnAnd, rdbtnPhrase, lstOutput}));
	}
}
