import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection to CS4400 Group 1 database
 * Sends and receives information using embedded SQL queries 
 * @author Ashutosh Gupta
 */
public class DBConnector {
	
	private static Connection conn;
	
	public DBConnector() throws SQLException{
        //Test JDBC MySQL driver on this machine
        try {
            System.out.println("Loading MySQL-Connector-Java-5.1.30 driver...");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find the driver in the classpath!", e);
        }

        //Setting up connection
        String hostIP = "130.207.114.235";
        String dbUser = "cs4400_Group_1";   //Username from MySQLREADME
        String dbPass = "ZfJnA2eA";         //Password from MySQLREADME

        System.out.println("Logging into CS4400_Group_1 database via JDBC");
        String url = "jdbc:mysql://" + hostIP + "/" + dbUser;       //The database name happens to match our username
        conn = DriverManager.getConnection(url, dbUser, dbPass);
        System.out.println("Connection successfuly established. Standing by.");
    }
	
	public Connection getConnection() {
		return conn;
	}
}
