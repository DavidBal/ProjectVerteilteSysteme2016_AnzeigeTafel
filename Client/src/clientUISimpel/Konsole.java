package clientUISimpel;

import java.net.UnknownHostException;
import java.util.Scanner;

import client.ServerConector;
import config.Manager;

/**
 * Implementiert auf Basis der Konsole einfache Testmöglichkeiten des Clients!
 * 
 * @author David
 *
 */
public class Konsole {
	Manager manager;

	public Konsole(Manager manager) {
		this.manager = manager;
	}

	/**
	 * Hauptmenue
	 */
	public void hauptMenue() {
		boolean exit = false;
		Scanner leser = new Scanner(System.in);
		do {
			this.showMenue();
			exit = this.useMenue(leser);
		} while (exit == false);
		leser.close();
	}

	/**
	 * Gibt die verfügbaren Funktionen aus
	 */
	private void showMenue() {
		int i = 1;
		System.out.println("--------------------------------------------------");
		System.out.println("                    Hauptmenue                    ");
		System.out.println("--------------------------------------------------");
		// Sind nutzbar:
		System.out.println(i++ + " - Ping Server");
		System.out.println(i++ + " - Send Msg");
		System.out.println(i++ + " - Login ");
		System.out.println(i++ + " - Show All Server ");
		System.out.println(i++ + " - Add a Server");
		System.out.println(i++ + " - Change Main-Server ");

		// Folgen:
		System.out.println("--------------------------------------------------");
		System.out.println("? - Delete Msg"); // TODO nur eigene (Abt.Leiter
												// alle seiner Abt.)
		System.out.println("? - Force Uppdate");// TODO Resetet den Auto Update
												// Thread
		System.out.println("? - Edit Msg");// TODO nur eigene (Abt.Leiter alle
											// seiner Abt.)
		System.out.println("? - Add User");// TODO Abt. Leiter ; Admin
		System.out.println("? - Delete User");// TODO Abt. Leiter ; Admin
		System.out.println("? - Change User Permission");// TODO Admin
		System.out.println("---------------------------------------------------");

		// --
		System.out.println(0 + " - Exit");
		System.out.println("---------------------------------------------------");

	}

	/**
	 * Liest die Auswahl des User ein und ruft die Funktionen auf
	 * 
	 * @param leser
	 * @return
	 */
	private boolean useMenue(Scanner leser) {
		int auswahl = Integer.valueOf(leser.nextLine());
		boolean exit = false;

		switch (auswahl) {
		case 0:
			exit = true;
			System.out.println("-Exit-");
			break;
		case 1:
			this.manager.server.ping();
			break;
		case 2:
			System.out.println("Msg(Press-Enter to Send): ");
			String msg = "";
			msg = leser.nextLine();
			this.manager.server.sendNewMessage(msg);
			break;
		case 3:
			System.out.println("Benutzer: ");
			String userName = leser.nextLine();
			System.out.println("Passwort: ");
			String pw = leser.nextLine();
			this.manager.server.auth(userName, pw);
			break;
		case 4:
			System.out.println("-----------Server List-----------");
			// int i = 0;
			for (ServerConector sc : manager.knownServer) {
				System.out.println(manager.knownServer.indexOf(sc) + " - " + sc.toString());
			}
			System.out.println("---------------END---------------");
			break;
		case 5:
			System.out.println("Name:");
			String name = leser.nextLine();
			System.out.println("ServerIP:");
			String serverIP = leser.nextLine();
			System.out.println("ServerPort:");
			String serverPort = leser.nextLine();

			try {
				manager.addAServer(new ServerConector(serverIP, Integer.valueOf(serverPort), name));
			} catch (NumberFormatException | UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 6:
			System.out.println("Change Main Server:");
			String position = leser.nextLine();

			int in = Integer.valueOf(position);

			if (in < manager.knownServer.size() && -1 < in ) {
				manager.changeMainServer(manager.knownServer.get(in));
			} else {
				System.out.println("Out of Bound!!");
			}

			break;
		default:
			System.out.println("Pls try again!!");
			break;
		}
		return exit;
	}
}
