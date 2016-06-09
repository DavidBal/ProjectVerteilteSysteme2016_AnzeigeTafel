/**
 * 
 * 
 * 
 * 
 */

package client;

import clientUISimpel.Konsole;

public class ClientMain {

	public static final int SERVER_PORT = 4690;
	
	/**
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ServerConector test = new ServerConector("localhost", SERVER_PORT);
			
			//TODO Thread der das Auto-Uppdate der Daten übernimmt!
			
			test.ping();
			//test.isReachable();
			test.sendNewMessage("Hallo Welt!!");
			test.auth("test", "test"); 	// Alles richtig
			test.auth("Nein", "test");	// User falsch
			test.auth("test", "Nein");	// PW falsch
			test.auth("nein", "nein");	// Alles falsch
			
			//Ruft das Test Menue auf !!Konsole!!
			Konsole c = new Konsole(test);
			c.hauptMenue();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
