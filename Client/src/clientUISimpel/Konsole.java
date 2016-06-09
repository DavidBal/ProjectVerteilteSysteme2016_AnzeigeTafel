package clientUISimpel;

import java.util.Scanner;

import client.ServerConector;

/**
 * Implementiert auf Basis der Konsole einfache Testmöglichkeiten des Clients!
 * 
 * @author David
 *
 */
public class Konsole {
	ServerConector server;

	public Konsole(ServerConector server) {
		this.server = server;
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

		// Folgen:
		System.out.println("--------------------------------------------------");
		System.out.println("? - Delete Msg"); // TODO
		System.out.println("? - Force Uppdate");// TODO
		System.out.println("? - Edit Msg");// TODO
		System.out.println("? - Add User");// TODO
		System.out.println("? - Delete User");// TODO
		System.out.println("? - Change User Permission");// TODO
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
			this.server.ping();
			break;
		case 2:
			System.out.println("Msg(Press-Enter to Send): ");
			String msg = "";
			msg = leser.nextLine();
			this.server.sendNewMessage(msg);
			break;
		case 3:
			System.out.println("Benutzer: ");
			String userName = leser.nextLine();
			System.out.println("Passwort: ");
			String pw = leser.nextLine();
			this.server.auth(userName, pw);
			break;
		default:
			System.out.println("Pls try again!!");
			break;
		}
		return exit;
	}
}
