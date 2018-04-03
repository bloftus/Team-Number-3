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
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTable;

public class MainView {

	static JFrame frmFileSearchSystem;
	private JTextField txtSearchBar;
	private final ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton rdbtnOr;
    private JRadioButton rdbtnAnd;
    private JRadioButton rdbtnPhrase;
    private JList lstOutput;
    private Map<String, List<Pair>> wordMap = PersistenceData.getWordMap();
    private JTable tblOutput = new JTable(new DefaultTableModel(new Object[] {"File Name"}, 0));
    private DefaultTableModel tblData = (DefaultTableModel) tblOutput.getModel();
	AdminView window2 = new AdminView();

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
						
						// More testing
						word = "orange";
						List<Pair> Pairs = PersistenceData.getWordList(word);
						for(int i = 0; i < Pairs.size(); i++) {
							// Nothing happens if the word isn't indexed (the size of Pairs would be 0)
							Pair p = Pairs.get(i);
							System.out.println(word + " is located at " + 
									p.getPositionNum() + " in file " + p.getFileNum() );
						}
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
		frmFileSearchSystem.setBounds(500, 300, 598, 442);
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
				Set<Integer> foundFiles = new HashSet<>();
				//Object[] row = {foundFiles};
				if(rdbtnOr.isSelected()) {
					foundFiles = IndexSearch.orSearch(txtSearchBar.getText(), wordMap);
				} else if(rdbtnAnd.isSelected()) {
					foundFiles = IndexSearch.andSearch(txtSearchBar.getText(), wordMap);
				} else if(rdbtnPhrase.isSelected() ) {
					foundFiles = IndexSearch.phraseSearch(txtSearchBar.getText(), wordMap);
				}
				for(Integer fileNum : foundFiles) {
					Object[] row = {fileNum};
					tblData.addRow(row);
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
							//AdminView window2 = new AdminView();
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
		
		rdbtnOr = new JRadioButton("OR");
		rdbtnOr.setMnemonic(KeyEvent.VK_O);
		buttonGroup.add(rdbtnOr);
		rdbtnOr.setSelected(true);
		
		rdbtnAnd = new JRadioButton("AND");
		rdbtnAnd.setMnemonic(KeyEvent.VK_A);
		buttonGroup.add(rdbtnAnd);
		
		rdbtnPhrase = new JRadioButton("PHRASE");
		rdbtnPhrase.setMnemonic(KeyEvent.VK_P);
		buttonGroup.add(rdbtnPhrase);
		
		
		tblOutput.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tblOutput.getColumnModel().getColumn(0).setPreferredWidth(125);
		tblOutput.setBounds(10, 42, 412, 291);
		
		GroupLayout groupLayout = new GroupLayout(frmFileSearchSystem.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(tblOutput, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(rdbtnOr)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnAnd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnPhrase))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(btnMaintenance)
							.addPreferredGap(ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
							.addComponent(lblSearch)
							.addGap(143)
							.addComponent(btnAbout))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
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
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tblOutput, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
					.addContainerGap())
		);
		frmFileSearchSystem.getContentPane().setLayout(groupLayout);
		frmFileSearchSystem.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnMaintenance, btnAbout, txtSearchBar, btnSearch, rdbtnOr, rdbtnAnd, rdbtnPhrase}));
	}
}
