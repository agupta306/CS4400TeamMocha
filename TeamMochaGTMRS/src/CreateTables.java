import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Creates tables for GTMRS in the mySQL database.
 * @author Annette Almonte Malagon
 */
public class CreateTables {
	
	private DBConnector dbc;
	private static Connection conn;

	/**
	 * Create all the tables needed in the database.
	 */
	public CreateTables() throws SQLException {
		connectDB();
		//makeTable();
		makeQuery();
		/*makeTable("USER " +
					"(USERNAME varchar(50) NOT NULL, " +
					"PASSWORD varchar(50) NOT NULL, " +
					"PRIMARY KEY ('USERNAME'))");*/	
	}
	
	/**
	 * Connect to the database. 
	 */
	public void connectDB() {
		try {
			dbc = new DBConnector();
			conn = dbc.getConnection();	
		} catch (SQLException e) {
			System.out.println("Error: Connection to CS4400 Group 1 database failed!");
            e.printStackTrace();
        }
	}	
	
	/**
	 * Create a single table given the CREATE TABLE statements String.
	 * 
	 * @String statement the CREATE TABLE statement.
	 */
	public void makeTable() throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        System.out.println("yo");
	        stmt.executeUpdate("insert into cs4400_Group_1.USER values('jconley','apple')"); //makes the table
	        System.out.println("woo: " + stmt);
	    } catch (SQLException e) {
	        System.out.println("Error: Could not create tables. Connection to database failed!");
	        e.printStackTrace();
	    } finally {
	        if (stmt != null) { stmt.close(); }
	    }	
	}
	
	public static void main(String[] args) throws SQLException {
		new CreateTables();
	}
	
	public void makeQuery() throws SQLException {
		Statement stmt = null;
		String query = "select * " +
		               "from cs4400_Group_1.USER";
		try {
		    stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String uname = rs.getString("username");
		        String pass= rs.getString("password");
		        System.out.println(uname + "\t" + pass);
		    }
		} catch (SQLException e ) {
		    e.printStackTrace();
		} finally {
		    if (stmt != null) { stmt.close(); }
		}		
		
	}  
}