package WindowsPack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;

import Storage.TaskStorage;
import Storage.UserStorage;
import TaskPack.Task;

import javax.swing.JButton;

public class SendWindow extends TaskFrame{

	//private JFrame frame;
	private String username;
	private JTextField taskField2;
	private JTextField passField;
	private JTextField userField;
	private JLabel userlabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SendWindow window = new SendWindow("manager");
					window.setVisible(true);    //used to be window.frame etc.
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SendWindow(String username) {
		super(username);
		this.username=username;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//frame = new JFrame();
			//frame.setBounds(100, 100, 450, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(null);
		
		 setTitle("Send Task - " + username);
	        getContentPane().removeAll(); // clear inherited layout if needed
	        setSize(450, 300);
	        setLayout(null);
		
		
		JLabel lblProvideAUser = new JLabel("Provide a user to send the task to as well as your password");
		lblProvideAUser.setBounds(10, 10, 500, 13);
		getContentPane().add(lblProvideAUser); //used to be frame.etc
		
		taskField2 = new JTextField();
		taskField2.setBounds(152, 33, 214, 17);
		getContentPane().add(taskField2);//used to be frame.etc
		taskField2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Insert task");
		lblNewLabel.setBounds(20, 36, 84, 11);
		getContentPane().add(lblNewLabel);//used to be frame.etc
		
		JLabel lblInsertPassword = new JLabel("Insert password");
		lblInsertPassword.setBounds(20, 60, 184, 11);
		getContentPane().add(lblInsertPassword);//used to be frame.etc
		
		passField = new JTextField();
		passField.setColumns(10);
		passField.setBounds(152, 57, 214, 17);
		getContentPane().add(passField);//used to be frame.etc
		
		JLabel lblInsertUserTo = new JLabel("Insert user to send to");
		lblInsertUserTo.setBounds(20, 86, 282, 32);
		getContentPane().add(lblInsertUserTo);//used to be frame.etc
		
		userField = new JTextField();
		userField.setColumns(10);
		userField.setBounds(152, 94, 190, 17);
		getContentPane().add(userField);//used to be frame.etc
		
			// userlabel = new JLabel("Provide a User");
    	     //userlabel.setBounds(20, 97, 95, 11);
    	    //getContentPane().add(userlabel);//used to be frame.etc
			
			
		JButton sendBtn2 = new JButton("Send");
		sendBtn2.setBounds(27, 128, 77, 19);
		getContentPane().add(sendBtn2);//used to be frame.etc
		
		
		 sendBtn2.addActionListener(e -> {
		    	
		    	 String desc = taskField2.getText().trim();
		    	      int id = model.getSize()+1;
		         String pass = passField.getText().trim();  
		         
		         String toUser = UserStorage.findUserPass(userField.getText().trim());
		         String senderPass = UserStorage.findUserPass(username); //used for testing the password
		         
		         if(toUser!=null && senderPass.equals(UserStorage.sha256(pass))) { 
		        	//first checks if there actually is the user the manager wants to send this task to and that the sender's password is 
		        	 //accurate in hash code
		    	    if (!desc.isEmpty()) {
		    	     Task task = new Task(desc,id);
		    	     TaskStorage.addTask(userField.getText().trim(), task); //sends it to employee instead of manager's username
		    	     refreshTasks();
		    	     taskField2.setText("");
		    	     dispose();
		    	    }
		         }
		         else if(senderPass.equals(pass)){JOptionPane.showMessageDialog(this, "User doesn't exist");}
		         else if (senderPass != pass) {JOptionPane.showMessageDialog(this, "Password Error pleaser reenter");}
		    	    
		       });
		
	}

}
