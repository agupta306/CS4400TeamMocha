import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class ApptCalPanel extends JPanel {
	private UserView parent;
	private JPanel monthPanel;
	private JPanel dayPanel;
	private JTextField date;
	private JButton selectB;
	private JButton returnB;
	private DefaultTableModel m;
	private DefaultTableModel d;
	private JTable monthTable;
	private JTable dayTable;
	
	
	public ApptCalPanel(UserView p) {
		parent = p;
		parent.changeHeader("Appointments Calender");
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		date = new JTextField(10);
		date.setMaximumSize(date.getPreferredSize());
		date.setAlignmentX(LEFT_ALIGNMENT);
		selectB = new JButton("Select");
		selectB.addActionListener(new SelectDateBListener());
		returnB = new JButton("Return Home");
		returnB.addActionListener(new ReturnBListener());
		
		d = new DefaultTableModel();
		dayTable = new JTable(d);
		
		dayPanel = new JPanel();
		dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
		dayPanel.add(new JLabel("Enter date (MM-DD-YYYY)"));
		dayPanel.add(date);
		dayPanel.add(selectB);
		dayPanel.add(new JLabel("Daily Summary:"));
		dayPanel.add(new JScrollPane(dayTable));
		
		m = new DefaultTableModel();
		monthTable = new JTable(m);
		
		monthPanel = new JPanel();
		monthPanel.setLayout(new BoxLayout(monthPanel, BoxLayout.Y_AXIS));
		monthPanel.add(new JLabel("Monthly Summary:"));
		monthPanel.add(new JScrollPane(monthTable));
		
		updateTables();
		
		add(dayPanel, BorderLayout.WEST);
		add(monthPanel, BorderLayout.CENTER);
		add(returnB, BorderLayout.SOUTH);
	}
	
	public void updateTables() {
		//TODO: get 2D array values for tables from dbc
		String[][] dData;
		String[][] mData;
		if(date.getText() == "10-10-2014") {
			dData = new String[][] { {"George Burdell", "12:30 - 1:00"}, {"John Smith", "3:00 - 4:00"}};
			mData = new String[][] { {"1", "4"}, {"12", "2"}};
		} else {
			dData = new String[][] { {"John Smith", "12:30 - 1:00"}, {"George Burdell", "3:00 - 4:00"}};
			mData = new String[][] { {"5", "3"}, {"8", "2"}};
		}
		
		d.setColumnIdentifiers(new String[]{"Patient", "Schedule Time"});
		m.setColumnIdentifiers(new String[] {"Day", "Appointments"});
		
		while(d.getRowCount() > 0) d.removeRow(0);
		for(int i = 0; i < dData.length; i++)
			d.addRow(dData[i]);
		while(m.getRowCount() > 0) m.removeRow(0);
		for(int i = 0; i < mData.length; i++)
			m.addRow(mData[i]);
		
	}
	
	private class SelectDateBListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(date.getText().length() == 10) {
				updateTables();
				monthTable.repaint();
				dayTable.repaint();
			} else parent.errorMessage("Date field is empty!");
		}
	}
	
	private class ReturnBListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			parent.goToHomePage(1);
		}
	}
}
