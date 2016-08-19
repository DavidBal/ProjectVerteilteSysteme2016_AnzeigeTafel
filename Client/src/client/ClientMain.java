/**
 *  
 * 
 */

package client;

import clientTUISimpel.Konsole;
import config.ManagerClient;

public class ClientMain {

	public static final int SERVER_PORT = 4690;

	/**
	 *
	 *	@param args
	 */
	public static void main(String[] args) {
		try {
			
<<<<<<< HEAD
			Manager manager = new Manager();
			ServerConector test = new ServerConector("localhost", SERVER_PORT);

			manager.server = test;
			// TODO Thread der das Auto-Uppdate der Daten uebernimmt!

=======
			ManagerClient manager = new ManagerClient();
			
			ServerConector test = new ServerConector("localhost", SERVER_PORT);

			manager.server = test;
			// TODO Thread der das Auto-Uppdate der Daten übernimmt!
			
			
			//------ Testfaelle - Start------
>>>>>>> master
			manager.server.ping();
			
			manager.server.sendNewMessage("Hallo Welt!!");
			
			manager.server.auth("test", "test"); // Alles richtig
			manager.server.auth("Nein", "test"); // User falsch
			manager.server.auth("test", "Nein"); // PW falsch
			manager.server.auth("nein", "nein"); // Alles falsch
			//------ Testfaelle - Ende ------
			
			
			manager.startUpdater();
			// Ruft das Test Menue auf !!Konsole!!
			Konsole c = new Konsole(manager);
			c.hauptMenue();
			
			
			manager.exitUpdater();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
