import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewVisitHistoryPanel extends JPanel{
	private UserView parent;
	private ViewPatientVisitsPanel p;
	private JComboBox datesOfVisitsCB;
	private JLabel bp;
	private JLabel diagnosis;
	private DefaultTableModel m;
	private JTable medPresT;
	private JButton returnB;
	
	public ViewVisitHistoryPanel(UserView p) {
		parent = p;
		parent.changeHeader("View Visit History");
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setUpTable(320);
		
		JPanel requestP = new JPanel();
		requestP.setLayout(new BoxLayout(requestP, BoxLayout.X_AXIS));
		requestP.setBackground(Color.WHITE);
		requestP.setPreferredSize(new Dimension(800, 50));
		returnB = new JButton("Return Home");
		returnB.addActionListener(new ReturnBListener());
		requestP.add(Box.createRigidArea(new Dimension(325, 50)));
		requestP.add(returnB);
		add(requestP, BorderLayout.SOUTH);
	}
	
	public ViewVisitHistoryPanel(ViewPatientVisitsPanel p) {
		this.p = p;
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setUpTable(50);
	}
	
	
	private void setUpTable(int num) {
		//TODO: Populate datesOfVisitsCB with past dates from dbc
		datesOfVisitsCB = new JComboBox(new String[]{"09/22/12", "10/23/12", "3/29/14"});
		datesOfVisitsCB.addActionListener(new DateChangeListener());
		datesOfVisitsCB.setMaximumSize(datesOfVisitsCB.getPreferredSize());
		datesOfVisitsCB.setAlignmentX(Component.LEFT_ALIGNMENT);
		bp = new JLabel();			bp.setAlignmentX(Component.LEFT_ALIGNMENT);
		diagnosis = new JLabel();	diagnosis.setAlignmentX(Component.LEFT_ALIGNMENT);

		m = new DefaultTableModel();
		medPresT = new JTable(m);
		
		updateContents();
		
		JPanel input = new JPanel();
		input.setBackground(Color.WHITE);
		input.setLayout(new BoxLayout(input, BoxLayout.X_AXIS));
		input.add(Box.createRigidArea(new Dimension(num, 50)));
		input.add(new JLabel("Dates of Visits: "));
		input.add(datesOfVisitsCB);
		
		JPanel data = new JPanel();
		data.setBackground(Color.WHITE);
		data.setLayout(new BorderLayout());
		JPanel d = new JPanel();
		d.setBackground(Color.WHITE);
		d.setLayout(new BoxLayout(d, BoxLayout.Y_AXIS));
		d.add(bp);
		d.add(diagnosis);
		d.add(Box.createRigidArea(new Dimension(100, 20)));
		d.add(new JLabel("Medications Prescribed:"));
		data.add(d, BorderLayout.NORTH);
		data.add(new JScrollPane(medPresT), BorderLayout.CENTER);
		
		add(input, BorderLayout.NORTH);
		add(data, BorderLayout.CENTER);
	}
	
	
	//Based on either UserView's username or ViewPatientVisitsPanel's selected patient username
	public void updateContents() {
		//TODO: Get diastolic and systolic bp for selected date
		int sys = 120;
		int dia = 80;
		bp.setText("Blood Pressure: Systolic: " + sys + "    Diastolic: " + dia);
		
		//TODO: Get diagnoses for selected date
		String[] ds = new String[]{"Flu"};
		String lbl = "Diagnosis: ";
		for(int i = 0; i < ds.length; i++)
			lbl += ds[i];
		diagnosis.setText(lbl);
		
		//TODO: Get 2d array of prescribed meds for selected date
		String[][] rowData;
		if(datesOfVisitsCB.getSelectedIndex() == 0)
			rowData = new String[][] { {"Asprin", "3 per day", "10 days", "After every meal"}, {"Alerid", "1 per day", "10 days", "After dinner"} };
		else rowData = new String[][] { {"Asprin", "9 per day", "10 days", "After every meal"}, {"Alerid", "1 per day", "10 days", "After dinner"} };
		
		String[] columnHeaders = new String[]{"Medicine", "Dosage", "Duration", "Notes"};
		m.setColumnIdentifiers(columnHeaders);
		while(m.getRowCount() > 0) m.removeRow(0);
		for(int i = 0; i < rowData.length; i++)
			m.addRow(rowData[i]);
	}
	
	private class DateChangeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			updateContents();
		}
	}

	private class ReturnBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			parent.goToHomePage(0);
		}
	}
}
