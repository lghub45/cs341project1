package Storage;

import TaskPack.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.sql.*;


public class TaskStorage {
	
	
	private static HashMap<String, List<Task>> taskMap = new HashMap<>();

    public static List<Task> getTasks(String username) {
        return taskMap.getOrDefault(username, new ArrayList<>());
    }

    public static void addTask(String username, Task task) {
        taskMap.computeIfAbsent(username, k -> new ArrayList<>()).add(task);
    }

    public static void removeTask(String username, Task task) {
        List<Task> tasks = taskMap.get(username);
        if (tasks != null) {
        	for(int i=0; i<tasks.size();i++) { //goes through the list and deletes the task with same Id 
        		if (task.getId()==tasks.get(i).getId()) {
        			tasks.remove(tasks.get(i));
        		}
        	}
        	}
}
}
