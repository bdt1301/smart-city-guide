package admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;

public class AdminMenu extends JFrame {
	private int menuWidth = 320;
	private int menuHeight = 360;	
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu frame = new AdminMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminMenu() {
		setTitle("Admin Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, menuWidth, menuHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel addDataLabel = new JLabel("Add Data");
		addDataLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addDataLabel.setBounds(35, 39, 97, 33);
		contentPane.add(addDataLabel);

		JButton addDataButton = new JButton("Add");
		addDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				AddData addData = new AddData();
				addData.setVisible(true);

			}
		});
		addDataButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addDataButton.setBounds(154, 42, 97, 31);
		contentPane.add(addDataButton);

		JLabel deleteDataLabel = new JLabel("Delete Data");
		deleteDataLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		deleteDataLabel.setBounds(35, 113, 129, 33);
		contentPane.add(deleteDataLabel);

		JButton deleteDataButton = new JButton("Delete");
		deleteDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DeleteData deleteData = new DeleteData();
				deleteData.setVisible(true);
			}
		});
		deleteDataButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deleteDataButton.setBounds(154, 115, 97, 33);
		contentPane.add(deleteDataButton);
		
		JLabel editDataLabel = new JLabel("Edit Data");
		editDataLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		editDataLabel.setBounds(35, 187, 129, 33);
		contentPane.add(editDataLabel);

		JButton editDataButton = new JButton("Edit");
		editDataButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        EditorMenu editData = new EditorMenu();
		        editData.setVisible(true);
		    }
		});
		editDataButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editDataButton.setBounds(154, 190, 97, 33);
		contentPane.add(editDataButton);

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		closeButton.setBounds(154, 256, 97, 21);
		contentPane.add(closeButton);

		JLabel backgroundLabel = new JLabel();
		ImageIcon icon = new ImageIcon("img/admin.jpg");
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
