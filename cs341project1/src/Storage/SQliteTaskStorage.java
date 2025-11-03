package Storage;

import TaskPack.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SQLite-backed task storage:
 * - getTasks(username): List<Task>
 * - addTask(username, task): inserts and sets generated id on Task
 * - removeTaskById(username, id)
 * - setTaskStatus(username, id, status)
 */
public class TaskStorage {

    private static final String SELECT_TASKS_SQL =
            "SELECT t.id, t.description, t.completed FROM tasks t JOIN users u ON t.user_id = u.id WHERE u.username = ? ORDER BY t.id";
    private static final String INSERT_TASK_SQL =
            "INSERT INTO tasks(user_id, description, completed) VALUES((SELECT id FROM users WHERE username = ?), ?, ?)";
    private static final String DELETE_BY_ID_SQL =
            "DELETE FROM tasks WHERE id = ? AND user_id = (SELECT id FROM users WHERE username = ?)";
    private static final String UPDATE_STATUS_SQL =
            "UPDATE tasks SET completed = ? WHERE id = ? AND user_id = (SELECT id FROM users WHERE username = ?)";

    public static List<Task> getTasks(String username) {
        List<Task> tasks = new ArrayList<>();
        try (Connection conn = DB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_TASKS_SQL)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String desc = rs.getString("description");
                    boolean completed = rs.getInt("completed") != 0;
                    Task t = new Task(desc, id);
                    t.setStatus(completed);
                    tasks.add(t);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public static void addTask(String username, Task task) {
        try (Connection conn = DB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_TASK_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, username);
            ps.setString(2, task.getDesc());
            ps.setInt(3, task.statusReport() ? 1 : 0);
            int affected = ps.executeUpdate();
            if (affected == 0) return;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    task.setId(keys.getInt(1));
                } else { // fallback to last_insert_rowid()
                    try (Statement st = conn.createStatement();
                         ResultSet rs = st.executeQuery("SELECT last_insert_rowid()")) {
                        if (rs.next()) task.setId(rs.getInt(1));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeTaskById(String username, int id) {
        try (Connection conn = DB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_BY_ID_SQL)) {
            ps.setInt(1, id);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setTaskStatus(String username, int id, boolean status) {
        try (Connection conn = DB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_STATUS_SQL)) {
            ps.setInt(1, status ? 1 : 0);
            ps.setInt(2, id);
            ps.setString(3, username);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
