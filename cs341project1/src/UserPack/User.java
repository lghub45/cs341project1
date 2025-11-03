package UserPack;
import TaskPack.Task;
import TaskPack.TaskList;

public class User {

	private String username; 
	private String password; 
	private TaskList tasks;
	
	public User(String username, String password) {
		this.username = username;
		this.password=password;
		tasks = new TaskList(); //every user has their own task list
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPass() {
		return password;
	}
	
	//public void addTask(Task t) {
		//tasks.addTask(t);
	//}
	
}
