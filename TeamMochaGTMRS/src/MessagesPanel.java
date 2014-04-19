import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class MessagesPanel extends JPanel {
	private UserView parent;
	private DefaultTableModel m;
	private JTable inboxTable;
	private JButton returnB;
	private JComboBox recepientIn;
	private JTextField contentIn;
	private JButton sendB;
	private int docOrP;
	
	public MessagesPanel(UserView p, int dP) {
		docOrP = dP;
		parent = p;
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		JPanel draftPanel = new JPanel();
		draftPanel.setLayout(new BoxLayout(draftPanel, BoxLayout.Y_AXIS));
		//TODO:Get all possible recepients for this user from dbc
		String[] people = new String[]{"Dr. A", "Dr. B", "Dr. C"};
		recepientIn = new JComboBox(people);
		contentIn = new JTextField();
		sendB = new JButton("Send Message");
		sendB.addActionListener(new SendBListener());
		draftPanel.add(new JLabel("Recepient Name"));	draftPanel.add(recepientIn);
		draftPanel.add(new JLabel("Message Content"));	draftPanel.add(contentIn);
		draftPanel.add(sendB);
		
		m = new DefaultTableModel();
		inboxTable = new JTable(m);
		//TODO: Query dbc for table of messages
		String[] columnHeaders = new String[]{"Status", "Date", "From", "Message"};
		m.setColumnIdentifiers(columnHeaders);
		returnB = new JButton("Return Home");
		returnB.addActionListener(new ReturnBListener());
		
		add(draftPanel, BorderLayout.CENTER);
		add(new JScrollPane(inboxTable), BorderLayout.WEST);
		add(returnB, BorderLayout.SOUTH);
	}
	
	private class SendBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Send message data to dbc, clear draftPanel fields
		}
	}
	
	private class ReturnBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Mark all messages as read in dbc
			parent.goToHomePage(docOrP);
		}
	}
}
