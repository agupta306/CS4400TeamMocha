import javax.swing.JFrame;

/**
 * Kick-starter of GTMRs Application
 * Sets up UserView and starts CreateTables
 * @author Ashutosh Gupta
 */
public class Driver {
	public static void main(String[] args) {
		UserView uv = new UserView("Georgia Tech Medical Records System");
		uv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		uv.startDBC();
	}
}
