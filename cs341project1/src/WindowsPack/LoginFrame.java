package WindowsPack;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Storage.UserStorage;

public class LoginFrame extends JFrame{

	  private JTextField usernameField;
	   private JPasswordField passwordField;
	   public LoginFrame() {
	       getContentPane().setBackground(new Color(128, 128, 128));
	       setTitle("Login");
	       setSize(320, 280);
	       setDefaultCloseOperation(EXIT_ON_CLOSE);
	       getContentPane().setLayout(null);
	       JLabel lblTitle = new JLabel("Agenda Pro");
	       lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
	       lblTitle.setBounds(100, 10, 150, 25);
	       getContentPane().add(lblTitle);
	       // ✅ Correct way to load and display the image
	       //ImageIcon imageIcon = new ImageIcon(getClass().getResource("/ui/smiley.png"));
	       //JLabel imageLabel = new JLabel(imageIcon);
	     //  imageLabel.setBounds(105, 40, 80, 76); // Position and size of image
	       //getContentPane().add(imageLabel);
	       JLabel lblUser = new JLabel("Username:");
	       lblUser.setBounds(30, 150, 80, 25);
	       getContentPane().add(lblUser);
	       usernameField = new JTextField();
	       usernameField.setBounds(120, 150, 150, 25);
	       getContentPane().add(usernameField);
	       JLabel lblPass = new JLabel("Password:");
	       lblPass.setBounds(30, 185, 80, 25);
	       getContentPane().add(lblPass);
	       passwordField = new JPasswordField();
	       passwordField.setBounds(120, 185, 150, 25);
	       getContentPane().add(passwordField);
	       JButton loginBtn = new JButton("Login");
	       loginBtn.setBounds(30, 220, 100, 25);
	       getContentPane().add(loginBtn);
	       JButton registerBtn = new JButton("Register");
	       registerBtn.setBounds(170, 220, 100, 25);
	       getContentPane().add(registerBtn);
	       // ✅ Button actions
	       loginBtn.addActionListener(e -> {
	           String user = usernameField.getText();
	           String pass = new String(passwordField.getPassword());
	           if (UserStorage.login(user, pass)) {
	               dispose();
	               new TaskFrame(user).setVisible(true);
	           } else {
	               JOptionPane.showMessageDialog(this, "Invalid login");
	           }
	       });
	       registerBtn.addActionListener(e -> {
	           dispose();
	           new RegisterFrame().setVisible(true);
	       });
	   }
	   // ✅ Optional main() for testing this frame directly
	  // public static void main(String[] args) {
	    //   SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));

	   //}
}
