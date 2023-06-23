package guiApp;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow {

	private JFrame frame;
	private JTextField username;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		LoginWindow login = new LoginWindow();
		login.createConnection();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frame.setSize(220,260);
					window.frame.setResizable(false);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	/**
	 * Create the connection.
	 */
	void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/apotek","root", "");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USER");
			while(rs.next()) {
				String name = rs.getString("Username");
				System.out.println(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.windowBorder);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setForeground(new Color(230, 230, 250));
		lblNewLabel.setBounds(10, 58, 89, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(new Color(230, 230, 250));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 114, 89, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblSelamatDatang = new JLabel("Selamat Datang");
		lblSelamatDatang.setBackground(new Color(255, 255, 255));
		lblSelamatDatang.setForeground(new Color(230, 230, 250));
		lblSelamatDatang.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSelamatDatang.setBounds(10, 11, 180, 36); frame.getContentPane().add(lblSelamatDatang);
		
		username = new JTextField();
		username.setBounds(10, 83, 180, 20);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(10, 139, 180, 20);
		frame.getContentPane().add(pass);

		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				String name = username.getText(); 
				@SuppressWarnings("deprecation")
				String password = pass.getText();
				
				AmbilData ambil = new AmbilData();
				ambil.login(name,password);
			}
		});
		btnNewButton.setBackground(SystemColor.windowBorder);
		btnNewButton.setFont(UIManager.getFont("Button.font"));
		btnNewButton.setBounds(101, 170, 89, 20);
		frame.getContentPane().add(btnNewButton);
	}
}
