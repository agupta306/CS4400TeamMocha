import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
		parent.changeHeader("Rate a Doctor");
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(Color.WHITE);
		
		JPanel input = new JPanel();
		input.setBackground(Color.WHITE);
		input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
		
		//TODO: Get all doctors the patient has visited to popultae doctorList
		doctorList = new JComboBox(new String[] {"Dr. A", "Dr. B", "Dr. C"});
		rating = new JTextField(5);
		doctorList.setAlignmentX(Component.LEFT_ALIGNMENT);
		rating.setAlignmentX(Component.LEFT_ALIGNMENT);
		doctorList.setMaximumSize(doctorList.getPreferredSize());
		rating.setMaximumSize(rating.getPreferredSize());
		
		submitB = new JButton("Submit Rating");
		submitB.addActionListener(new SubmitBListener());
		submitB.setAlignmentX(Component.LEFT_ALIGNMENT);

		input.add(Box.createRigidArea(new Dimension(0, 50)));
		input.add(new JLabel("Select Doctor: "));
		input.add(doctorList);
		input.add(Box.createRigidArea(new Dimension(0, 20)));
		input.add(new JLabel("Rating (1-5): "));
		input.add(rating);
		input.add(Box.createRigidArea(new Dimension(0, 20)));
		input.add(submitB);
		
		add(Box.createRigidArea(new Dimension(100, 0)));
		add(new JLabel(new ImageIcon("rate.png")));
		add(Box.createRigidArea(new Dimension(50, 0)));
		add(input);
	}
	
	private class SubmitBListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//TODO: Submit rating to dbc
			parent.goToHomePage(0);
		}
	}
}
