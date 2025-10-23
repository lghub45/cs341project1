package cs341project1;

public class User {

	private String username; 
	private String password; 
	//private Tasklist tasks;
	
	public User(String username, String password) {
		this.username = username;
		this.password=password;
	}
	
	public String getUsername() {
		return username;
	}
	
}
