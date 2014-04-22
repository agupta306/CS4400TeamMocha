import javax.swing.table.AbstractTableModel;


public class MakeApptTableModel extends AbstractTableModel {
	String[] columnHeaders = {"Doctor", "Phone Number", "Room #", "Time slot", "Selected", "Average Rating"};
	Object[][] rowData;

	public void setData(Object[][] data) {
		rowData = data;
	}
	
	public int getRowCount() { return rowData.length; }
	
	public int getColumnCount() { return columnHeaders.length; }
	
	public Object getValueAt(int rowIndex, int columnIndex) { return rowData[rowIndex][columnIndex]; }
	
	public String getColumnName(int column) { return columnHeaders[column]; }

	public Class getColumnClass(int column) { return (getValueAt(0, column).getClass()); }

	public void setValueAt(Object value, int row, int column) { rowData[row][column] = value; }

	public boolean isCellEditable(int row, int column) { return (column == 4); } //Can only edit Selected column
}
