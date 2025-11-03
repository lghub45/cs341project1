package WindowsPack;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Storage.TaskStorage;
import Storage.UserStorage;
import TaskPack.Task;

public class TaskFrame extends JFrame{
	public String username;                     //used to be private
	   public DefaultListModel<Task> model;    //used to be private
	   public JList<Task> taskList;
	   private int managertest;
	   
	   public TaskFrame(String username) {
		   managertest=0;
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
	       JButton sendBtn = new JButton("Send");
           sendBtn.setBounds(220, 235, 77, 19);
           getContentPane().add(sendBtn);
	       
	       
	       //add btn
	       addBtn.addActionListener(e -> {
	           String desc = taskField.getText().trim();
	           int id = model.getSize()+1;
	           
	          // User temp = UserStorage.
	           
	           if (!desc.isEmpty()) {
	               Task task = new Task(desc,id);
	               TaskStorage.addTask(username, task);
	               refreshTasks();
	               taskField.setText("");
	               
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
	        	   TaskStorage.statusChange(username, selected);
	           }
	           refreshTasks();
	           //test
              // taskField.setText(selected.getStatus()+" should be true");
	       });
	       refreshTasks();
	       
	       //send btn
	       sendBtn.addActionListener(e -> {
	    	     //String user = usernameField.getText();
		           //String pass = new String(passwordField.getPassword());
		           //if (UserStorage.login(user, pass)) {
		               //dispose();
		               new SendWindow(username).setVisible(true);
		           //} else {
		             //  JOptionPane.showMessageDialog(this, "Invalid login");
		           //}
	       });
	   }
	   public void refreshTasks() {                                 //used to be private
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
