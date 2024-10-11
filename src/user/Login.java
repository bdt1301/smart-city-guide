package user;

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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
    private int frameWidth = 420;
    private int frameHeight = 260;

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, frameWidth, frameHeight);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(55, 47, 116, 13);
        contentPane.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(55, 86, 116, 13);
        contentPane.add(passwordLabel);

        usernameField = new JTextField();
        usernameField.setBounds(181, 44, 147, 19);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(181, 83, 147, 19);
        contentPane.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String loginDetails = username + " " + password;
                String storedData = "";
                boolean loginSuccess = false;

                try {
                    File userFile = new File("usernames.txt");
                    Scanner fileScanner = new Scanner(userFile);
                    while (fileScanner.hasNextLine()) {
                        storedData = fileScanner.nextLine();
                        if (storedData.equals(loginDetails)) {
                            loginSuccess = true;
                            storedData = fileScanner.nextLine();
                            break;
                        }
                    }
                    fileScanner.close();
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "File not found.");
                    e.printStackTrace();
                }

                if (loginSuccess) {
                    PlaceMenu placeMenu = new PlaceMenu(storedData);
                    placeMenu.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
                }
            }
        });

        loginButton.setBounds(181, 127, 68, 21);
        contentPane.add(loginButton);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        closeButton.setBounds(259, 127, 68, 21);
        contentPane.add(closeButton);

        JLabel background = new JLabel();
        ImageIcon icon = new ImageIcon("img/user.jpg");
        Image image = icon.getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(image));
        background.setBounds(0, 0, frameWidth, frameHeight);
        contentPane.add(background);

        contentPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int newWidth = contentPane.getWidth();
                int newHeight = contentPane.getHeight();
                Image scaledImage = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                background.setIcon(new ImageIcon(scaledImage));
                background.setBounds(0, 0, newWidth, newHeight);
            }
        });
    }
}
