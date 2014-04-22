import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
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
		parent.changeHeader("Welcome to GTMRS");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		
		JLabel img = new JLabel(new ImageIcon("GeorgiaTechSeal.svg.png"));	img.setAlignmentX(Component.CENTER_ALIGNMENT);
		newUserB = new JButton("New User");									newUserB.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginB = new JButton("Login");										loginB.setAlignmentX(Component.CENTER_ALIGNMENT);
		newUserB.addActionListener(new NewUserBListener());
		loginB.addActionListener(new LoginBListener());
		
		add(Box.createRigidArea(new Dimension(0, 40)));
		add(img);
		add(Box.createRigidArea(new Dimension(0, 40)));
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
			//Construct login dialog
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
			JTextField usernameIn = new JTextField(20);		usernameIn.setMaximumSize(usernameIn.getPreferredSize());
			JTextField passwordIn = new JPasswordField(20); passwordIn.setMaximumSize(passwordIn.getPreferredSize());
			usernameIn.setAlignmentX(Component.LEFT_ALIGNMENT);
			passwordIn.setAlignmentX(Component.LEFT_ALIGNMENT);
			p.add(new JLabel("Username: "));
			p.add(usernameIn);
			p.add(new JLabel("Password: "));
			p.add(passwordIn);
			int result = JOptionPane.showConfirmDialog(parent, p, "User Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			if(result == JOptionPane.OK_OPTION) {
				if(usernameIn.getText().equals("") || passwordIn.getText().equals(""))			//Check for syntactic errors on GUI-side
						parent.errorMessage("Please fill in all fields");
				else {
					int check = Queries.login(usernameIn.getText(), passwordIn.getText());		//Check for semantic errors on databse-side
					if(check == -1)
						parent.errorMessage("Invalid Username/Password combination. Please try again.");
					else {
						parent.setUsername(usernameIn.getText());
						parent.goToHomePage(check);
					}
				}
			}
		}
	}
}
