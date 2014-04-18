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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Allows users to enter information in order to
 * create new profile in the GTMRS database
 * @author Ashutosh Gupta
 */
public class CreateNewUserPanel extends JPanel {
	private UserView parent;
	private JTextField usernameIn;
	private JTextField passwordIn;
	private JTextField confirmpIn;
	private JComboBox userTypeCb;
	private JButton registerB;
	
	public CreateNewUserPanel(UserView p) {
		parent = p;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		
		usernameIn = new JTextField(20);										usernameIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		passwordIn = new JPasswordField(20);									passwordIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		confirmpIn = new JPasswordField(20);									confirmpIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		userTypeCb = new JComboBox(new String[]{"Patient", "Doctor", "Admin"});	userTypeCb.setAlignmentX(Component.LEFT_ALIGNMENT);
		registerB = new JButton("Register");									registerB.setAlignmentX(Component.LEFT_ALIGNMENT);
		registerB.addActionListener(new RegisterBListener());
		
		add(new JLabel("Username: "));			add(usernameIn);	add(Box.createRigidArea(new Dimension(0, 20)));
		add(new JLabel("Password: "));			add(passwordIn);    add(Box.createRigidArea(new Dimension(0, 20)));
		add(new JLabel("Confirm Password: "));	add(confirmpIn);	add(Box.createRigidArea(new Dimension(0, 20)));
		add(new JLabel("Type of User: "));		add(userTypeCb);	add(Box.createRigidArea(new Dimension(0, 20)));
		add(registerB);
		add(Box.createRigidArea(new Dimension(0, 200)));
	}
	
	private class RegisterBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(usernameIn.getText().equals("") || passwordIn.getText().equals("") || confirmpIn.getText().equals(""))
				parent.errorMessage("Please fill in all fields");
			else if(!passwordIn.getText().equals(confirmpIn.getText()))
				parent.errorMessage("Your passwords do not match");
			else {
				boolean check = false; // TODO: Send username to dbc for validation
				check = true;
				if(check) {
					parent.setUsername(usernameIn.getText());
					parent.goToProfilePage(userTypeCb.getSelectedIndex());
				} else parent.errorMessage("That username has been taken");
			}
		}
	}
}