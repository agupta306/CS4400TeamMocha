import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Initial panel of GTMRS. Presents users with the options of
 * logging in or creating a new profile
 * @author Ashutosh Gupta
 */
public class WelcomePanel extends JPanel {
	private UserView parent;
	private JButton newUserB;
	private JButton loginB;
	
	public WelcomePanel(UserView p) {
		parent = p;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		
		newUserB = new JButton("New User");
		loginB = new JButton("Login");
		newUserB.addActionListener(new NewUserBListener());
		loginB.addActionListener(new LoginBListener());
		
		add(newUserB);
		add(loginB);
	}
	
	private class NewUserBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JPanel p = new CreateNewUserPanel(parent);
			parent.changePanel(p);
		}
	}
	
	private class LoginBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
			
			JTextField usernameIn = new JTextField(20);
			JTextField passwordIn = new JPasswordField(20);
			
			p.add(new JLabel("Username: "));
			p.add(usernameIn);
			p.add(new JLabel("Password: "));
			p.add(passwordIn);
			
			JOptionPane.showMessageDialog(parent, p, "User Login", JOptionPane.CLOSED_OPTION, new ImageIcon("drBuzzTiny.jpg"));
			
			if(usernameIn.getText().equals("") || passwordIn.getText().equals(""))
				parent.errorMessage("Please fill in all fields");
			else {
				int check = -1;
				// TODO: Send queries to dbc to confirm username/password
				// TODO: Retrieve type of user (0 - patient, 1 - doctor, 2 - admin, -1 - error)
				check = 0;
				if(check == -1)
					parent.errorMessage("Incorrect Username/Password. Please try again.");
				else {
					parent.setUsername(usernameIn.getText());
					parent.goToHomePage(check);
				}
			}
		}
	}
}
