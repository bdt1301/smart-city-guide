package admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditorMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private int menuWidth = 550;
	private int menuHeight = 550;

	private JPanel contentPane;

	public static void main(String[] args) {
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

	public EditorMenu() {
		setTitle("Edit Data");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, menuWidth, menuHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] categories = { "Health Facility", "Public Utilities", "Tourist Attraction", "Hotel - Restaurant",
				"Educational Facility", "Shopping Location" };

		for (int i = 0; i < categories.length; i++) {
			createCategoryPanel(categories[i], i * 68 + 22);
		}

		createCloseButton();
		createBackgroundImage();
	}

	private void createCategoryPanel(String category, int yPosition) {
		JLabel label = new JLabel(category);
		label.setBounds(24, yPosition, 140, 13);
		contentPane.add(label);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(150, yPosition - 2, 360, 21);
		contentPane.add(comboBox);

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditData ed = new EditData(category, comboBox.getSelectedItem().toString());
				ed.setVisible(true);
			}
		});
		editButton.setBounds(425, yPosition + 28, 85, 21);
		contentPane.add(editButton);

		List<String> itemList = new ArrayList<String>();
		try {
			File file = new File("places.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				if (data.equals(category)) {
					itemList.add(scanner.nextLine());
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		String[] itemArray = itemList.toArray(new String[] {});
		comboBox.setModel(new DefaultComboBoxModel<String>(itemArray));
	}

	private void createCloseButton() {
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		closeButton.setBounds(425, 455, 83, 21);
		contentPane.add(closeButton);
	}

	private void createBackgroundImage() {
		JLabel backgroundLabel = new JLabel();
		ImageIcon icon = new ImageIcon("img/data.jpg");
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
