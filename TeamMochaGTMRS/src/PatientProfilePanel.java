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
 * Patient's Profile editor in client
 * @author Ashutosh Gupta
 */
public class PatientProfilePanel extends JPanel{
	private UserView parent;
	private JTextField nameIn;
	private JTextField dobIn;
	private JComboBox genderIn;
	private JTextField addressIn;
	private JTextField homePhoneIn;
	private JTextField workPhoneIn;
	private JTextField weightIn;
	private JTextField heightIn;
	private JComboBox incomeIn;
	private JTextField allergiesIn;
	private JButton submitB;
	
	public PatientProfilePanel(UserView p, String n, String d, String g, String a, String hP, String wP, String w, String h, String i, String all) {
		parent = p;
		parent.changeHeader("Patient Profile");
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(Color.WHITE);

		JPanel input = new JPanel();
		input.setBackground(Color.WHITE);
		input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
		
		nameIn = new JTextField(n, 20);					nameIn.setMaximumSize(nameIn.getPreferredSize());			nameIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		dobIn = new JTextField(d, 20);					dobIn.setMaximumSize(dobIn.getPreferredSize());				dobIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		genderIn = new JComboBox(new String[]{"Male", "Female"});												
		genderIn.setSelectedItem(g);					genderIn.setMaximumSize(genderIn.getPreferredSize());		genderIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		addressIn = new JTextField(a, 20);				addressIn.setMaximumSize(addressIn.getPreferredSize());		addressIn.setAlignmentX(Component.LEFT_ALIGNMENT);										addressIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		homePhoneIn = new JTextField(hP, 20);			homePhoneIn.setMaximumSize(homePhoneIn.getPreferredSize());	homePhoneIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		workPhoneIn = new JTextField(wP, 20);			workPhoneIn.setMaximumSize(workPhoneIn.getPreferredSize());	workPhoneIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		weightIn = new JTextField(w, 20);				weightIn.setMaximumSize(weightIn.getPreferredSize());		weightIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		heightIn = new JTextField(h, 20);				heightIn.setMaximumSize(heightIn.getPreferredSize());		heightIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		incomeIn = new JComboBox(new String[]{"100000+", "50000 - 100000", "25000 - 50000", "0 - 25000"});		
		incomeIn.setSelectedItem(i);					incomeIn.setMaximumSize(incomeIn.getPreferredSize());		incomeIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		allergiesIn = new JTextField(all, 20);			allergiesIn.setMaximumSize(allergiesIn.getPreferredSize());	allergiesIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		submitB = new JButton("Submit");				submitB.setAlignmentX(Component.LEFT_ALIGNMENT);
		submitB.addActionListener(new SubmitBListener());
	
		input.add(new JLabel("Patient Name: "));					input.add(nameIn);		input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(new JLabel("Date of Birth: "));					input.add(dobIn);		input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(new JLabel("Gender: "));							input.add(genderIn);	input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(new JLabel("Address: "));							input.add(addressIn);	input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(new JLabel("Home Phone (###-###-####): "));		input.add(homePhoneIn);	input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(new JLabel("Work Phone (###-###-####): "));		input.add(workPhoneIn);	input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(new JLabel("Weight (lbs):"));						input.add(weightIn);	input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(new JLabel("Height (in):"));						input.add(heightIn);	input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(new JLabel("Annual Income ($):"));				input.add(incomeIn);	input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(new JLabel("Allergies (dairy, pollen, ...):"));	input.add(allergiesIn);	input.add(Box.createRigidArea(new Dimension(0, 5)));
		input.add(submitB);
		input.add(Box.createRigidArea(new Dimension(0, 10)));
		
		add(Box.createRigidArea(new Dimension(100, 0)));
		add(new JLabel(new ImageIcon("editProf.png")));
		add(Box.createRigidArea(new Dimension(50, 0)));
		add(input);
	}
	
	private class SubmitBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(nameIn.getText().equals("") || dobIn.getText().equals("") || addressIn.getText().equals("")
			|| homePhoneIn.getText().equals("") || workPhoneIn.getText().equals("") || weightIn.getText().equals("")
			|| heightIn.getText().equals(""))
				parent.errorMessage("Please fill in all fields");
			// TODO: Regex checks for fields?
			else {
				boolean check = false;
				// TODO: Send information to dbc and update corresponding patient tuple
				// TODO: Ensure that name/phone# are unique
				check = true;
				if(check) parent.goToHomePage(0);
				else parent.errorMessage("Someone else has that name and phone number!");
			}
		}
	}
}