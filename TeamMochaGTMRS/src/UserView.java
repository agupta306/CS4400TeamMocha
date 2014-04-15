import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * View Layer of application
 * Handles all user-generated events
 * @author Ashutosh Gupta
 */
public class UserView extends JFrame{
	private DBConnector dbc;
	private JPanel loginP;
	
	private JPanel newUserP;
	private JPanel pCreateP;
	private JPanel dCreateP;
	
	private JPanel aHomeP;
	private JPanel pHomeP;
	private JPanel dHomeP;
	
	private ImageIcon drBuzz;
	
	public UserView (String title) {
		setTitle(title);
		drBuzz = new ImageIcon("drBuzz.jpg");
		loginP = new WelcomePanel();
		setContentPane(loginP);
		setPreferredSize(new Dimension(800, 600));
		setResizable(false);
		setBackground(Color.WHITE);
		pack();
		setVisible(true);
	}
	
	public void homePageSelector(int type) {
		if(type == 0) 		 { 	pHomeP = new PatientHomePanel();	changePanel(pHomeP); }
		else if(type == 1)   {	dHomeP = new DoctorHomePanel();		changePanel(dHomeP); }
		else 				 {	aHomeP = new AdminHomePanel();		changePanel(aHomeP); }
	}
	
	public void creationPageSelector(int type) {
		if(type == 0) 		 { 	pCreateP = new CreatePatientPanel();	changePanel(pCreateP); }
		else if(type == 1)   {	dCreateP = new CreateDoctorPanel();		changePanel(dCreateP); }
		else 				 {	aHomeP = new AdminHomePanel();			changePanel(aHomeP);   }
	}

	public void changePanel(JPanel p) {
		setContentPane(p);
		revalidate();
	}
	
	public void startDBC() {
		try {
			dbc = new DBConnector();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error: Could not connect to CS4400 Group 1 database!");
            e.printStackTrace();
        }
	}

	private void errorMsg(JComponent p, String m) {
		JOptionPane.showMessageDialog(p, m, "Alert", JOptionPane.CLOSED_OPTION, new ImageIcon("drBuzzTiny.jpg"));
	}
	
	private class WelcomePanel extends JPanel {
		private JLabel title;
		private JButton newUserB;
		private JButton loginB;
		
		public WelcomePanel() {
			setLayout(new BorderLayout());
			setBackground(Color.WHITE);
			
			title = new JLabel(drBuzz);
			newUserB = new JButton("New User");
			newUserB.addActionListener(new NewUserBListener());
			loginB = new JButton("Login");
			loginB.addActionListener(new LoginBListener());
			
			JPanel options = new JPanel();
			options.add(newUserB);
			options.add(loginB);
			options.setBackground(Color.WHITE);
			
			add(title, BorderLayout.CENTER);
			add(options, BorderLayout.SOUTH);
		}
		
