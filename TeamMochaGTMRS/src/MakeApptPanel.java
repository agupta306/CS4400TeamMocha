import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MakeApptPanel extends JPanel{
	private UserView parent;
	private JComboBox specialtyCB;
	private JButton searchB;
	private MakeApptTableModel tableModel;
	private JTable makeApptTable;
	private JButton requestB;
	
	public MakeApptPanel(UserView p) {
		parent = p;
		parent.changeHeader("Schedule Appointments");
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		specialtyCB = new JComboBox(new String[]{"General Physician", "Heart Specialist", "Eye Physician", "Orthopedics", "Psychiatry", "Gynecologist"});
		specialtyCB.setSelectedIndex(0);
		searchB = new JButton("Search");
		searchB.addActionListener(new SearchBListener());
		
		//TODO: Query dbc for 2d array of General Physicians and send this into setData
		Object[][] initData = {
				{"Dr. A", "123-456-7890", "123", "Monday: 10:00 - 12:00", Boolean.FALSE, "5"},
				{"Dr. B", "123-546-9078", "111", "Tuesday: 10:30 - 1:30", Boolean.FALSE, "4"},
				{"Dr. C", "321-432-3421", "321", "Tuesday: 8:30 - 4:00", Boolean.FALSE, "3"},
				{"Dr. D", "234-432-3122", "333", "Friday: 9:30 - 5:00", Boolean.FALSE, "2"},
		};
		tableModel = new MakeApptTableModel();
		tableModel.setData(initData);
		makeApptTable = new JTable(tableModel);
		makeApptTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		makeApptTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		makeApptTable.getColumnModel().getColumn(2).setPreferredWidth(10);
		makeApptTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		makeApptTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		makeApptTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		
		JPanel requestP = new JPanel();
		requestP.setLayout(new BoxLayout(requestP, BoxLayout.X_AXIS));
		requestP.setBackground(Color.WHITE);
		requestP.setPreferredSize(new Dimension(800, 50));
		requestB = new JButton("Request Appointments");
		requestB.addActionListener(new RequestBListener());
		requestP.add(Box.createRigidArea(new Dimension(300, 50)));
		requestP.add(requestB);
		
		JPanel searchP = new JPanel();
		searchP.setBackground(Color.WHITE);
		searchP.setPreferredSize(new Dimension(800, 50));
		searchP.setLayout(new BoxLayout(searchP, BoxLayout.X_AXIS));
		specialtyCB.setMaximumSize(specialtyCB.getPreferredSize());
		searchP.add(Box.createRigidArea(new Dimension(250, 50)));
		searchP.add(specialtyCB);
		searchP.add(searchB);
		
		JScrollPane displayDoctors = new JScrollPane(makeApptTable);
		makeApptTable.setFillsViewportHeight(true);
		
		add(searchP, BorderLayout.NORTH);
		add(displayDoctors, BorderLayout.CENTER);
		add(requestP, BorderLayout.SOUTH);
	}
	
	private class SearchBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Pull information from dbc based on specialtyCB
			// populate tableModel rows
		}
	}
	
	private class RequestBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// if selected multiple times for one doctor
			//     error msg
			// else
			//     Update dbc with appt
			//     Return to patient homepage
			parent.goToHomePage(0);
		}
	}
}
