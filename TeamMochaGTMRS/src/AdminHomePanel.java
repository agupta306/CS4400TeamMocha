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

public class AdminHomePanel extends JPanel{
	private UserView parent;
	private JButton billingB;
	private JButton dPerformRB;
	private JButton surgeryB;
	private JButton patientVRB;
	
	public AdminHomePanel(UserView p) {
		parent = p;
		parent.changeHeader("Admin Home");
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(Color.WHITE);
		
		JPanel input = new JPanel();
		input.setBackground(Color.WHITE);
		input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
		
		billingB = new JButton("Billing");						billingB.addActionListener(new BillingBListener());
		dPerformRB = new JButton("Doctor Performance Report");	dPerformRB.addActionListener(new DPerformRBListener());
		surgeryB = new JButton("Surgery Report");				surgeryB.addActionListener(new SurgeryBListener());
		patientVRB = new JButton("Patient Visit Report");		patientVRB.addActionListener(new PatientVRBListener());

		input.add(billingB);		input.add(Box.createRigidArea(new Dimension(0, 30)));
		input.add(dPerformRB);		input.add(Box.createRigidArea(new Dimension(0, 30)));
		input.add(surgeryB);		input.add(Box.createRigidArea(new Dimension(0, 30)));
		input.add(patientVRB);		input.add(Box.createRigidArea(new Dimension(0, 30)));
		input.add(Box.createRigidArea(new Dimension(0, 10)));
		
		add(Box.createRigidArea(new Dimension(100, 0)));
		add(new JLabel(new ImageIcon("adminIcon.png")));
		add(Box.createRigidArea(new Dimension(50, 0)));
		add(input);
	}
	
	private class BillingBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			parent.changePanel(new BillingPanel(parent));
		}
	}
	
	private class DPerformRBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			parent.changePanel(new DoctorPReportPanel(parent));
		}
	}

	private class SurgeryBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			parent.changePanel(new SurgeryReportPanel(parent));
		}
	}

	private class PatientVRBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			parent.changePanel(new PatientVisitReportPanel(parent));
		}
	}
}
