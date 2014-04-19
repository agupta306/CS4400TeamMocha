import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RecordVisitPanel extends JPanel {
	private UserView parent;
	private JTextField dateIn;
	private JTextField pNameIn;
	private JTextField sysBPIn;
	private JTextField diaBPIn;
	private JTextField diagnIn;
	private JTextField medNameIn;
	private JTextField dosageIn;
	private JTextField durationIn;
	private JTextField notesIn;
	private JButton submitB;
	private JButton addMedB;
	
	public RecordVisitPanel(UserView p) {
		parent = p;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		
		dateIn = new JTextField();
		pNameIn = new JTextField();
		sysBPIn = new JTextField();
		diaBPIn = new JTextField();
		diagnIn = new JTextField();
		medNameIn = new JTextField();
		dosageIn = new JTextField();
		durationIn = new JTextField();
		notesIn = new JTextField();
		addMedB = new JButton("Add Another Prescription");
		submitB = new JButton("Submit");
		
		addMedB.addActionListener(new AddMedBListener());
		submitB.addActionListener(new SubmitBListener());
		
		add(new JLabel("Date of Visit: "));		add(dateIn);
		add(new JLabel("Patient Name: "));		add(pNameIn);
		add(new JLabel("Systolic BP: "));		add(sysBPIn);
		add(new JLabel("Diastolic BP: "));		add(diaBPIn);
		add(new JLabel("Diagnosis:")); 			add(diagnIn);
		add(new JLabel("Medication Name:")); 	add(medNameIn);
		add(new JLabel("Dosage (per day):"));	add(dosageIn);
		add(new JLabel("Duration (MM-DD):")); 	add(durationIn);
		add(new JLabel("Notes:"));				add(notesIn);
		add(addMedB);
		add(submitB);
	}
	
	private class AddMedBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO store prescription in dbc, show dialogue confirming addition, clear corresponding textfields
		}
	}
	
	private class SubmitBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO store visit in dbc, return to doctorHome
			parent.goToHomePage(1);
		}
	}
}
