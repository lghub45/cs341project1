package WindowsPack;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RemoveFrame {

	private JFrame frame;
	private JTextField textField;
	private JButton removeBtn;
	public int ID;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveFrame window = new RemoveFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RemoveFrame() {
		initialize();
		btnClick();
	}

	private void btnClick(){
		removeBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		removeFromList();
		}
		});
		}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Type in the index of the task to remove");
		lblNewLabel.setBounds(8, 8, 181, 27);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(8, 31, 66, 17);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		removeBtn = new JButton("Remove");
		removeBtn.setBounds(8, 57, 77, 19);
		frame.getContentPane().add(removeBtn);
	}
	private void removeFromList() {
		String index = textField.getText();
		ID = Integer.parseInt(index);
	}
	
	public int getIndex() {
		return ID;
		//taskList.removeTask(Integer.parseInt(index));
	}

}
