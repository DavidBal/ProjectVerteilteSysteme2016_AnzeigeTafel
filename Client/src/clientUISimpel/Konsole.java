package clientUISimpel;

import java.util.Scanner;

import client.ServerConector;

public class Konsole {
	ServerConector server;
	
	public Konsole(ServerConector server){
		this.server = server;
	}
	
	
	public void hauptMenue(){
		this.showMenue();
		this.useMenue();
	}

	private void showMenue(){
		System.out.println("1 - Ping Server");
		System.out.println("2 - Send Msg");
		System.out.println("3 - Login ");
		
		
		System.out.println("---------------------------------------------------");
		System.out.println("? - Delete Msg"); //TODO 
		
	}
	
	private void useMenue(){
		Scanner leser = new Scanner(System.in);
		int i = Integer.valueOf(leser.nextLine());
		
		switch(i){
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
			break;
		}
		leser.close();
	}
}
