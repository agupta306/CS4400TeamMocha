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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * View Layer of application
 * Handles all user-generated events
 * @author Ashutosh Gupta
 */
public class UserView extends JFrame{
	private DBConnector dbc;
	private JPanel parentPanel;
	private JPanel contentPanel;
	private String username;
	
	public UserView(String title) {
		setPreferredSize(new Dimension(800, 700));
		setBackground(Color.WHITE);
		setTitle(title);
		
		parentPanel = new JPanel();
		parentPanel.setLayout(new BorderLayout());
		parentPanel.add(drBuzzBanner("Welcome to GTMRS"), BorderLayout.NORTH);
		contentPanel = new WelcomePanel(this);
		parentPanel.add(contentPanel, BorderLayout.CENTER);
		setContentPane(parentPanel);
		pack();
		setVisible(true);
		setResizable(false);
	}
	
	public JPanel drBuzzBanner(String head) {
		JPanel title = new JPanel();
		title.setBackground(Color.WHITE);
		JLabel b = new JLabel(new ImageIcon("drBuzzTiny.jpg"));
		title.add(b);
		JLabel h = new JLabel("          " + head);
		title.add(h);
		return title;
	}
	
	public void changePanel(JPanel p) {
		parentPanel.remove(contentPanel);
		contentPanel = p;
		parentPanel.add(contentPanel, BorderLayout.CENTER);
		parentPanel.revalidate();
	}

	public void errorMessage(String m) { JOptionPane.showMessageDialog(this, m, "Alert", JOptionPane.CLOSED_OPTION, new ImageIcon("drBuzzTiny.jpg")); }
	
	public void goToHomePage(int type) {
		if(type != 2) {
			int unread = 0; // TODO: Get # of unread msgs from dbc using username
			if(type == 0) changePanel(new PatientHomePanel(this, unread));
			else if(type == 1) changePanel(new DoctorHomePanel(this, unread));
		} else changePanel(new AdminHomePanel(this));
	}
	
	public void goToProfilePage(int type) {
		if(type == 0) {
			// TODO: Get patient information from dbc using username
			changePanel(new PatientProfilePanel(this, "", "", "Male", "", "", "", "", "", "0 - 25000", ""));
		} else if(type == 1) {
			// TODO: Get doctor information from dbc using username
			changePanel(new DoctorProfilePanel(this, "", "", "", "", "", "General Physician", "", "", ""));
		}
	}
	
	public void startDBC() {
		try {
			dbc = new DBConnector();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error: Could not connect to CS4400 Group 1 database!");
            e.printStackTrace();
        }
	}
	
	public void setUsername(String u) { username = u; }
	
}
	/*
	

	private class OrderMedPanel extends JPanel {
		private JTextField medNameF;
		private JTextField dosageF;
		private JTextField durationF;
		private JTextField doctorF;
		private JTextField presDateF;
		private JButton addCartB;
		private JButton checkoutB;
		
		public OrderMedPanel() {
			setBackground(Color.WHITE);
			setLayout(new BorderLayout());
			JPanel buzz = drBuzzTitle("Order Medications");
			
			medNameF = new JTextField();
			dosageF = new JTextField();
			durationF = new JTextField();
			doctorF = new JTextField();
			presDateF = new JTextField();
			addCartB = new JButton("Add to Cart");
			addCartB.addActionListener(new AddCartBListener());
			checkoutB = new JButton("Checkout Cart");
			checkoutB.addActionListener(new CheckoutBListener());
			
			medNameF.setAlignmentX(Component.LEFT_ALIGNMENT);
			dosageF.setAlignmentX(Component.LEFT_ALIGNMENT);
			durationF.setAlignmentX(Component.LEFT_ALIGNMENT);
			doctorF.setAlignmentX(Component.LEFT_ALIGNMENT);
			presDateF.setAlignmentX(Component.LEFT_ALIGNMENT);
			addCartB.setAlignmentX(Component.LEFT_ALIGNMENT);
			checkoutB.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			JPanel input = new JPanel();
			input.setBackground(Color.WHITE);
			input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
			input.add(Box.createRigidArea(new Dimension(20, 100)));
			input.add(new JLabel("Medication Name: "));			input.add(medNameF);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Dosage (per day): "));		input.add(dosageF);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Duration (mm/dd): "));		input.add(durationF);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Consulting Doctor: "));		input.add(doctorF);		input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(new JLabel("Date of Prescription: "));	input.add(presDateF);	input.add(Box.createRigidArea(new Dimension(0, 5)));
			input.add(addCartB);
			input.add(checkoutB);
			
			input.add(Box.createRigidArea(new Dimension(0, 110)));
			
			
			add(buzz, BorderLayout.WEST);
			add(input, BorderLayout.CENTER);
		}
		
		private class AddCartBListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				//Try to insert order into dbc
				//if failed, show error message
				//else, display dialogue confirming addition
			}
		}
		
		private class CheckoutBListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				//show dialogue with payment information
					//if first time, have editable textfields and store info to dbc
					//else, just display text
				//if confirmed, send information to dbc and changePanel back to patient homepage
			}
		}
	}
}
	*/