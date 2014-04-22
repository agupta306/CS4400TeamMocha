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

/**
 * Doctor's Profile editor in client
 * @author Ashutosh Gupta
 */
public class DoctorProfilePanel extends JPanel{
	private UserView parent;
	private JTextField licenseIn;
	private JTextField firstNameIn;
	private JTextField lastNameIn;
	private JTextField dobIn;
	private JTextField workPhoneIn;
	private JComboBox specialityIn;
	private JTextField roomNoIn;
	private JTextField homeAddressIn;
	private JTextField availabilityIn;
	private JButton submitB;
	
	public DoctorProfilePanel(UserView p, String l, String fN, String lN, String d, String wP, String s, String r, String h, String a) {
		parent = p;
		parent.changeHeader("Doctor Profile");
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(Color.WHITE);
		
		JPanel input = new JPanel();
		input.setBackground(Color.WHITE);
		input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
		
		licenseIn = new JTextField(l, 20);			licenseIn.setMaximumSize(licenseIn.getPreferredSize());				licenseIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		firstNameIn = new JTextField(fN, 20);		firstNameIn.setMaximumSize(firstNameIn.getPreferredSize());			firstNameIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		lastNameIn = new JTextField(lN, 20);		lastNameIn.setMaximumSize(lastNameIn.getPreferredSize());			lastNameIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		dobIn = new JTextField(d, 20);				dobIn.setMaximumSize(dobIn.getPreferredSize());						dobIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		workPhoneIn = new JTextField(wP, 20);		workPhoneIn.setMaximumSize(workPhoneIn.getPreferredSize());			workPhoneIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		specialityIn = new JComboBox(new String[]{"General Physician", "Heart Specialist", "Eye Physician", "Orthopedics", "Psychiatry", "Gynecologist"});
		specialityIn.setSelectedItem(s);			specialityIn.setMaximumSize(specialityIn.getPreferredSize());		specialityIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		roomNoIn = new JTextField(r, 20);			roomNoIn.setMaximumSize(roomNoIn.getPreferredSize());				roomNoIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		homeAddressIn = new JTextField(h, 20);		homeAddressIn.setMaximumSize(homeAddressIn.getPreferredSize());		homeAddressIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		availabilityIn = new JTextField(a, 20);		availabilityIn.setMaximumSize(availabilityIn.getPreferredSize());	availabilityIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		submitB = new JButton("Submit");			submitB.setAlignmentX(Component.LEFT_ALIGNMENT);
		submitB.addActionListener(new SubmitBListener());
	
		input.add(new JLabel("License Number: "));				input.add(licenseIn);		input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(new JLabel("First Name: "));					input.add(firstNameIn);		input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(new JLabel("Last Name: "));					input.add(lastNameIn);		input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(new JLabel("dob: "));							input.add(dobIn);			input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(new JLabel("Work Phone (###-###-####): "));	input.add(workPhoneIn);		input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(new JLabel("Speciality:"));					input.add(specialityIn);	input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(new JLabel("Room Number:"));					input.add(roomNoIn);		input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(new JLabel("Home Address:"));					input.add(homeAddressIn);	input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(new JLabel("Availability (Day [0:00-0:00], Day[0:00-0:00], ...):"));	
		input.add(availabilityIn);	input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(submitB);
		input.add(Box.createRigidArea(new Dimension(0, 10)));
		
		add(Box.createRigidArea(new Dimension(100, 0)));
		add(new JLabel(new ImageIcon("editProf.png")));
		add(Box.createRigidArea(new Dimension(50, 0)));
		add(input);
	}
		
	private class SubmitBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(licenseIn.getText().equals("") || firstNameIn.getText().equals("") || lastNameIn.getText().equals("")
			|| dobIn.getText().equals("") || workPhoneIn.getText().equals("") || roomNoIn.getText().equals("")
			|| homeAddressIn.getText().equals("") || availabilityIn.getText().equals(""))
				parent.errorMessage("Please fill in all fields");
			//Regex checks for fields?
			else {
				boolean check = false;
				// TODO: Send information to dbc and update corresponding doctor tuple
				check = true;
				if(check) parent.goToHomePage(1);
			}
		}
	}
}