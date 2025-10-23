package UserPack;
import TaskPack.Task;

public class Manager extends User {

	
	public Manager (String username, String password) {
		super (username, password);}
	
	
	public void assignTask(Task t, User u) {
		//will assign another user a task
		u.addTask(t);
	}
	
	
}
