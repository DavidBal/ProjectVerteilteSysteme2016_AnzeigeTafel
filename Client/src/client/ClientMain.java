/**
 *  
 * 
 */

package client;

import clientUISimpel.Konsole;
import config.Manager;

public class ClientMain {

	public static final int SERVER_PORT = 4690;

	/**
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			Manager manager = new Manager();
			ServerConector test = new ServerConector("localhost", SERVER_PORT);

			manager.server = test;
			// TODO Thread der das Auto-Uppdate der Daten uebernimmt!

			manager.server.ping();
			// test.isReachable();
			manager.server.sendNewMessage("Hallo Welt!!");
			manager.server.auth("test", "test"); // Alles richtig
			manager.server.auth("Nein", "test"); // User falsch
			manager.server.auth("test", "Nein"); // PW falsch
			manager.server.auth("nein", "nein"); // Alles falsch

			// Ruft das Test Menue auf !!Konsole!!
			Konsole c = new Konsole(manager);
			c.hauptMenue();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
