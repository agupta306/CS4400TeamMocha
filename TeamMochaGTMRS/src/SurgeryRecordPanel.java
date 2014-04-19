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


public class SurgeryRecordPanel extends JPanel {
	private UserView parent;
	private JTextField nameIn;
	private JButton searchB;
	private PVHistoryTableModel pVHTM;
	private JTable patientTable;
	private JTextField patientNameIn;
	private JTextField doctorNameIn;
	private JTextField procedureIn;
	private JTextField cptCodeIn;
	private JTextField numAssistsIn;
	private JTextField preOpMedsIn;
	private JTextField anesStartIn;
	private JTextField surgStartIn;
	private JTextField surgEndin;
	private JTextField descripIn;
	private JButton recordB;
	
	
	public SurgeryRecordPanel(UserView p) {
		parent = p;
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		nameIn = new JTextField();
		searchB = new JButton("Search");
		searchB.addActionListener(new SearchBListener());
		pVHTM = new PVHistoryTableModel();
		pVHTM.setData(new Object[][] {{"", "", Boolean.FALSE}});
		patientTable = new JTable(pVHTM);
		
		patientNameIn = new JTextField();
		doctorNameIn = new JTextField();
		procedureIn = new JTextField();
		cptCodeIn = new JTextField();
		numAssistsIn = new JTextField();
		preOpMedsIn = new JTextField();
		anesStartIn = new JTextField();
		surgStartIn = new JTextField();
		surgEndin = new JTextField();
		descripIn = new JTextField();
		recordB = new JButton("Record");
		recordB.addActionListener(new RecordBListener());
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BorderLayout());
			JPanel s = new JPanel();
			s.setLayout(new BoxLayout(s, BoxLayout.X_AXIS));
			s.add(new JLabel("Search Patient:"));	s.add(nameIn);
			s.add(searchB);
		searchPanel.add(s, BorderLayout.NORTH);
		searchPanel.add(new JScrollPane(patientTable), BorderLayout.CENTER);
		
		JPanel recordPanel = new JPanel();
		recordPanel.setLayout(new BoxLayout(recordPanel, BoxLayout.Y_AXIS));
		recordPanel.add(new JLabel("Patient Name: "));					recordPanel.add(patientNameIn);
		recordPanel.add(new JLabel("Doctor Name: "));					recordPanel.add(doctorNameIn);
		recordPanel.add(new JLabel("Procedure Name"));					recordPanel.add(procedureIn);
		recordPanel.add(new JLabel("CPT Code: "));						recordPanel.add(cptCodeIn);
		recordPanel.add(new JLabel("Number of Assistants:"));			recordPanel.add(numAssistsIn);
		recordPanel.add(new JLabel("Preoperative Medications:"));		recordPanel.add(preOpMedsIn);
		recordPanel.add(new JLabel("Anesthesia Start Time:"));			recordPanel.add(anesStartIn);
		recordPanel.add(new JLabel("Surgery Start Time:"));				recordPanel.add(surgStartIn);
		recordPanel.add(new JLabel("Surgery End Time:"));				recordPanel.add(surgEndin);
		recordPanel.add(new JLabel("Description of Complications:"));	recordPanel.add(descripIn);
		recordPanel.add(recordB);
		
		add(recordPanel, BorderLayout.CENTER);
		add(searchPanel, BorderLayout.WEST);
		
	}
	
	private class SearchBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//TODO: Query dbc for 2d array of patient & phone numbers and populate pVHTM as well as patientNameIn and doctorNameIn
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
	
	private class RecordBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//TODO: Send surgery record to dbc and return home
			parent.goToHomePage(1);
		}
	}
}
