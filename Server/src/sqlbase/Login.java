package sqlbase;

import java.sql.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;

	private JTextField txtBenutzername;
	private JTextField txtPasswort;
	private JTextField textFieldBenutzer;
	private JTextField textField_Password;
	private static String abteilung;

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		////////////////////////////////////////////////////////////////////
		// Verbindung zur Db
		conn = Driver.dbConnector();
		////////////////////////////////////////////////////////////////////
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		txtBenutzername = new JTextField();
		txtBenutzername.setEditable(false);
		txtBenutzername.setText("Benutzername");
		springLayout.putConstraint(SpringLayout.NORTH, txtBenutzername, 69, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtBenutzername, 60, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(txtBenutzername);
		txtBenutzername.setColumns(10);

		txtPasswort = new JTextField();
		txtPasswort.setEditable(false);
		txtPasswort.setText("Passwort");
		springLayout.putConstraint(SpringLayout.NORTH, txtPasswort, 6, SpringLayout.SOUTH, txtBenutzername);
		springLayout.putConstraint(SpringLayout.WEST, txtPasswort, 0, SpringLayout.WEST, txtBenutzername);
		frame.getContentPane().add(txtPasswort);
		txtPasswort.setColumns(10);

		textFieldBenutzer = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textFieldBenutzer, 6, SpringLayout.EAST, txtBenutzername);
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldBenutzer, 0, SpringLayout.SOUTH, txtBenutzername);
		frame.getContentPane().add(textFieldBenutzer);
		textFieldBenutzer.setColumns(10);

		textField_Password = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_Password, 0, SpringLayout.NORTH, txtPasswort);
		springLayout.putConstraint(SpringLayout.WEST, textField_Password, 6, SpringLayout.EAST, txtPasswort);
		frame.getContentPane().add(textField_Password);
		textField_Password.setColumns(10);

		////////////////////////////////////////////////////////////////////
		// Login
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from Login where username=? and password=? ";
					PreparedStatement pst = conn.prepareStatement(query);

					pst.setString(1, textFieldBenutzer.getText());
					pst.setString(2, textField_Password.getText());

					ResultSet rs = pst.executeQuery();
					int count = 0;

					while (rs.next()) {
						count = count + 1;
						abteilung = rs.getString("Abteilung");
					}
					if (count == 1) {

						JOptionPane.showMessageDialog(null, "Username and Password are Correct");
						MessageEdit.main(textFieldBenutzer.getText(), abteilung);
						frame.dispose();

					} else if (count > 1) {
						JOptionPane.showMessageDialog(null, "Duplicatet Username and Password");
					} else {
						JOptionPane.showMessageDialog(null, "Username and / or Password are not Correct");
					}

					rs.close();
					pst.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		////////////////////////////////////////////////////////////////////
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, -1, SpringLayout.NORTH, txtPasswort);
		springLayout.putConstraint(SpringLayout.WEST, btnLogin, 6, SpringLayout.EAST, textField_Password);
		frame.getContentPane().add(btnLogin);
	}
}
