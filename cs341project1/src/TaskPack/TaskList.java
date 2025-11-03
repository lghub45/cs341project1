package TaskPack;
import java.util.LinkedList;

public class TaskList {

	private LinkedList <Task> tasks;
	
	public TaskList() {
		tasks = new <Task> LinkedList();
	}
	
	//public void addTask(Task t) {
	//if (tasks.size()==0) { //if list is empty
	//	t.setId(1);
	//}
	//else {
	//t.setId(tasks.size());
	//}
	//tasks.add(t);//add to end of list
	//}
	
	public void editTask(int index, String desc) {
		tasks.get(index).update(desc);
	}
	public Task getTask(int index) {
		return tasks.get(index);
	}
	
	//public void removeTask(int index) {
	//if (tasks.size()==0) {return;}//nothing to remove
	//tasks.remove(index);
	//for (int i=index;i<tasks.size();i++ ) {
	//	tasks.get(i).setId(i);   //ex index=5 so we change 6 to 5 7 to 6 etc
	//}
	//}
	
	public String toString() {
		String list = "";
		for (int i=0; i<tasks.size();i++) {
			list+= tasks.get(i).getDesc()+"\n";
		}
		return list;
	}
	
	
}
