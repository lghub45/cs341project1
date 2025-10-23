package TaskPack;
import java.util.LinkedList;

public class TaskList {

	private LinkedList <Task> tasks;
	
	public TaskList() {
		tasks = new <Task> LinkedList();
	}
	
	public void addTask(Task t) {
		tasks.add(t);
	}
	
	public void editTask(int index, String desc) {
		tasks.get(index).update(desc);
	}
	public Task getTask(int index) {
		return tasks.get(index);
	}
	
	public String toString() {
		String list = "";
		for (int i=0; i<tasks.size();i++) {
			list+= tasks.get(i).getDesc()+"\n";
		}
		return list;
	}
	
	
}
