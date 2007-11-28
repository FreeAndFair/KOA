import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This class is responsible for all connection requests to the mySQL server.
 * An instance is created by the server, which is conseqiently used to manipulate and 
 * retrieve relevent information from the database.
 */
public class StatementExecutor {
	private String URL;
	private String dbusername;
	private String dbpassword;
	private Connection con;
	private int count = 0;
	
	/**
	 * Constructor which initiates a connection to the database specified
	 * using the JDBC driver.
	 */
	public StatementExecutor() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			URL = "jdbc:mysql://csserver.ucd.ie/voting"; 
			dbusername = "s01bf213";
			dbpassword = "eamonnlawler";
			con = DriverManager.getConnection(URL,dbusername,dbpassword);
		}
		catch (SQLException e) {
			System.out.println("Problem with connection: " + e.getMessage());
		}
		catch (ClassNotFoundException f) {
			System.out.println("Problem with class: " + f.getMessage());
		}
	}
	
	/**
	 * Retieves all information pertaining to the topics if they are currently active.
	 * @return List representing the topics and all associated data.
	 */
	public ArrayList showTopics() {
		ArrayList topicList = new ArrayList();
		try {
			Calendar c = Calendar.getInstance();
			
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery("select topic, image, idNum, sms, smsnum, styear, stmonth, stday, expyear, " +
			"expmonth, expday from topic;");
			while(result.next()) {
				int stYear = result.getInt("styear");
				int stMonth = result.getInt("stmonth")-1;
				int stDay = result.getInt("stday");  
				
				int retYear = result.getInt("expyear");
				int retMonth = result.getInt("expmonth")-1;
				int retDay = result.getInt("expday");             
				
				Calendar retStDate = Calendar.getInstance();
				retStDate.set(stYear,stMonth,stDay);
				Calendar retEndDate = Calendar.getInstance();
				retEndDate.set(retYear,retMonth,retDay);
				
				if((c.compareTo(retStDate) > 0) && (c.compareTo(retEndDate) < 0)) {
					topicList.add(result.getString("topic"));
					topicList.add(String.valueOf(result.getInt("idNum")));
					topicList.add(result.getBytes("image"));
					topicList.add(String.valueOf(result.getInt("sms")));
					topicList.add(String.valueOf(result.getLong("smsnum")));
				}	
			}
		}
		catch (SQLException e) {
			System.out.println("Problem with connection: " + e.getMessage());
		}
		return topicList;	
	}
	
	/**
	 * Retrieves choices and associated information (images) for the specified ID.
	 * @param topicId The ID for which to retrieve associated choices.
	 * @return List representing all possible choices for the topic.
	 */
	public ArrayList showAnswers(int topicId) {
		ArrayList topicList = new ArrayList();
		try {            
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery("select topicAnswers, idNum, ansimage " + 
					"from topicAnswers where topicId = " + topicId + ";");
			while (result.next()) {
				topicList.add(result.getString("topicAnswers"));
				topicList.add(String.valueOf(result.getInt("idNum")));
				topicList.add(result.getBytes("ansimage"));
				count++;
			}
		}
		catch (SQLException e) {
			System.out.println("Problem with connection: " +e.getMessage());
		}
		return topicList;
	}
	
	/**
	 * Register a vote.
	 * @param ansID The answer ID whose field is to be incremented.
	 * @return Boolean indicating whether the vote was successfully registered.
	 */
	public boolean voteRegister(int ansID) {
		int register = 0;
		boolean wasRegistered = false;
		try {              
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery("select registerAns from topicAnswers " + 
					"where idNum = " + ansID);
			
			while (result.next()) {
				register = result.getInt("registerAns");
			}
			register++;
			stmt.executeUpdate("UPDATE topicAnswers SET registerAns = " + register + 
					" where idNum = " + ansID);
			wasRegistered = true;
		}
		catch (SQLException e) {
			System.out.println("Problem with connection: " + e.getMessage());            
		}
		return wasRegistered;
	}
	
	/**
	 * Vote statistics.
	 * @param topicID ID repesenting the topic for which statistics should be obtained.
	 * @return List containing vote statistic infromation.
	 */
	public ArrayList getStats(int topicID) {
		ArrayList voteState = new ArrayList();
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery("select topicAnswers, registerAns " + 
					"from topicAnswers where topicId = " + (topicID));
			while (result.next()) {
				voteState.add(result.getString("topicAnswers"));
				voteState.add(String.valueOf(result.getInt("registerAns")));
			}
		}
		catch (SQLException e) {
			System.out.println("Problem with connection: " + e.getMessage());
		}
		return voteState;
	}
	
	/**
	 * Check if an IP address already exists alongside a specified topic ID.
	 * @param receivedTopic Topic ID for which to check
	 * @param ip Host IP address for which to check.
	 * @return Boolean indicating whether the ID / IP pair already exists.
	 */
	public boolean checkIP(int receivedTopic, String ip) {
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery("select host from hostlist where topicId = " + receivedTopic);
			while (result.next()) {
				if(result.getString("host").equalsIgnoreCase(ip))
					return true;
			}
		}
		catch (SQLException e) {
			System.out.println("Problem with connection: " + e.getMessage());
		}
		return false;
	}
	
	/**
	 * Add a new ID / IP pair to the database.
	 * @param receivedTopic Topic ID for which to add a new entry.
	 * @param ip Host IP address for which to add a new entry.
	 */
	public void addIP(int receivedTopic, String ip) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT into hostlist VALUES ("+receivedTopic+",'"+ip+"')");
			
		}
		catch (SQLException e) {
			System.out.println("Problem with connection: " + e.getMessage());
		}	
	}
}