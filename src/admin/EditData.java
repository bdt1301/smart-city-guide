package admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class EditData extends JFrame {
	private int frameWidth = 480;
	private int frameHeight = 480;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField contactInfoField;
	private String[] placeCategories = { "Health Facility", "Tourist Attraction", "Public Utilities",
			"Hotel - Restaurant", "Educational Facility", "Shopping Location" };

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditData frame = new EditData("place", "name");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditData(String place, String name) {
		setTitle("EditData");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, frameWidth, frameHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(25, 23, 45, 13);
		contentPane.add(nameLabel);

		nameField = new JTextField();
		nameField.setBounds(118, 20, 321, 19);
		contentPane.add(nameField);
		nameField.setColumns(10);

		JLabel contactInfoLabel = new JLabel("Contact Info");
		contactInfoLabel.setBounds(25, 128, 79, 13);
		contentPane.add(contactInfoLabel);

		contactInfoField = new JTextField();
		contactInfoField.setBounds(118, 125, 321, 19);
		contentPane.add(contactInfoField);
		contactInfoField.setColumns(10);

		JLabel addressLabel = new JLabel("Address");
		addressLabel.setBounds(25, 51, 50, 13);
		contentPane.add(addressLabel);

		JTextArea addressTextArea = new JTextArea();
		addressTextArea.setWrapStyleWord(true);
		addressTextArea.setLineWrap(true);
		JScrollPane addressScrollPane = new JScrollPane(addressTextArea);
		addressScrollPane.setBounds(25, 70, 414, 36);
		contentPane.add(addressScrollPane);

		JLabel aboutLabel = new JLabel("About");
		aboutLabel.setBounds(25, 165, 65, 13);
		contentPane.add(aboutLabel);

		JTextArea aboutTextArea = new JTextArea();
		aboutTextArea.setWrapStyleWord(true);
		aboutTextArea.setLineWrap(true);
		JScrollPane aboutScrollPane = new JScrollPane(aboutTextArea);
		aboutScrollPane.setBounds(25, 188, 414, 148);
		contentPane.add(aboutScrollPane);

		try {
			File placesFile = new File("places.txt");
			Scanner fileScanner = new Scanner(placesFile);
			while (fileScanner.hasNextLine()) {
				String plc = fileScanner.nextLine();
				if (plc.equals(place)) {
					String nm = fileScanner.nextLine();
					if (nm.equals(name)) {
						String addr = fileScanner.nextLine();
						String ctc = fileScanner.nextLine();
						String abt = "";
						while (fileScanner.hasNextLine()) {
							String line = fileScanner.nextLine();
							if (Arrays.asList(placeCategories).contains(line)) {
								break;
							}
							abt += line + "\n";
						}
						if (plc.equals(place) && nm.equals(name)) {
							nameField.setText(nm);
							contactInfoField.setText(ctc);
							addressTextArea.setText(addr);
							aboutTextArea.setText(abt);
						}
					}
				}
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newName = nameField.getText();
				String newContact = contactInfoField.getText();
				String newAddress = addressTextArea.getText();
				String newAbout = aboutTextArea.getText();

				DeleteData dd = new DeleteData();
				dd.deleteData(place, name);

				AddData ad = new AddData();
				ad.addData(place, newName, newAddress, newContact, newAbout);

				dispose();
				
		        Window[] windows = Window.getWindows();
		        for (Window window : windows) {
		            if (window instanceof EditorMenu) {
		                window.dispose();
		            }
		        }
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							EditorMenu frame = new EditorMenu();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		saveButton.setBounds(256, 364, 83, 19);
		contentPane.add(saveButton);

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		closeButton.setBounds(356, 364, 83, 19);
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
