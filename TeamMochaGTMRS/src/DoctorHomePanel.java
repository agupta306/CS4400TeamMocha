import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Doctor's HomePage in client
 * Presents doctor with all actions they can take
 * @author Ashutosh Gupta
 */
public class DoctorHomePanel extends JPanel{
	private UserView parent;
	private int unreadMsgs;
	private JButton viewApptCalB;
	private JButton patientVisitsB;
	private JButton recordSurgeryB;
	private JButton unreadMsgsB;
	private JButton editProfileB;
	
	public DoctorHomePanel(UserView p, int u) {
		parent = p;
		unreadMsgs = u;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		
		viewApptCalB = new JButton("View Appointment Calendar");	viewApptCalB.addActionListener(new ViewApptCalListener());
		patientVisitsB = new JButton("Patient Visits");				patientVisitsB.addActionListener(new PatientVisitsListener());
		recordSurgeryB = new JButton("Record a Surgery");			recordSurgeryB.addActionListener(new RecordSurgeryListener());
		unreadMsgsB = new JButton("Messages" + "(" + u + ")");		unreadMsgsB.addActionListener(new UnreadMsgListener());
		editProfileB= new JButton("Edit Profile");					editProfileB.addActionListener(new EditProfListener());

		add(viewApptCalB);		add(Box.createRigidArea(new Dimension(0, 5)));
		add(patientVisitsB);	add(Box.createRigidArea(new Dimension(0, 5)));
		add(recordSurgeryB);	add(Box.createRigidArea(new Dimension(0, 5)));
		add(unreadMsgsB);		add(Box.createRigidArea(new Dimension(0, 5)));
		add(editProfileB);		add(Box.createRigidArea(new Dimension(0, 5)));
		add(Box.createRigidArea(new Dimension(0, 10)));
	}
	
	private class ViewApptCalListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			parent.changePanel(new ApptCalPanel(parent));
		}
	}

	private class PatientVisitsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			parent.changePanel(new ViewPatientVisitsPanel(parent));
		}
	}

	private class RecordSurgeryListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			parent.changePanel(new SurgeryRecordPanel(parent));
		}
	}

	private class UnreadMsgListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			parent.changePanel(new MessagesPanel(parent, 1));
		}
	}

	private class EditProfListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			parent.goToProfilePage(1);
		}
	}
}