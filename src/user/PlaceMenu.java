package user;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaceMenu extends JFrame {
    private static final long serialVersionUID = 1L;
    private int menuWidth = 1024;
    private int menuHeight = 720;
    private JPanel contentPane;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PlaceMenu menuFrame = new PlaceMenu("Admin");
                    menuFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PlaceMenu(String name) {
        setTitle("PlaceMenu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 50, menuWidth, menuHeight);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        createWelcomeLabel(name);
        createComboBox("Health Facility", "Cơ sở y tế", 50);
        createComboBox("Public Utilities", "Tiện ích công cộng", 128);
        createComboBox("Hotel - Restaurant", "Nhà hàng, khách sạn", 206);
        createComboBox("Tourist Attraction", "Địa điểm du lịch", 284);
        createComboBox("Shopping Location", "Địa điểm mua sắm", 362);
        createComboBox("Educational Facility", "Cơ sở giáo dục", 440);

        createCloseButton();
        createBackgroundImage();
    }

    private void createWelcomeLabel(String name) {
        JLabel welcomeLabel = new JLabel("Xin chào " + name);
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.GREEN);
        welcomeLabel.setBounds(720, 14, 200, 23);
        contentPane.add(welcomeLabel);
    }

    private void createComboBox(String type, String label, int yPosition) {
        JLabel placeLabel = new JLabel(label);
        placeLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        placeLabel.setBounds(29, yPosition, 175, 23);
        placeLabel.setForeground(Color.WHITE);
        contentPane.add(placeLabel);

        List<String> placeList = new ArrayList<>();
        try {
            File placesFile = new File("places.txt");
            Scanner myReader = new Scanner(placesFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.equals(type)) {
                    placeList.add(myReader.nextLine());
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String[] placeArray = placeList.toArray(new String[]{});

        JComboBox<String> placeComboBox = new JComboBox<>();
        placeComboBox.setModel(new DefaultComboBoxModel<>(placeArray));
        placeComboBox.setBounds(220, yPosition, 380, 21);
        contentPane.add(placeComboBox);

        JButton viewButton = new JButton("View Information");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String place, name;
                name = placeComboBox.getSelectedItem().toString();
                place = type;
                DisplayPlaces dp = new DisplayPlaces(place, name);
                dp.setVisible(true);
            }
        });
        viewButton.setBounds(472, yPosition + 30, 128, 21);
        contentPane.add(viewButton);
    }

    private void createCloseButton() {
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        closeButton.setBounds(501, 569, 99, 21);
        contentPane.add(closeButton);
    }

    private void createBackgroundImage() {
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("img/place_menu.jpg");
        Image image = icon.getImage().getScaledInstance(menuWidth, menuHeight, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
        imageLabel.setBounds(0, 0, menuWidth, menuHeight);
        contentPane.add(imageLabel);

        contentPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int newWidth = contentPane.getWidth();
                int newHeight = contentPane.getHeight();
                Image scaledImage = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
                imageLabel.setBounds(0, 0, newWidth, newHeight);
            }
        });
    }
}
