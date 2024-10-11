package admin;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;

public class AdminLogin extends JFrame {
	private int menuWidth = 470;
	private int menuHeight = 496;
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminLogin() {
		setTitle("Admin Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 395, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(43, 46, 106, 13);
		contentPane.add(usernameLabel);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(43, 87, 106, 13);
		contentPane.add(passwordLabel);

		usernameTextField = new JTextField();
		usernameTextField.setBounds(159, 43, 96, 19);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(159, 87, 96, 19);
		contentPane.add(passwordField);

		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username, password;
				username = usernameTextField.getText();
				password = String.valueOf(passwordField.getPassword());
				String adminUsername = "admin";
				String adminPassword = "admin";

				if (username.equals(adminUsername) && password.equals(adminPassword)) {
					AdminMenu adminMenu = new AdminMenu();
					adminMenu.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
				}
			}
		});
		loginButton.setBounds(159, 143, 85, 21);
		contentPane.add(loginButton);

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		closeButton.setBounds(159, 174, 85, 21);
		contentPane.add(closeButton);

		JLabel backgroundLabel = new JLabel();
		ImageIcon icon = new ImageIcon("img/admin_login.jpg");
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
}
