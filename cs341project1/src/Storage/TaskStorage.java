package Storage;

import TaskPack.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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
        if (tasks != null) tasks.remove(task);
}
}
