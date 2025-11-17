package tester;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import TaskPack.TaskList;

import java.util.LinkedList;
import model.Task;

public class TaskListTester {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();

        try {
            var field = TaskList.class.getDeclaredField("tasks");
            field.setAccessible(true);
            LinkedList<Task> tasks = new LinkedList<>();
            tasks.add(new Task("Task 1", 1));
            tasks.add(new Task("Task 2", 2));
            tasks.add(new Task("Task 3", 3));
            field.set(taskList, tasks);
        } catch (Exception e) {
            fail("Failed to initialize: " + e.getMessage());
        }
    }
    @Test
    void testConstructor() {
        TaskList emptyList = new TaskList();

        try {
            var field = TaskList.class.getDeclaredField("tasks");
            field.setAccessible(true);
            LinkedList<Task> tasks = (LinkedList<Task>) field.get(emptyList);
            assertNotNull(tasks, "Task list should be initialized");
            assertTrue(tasks.isEmpty(), "Task list should start empty");
        } catch (Exception e) {
            fail(" failed: " + e.getMessage());
        }
    }

    @Test
    void testGetTask() {
        TaskPack.Task result = taskList.getTask(1);
        assertEquals("Task 2", result.getDesc(), "getTask() should return the task at index 1");
    }

    @Test
    void testEditTask() {
        taskList.editTask(0, "Updated Task 1");
        TaskPack.Task updated = taskList.getTask(0);
        assertEquals("Updated Task 1", updated.getDesc(), "editTask() should update task description");
    }

    @Test
    void testToString() {
        String expected = "Task 1\nTask 2\nTask 3\n";
        assertEquals(expected, taskList.toString(), "toString() should match formatted list of tasks");
    }

    @Test
    void testEditTaskInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.editTask(5, "Invalid Edit");
        }, "Editing with invalid index should throw IndexOutOfBoundsException");
    }

    @Test
    void testGetTaskInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.getTask(-1);
        }, "getTask() with negative index should throw IndexOutOfBoundsException");
    }

    @Test
    void testToStringEmptyList() {
        TaskList emptyList = new TaskList();
        assertEquals("", emptyList.toString(), "Empty TaskList should return empty string in toString()");
    }
}
