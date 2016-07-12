package sqlbase;

import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.proteanit.sql.DbUtils;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class MessageEdit extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMessage;
	private static String constBenutzer;
	private static String constAbteilung;
	private static String auswahl;
	
	/**
	 * Launch the application.
	 */
	public static void main(String benutzer,String abteilung) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					constBenutzer = benutzer;
					constAbteilung = abteilung;
					MessageEdit frame = new MessageEdit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	private JTextField txtId;
	private JTextField insertID;
	private JTable table;
	/**
	 * Create the frame.
	 */
	public MessageEdit() {
		conn = Driver.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtMessage = new JTextField();
		txtMessage.setColumns(10);
		
		JButton btnNeueNachrichtSpeichern = new JButton("Neue Nachricht Speichern");
		btnNeueNachrichtSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "insert into Nachrichten values($next_id,?,?,?)";
				PreparedStatement newMessage;
				try {
					newMessage = conn.prepareStatement(query);
					newMessage.setString(2, txtMessage.getText());
					newMessage.setString(3, constAbteilung );
					newMessage.setString(4, constBenutzer );
					newMessage.executeUpdate();
					newMessage.close();
					JOptionPane.showMessageDialog(null, "Nachricht gespeichert");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				
				
				
			}
		});
		
		JButton btnShowDatabase = new JButton("Show Database");
		btnShowDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				////////////////////////////////////////////////////////////////////
				// Tabelle erstellen
				////////////////////////////////////////////////////////////////////
				try {
					String query = "Select * from Nachrichten";
					PreparedStatement output = conn.prepareStatement(query);
					ResultSet rs = output.executeQuery();
					
					////////////////////////////////////////////////////////////////////
					// Tabelle mit datenbank füllen
					table.setModel(DbUtils.resultSetToTableModel(rs));
					////////////////////////////////////////////////////////////////////

					output.close();
					rs.close();
					JOptionPane.showMessageDialog(null, "Refreshed");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				
				
			}
		});
		
		JButton btnNachrichtLschen = new JButton("Nachricht L\u00F6schen");
		btnNachrichtLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					
					String del = "delete from Nachrichten where Id ='"+auswahl+"' ";
					PreparedStatement delete = conn.prepareStatement(del);
					delete.execute();
					delete.close();
					
					
					JOptionPane.showMessageDialog(null, "Gelöscht");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
				
			}
		});
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setText("Id = ");
		txtId.setColumns(10);
		
		insertID = new JTextField();
		insertID.setColumns(10);
		
		table = new JTable();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	auswahl = table.getValueAt(table.getSelectedRow(), 0).toString();
	        }
	    });
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nachricht", "Abteilung", "Benutzer"
			}
		));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(8)
							.addComponent(table, GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
							.addGap(8))
						.addComponent(txtMessage, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
						.addComponent(btnNeueNachrichtSpeichern)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnShowDatabase)
							.addGap(241)
							.addComponent(btnNachrichtLschen)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(insertID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtMessage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNeueNachrichtSpeichern)
					.addPreferredGap(ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnShowDatabase)
						.addComponent(btnNachrichtLschen)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(insertID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
