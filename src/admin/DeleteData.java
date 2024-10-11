package admin;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
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
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class DeleteData extends JFrame {
	private int menuWidth = 550;
	private int menuHeight = 550;

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private String[] placeCategories = { "Health Facility", "Tourist Attraction", "Public Utilities",
			"Hotel - Restaurant", "Educational Facility", "Shopping Location" };

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteData frame = new DeleteData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void deleteData(String category, String name) {
		try {
			File file = new File("places.txt");
			Scanner scanner = new Scanner(file);
			String currentLine = scanner.nextLine();
			while (scanner.hasNextLine()) {
				String placeCategory = currentLine;
				String placeName = scanner.nextLine();
				String placeAddress = scanner.nextLine();
				String placeContact = scanner.nextLine();
				String placeAbout = "";
				while (scanner.hasNextLine()) {
					currentLine = scanner.nextLine();
					if (Arrays.asList(placeCategories).contains(currentLine)) {
						break;
					}
					placeAbout += currentLine + "\n";
				}
				if (!(placeCategory.equals(category) && placeName.equals(name))) {
					try {
						FileWriter writer = new FileWriter("temp.txt", true);
						writer.write(placeCategory + "\n" + placeName + "\n" + placeAddress + "\n" + placeContact + "\n"
								+ placeAbout);
						writer.close();
						System.out.println("Successfully wrote to the file.");
					} catch (IOException e) {
						System.out.println("An error occurred.");
						e.printStackTrace();
					}
				}
			}
			scanner.close();
			File originalFile = new File("places.txt");
			File newFile = new File("temp.txt");
			originalFile.delete();
			newFile.renameTo(originalFile);
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public DeleteData() {
		setTitle("Delete Data");

		List<String> healthFacilitiesList = new ArrayList<String>();
		try {
			File file = new File("places.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				if (data.equals("Health Facility")) {
					healthFacilitiesList.add(scanner.nextLine());
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		String[] healthFacilitiesArray = healthFacilitiesList.toArray(new String[] {});

		List<String> publicUtilitiesList = new ArrayList<String>();
		try {
			File file = new File("places.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				if (data.equals("Public Utilities")) {
					publicUtilitiesList.add(scanner.nextLine());
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		String[] publicUtilitiesArray = publicUtilitiesList.toArray(new String[] {});

		List<String> touristAttractionsList = new ArrayList<String>();
		try {
			File file = new File("places.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				if (data.equals("Tourist Attraction")) {
					touristAttractionsList.add(scanner.nextLine());
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		String[] touristAttractionsArray = touristAttractionsList.toArray(new String[] {});

		List<String> hotelRestaurantsList = new ArrayList<String>();
		try {
			File file = new File("places.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				if (data.equals("Hotel - Restaurant")) {
					hotelRestaurantsList.add(scanner.nextLine());
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		String[] hotelRestaurantsArray = hotelRestaurantsList.toArray(new String[] {});
		
		List<String> educationalFacilitiesList = new ArrayList<String>();
		try {
		    File file = new File("places.txt");
		    Scanner scanner = new Scanner(file);
		    while (scanner.hasNextLine()) {
		        String data = scanner.nextLine();
		        if (data.equals("Educational Facility")) {
		            educationalFacilitiesList.add(scanner.nextLine());
		        }
		    }
		    scanner.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
		String[] educationalFacilitiesArray = educationalFacilitiesList.toArray(new String[] {});

		List<String> shoppingLocationsList = new ArrayList<String>();
		try {
		    File file = new File("places.txt");
		    Scanner scanner = new Scanner(file);
		    while (scanner.hasNextLine()) {
		        String data = scanner.nextLine();
		        if (data.equals("Shopping Location")) {
		            shoppingLocationsList.add(scanner.nextLine());
		        }
		    }
		    scanner.close();
		} catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
		String[] shoppingLocationsArray = shoppingLocationsList.toArray(new String[] {});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, menuWidth, menuHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//		Health Facility
		JLabel healthFacilityLabel = new JLabel("Health Facility");
		healthFacilityLabel.setBounds(24, 22, 80, 13);
		contentPane.add(healthFacilityLabel);

		JComboBox<String> healthFacilityComboBox = new JComboBox<>();
		healthFacilityComboBox.setModel(new DefaultComboBoxModel<String>(healthFacilitiesArray));
		healthFacilityComboBox.setBounds(150, 20, 360, 21);
		contentPane.add(healthFacilityComboBox);

		JButton healthFacilityDeleteButton = new JButton("Delete");
		healthFacilityDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteData("Health Facility", healthFacilityComboBox.getSelectedItem().toString());
				dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							DeleteData frame = new DeleteData();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		healthFacilityDeleteButton.setBounds(425, 50, 85, 21);
		contentPane.add(healthFacilityDeleteButton);

//		Public Utilities
		JLabel publicUtilitiesLabel = new JLabel("Public Utilities");
		publicUtilitiesLabel.setBounds(24, 90, 90, 13);
		contentPane.add(publicUtilitiesLabel);

		JComboBox<String> publicUtilitiesComboBox = new JComboBox<>();
		publicUtilitiesComboBox.setModel(new DefaultComboBoxModel<String>(publicUtilitiesArray));
		publicUtilitiesComboBox.setBounds(150, 88, 360, 21);
		contentPane.add(publicUtilitiesComboBox);

		JButton publicUtilitiesDeleteButton = new JButton("Delete");
		publicUtilitiesDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteData("Public Utilities", publicUtilitiesComboBox.getSelectedItem().toString());
				dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							DeleteData frame = new DeleteData();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		publicUtilitiesDeleteButton.setBounds(425, 118, 85, 21);
		contentPane.add(publicUtilitiesDeleteButton);

//		Tourist Attraction
		JLabel touristAttractionLabel = new JLabel("Tourist Attraction");
		touristAttractionLabel.setBounds(24, 158, 105, 13);
		contentPane.add(touristAttractionLabel);

		JComboBox<String> touristAttractionComboBox = new JComboBox<>();
		touristAttractionComboBox.setModel(new DefaultComboBoxModel<String>(touristAttractionsArray));
		touristAttractionComboBox.setBounds(150, 156, 360, 21);
		contentPane.add(touristAttractionComboBox);

		JButton touristAttractionDeleteButton = new JButton("Delete");
		touristAttractionDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteData("Tourist Attraction", touristAttractionComboBox.getSelectedItem().toString());
				dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							DeleteData frame = new DeleteData();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		touristAttractionDeleteButton.setBounds(425, 186, 85, 21);
		contentPane.add(touristAttractionDeleteButton);

//		Hotel - Restaurant
		JLabel hotelRestaurantLabel = new JLabel("Hotel - Restaurant");
		hotelRestaurantLabel.setBounds(24, 226, 120, 13);
		contentPane.add(hotelRestaurantLabel);

		JComboBox<String> hotelRestaurantComboBox = new JComboBox<>();
		hotelRestaurantComboBox.setModel(new DefaultComboBoxModel<String>(hotelRestaurantsArray));
		hotelRestaurantComboBox.setBounds(150, 224, 360, 21);
		contentPane.add(hotelRestaurantComboBox);

		JButton hotelRestaurantDeleteButton = new JButton("Delete");
		hotelRestaurantDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteData("Hotel - Restaurant", hotelRestaurantComboBox.getSelectedItem().toString());
				dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							DeleteData frame = new DeleteData();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		hotelRestaurantDeleteButton.setBounds(425, 254, 85, 21);
		contentPane.add(hotelRestaurantDeleteButton);
		
//		Educational Facility
		JLabel educationalFacilityLabel = new JLabel("Educational Facility");
		educationalFacilityLabel.setBounds(24, 294, 120, 13);
		contentPane.add(educationalFacilityLabel);

		JComboBox<String> educationalFacilityComboBox = new JComboBox<>();
		educationalFacilityComboBox.setModel(new DefaultComboBoxModel<String>(educationalFacilitiesArray));
		educationalFacilityComboBox.setBounds(150, 292, 360, 21);
		contentPane.add(educationalFacilityComboBox);

		JButton educationalFacilityDeleteButton = new JButton("Delete");
		educationalFacilityDeleteButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        deleteData("Educational Facility", educationalFacilityComboBox.getSelectedItem().toString());
		        dispose();
		        EventQueue.invokeLater(new Runnable() {
		            public void run() {
		                try {
		                    DeleteData frame = new DeleteData();
		                    frame.setVisible(true);
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        });
		    }
		});
		educationalFacilityDeleteButton.setBounds(425, 322, 85, 21);
		contentPane.add(educationalFacilityDeleteButton);

//		Shopping Location
		JLabel shoppingLocationLabel = new JLabel("Shopping Location");
		shoppingLocationLabel.setBounds(24, 362, 120, 13);
		contentPane.add(shoppingLocationLabel);

		JComboBox<String> shoppingLocationComboBox = new JComboBox<>();
		shoppingLocationComboBox.setModel(new DefaultComboBoxModel<String>(shoppingLocationsArray));
		shoppingLocationComboBox.setBounds(150, 360, 360, 21);
		contentPane.add(shoppingLocationComboBox);

		JButton shoppingLocationDeleteButton = new JButton("Delete");
		shoppingLocationDeleteButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        deleteData("Shopping Location", shoppingLocationComboBox.getSelectedItem().toString());
		        dispose();
		        EventQueue.invokeLater(new Runnable() {
		            public void run() {
		                try {
		                    DeleteData frame = new DeleteData();
		                    frame.setVisible(true);
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        });
		    }
		});
		shoppingLocationDeleteButton.setBounds(425, 390, 85, 21);
		contentPane.add(shoppingLocationDeleteButton);


		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		closeButton.setBounds(425, 455, 83, 21);
		contentPane.add(closeButton);

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