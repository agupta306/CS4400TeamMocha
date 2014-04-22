import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
		parent.changeHeader("Record a Visit");
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(Color.WHITE);
		
		JPanel input = new JPanel();
		input.setBackground(Color.WHITE);
		input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
		
		dateIn = new JTextField(20);		dateIn.setMaximumSize(dateIn.getPreferredSize());			dateIn.setAlignmentX(LEFT_ALIGNMENT);
		pNameIn = new JTextField(20);		pNameIn.setMaximumSize(pNameIn.getPreferredSize());			pNameIn.setAlignmentX(LEFT_ALIGNMENT);
		sysBPIn = new JTextField(20);		sysBPIn.setMaximumSize(sysBPIn.getPreferredSize());			sysBPIn.setAlignmentX(LEFT_ALIGNMENT);
		diaBPIn = new JTextField(20);		diaBPIn.setMaximumSize(diaBPIn.getPreferredSize());			diaBPIn.setAlignmentX(LEFT_ALIGNMENT);
		diagnIn = new JTextField(20);		diagnIn.setMaximumSize(diagnIn.getPreferredSize());			diagnIn.setAlignmentX(LEFT_ALIGNMENT);
		medNameIn = new JTextField(20);		medNameIn.setMaximumSize(medNameIn.getPreferredSize());		medNameIn.setAlignmentX(LEFT_ALIGNMENT);
		dosageIn = new JTextField(20);		dosageIn.setMaximumSize(dosageIn.getPreferredSize());		dosageIn.setAlignmentX(LEFT_ALIGNMENT);
		durationIn = new JTextField(20);	durationIn.setMaximumSize(durationIn.getPreferredSize());	durationIn.setAlignmentX(LEFT_ALIGNMENT);
		notesIn = new JTextField(20);		notesIn.setMaximumSize(notesIn.getPreferredSize());			notesIn.setAlignmentX(LEFT_ALIGNMENT);
		addMedB = new JButton("Add Another Prescription");
		submitB = new JButton("Submit");
		
		addMedB.addActionListener(new AddMedBListener());
		submitB.addActionListener(new SubmitBListener());
		
		input.add(new JLabel("Date of Visit: "));		input.add(dateIn);
		input.add(new JLabel("Patient Name: "));		input.add(pNameIn);
		input.add(new JLabel("Systolic BP: "));			input.add(sysBPIn);
		input.add(new JLabel("Diastolic BP: "));		input.add(diaBPIn);
		input.add(new JLabel("Diagnosis:")); 			input.add(diagnIn);
		input.add(new JLabel("Medication Name:")); 		input.add(medNameIn);
		input.add(new JLabel("Dosage (per day):"));		input.add(dosageIn);
		input.add(new JLabel("Duration (MM-DD):")); 	input.add(durationIn);
		input.add(new JLabel("Notes:"));				input.add(notesIn);
		input.add(addMedB);
		input.add(submitB);
		
		add(Box.createRigidArea(new Dimension(100, 0)));
		add(new JLabel(new ImageIcon("check.jpg")));
		add(Box.createRigidArea(new Dimension(50, 0)));
		add(input);
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
