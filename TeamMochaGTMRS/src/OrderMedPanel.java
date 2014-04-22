import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
		parent.changeHeader("Order Medication");
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel input = new JPanel();
		input.setBackground(Color.WHITE);
		input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
		
		medNameIn = new JTextField(20);					medNameIn.setMaximumSize(medNameIn.getPreferredSize());		medNameIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		dosageIn = new JTextField(20);					dosageIn.setMaximumSize(dosageIn.getPreferredSize());		dosageIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		durationIn = new JTextField(20);				durationIn.setMaximumSize(durationIn.getPreferredSize());	durationIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		doctorIn = new JTextField(20);					doctorIn.setMaximumSize(doctorIn.getPreferredSize());		doctorIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		presDateIn = new JTextField(20);				presDateIn.setMaximumSize(presDateIn.getPreferredSize());	presDateIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		addCartB = new JButton("Add to Cart");			addCartB.setAlignmentX(Component.LEFT_ALIGNMENT);
		checkoutB = new JButton("Checkout");			checkoutB.setAlignmentX(Component.LEFT_ALIGNMENT);
		addCartB.addActionListener(new AddCartBListener());
		checkoutB.addActionListener(new CheckoutBListener());

		input.add(new JLabel("Medication Name: "));			input.add(medNameIn);	input.add(Box.createRigidArea(new Dimension(0, 20)));
		input.add(new JLabel("Dosage (per day): "));		input.add(dosageIn);	input.add(Box.createRigidArea(new Dimension(0, 20)));
		input.add(new JLabel("Duration (mm/dd): "));		input.add(durationIn);	input.add(Box.createRigidArea(new Dimension(0, 20)));
		input.add(new JLabel("Consulting Doctor: "));		input.add(doctorIn);	input.add(Box.createRigidArea(new Dimension(0, 20)));
		input.add(new JLabel("Date of Prescription: "));	input.add(presDateIn);	input.add(Box.createRigidArea(new Dimension(0, 20)));
		input.add(addCartB);
		input.add(checkoutB);
		input.add(Box.createRigidArea(new Dimension(0, 110)));
		
		add(Box.createRigidArea(new Dimension(100, 0)));
		add(new JLabel(new ImageIcon("med_icon.jpg")));
		add(Box.createRigidArea(new Dimension(50, 0)));
		add(input);
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