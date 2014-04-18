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
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);

		nameIn = new JTextField(n, 20);						nameIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		dobIn = new JTextField(d, 20);						dobIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		genderIn = new JComboBox(new String[]{"Male", "Female"});												
		genderIn.setSelectedItem(g);						genderIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		addressIn = new JTextField(a, 20);					addressIn.setAlignmentX(Component.LEFT_ALIGNMENT);										addressIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		homePhoneIn = new JTextField(hP, 20);				homePhoneIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		workPhoneIn = new JTextField(wP, 20);				workPhoneIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		weightIn = new JTextField(w, 20);					weightIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		heightIn = new JTextField(h, 20);					heightIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		incomeIn = new JComboBox(new String[]{"100000+", "50000 - 100000", "25000 - 50000", "0 - 25000"});		
		incomeIn.setSelectedItem(i);						incomeIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		allergiesIn = new JTextField(all, 20);				allergiesIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		submitB = new JButton("Submit");					submitB.setAlignmentX(Component.LEFT_ALIGNMENT);
		submitB.addActionListener(new SubmitBListener());
	
		add(new JLabel("Patient Name: "));					add(nameIn);		add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Date of Birth: "));					add(dobIn);			add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Gender: "));						add(genderIn);		add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Address: "));						add(addressIn);		add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Home Phone (###-###-####): "));		add(homePhoneIn);	add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Work Phone (###-###-####): "));		add(workPhoneIn);	add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Weight (lbs):"));					add(weightIn);		add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Height (in):"));					add(heightIn);		add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Annual Income ($):"));				add(incomeIn);		add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Allergies (dairy, pollen, ...):"));	add(allergiesIn);	add(Box.createRigidArea(new Dimension(0, 5)));
		add(submitB);
		add(Box.createRigidArea(new Dimension(0, 10)));
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