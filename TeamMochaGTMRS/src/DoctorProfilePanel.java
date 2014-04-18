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
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		
		licenseIn = new JTextField(l, 20);			licenseIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		firstNameIn = new JTextField(fN, 20);		firstNameIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		lastNameIn = new JTextField(lN, 20);		lastNameIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		dobIn = new JTextField(d, 20);				dobIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		workPhoneIn = new JTextField(wP, 20);		workPhoneIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		specialityIn = new JComboBox(new String[]{"General Physician", "Heart Specialist", "Eye Physician", "Orthopedics", "Psychiatry", "Gynecologist"});
		specialityIn.setSelectedItem(s);			specialityIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		roomNoIn = new JTextField(r, 20);			roomNoIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		homeAddressIn = new JTextField(h, 20);		homeAddressIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		availabilityIn = new JTextField(a, 20);		availabilityIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		submitB = new JButton("Submit");			submitB.setAlignmentX(Component.LEFT_ALIGNMENT);
		submitB.addActionListener(new SubmitBListener());
	
		add(new JLabel("License Number: "));				add(licenseIn);			add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("First Name: "));					add(firstNameIn);		add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Last Name: "));						add(lastNameIn);		add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("dob: "));							add(dobIn);				add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Work Phone (###-###-####): "));		add(workPhoneIn);		add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Speciality:"));						add(specialityIn);		add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Room Number:"));					add(roomNoIn);			add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Home Address:"));					add(homeAddressIn);		add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Availability (Day [0:00-0:00], Day[0:00-0:00], ...):"));	
															add(availabilityIn);	add(Box.createRigidArea(new Dimension(0, 5)));
															add(submitB);
		add(Box.createRigidArea(new Dimension(0, 10)));
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