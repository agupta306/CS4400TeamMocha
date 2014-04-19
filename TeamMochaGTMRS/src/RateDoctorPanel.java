import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RateDoctorPanel extends JPanel {
	private UserView parent;
	private JComboBox doctorList;
	private JTextField rating;
	private JButton submitB;
	
	
	public RateDoctorPanel(UserView p) {
		parent = p;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		
		//TODO: Get all doctors the patient has visited to popultae doctorList
		doctorList = new JComboBox(new String[] {"Dr. A", "Dr. B", "Dr. C"});
		rating = new JTextField("5");						doctorList.setAlignmentX(Component.LEFT_ALIGNMENT);
		submitB = new JButton("Submit Rating");				rating.setAlignmentX(Component.LEFT_ALIGNMENT);
		submitB.addActionListener(new SubmitBListener());	submitB.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		add(new JLabel("Select Doctor: "));
		add(doctorList);
		add(new JLabel("Rating (1-5): "));
		add(rating);
		add(submitB);
		add(Box.createRigidArea(new Dimension(0, 500)));
	}
	
	private class SubmitBListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//TODO: Submit rating to dbc
			parent.goToHomePage(0);
		}
	}
}
