package tester;
import TaskPack.Task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskTester {

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task("Initial Task", 1);
    }

    @Test
    void testConstructorInitialization() {
        assertEquals("Initial Task", task.getDesc(), "Description should be initialized correctly");
        assertEquals(1, task.getId(), "ID should be initialized correctly");
        assertEquals("Incomplete", task.getStatus(), "New task should be Incomplete by default");
    }

    @Test
    void testUpdateDescription() {
        task.update("Updated Task Description");
        assertEquals("Updated Task Description", task.getDesc(), "Description should update properly");
    }

    @Test
    void testSetStatusComplete() {
        task.setStatus(true);
        assertTrue(task.statusReport(), "Status flag should be true");
        assertEquals("Complete", task.getStatus(), "Status text should be 'Complete'");
    }

    @Test
    void testSetStatusIncomplete() {
        task.setStatus(false);
        assertFalse(task.statusReport(), "Status flag should be false");
        assertEquals("Incomplete", task.getStatus(), "Status text should be 'Incomplete'");
    }

    @Test
    void testSetId() {
        task.setId(99);
        assertEquals(99, task.getId(), "Task ID should update properly");
    }

    @Test
    void testMultipleUpdates() {
        task.update("First update");
        task.update("Second update");
        assertEquals("Second update", task.getDesc(), "Description should reflect latest update");
    }

    @Test
    void testStatusPersistence() {
        task.setStatus(true);
        assertTrue(task.statusReport(), "Should remain Complete after being set");
        task.setStatus(false);
        assertFalse(task.statusReport(), "Should switch to Incomplete when updated");
    }
}
