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
		if(type != 2) {
			int unread = 0;
			//TODO: Get # of unread msg from dbc
			if(type == 0) {
				pHomeP = new PatientHomePanel(unread);	changePanel(pHomeP);
			} else if(type == 1) {
				dHomeP = new DoctorHomePanel(unread);   changePanel(dHomeP); 
			}
		} else {
			aHomeP = new AdminHomePanel();		changePanel(aHomeP);
		}
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
	
	public JPanel drBuzzTitle(String head) {
		JPanel title = new JPanel();
		title.setBackground(Color.WHITE);
		title.setLayout(new BoxLayout(title, BoxLayout.Y_AXIS));
		title.add(Box.createRigidArea(new Dimension(0, 45)));
		JLabel h = new JLabel(head + "                              ");
		h.setAlignmentX(Component.RIGHT_ALIGNMENT);
		title.add(h);
		title.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel b = new JLabel(drBuzz);
		b.setAlignmentX(Component.RIGHT_ALIGNMENT);
		title.add(b);
		return title;
	}
	
	private class WelcomePanel extends JPanel {
		private JLabel title;
		private JButton newUserB;
		private JButton loginB;
		
		public WelcomePanel() {
			setLayout(new BorderLayout());
			setBackground(Color.WHITE);
			
			title = new JLabel(drBuzz, JLabel.CENTER);
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

			input.add(Box.createRigidArea(new Dimension(20, 150)));
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
			
			JPanel title = drBuzzTitle("User Registration");
			
			add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.WEST);
			add(Box.createRigidArea(new Dimension(40, 0)), BorderLayout.EAST);
			add(input, BorderLayout.CENTER);
			add(title, BorderLayout.WEST);
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
		private JButton makeAppt;
		private JButton vVisitHist;
		private JButton orderMed;
		private JButton communicate;
		private JButton unreadMsg;
		private JButton rateDoctor;
		private JButton editProfile;

		public PatientHomePanel() {
			this(0);
		}
		
		public PatientHomePanel(int num) {
			makeAppt = new JButton("Make Appointments");
			vVisitHist = new JButton("View Visit History");
			orderMed = new JButton("Order Medication");
			unreadMsg = new JButton("(" + num + ")" + " Unread Messages");
			communicate = new JButton("Communicate");
			rateDoctor = new JButton("Rate a Doctor");
			editProfile = new JButton("Edit Profile");
			constructPanel();
		}
		
		public void constructPanel() {
			setBackground(Color.WHITE);
			setLayout(new BorderLayout());
			
			makeAppt.addActionListener(new MakeApptListener());
			vVisitHist.addActionListener(new VisitHistoryListener());
			orderMed.addActionListener(new OrderMedListener());
			unreadMsg.addActionListener(new UnreadMsgListener());
			communicate.addActionListener(new CommunicateListener());
			rateDoctor.addActionListener(new RateDoctorListener());
			editProfile.addActionListener(new EditProfListener());
			
			JPanel input = new JPanel();
			input.setBackground(Color.WHITE);
			input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
			input.add(Box.createRigidArea(new Dimension(75, 200)));
			input.add(makeAppt);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(vVisitHist);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(orderMed);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(unreadMsg);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(communicate);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(rateDoctor);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(editProfile);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(Box.createRigidArea(new Dimension(0, 10)));
			
			JPanel title = drBuzzTitle("Patient Homepage");
			
			add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.WEST);
			add(Box.createRigidArea(new Dimension(40, 0)), BorderLayout.EAST);
			add(input, BorderLayout.CENTER);
			add(title, BorderLayout.WEST);
		}
		
		private class MakeApptListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		}
		
		private class VisitHistoryListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		}
		
		private class OrderMedListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		}
		
		private class UnreadMsgListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		}
		
		private class CommunicateListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		}
		
		private class RateDoctorListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		}
		
		private class EditProfListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		}
	}
	
	private class DoctorHomePanel extends JPanel {
		private JButton viewApptCal;
		private JButton patientVisits;
		private JButton recordSurgery;
		private JButton communicate;
		private JButton unreadMsg;
		private JButton editProfile;

		public DoctorHomePanel() {
			this(0);
		}
		
		public DoctorHomePanel(int num) {
			viewApptCal = new JButton("View Appointment Calendar");
			patientVisits = new JButton("Patient Visits");
			recordSurgery = new JButton("Record a Surgery");
			unreadMsg = new JButton("(" + num + ")" + " Unread Messages");
			communicate = new JButton("Communicate");
			editProfile = new JButton("Edit Profile");
			constructPanel();
		}
		
		public void constructPanel() {
			setBackground(Color.WHITE);
			setLayout(new BorderLayout());
			
			viewApptCal.addActionListener(new ViewApptCalListener());
			patientVisits.addActionListener(new PatientVisitsListener());
			recordSurgery.addActionListener(new RecordSurgeryListener());
			unreadMsg.addActionListener(new UnreadMsgListener());
			communicate.addActionListener(new CommunicateListener());
			editProfile.addActionListener(new EditProfListener());
			
			JPanel input = new JPanel();
			input.setBackground(Color.WHITE);
			input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
			input.add(Box.createRigidArea(new Dimension(75, 200)));
			input.add(viewApptCal);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(patientVisits);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(recordSurgery);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(unreadMsg);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(communicate);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(editProfile);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(Box.createRigidArea(new Dimension(0, 10)));
			
			JPanel title = drBuzzTitle("Doctor Homepage");
			
			add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.WEST);
			add(Box.createRigidArea(new Dimension(40, 0)), BorderLayout.EAST);
			add(input, BorderLayout.CENTER);
			add(title, BorderLayout.WEST);
		}
		
		private class ViewApptCalListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		}
		
		private class PatientVisitsListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		}
		
		private class RecordSurgeryListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		}
		
		private class UnreadMsgListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		}
		
		private class CommunicateListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		}
		
		private class EditProfListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		}
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
			this("", "", "Male", "", "", "", "", "", "0 - 25000", "");
		}
		
		public CreatePatientPanel(String name, String dob, String gender, String address, String homePhone, String workPhone, String weight, String height, String income, String allergies) {
			this.name = new JTextField(name, 20);
			this.dob = new JTextField(dob, 20);
			this.gender = new JComboBox(new String[]{"Male", "Female"});
			this.gender.setSelectedItem(gender);
			this.address = new JTextField(address, 20);
			this.homePhone = new JTextField(homePhone, 20);
			this.workPhone = new JTextField(workPhone, 20);
			this.weight = new JTextField(weight, 20);
			this.height = new JTextField(height, 20);
			this.income = new JComboBox(new String[]{"100000+", "50000 - 100000", "25000 - 50000", "0 - 25000"});
			this.income.setSelectedItem(income);
			this.allergies = new JTextField(allergies, 20);
			submit = new JButton("Submit");
			constructPanel();
		}
		
		private void constructPanel() {
			setBackground(Color.WHITE);
			setLayout(new BorderLayout());
			
			name.setAlignmentX(Component.LEFT_ALIGNMENT);
			dob.setAlignmentX(Component.LEFT_ALIGNMENT);
			gender.setAlignmentX(Component.LEFT_ALIGNMENT);
			address.setAlignmentX(Component.LEFT_ALIGNMENT);
			homePhone.setAlignmentX(Component.LEFT_ALIGNMENT);
			workPhone.setAlignmentX(Component.LEFT_ALIGNMENT);
			weight.setAlignmentX(Component.LEFT_ALIGNMENT);
			height.setAlignmentX(Component.LEFT_ALIGNMENT);
			income.setAlignmentX(Component.LEFT_ALIGNMENT);
			allergies.setAlignmentX(Component.LEFT_ALIGNMENT);
			submit.addActionListener(new SubmitBListener());
			
			JPanel input = new JPanel();
			input.setBackground(Color.WHITE);
			input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
			input.add(Box.createRigidArea(new Dimension(20, 10)));
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
			
			JPanel title = drBuzzTitle("Patient Profile");
			
			add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.WEST);
			add(Box.createRigidArea(new Dimension(40, 0)), BorderLayout.EAST);
			add(input, BorderLayout.CENTER);
			add(title, BorderLayout.WEST);
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
					// TODO: Send information to dbc and update corresponding patient tuple
					check = true;
					if(check) homePageSelector(0);
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
			this("", "", "", "", "", "General Physician", "", "", "");
		}
		
		public CreateDoctorPanel(String license, String firstName, String lastName, String dob, String workPhone, String speciality, String roomNo, String homeAddress, String availability) {
			this.license = new JTextField(license, 20);
			this.firstName = new JTextField(firstName, 20);
			this.lastName = new JTextField(lastName, 20);
			this.dob = new JTextField(dob, 20);
			this.workPhone = new JTextField(workPhone, 20);
			this.speciality = new JComboBox(new String[]{"General Physician", "Heart Specialist", "Eye Physician", "Orthopedics", "Psychiatry", "Gynecologist"});
			this.speciality.setSelectedItem(speciality);
			this.roomNo = new JTextField(roomNo, 20);
			this.homeAddress = new JTextField(homeAddress, 20);
			this.availability = new JTextField(availability, 20);				
			submit = new JButton("Submit");
			constructPanel();
		}
		
		public void constructPanel() {
			setBackground(Color.WHITE);
			setLayout(new BorderLayout());
			
			license.setAlignmentX(Component.LEFT_ALIGNMENT);
			firstName.setAlignmentX(Component.LEFT_ALIGNMENT);
			lastName.setAlignmentX(Component.LEFT_ALIGNMENT);
			dob.setAlignmentX(Component.LEFT_ALIGNMENT);
			workPhone.setAlignmentX(Component.LEFT_ALIGNMENT);
			speciality.setAlignmentX(Component.LEFT_ALIGNMENT);
			roomNo.setAlignmentX(Component.LEFT_ALIGNMENT);
			homeAddress.setAlignmentX(Component.LEFT_ALIGNMENT);
			availability.setAlignmentX(Component.LEFT_ALIGNMENT);
			submit.addActionListener(new SubmitBListener());
			
			JPanel input = new JPanel();
			input.setBackground(Color.WHITE);
			input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
			input.add(Box.createRigidArea(new Dimension(20, 20)));
			input.add(new JLabel("License Number: "));					input.add(license);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("First Name: "));						input.add(firstName);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Last Name: "));						input.add(lastName);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("dob: "));								input.add(dob);			input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Work Phone (###-###-####): "));		input.add(workPhone);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Speciality:"));						input.add(speciality);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Room Number:"));						input.add(roomNo);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Home Address:"));						input.add(homeAddress);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Availability (Day [0:00-0:00], Day[0:00-0:00], ...):"));	input.add(availability);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(submit);
			input.add(Box.createRigidArea(new Dimension(0, 10)));
			
			JPanel title = drBuzzTitle("Doctor Profile");
			
			add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.WEST);
			add(Box.createRigidArea(new Dimension(40, 0)), BorderLayout.EAST);
			add(input, BorderLayout.CENTER);
			add(title, BorderLayout.WEST);
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
					// TODO: Send information to dbc and update corresponding doctor tuple
					check = true;
					if(check) homePageSelector(1);
				}
			}
		}
	}
}