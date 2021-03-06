import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
	private CreateTables dbc;
	private JPanel parentPanel;
	private JPanel contentPanel;
	private JPanel headPanel;
	private String username;
	
	public UserView(String title) {
		setPreferredSize(new Dimension(800, 700));
		setBackground(Color.WHITE);
		setTitle(title);
		parentPanel = new JPanel();
		parentPanel.setLayout(new BorderLayout());
		headPanel = new JPanel();
		contentPanel = new WelcomePanel(this);
		parentPanel.add(contentPanel, BorderLayout.CENTER);
		setContentPane(parentPanel);
		pack();
		setVisible(true);
		setResizable(false);
	}
	
	public JPanel drBuzzBanner(String head) {
		JPanel title = new JPanel();
		title.setLayout(new BoxLayout(title, BoxLayout.X_AXIS));
		title.setSize(new Dimension(800, 50));
		title.setBackground(new Color(207, 181, 59));
		JLabel b = new JLabel(new ImageIcon("drBuzz.png"));
		title.add(b);
		title.add(Box.createRigidArea(new Dimension(300, 50)));
		JLabel h = new JLabel(head);
		title.add(h);
		return title;
	}
	
	public void changeHeader(String head) {
		parentPanel.remove(headPanel);
		headPanel = drBuzzBanner(head);
		parentPanel.add(headPanel, BorderLayout.NORTH);
		headPanel.repaint();
	}
	
	public void changePanel(JPanel p) {
		parentPanel.remove(contentPanel);
		contentPanel = p;
		parentPanel.add(contentPanel, BorderLayout.CENTER);
		parentPanel.revalidate();
	}

	public void errorMessage(String m) { JOptionPane.showMessageDialog(this, m, "Alert", JOptionPane.CLOSED_OPTION, new ImageIcon("drBuzz.png")); }
	
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
			dbc = new CreateTables();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error: Could not connect to CS4400 Group 1 database!");
            e.printStackTrace();
        }
	}
	
	public void setUsername(String u) { username = u; }
	
}