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

/**
 * Patient's HomePage in client
 * Presents patient with all actions they can take
 * @author Ashutosh Gupta
 */
public class PatientHomePanel extends JPanel {
	private UserView parent;
	private int unreadMsgs;
	private JButton makeApptB;
	private JButton vVisitsB;
	private JButton orderMedB;
	private JButton unreadMsgsB;
	private JButton rateDoctorB;
	private JButton editProfileB;
	
	public PatientHomePanel(UserView p, int u) {
		parent = p;
		unreadMsgs = u;
		parent.changeHeader("Patient Home");
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(Color.WHITE);
		
		JPanel input = new JPanel();
		input.setBackground(Color.WHITE);
		input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
		
		makeApptB = new JButton("Make Appointments");			makeApptB.addActionListener(new MakeApptListener());
		vVisitsB = new JButton("View Visit History");			vVisitsB.addActionListener(new VisitHistoryListener());
		orderMedB = new JButton("Order Medication");			orderMedB.addActionListener(new OrderMedListener());
		unreadMsgsB = new JButton("Messages" + "(" + u + ")");	unreadMsgsB.addActionListener(new UnreadMsgListener());
		rateDoctorB = new JButton("Rate a Doctor");				rateDoctorB.addActionListener(new RateDoctorListener());
		editProfileB = new JButton("Edit Profile");				editProfileB.addActionListener(new EditProfListener());

		input.add(makeApptB);		input.add(Box.createRigidArea(new Dimension(0, 30)));
		input.add(vVisitsB);		input.add(Box.createRigidArea(new Dimension(0, 30)));
		input.add(orderMedB);		input.add(Box.createRigidArea(new Dimension(0, 30)));
		input.add(unreadMsgsB);		input.add(Box.createRigidArea(new Dimension(0, 30)));
		input.add(rateDoctorB);		input.add(Box.createRigidArea(new Dimension(0, 30)));
		input.add(editProfileB);	input.add(Box.createRigidArea(new Dimension(0, 30)));
		input.add(Box.createRigidArea(new Dimension(0, 10)));
		
		add(Box.createRigidArea(new Dimension(100, 0)));
		add(new JLabel(new ImageIcon("patientIcon.png")));
		add(Box.createRigidArea(new Dimension(50, 0)));
		add(input);
	}
	
	private class MakeApptListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JPanel p = new MakeApptPanel(parent);
			parent.changePanel(p);
		}
	}

	private class VisitHistoryListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JPanel p = new ViewVisitHistoryPanel(parent);
			parent.changePanel(p);
		}
	}

	private class OrderMedListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JPanel p = new OrderMedPanel(parent);
			parent.changePanel(p);
		}
	}

	private class UnreadMsgListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			parent.changePanel(new MessagesPanel(parent, 0));
		}
	}

	private class RateDoctorListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			parent.changePanel(new RateDoctorPanel(parent));
		}
	}

	private class EditProfListener implements ActionListener{
		public void actionPerformed(ActionEvent e) { 
			parent.goToProfilePage(0);
		}
	}
}