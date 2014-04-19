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


public class ViewPatientVisitsPanel extends JPanel{
	private UserView parent;
	private JTextField nameIn;
	private JTextField phoneIn;
	private JButton searchB;
	private PVHistoryTableModel pVHTM;
	private JTable patientTable;
	private ViewVisitHistoryPanel v;
	private JButton viewB;
	private JButton recordVB;
	
	public ViewPatientVisitsPanel(UserView p) {
		parent = p;
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		nameIn = new JTextField();
		phoneIn = new JTextField();
		searchB = new JButton("Search");
		searchB.addActionListener(new SearchBListener());
		pVHTM = new PVHistoryTableModel();
		pVHTM.setData(new Object[][] {{"", "", Boolean.FALSE}});
		patientTable = new JTable(pVHTM);	
		viewB = new JButton("View");
		recordVB = new JButton("Record");
		viewB.addActionListener(new ViewBListener());
		recordVB.addActionListener(new RecordVBListener());
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BorderLayout());
			JPanel s = new JPanel();
			s.setLayout(new BoxLayout(s, BoxLayout.X_AXIS));
			s.add(new JLabel("Patient:"));	s.add(nameIn);
			s.add(new JLabel("Phone:"));	s.add(phoneIn);
			s.add(searchB);
		searchPanel.add(s, BorderLayout.NORTH);
		searchPanel.add(new JScrollPane(patientTable), BorderLayout.CENTER);
			JPanel vr = new JPanel();
			vr.setLayout(new BoxLayout(vr, BoxLayout.X_AXIS));
			vr.add(viewB);
			vr.add(recordVB);
		searchPanel.add(vr, BorderLayout.SOUTH);
		v = new ViewVisitHistoryPanel(this);
		
		add(v, BorderLayout.CENTER);
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
			//TODO: update v panel based on first patient selected in pVHTM
		}
	}
	
	private class RecordVBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			parent.changePanel(new RecordVisitPanel(parent));
		}
	}
}
