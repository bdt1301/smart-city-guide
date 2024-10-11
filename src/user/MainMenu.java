package user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import admin.AdminLogin;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {
	private int menuWidth = 900;
	private int menuHeight = 600;
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu menuFrame = new MainMenu();
					menuFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainMenu() {
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, menuWidth, menuHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel newUserLabel = new JLabel("New user? Click here to register");
		newUserLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		newUserLabel.setBounds(98, 130, 241, 21);
		contentPane.add(newUserLabel);

		JButton signUpButton = new JButton("Sign up");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUp signUpForm = new SignUp();
				signUpForm.setVisible(true);
			}
		});
		signUpButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		signUpButton.setBounds(349, 122, 133, 31);
		contentPane.add(signUpButton);

		JLabel existingUserLabel = new JLabel("Existing user? Click here to login");
		existingUserLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		existingUserLabel.setBounds(98, 190, 229, 36);
		contentPane.add(existingUserLabel);

		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login loginForm = new Login();
				loginForm.setVisible(true);
			}
		});
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		loginButton.setBounds(349, 190, 133, 31);
		contentPane.add(loginButton);

		JButton adminLoginButton = new JButton("Admin login");
		adminLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminLogin adminLoginForm = new AdminLogin();
				adminLoginForm.setVisible(true);
			}
		});
		adminLoginButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		adminLoginButton.setBounds(688, 122, 133, 29);
		contentPane.add(adminLoginButton);

		JLabel backgroundLabel = new JLabel();
		ImageIcon backgroundIcon = new ImageIcon("img/main_menu.jpg");
		Image backgroundImage = backgroundIcon.getImage().getScaledInstance(menuWidth, menuHeight, Image.SCALE_SMOOTH);
		backgroundLabel.setIcon(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, menuWidth, menuHeight);
		contentPane.add(backgroundLabel);

		contentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int newWidth = contentPane.getWidth();
				int newHeight = contentPane.getHeight();
				Image scaledBackgroundImage = backgroundIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
				backgroundLabel.setIcon(new ImageIcon(scaledBackgroundImage));
				backgroundLabel.setBounds(0, 0, newWidth, newHeight);
			}
		});
	}
}
