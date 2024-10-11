package admin;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

class Location {
	String name;
	String about;
	String contactInfo;
	String address;

	public Location() {
	}

	public Location(String name, String address, String contactInfo, String about) {
		this.name = name;
		this.about = about;
		this.contactInfo = contactInfo;
		this.address = address;
	}
}

class HealthFacility extends Location {
	public HealthFacility() {
	}

	public HealthFacility(String name, String address, String contactInfo, String about) {
		super(name, address, contactInfo, about);
	}
}

class TouristAttraction extends Location {
	public TouristAttraction() {
	}

	public TouristAttraction(String name, String address, String contactInfo, String about) {
		super(name, address, contactInfo, about);
	}
}

class PublicUtilities extends Location {
	public PublicUtilities() {
	}

	public PublicUtilities(String name, String address, String contactInfo, String about) {
		super(name, address, contactInfo, about);
	}
}

class HotelRestaurant extends Location {
	public HotelRestaurant() {
	}

	public HotelRestaurant(String name, String address, String contactInfo, String about) {
		super(name, address, contactInfo, about);
	}
}

class EducationalFacility extends Location {
	public EducationalFacility() {
	}

	public EducationalFacility(String name, String address, String contactInfo, String about) {
		super(name, address, contactInfo, about);
	}
}

class ShoppingLocation extends Location {
	public ShoppingLocation() {
	}

	public ShoppingLocation(String name, String address, String contactInfo, String about) {
		super(name, address, contactInfo, about);
	}
}

public class AddData extends JFrame {
	private int widthMenu = 470;
	private int heightMenu = 496;

	private static final long serialVersionUID = 1L;

	public ArrayList<HotelRestaurant> hotelRestaurants = new ArrayList<HotelRestaurant>();
	public ArrayList<PublicUtilities> publicUtilities = new ArrayList<PublicUtilities>();
	public ArrayList<TouristAttraction> touristAttractions = new ArrayList<TouristAttraction>();
	public ArrayList<HealthFacility> healthFacilities = new ArrayList<HealthFacility>();
	public ArrayList<EducationalFacility> educationalFacilities = new ArrayList<EducationalFacility>();
	public ArrayList<ShoppingLocation> shoppingLocations = new ArrayList<ShoppingLocation>();
	private String[] placeCategories = { "Health Facility", "Tourist Attraction", "Public Utilities",
			"Hotel - Restaurant", "Educational Facility", "Shopping Location" };

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField contactInfoTextField;