		private class NewUserBListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				newUserP = new NewUserPanel();
				changePanel(newUserP);
			}
		}
		
		private class LoginBListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JPanel input = new JPanel();
				input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
				input.setAlignmentX(Component.LEFT_ALIGNMENT);
				JTextField username = new JTextField(20);
				JTextField password = new JPasswordField(20);
				input.add(new JLabel("Username: "));
				input.add(username);
				input.add(new JLabel("Password: "));
				input.add(password);
				JOptionPane.showMessageDialog(loginP, input, "User Login", JOptionPane.CLOSED_OPTION, new ImageIcon("drBuzzTiny.jpg"));
				
				if(username.getText().equals("") || password.getText().equals(""))
					errorMsg(loginP, "Please fill in all fields");
				else {
					int check = -1; 
					// TODO: Send queries to dbc to confirm username/password and retrieve type of user
					check = 0;
					
					if(check == -1) errorMsg(loginP, "Incorrect Username/Password. Please try again.");
					else homePageSelector(check);
				}
			}
		}
	}
	
	private class NewUserPanel extends JPanel{
		private JTextField username;
		private JTextField password;
		private JTextField confirmpass;
		private JComboBox type;
		
		public NewUserPanel() {
			setBackground(Color.WHITE);
			setLayout(new BorderLayout());
			
			JPanel input = new JPanel();
			input.setBackground(Color.WHITE);
			input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
			username = new JTextField(20);
			username.setAlignmentX(Component.LEFT_ALIGNMENT);
			password = new JPasswordField(20);
			password.setAlignmentX(Component.LEFT_ALIGNMENT);
			confirmpass = new JPasswordField(20);
			confirmpass.setAlignmentX(Component.LEFT_ALIGNMENT);
			type = new JComboBox(new String[]{"Patient", "Doctor", "Admin"});
			type.setAlignmentX(Component.LEFT_ALIGNMENT);
			JButton confirm = new JButton("Register");
			confirm.setAlignmentX(Component.LEFT_ALIGNMENT);
			confirm.addActionListener(new RegisterBListener());
			
			input.add(Box.createRigidArea(new Dimension(0, 150)));
			input.add(new JLabel("Username: "));
			input.add(username);
			input.add(Box.createRigidArea(new Dimension(0, 20)));
			input.add(new JLabel("Password: "));
			input.add(password);
			input.add(Box.createRigidArea(new Dimension(0, 20)));
			input.add(new JLabel("Confirm Password: "));
			input.add(confirmpass);
			input.add(Box.createRigidArea(new Dimension(0, 20)));
			input.add(new JLabel("Type of User: "));
			input.add(type);
			input.add(Box.createRigidArea(new Dimension(0, 20)));
			input.add(confirm);
			input.add(Box.createRigidArea(new Dimension(0, 150)));

			add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.WEST);
			add(Box.createRigidArea(new Dimension(40, 0)), BorderLayout.EAST);
			add(input, BorderLayout.CENTER);
			add(new JLabel(drBuzz), BorderLayout.WEST);
		}
		
		private class RegisterBListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(username.getText().equals("") || password.getText().equals("") || confirmpass.getText().equals(""))
					errorMsg(newUserP, "Please fill in all fields");
				else if(!password.getText().equals(confirmpass.getText()))
					errorMsg(newUserP, "Your passwords do not match");
				else {
					boolean check = false;
					// TODO: Send username to dbc for validation
					check = true;
					if(check) creationPageSelector(type.getSelectedIndex());
					else errorMsg(newUserP, "That username has been taken");
				}
			}
		}
	}
	
	private class AdminHomePanel extends JPanel {
		public AdminHomePanel() {
			
		}
	}
	
	private class PatientHomePanel extends JPanel {
		
	}
	
	private class DoctorHomePanel extends JPanel {
		
	}

	private class CreatePatientPanel extends JPanel {
		private JTextField name;
		private JTextField dob;
		private JComboBox gender;
		private JTextField address;
		private JTextField homePhone;
		private JTextField workPhone;
		private JTextField weight;
		private JTextField height;
		private JComboBox income;
		private JTextField allergies;
		private JButton submit;
		
		
		public CreatePatientPanel() {
			setBackground(Color.WHITE);
			setLayout(new BorderLayout());
			
			JPanel input = new JPanel();
			input.setBackground(Color.WHITE);
			input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
			name = new JTextField(20);								name.setAlignmentX(Component.LEFT_ALIGNMENT);
			dob = new JTextField(20);								dob.setAlignmentX(Component.LEFT_ALIGNMENT);
			gender = new JComboBox(new String[]{"Male", "Female"});	gender.setAlignmentX(Component.LEFT_ALIGNMENT);
			address = new JTextField(20);							address.setAlignmentX(Component.LEFT_ALIGNMENT);
			homePhone = new JTextField(20);							homePhone.setAlignmentX(Component.LEFT_ALIGNMENT);
			workPhone = new JTextField(20);							workPhone.setAlignmentX(Component.LEFT_ALIGNMENT);
			weight = new JTextField(20);							weight.setAlignmentX(Component.LEFT_ALIGNMENT);
			height = new JTextField(20);							height.setAlignmentX(Component.LEFT_ALIGNMENT);
			income = new JComboBox(new String[]{"100000+", "50000 - 100000", "25000 - 50000", "0 - 25000"});
			income.setAlignmentX(Component.LEFT_ALIGNMENT);
			allergies = new JTextField(20);							allergies.setAlignmentX(Component.LEFT_ALIGNMENT);
			JButton submit = new JButton("Submit");					submit.addActionListener(new SubmitBListener());
			input.add(Box.createRigidArea(new Dimension(0, 10)));
			input.add(new JLabel("Patient Name: "));					input.add(name);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Date of Birth: "));					input.add(dob);			input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Gender: "));							input.add(gender);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Address: "));							input.add(address);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Home Phone (###-###-####): "));		input.add(homePhone);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Work Phone (###-###-####): "));		input.add(workPhone);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Weight (lbs):"));						input.add(weight);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Height (in):"));						input.add(height);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Annual Income ($):"));				input.add(income);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Allergies (dairy, pollen, ...):"));	input.add(allergies);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(submit);
			input.add(Box.createRigidArea(new Dimension(0, 10)));
			add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.WEST);
			add(Box.createRigidArea(new Dimension(40, 0)), BorderLayout.EAST);
			add(input, BorderLayout.CENTER);
			add(new JLabel(drBuzz), BorderLayout.WEST);
		}
		
		private class SubmitBListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(name.getText().equals("") || dob.getText().equals("") || address.getText().equals("")
				|| homePhone.getText().equals("") || workPhone.getText().equals("") || weight.getText().equals("")
				|| height.getText().equals(""))
					errorMsg(pCreateP, "Please fill in all fields");
				//Regex checks for fields?
				else {
					boolean check = false;
					// TODO: Send information to dbc and check uniqueness of homePhone and name
					check = true;
					pHomeP = new PatientHomePanel();
					if(check) changePanel(pHomeP);
				}
			}
		}
	}
	
	private class CreateDoctorPanel extends JPanel {
		private JTextField license;
		private JTextField firstName;
		private JTextField lastName;
		private JTextField dob;
		private JTextField workPhone;
		private JComboBox speciality;
		private JTextField roomNo;
		private JTextField homeAddress;
		private JTextField availability;
		private JButton submit;
		
		
		public CreateDoctorPanel() {
			setBackground(Color.WHITE);
			setLayout(new BorderLayout());
			
			JPanel input = new JPanel();
			input.setBackground(Color.WHITE);
			input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
			license = new JTextField(20);							license.setAlignmentX(Component.LEFT_ALIGNMENT);
			firstName = new JTextField(20);							firstName.setAlignmentX(Component.LEFT_ALIGNMENT);
			lastName = new JTextField(20);							lastName.setAlignmentX(Component.LEFT_ALIGNMENT);
			dob = new JTextField(20);								dob.setAlignmentX(Component.LEFT_ALIGNMENT);
			workPhone = new JTextField(20);							workPhone.setAlignmentX(Component.LEFT_ALIGNMENT);
			speciality = new JComboBox(new String[]{"General Physician", "Heart Specialist", "Eye Physician", "Orthopedics", "Psychiatry", "Gynecologist"}); speciality.setAlignmentX(Component.LEFT_ALIGNMENT);
			roomNo = new JTextField(20);							roomNo.setAlignmentX(Component.LEFT_ALIGNMENT);
			homeAddress = new JTextField(20);						homeAddress.setAlignmentX(Component.LEFT_ALIGNMENT);
			availability = new JTextField(20);						availability.setAlignmentX(Component.LEFT_ALIGNMENT);
			JButton submit = new JButton("Submit");					submit.addActionListener(new SubmitBListener());
			
			input.add(Box.createRigidArea(new Dimension(0, 20)));
			input.add(new JLabel("License Number: "));					input.add(license);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("First Name: "));						input.add(firstName);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Last Name: "));						input.add(lastName);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("dob: "));								input.add(dob);			input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Work Phone (###-###-####): "));		input.add(workPhone);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Speciality:"));						input.add(speciality);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Room Number:"));						input.add(roomNo);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Home Address:"));						input.add(homeAddress);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Availability (Day [0:00-0:00], Day[0:00-0:00], ...):"));	input.add(availability);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			//Change to JSpinners?
			input.add(submit);
			
			input.add(Box.createRigidArea(new Dimension(0, 10)));
			add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.WEST);
			add(Box.createRigidArea(new Dimension(40, 0)), BorderLayout.EAST);
			add(input, BorderLayout.CENTER);
			add(new JLabel(drBuzz), BorderLayout.WEST);
		}
		
		private class SubmitBListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(license.getText().equals("") || firstName.getText().equals("") || lastName.getText().equals("")
				|| dob.getText().equals("") || workPhone.getText().equals("") || roomNo.getText().equals("")
				|| homeAddress.getText().equals("") || availability.getText().equals(""))
					errorMsg(dCreateP, "Please fill in all fields");
				//Regex checks for fields?
				else {
					boolean check = false;
					// TODO: Send information to dbc and check uniqueness of homePhone and name
					check = true;
					dHomeP = new DoctorHomePanel();
					if(check) changePanel(dHomeP);
				}
			}
		}
	}
}
