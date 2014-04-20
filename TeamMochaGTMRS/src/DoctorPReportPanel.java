import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class DoctorPReportPanel extends JPanel {
	private UserView parent;
	private DefaultTableModel mT;
	private JTable performanceTable;
	private JButton returnB;
	
	public DoctorPReportPanel(UserView p) {
		parent = p;
		parent.changeHeader("Doctor Performance Report");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		
		//TODO: Get data from dbc for table
		String[] columnHeaders = new String[]{"Specialty", "Average Rating", "Number of Surgeries"};
		mT = new DefaultTableModel();
		mT.setColumnIdentifiers(columnHeaders);
		mT.addRow(new String[] {"General Physicians", "4", "20"});
		performanceTable = new JTable(mT);
		
		returnB = new JButton("Return Home");
		returnB.addActionListener(new ReturnBListener());
		
		add(new JScrollPane(performanceTable));
		add(returnB);
	}
	
	private class ReturnBListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			parent.goToHomePage(2);
		}
	}
}
