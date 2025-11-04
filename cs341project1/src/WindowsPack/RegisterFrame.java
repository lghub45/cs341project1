package WindowsPack;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Storage.UserStorage;

public class RegisterFrame extends JFrame{

	   private JTextField usernameField;
	   private JPasswordField passwordField;
	  
	   public RegisterFrame() {
	   	getContentPane().setForeground(new Color(0, 128, 0));
	   	getContentPane().setBackground(new Color(128, 128, 0));
	       setTitle("Register");
	       setSize(300, 200);
	       setDefaultCloseOperation(EXIT_ON_CLOSE);
	       getContentPane().setLayout(null);
	       JLabel lblUser = new JLabel("Username:");
	       lblUser.setFont(new Font("Verdana", Font.BOLD, 12));
	       lblUser.setForeground(new Color(255, 255, 128));
	       lblUser.setBounds(30, 30, 80, 25);
	       getContentPane().add(lblUser);
	       usernameField = new JTextField();
	       usernameField.setBackground(new Color(255, 255, 0));
	       usernameField.setBounds(120, 30, 130, 25);
	       getContentPane().add(usernameField);
	       JLabel lblPass = new JLabel("Password:");
	       lblPass.setForeground(new Color(255, 255, 128));
	       lblPass.setBackground(new Color(255, 255, 128));
	       lblPass.setFont(new Font("Verdana", Font.BOLD, 12));
	       lblPass.setBounds(30, 70, 80, 25);
	       getContentPane().add(lblPass);
	       passwordField = new JPasswordField();
	       passwordField.setBackground(new Color(255, 255, 0));
	       passwordField.setForeground(new Color(255, 255, 0));
	       passwordField.setBounds(120, 70, 130, 25);
	       getContentPane().add(passwordField);
	       JButton registerBtn = new JButton("Register");
	       registerBtn.setForeground(new Color(0, 64, 64));
	       registerBtn.setBackground(new Color(64, 128, 128));
	       registerBtn.setFont(new Font("Verdana", Font.BOLD, 12));
	       registerBtn.setBounds(90, 110, 100, 25);
	       getContentPane().add(registerBtn);
	       registerBtn.addActionListener(e -> {
	           String user = usernameField.getText();
	           String pass = new String(passwordField.getPassword());
	           if (UserStorage.register(user, pass)) {
	               JOptionPane.showMessageDialog(this, "Registration successful");
	               dispose();
	               new LoginFrame().setVisible(true);
	           } else {
	               JOptionPane.showMessageDialog(this, "User already exists");
	               //for testing purposes
	               dispose();
	               new LoginFrame().setVisible(true);
	           }
	       });
	   }


}
