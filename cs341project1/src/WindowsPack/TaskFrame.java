package WindowsPack;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Storage.TaskStorage;
import TaskPack.Task;

public class TaskFrame extends JFrame{
	private String username;
	   private DefaultListModel<Task> model;
	   public JList<Task> taskList;
	   
	   public TaskFrame(String username) {
	       this.username = username;
	       setTitle("Tasks - " + username);
	       setSize(400, 300);
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
	       addBtn.setBounds(220, 200, 70, 25);
	       getContentPane().add(addBtn);
	       JButton removeBtn = new JButton("Remove");
	       removeBtn.setBounds(300, 200, 90, 25);
	       getContentPane().add(removeBtn);
	       JButton completeBtn = new JButton("Toggle Complete");
	       completeBtn.setBounds(10, 230, 150, 25);
	       getContentPane().add(completeBtn);
	       
	       //add btn
	       addBtn.addActionListener(e -> {
	           String desc = taskField.getText().trim();
	           int id = model.getSize()+1;
	           if (!desc.isEmpty()) {
	               Task task = new Task(desc,id);
	               TaskStorage.addTask(username, task);
	               refreshTasks();
	               taskField.setText(task+" is the task we added"); //used to be ""
	               
	           }
	       });
	       //remove btn
	       removeBtn.addActionListener(e -> {
	    	   //dispose();
	    	   // i tried making a frame to ask the user to input a number (this number would be connected to a spot on the task list
	    	   //which would then remove the selected number from the list, but it didn't pan out 
	    	    	   
	    	  // RemoveFrame remy = new RemoveFrame();
	    	   //remy.setVisible(true);
	    	  // remy.getIndex();
	    	  // int id = Integer.parseInt(taskField.getText()); //gets id of task from textbox
	    	   
	    	   Task selected = taskList.getSelectedValue(); //get task that was clicked on
	    	   if (selected != null) { 
	               TaskStorage.removeTask(username, selected); 
	               refreshTasks();
	           }
	           else { return;}
	       
	       });
	       //complete btn
	       completeBtn.addActionListener(e -> {
	           Task selected = taskList.getSelectedValue();
	           if (selected != null) {
	        	   if (selected.statusReport()) {selected.setStatus(false);} //if the task is already complete this sets it back to false
	        	   else{selected.setStatus(true);} //if task isn't complete this button sets it to complete
	              
	           }
	           refreshTasks();
	           //test
               taskField.setText(selected.getStatus()+" should be true");
	       });
	       refreshTasks();
	   }
	   private void refreshTasks() {
	       List<Task> tasks = TaskStorage.getTasks(username);
	       model.clear();
	       for (Task t : tasks) {
	           model.addElement(new TaskDisplayWrapper(t));
	       }
	   }
	   // Wrapper for better display     individual task display??
	   class TaskDisplayWrapper extends Task {
	       public TaskDisplayWrapper(Task t) {
	           super(t.getDesc(),t.getId());
	           if (t.statusReport()) setStatus(true);
	       }
	       @Override
	       public String toString() {
	    	   
	       	return //(//getStatus()
	       			//statusReport() ? 
	       			//"[✓] " : "[ ] "
	       			//)+
	       			getStatus() + getDesc();
	       }

}
}
