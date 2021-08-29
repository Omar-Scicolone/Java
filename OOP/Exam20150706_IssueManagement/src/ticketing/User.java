package ticketing;
import java.util.HashSet;
import java.util.Set;
import ticketing.IssueManager.UserClass;

public class User {
	private String username;
	private Set<UserClass> userClasses = new HashSet<>();
	
	
	public User(String username, UserClass[] classes) {
		this.username = username;
		for (UserClass u : classes) {
			userClasses.add(u);
		}
		
	}
	
	public User(String username, Set<UserClass> classes) {
		this.username = username;
		//userClasses = classes;
		for (UserClass uc : classes) {
			userClasses.add(uc);
		}
		
	}

	public String getUsername() {	
		return username;
	}

	public Set<UserClass> getUserClass() {
		return userClasses;
	}
	

}
