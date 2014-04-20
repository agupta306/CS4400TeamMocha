import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PatientVisitReportPanel extends JPanel {
	private UserView parent;
	private DefaultTableModel mT;
	private JTable performanceTable;
	private JButton returnB;
	private JTextField dateIn;
	private JButton prepB;
	
	public PatientVisitReportPanel(UserView p) {
		parent = p;
		parent.changeHeader("Patient Visit Report");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		
		dateIn = new JTextField(20);
		
		//TODO: Get data from dbc for table for initial date
		String[] columnHeaders = new String[]{"Doctor", "No. of Patients Seen", "No. of Prescriptions", "Total Billing($)"};
		mT = new DefaultTableModel();
		mT.setColumnIdentifiers(columnHeaders);
		mT.addRow(new String[] {"Dr. Mary Rose", "40", "56", "7827"});
		performanceTable = new JTable(mT);
		
		returnB = new JButton("Return Home");
		returnB.addActionListener(new ReturnBListener());
		
		prepB = new JButton("Prepare Report");
		prepB.addActionListener(new PrepBListener());
		
		add(new JLabel("Select a Month (MM-YYYY):"));
		add(dateIn);
		add(prepB);
		add(new JScrollPane(performanceTable));
		add(returnB);
	}
	
	private class ReturnBListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			parent.goToHomePage(2);
		}
	}
	
	private class PrepBListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//TODO: updated table based on date in textfield
		}
	}
}