	private ArrayList<String> loadData() {
		ArrayList<String> data = new ArrayList<String>();
		data.add("");
		try {
			File file = new File("places.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (Arrays.asList(placeCategories).contains(line)) {
					data.add(line + " - " + scanner.nextLine());
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return data;
	}

	private ArrayList<String> addedData = loadData();

	public void addData(String place, String name, String address, String contact, String about) {
		String placeNamePair = place + " - " + name;

		if (addedData.contains(placeNamePair)) {
			JOptionPane.showMessageDialog(null, "Data with the same Place and Name already exists!");
		} else if (address.length() > 100) {
			JOptionPane.showMessageDialog(null, "Address should not exceed 100 characters!");
		} else if (about.equals("") || contact.equals("") || name.equals("") || address.equals("")) {
			JOptionPane.showMessageDialog(null, "Invalid Input!");
		} else {
			try {
				FileWriter myWriter = new FileWriter("places.txt", true);
				myWriter.write(place + "\n" + name + "\n" + address + "\n" + contact + "\n" + about + "\n");
				myWriter.close();
				System.out.println("Successfully wrote to the file.");
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			addedData.add(placeNamePair);

			if (place.equals("Health Facility")) {
				HealthFacility facility = new HealthFacility(name, address, contact, about);
				healthFacilities.add(facility);
				JOptionPane.showMessageDialog(null, "Health Facility Added!");
			}
			if (place.equals("Tourist Attraction")) {
				TouristAttraction attraction = new TouristAttraction(name, address, contact, about);
				touristAttractions.add(attraction);
				JOptionPane.showMessageDialog(null, "Tourist Attraction Added!");
			}
			if (place.equals("Public Utilities")) {
				PublicUtilities utility = new PublicUtilities(name, address, contact, about);
				publicUtilities.add(utility);
				JOptionPane.showMessageDialog(null, "Public Utilities Added!");
			}
			if (place.equals("Hotel - Restaurant")) {
				HotelRestaurant restaurant = new HotelRestaurant(name, address, contact, about);
				hotelRestaurants.add(restaurant);
				JOptionPane.showMessageDialog(null, "Hotel - Restaurant Added!");
			}
			if (place.equals("Educational Facility")) {
				EducationalFacility facility = new EducationalFacility(name, address, contact, about);
				educationalFacilities.add(facility);
				JOptionPane.showMessageDialog(null, "Educational Facility Added!");
			}
			if (place.equals("Shopping Location")) {
				ShoppingLocation location = new ShoppingLocation(name, address, contact, about);
				shoppingLocations.add(location);
				JOptionPane.showMessageDialog(null, "Shopping Location Added!");
			}

		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					AddData frame = new AddData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddData() {
		setTitle("AddData");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(46, 41, 45, 13);
		contentPane.add(nameLabel);

		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(139, 38, 271, 19);
		contentPane.add(nameTextField);

		JLabel addressLabel = new JLabel("Address");
		addressLabel.setBounds(46, 69, 50, 13);
		contentPane.add(addressLabel);

		JTextArea addressTextArea = new JTextArea();
		addressTextArea.setWrapStyleWord(true);
		addressTextArea.setLineWrap(true);
		JScrollPane addressScrollPane = new JScrollPane(addressTextArea);
		addressScrollPane.setBounds(46, 90, 364, 36);
		contentPane.add(addressScrollPane);

		JLabel contactInfoLabel = new JLabel("Contact Info");
		contactInfoLabel.setBounds(46, 146, 79, 13);
		contentPane.add(contactInfoLabel);

		contactInfoTextField = new JTextField();
		contactInfoTextField.setColumns(10);
		contactInfoTextField.setBounds(139, 143, 271, 19);
		contentPane.add(contactInfoTextField);

		JLabel aboutLabel = new JLabel("About");
		aboutLabel.setBounds(46, 183, 65, 13);
		contentPane.add(aboutLabel);

		JTextArea aboutTextArea = new JTextArea();
		aboutTextArea.setWrapStyleWord(true);
		aboutTextArea.setLineWrap(true);
		JScrollPane aboutScrollPane = new JScrollPane(aboutTextArea);
		aboutScrollPane.setBounds(46, 205, 364, 148);
		contentPane.add(aboutScrollPane);

		JComboBox<String> placeComboBox = new JComboBox<>();
		placeComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Health Facility", "Educational Facility",
				"Shopping Location", "Tourist Attraction", "Public Utilities", "Hotel - Restaurant" }));
		placeComboBox.setBounds(46, 7, 136, 21);
		contentPane.add(placeComboBox);

		JButton addDataButton = new JButton("Add Data");
		addDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String place = placeComboBox.getSelectedItem().toString();
				String name = nameTextField.getText();
				String address = addressTextArea.getText();
				String contact = contactInfoTextField.getText();
				String about = aboutTextArea.getText();

				addData(place, name, address, contact, about);
			}
		});
		addDataButton.setBounds(46, 381, 85, 21);
		contentPane.add(addDataButton);

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		closeButton.setBounds(146, 381, 79, 19);
		contentPane.add(closeButton);

		JLabel background = new JLabel();
		ImageIcon icon = new ImageIcon("img/data.jpg");
		Image image = icon.getImage().getScaledInstance(widthMenu, heightMenu, Image.SCALE_SMOOTH);
		background.setIcon(new ImageIcon(image));
		background.setBounds(0, 0, widthMenu, heightMenu);
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
