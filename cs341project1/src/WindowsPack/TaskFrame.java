package WindowsPack;

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import Storage.TaskStorage;
import TaskPack.Task;
import java.awt.Font;

public class TaskFrame extends JFrame {
    private String username;
    public DefaultListModel<Task> model;  //used to be private
    public JList<Task> taskList;

    public TaskFrame(String username) {
        this.username = username;
        setTitle("Tasks - " + username);
        setSize(428, 338);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        model = new DefaultListModel<>();
        taskList = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBounds(10, 10, 360, 180);
        getContentPane().add(scrollPane);

        JTextField taskField = new JTextField();
        taskField.setBounds(10, 200, 200, 25);
        getContentPane().add(taskField);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(215, 200, 70, 25);
        getContentPane().add(addBtn);

        JButton removeBtn = new JButton("Remove");
        removeBtn.setBounds(289, 200, 98, 25);
        getContentPane().add(removeBtn);

        JButton completeBtn = new JButton("Toggle Complete");
        completeBtn.setBounds(10, 230, 150, 25);
        getContentPane().add(completeBtn);

        JButton sendBtn = new JButton("Send");
        sendBtn.setBounds(215, 225, 70, 25);
        getContentPane().add(sendBtn);
        
        JButton logoutBtn = new JButton("Switch User");
        logoutBtn.setFont(new Font("Tahoma", Font.BOLD, 10));
        logoutBtn.setBounds(240, 266, 98, 25);
        getContentPane().add(logoutBtn);
        
        JButton editBtn = new JButton("Edit Task");
        editBtn.setBounds(289, 225, 98, 25);
        getContentPane().add(editBtn);
        
        // add button: persist task into DB
        addBtn.addActionListener(e -> {
            String desc = taskField.getText().trim();
            if (!desc.isEmpty()) {
                Task task = new Task(desc, -1);
                TaskStorage.addTask(username, task);
                refreshTasks();
                taskField.setText("");
            }
        });

        // remove button: remove selected or parse id from text box
        removeBtn.addActionListener(e -> {
            Task selected = taskList.getSelectedValue();
            if (selected != null) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Remove task \"" + selected.getDesc() + "\"?", "Confirm remove",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    TaskStorage.removeTaskById(username, selected.getId());
                    refreshTasks();
                }
            } else {
                String txt = taskField.getText().trim();
                if (!txt.isEmpty()) {
                    try {
                        int id = Integer.parseInt(txt);
                        TaskStorage.removeTaskById(username, id);
                        refreshTasks();
                        taskField.setText("");
                        
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Select a task or type a numeric id to remove.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No task selected.");
                }
            }
        });

        // toggle complete: updates DB
        completeBtn.addActionListener(e -> {
            Task selected = taskList.getSelectedValue();
            if (selected != null) {
                boolean newStatus = !selected.statusReport();
                TaskStorage.setTaskStatus(username, selected.getId(), newStatus);
                refreshTasks();
            } else {
                JOptionPane.showMessageDialog(this, "Select a task to toggle complete.");
            }
        });

        //edit task btn
        editBtn.addActionListener(e -> {
            Task selected = taskList.getSelectedValue();
            String desc = taskField.getText().trim();  //uses input from the text box to update a selected task
            if (selected != null && desc!="") {
                 selected.update(desc);//should update the desc of selected which we can then input as our updated task
                 
                TaskStorage.newObjective(username, selected.getId(), selected);
                refreshTasks();
            } else {
                JOptionPane.showMessageDialog(this, "Input a new task in the task bar, then select a task and click edit");
            }
        });
        
        //send btn
	       sendBtn.addActionListener(e -> {
		               new SendWindow(username).setVisible(true);
	       });
	  
	       //logout btn: takes you back to loginframe
	       logoutBtn.addActionListener(e -> {
               new LoginFrame().setVisible(true);
               dispose();
   });
        
        refreshTasks();
    }

    //used to be private
    public void refreshTasks() {
        List<Task> tasks = TaskStorage.getTasks(username);
        model.clear();
        for (Task t : tasks) {
            model.addElement(new TaskDisplayWrapper(t));
        }
    }

    // Display wrapper
    class TaskDisplayWrapper extends Task {
        public TaskDisplayWrapper(Task t) {
            super(t.getDesc(), t.getId());
            if (t.statusReport()) setStatus(true);
        }

        @Override
        public String toString() {
            return (statusReport() ? "[✓] " : "[ ] ") + getDesc(); 
            //used to display id as well but weird glitch where kratos has tasks 1-3 masterchief has 4 & 6 and kratos gets 5
        }
    }
}
