package user;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

class UserDetails {

	public String name;
	public String password;
	public int age;
	public String username;

	public UserDetails() {

	}

	public UserDetails(String name, String password, String username, int age) {
		this.name = name;
		this.password = password;
		this.age = age;
		this.username = username;
		try {
			FileWriter myWriter = new FileWriter("usernames.txt", true);
			myWriter.write(username + " " + password + "\n" + name + "\n" + age + "\n");
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}
}

public class SignUp extends JFrame {
	private int menuWidth = 480;
	private int menuHeight = 320;

	private static final long serialVersionUID = 1L;
	public ArrayList<UserDetails> users = new ArrayList<UserDetails>();

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JTextField nameTextField;
	private JTextField ageTextField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SignUp() {
		setTitle("Sign Up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, menuWidth, menuHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(70, 31, 129, 13);
		contentPane.add(usernameLabel);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(70, 65, 129, 13);
		contentPane.add(passwordLabel);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(70, 98, 129, 13);
		contentPane.add(nameLabel);

		JLabel ageLabel = new JLabel("Age");
		ageLabel.setBounds(70, 133, 129, 13);
		contentPane.add(ageLabel);

		usernameTextField = new JTextField();
		usernameTextField.setBounds(224, 28, 174, 19);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);

		nameTextField = new JTextField();
		nameTextField.setBounds(224, 95, 174, 19);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);

		ageTextField = new JTextField();
		ageTextField.setBounds(224, 130, 174, 19);
		contentPane.add(ageTextField);
		ageTextField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(224, 62, 174, 19);
		contentPane.add(passwordField);

		JButton signUpButton = new JButton("Sign up");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username, password, name, ageStr;
				int age;

				username = usernameTextField.getText();
				password = String.valueOf(passwordField.getPassword());
				name = nameTextField.getText();
				ageStr = ageTextField.getText();

				int flag = 0;
				char[] chars = name.toCharArray();
				for (char c : chars) {
					if (Character.isDigit(c)) {
						flag = 1;
						break;
					}
				}

				if (flag == 1) {
					JOptionPane.showMessageDialog(null, "Name should not contain numbers!!");
				} else if (!ageStr.matches("[0-9]+")) {
					JOptionPane.showMessageDialog(null, "Age should be a whole number!!");
				} else if (username.equals("") || password.equals("") || name.equals("")
						|| ageTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid Input!!");
				} else if (isUsernameTaken(username)) {
					JOptionPane.showMessageDialog(null, "Username already taken!");
				} else {
					age = Integer.parseInt(ageTextField.getText());
					UserDetails newUser = new UserDetails(name, password, username, age);
					users.add(newUser);
					Login login = new Login();
					login.setVisible(true);
					dispose();
				}
			}
		});
		signUpButton.setBounds(226, 187, 85, 21);
		contentPane.add(signUpButton);

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		closeButton.setBounds(321, 187, 76, 21);
		contentPane.add(closeButton);

		JLabel backgroundLabel = new JLabel();
		ImageIcon icon = new ImageIcon("img/user.jpg");
		Image image = icon.getImage().getScaledInstance(menuWidth, menuHeight, Image.SCALE_SMOOTH);
		backgroundLabel.setIcon(new ImageIcon(image));
		backgroundLabel.setBounds(0, 0, menuWidth, menuHeight);
		contentPane.add(backgroundLabel);

		contentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int newWidth = contentPane.getWidth();
				int newHeight = contentPane.getHeight();
				Image scaledImage = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
				backgroundLabel.setIcon(new ImageIcon(scaledImage));
				backgroundLabel.setBounds(0, 0, newWidth, newHeight);
			}
		});
	}

	private boolean isUsernameTaken(String username) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("usernames.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(" ");
				if (parts.length >= 1 && parts[0].equals(username)) {
					br.close();
					return true;
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
