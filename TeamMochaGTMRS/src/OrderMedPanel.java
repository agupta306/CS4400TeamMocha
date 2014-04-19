import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OrderMedPanel extends JPanel{
	private UserView parent;
	private JTextField medNameIn;
	private JTextField dosageIn;
	private JTextField durationIn;
	private JTextField doctorIn;
	private JTextField presDateIn;
	private JButton addCartB;
	private JButton checkoutB;
	
	public OrderMedPanel(UserView p) {
		parent = p;
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		medNameIn = new JTextField();					medNameIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		dosageIn = new JTextField();					dosageIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		durationIn = new JTextField();					durationIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		doctorIn = new JTextField();					doctorIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		presDateIn = new JTextField();					presDateIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		addCartB = new JButton("Add to Cart");			addCartB.setAlignmentX(Component.LEFT_ALIGNMENT);
		checkoutB = new JButton("Checkout Cart");		checkoutB.setAlignmentX(Component.LEFT_ALIGNMENT);
		addCartB.addActionListener(new AddCartBListener());
		checkoutB.addActionListener(new CheckoutBListener());

		add(new JLabel("Medication Name: "));		add(medNameIn);		add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Dosage (per day): "));		add(dosageIn);		add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Duration (mm/dd): "));		add(durationIn);	add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Consulting Doctor: "));		add(doctorIn);		add(Box.createRigidArea(new Dimension(0, 5)));
		add(new JLabel("Date of Prescription: "));	add(presDateIn);	add(Box.createRigidArea(new Dimension(0, 5)));
		add(addCartB);
		add(checkoutB);
		add(Box.createRigidArea(new Dimension(0, 110)));
	}
	
	private class AddCartBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Try to insert order into dbc
			//if failed, show error message
			//else, display dialogue confirming addition and clear fields
		}
	}
	
	private class CheckoutBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//show dialogue with payment information
				//if first time, have editable textfields and store info to dbc
				//else, just display text
			//if confirmed, send information to dbc and changePanel back to patient homepage
			parent.goToHomePage(0);
		}
	}
}