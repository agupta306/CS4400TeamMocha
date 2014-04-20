import java.awt.BorderLayout;
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


public class BillingPanel extends JPanel{
	private UserView parent;
	private JTextField nameIn;
	private JButton searchB;
	private PVHistoryTableModel pVHTM;
	private JTable patientTable;
	private JButton viewB;
	private JPanel billingP;
	private DefaultTableModel vT;
	private DefaultTableModel sT;
	private JTable visitTable;
	private JTable surgeTable;
	private JLabel totalCost;
	private JButton returnB;
	
	public BillingPanel(UserView p) {
		parent = p;
		parent.changeHeader("Billing Report");
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		nameIn = new JTextField();
		searchB = new JButton("Search");
		searchB.addActionListener(new SearchBListener());
		pVHTM = new PVHistoryTableModel();
		pVHTM.setData(new Object[][] {{"", "", Boolean.FALSE}});
		patientTable = new JTable(pVHTM);	
		viewB = new JButton("View");
		viewB.addActionListener(new ViewBListener());
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BorderLayout());
			JPanel s = new JPanel();
			s.setLayout(new BoxLayout(s, BoxLayout.X_AXIS));
			s.add(new JLabel("Patient:"));	s.add(nameIn);
			s.add(searchB);
		searchPanel.add(s, BorderLayout.NORTH);
		searchPanel.add(new JScrollPane(patientTable), BorderLayout.CENTER);
			JPanel vr = new JPanel();
			vr.setLayout(new BoxLayout(vr, BoxLayout.X_AXIS));
			vr.add(viewB);
		searchPanel.add(vr, BorderLayout.SOUTH);
		
		billingP = new JPanel();
		billingP.setLayout(new BoxLayout(billingP, BoxLayout.Y_AXIS));
		

		String [] columnheaderV = new String[] {"Visit Date", "Cost ($)"};
		String [] columnHeaderS = new String[] {"Surgery Date", "Cost ($)"};
		
		vT = new DefaultTableModel();
		sT = new DefaultTableModel();
		vT.setColumnIdentifiers(columnheaderV);
		vT.addRow(new String[] {"11-11-13", "431.45"});
		sT.setColumnIdentifiers(columnHeaderS);
		sT.addRow(new String[] {"12-03-12", "503.00"});
		visitTable = new JTable(vT);
		surgeTable = new JTable(sT);
		
		totalCost = new JLabel("Total Cost: $0.00");
		
		billingP.add(new JScrollPane(visitTable));
		billingP.add(new JScrollPane(surgeTable));
		billingP.add(totalCost);
		returnB = new JButton("Return Home");
		returnB.addActionListener(new ReturnBListener());
		billingP.add(returnB);
		
		add(billingP, BorderLayout.CENTER);
		add(searchPanel, BorderLayout.WEST);
	}
	
	private class SearchBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//TODO: Query dbc for 2d array of patient & phone numbers and populate pVHTM
			Object[][] data = {
					{"George Burdell", "123-234-3456", Boolean.FALSE},
					{"George Georgie", "234-234-7766", Boolean.FALSE},
					{"Buzz Burdell", "133-999-3456", Boolean.FALSE},
					{"John Smith", "523-234-0986", Boolean.FALSE},
			};
			
			pVHTM.setData(data);
			patientTable.revalidate();
		}
	}
	
	private class ViewBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//TODO: update billingP tables based on first patient selected in pVHTM
		}
	}
	
	private class ReturnBListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			parent.goToHomePage(2);
		}
	}
}
