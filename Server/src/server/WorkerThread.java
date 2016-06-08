package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import ControllCalls.ControllCalls;
/**
 * Jede Anfrage eines Clients öffnet eine neue Thread ...
 * @author David
 *
 */
public class WorkerThread implements Runnable {

	Socket client;
	BufferedReader in;
	PrintWriter out;
	int id;

	@Override
	public void run() {

		try {
			
			System.out.println("Worker- " + this.id + ": Iâ€˜m UP!!");
			String controllCall = this.in.readLine(); // Lese Befehl
			//System.out.println(controllCall);

			this.controll(controllCall);
			
			client.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println("Worker - " + this.id + " END!!");
	}

	WorkerThread(Socket client, int id) {
		this.client = client;
		this.id = id;
		try {
			this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			this.out = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void controll(String controllCall) {

		/**TODO 
		 * ------ *
		 * CC -> One Line 
		 * Every Msg -> one Line
		 * END -> One Line 
		 */
		
		switch (ControllCalls.stringToControllCall(controllCall)) {
		case Ping:
			// System.out.println("Worker: Right Case");
			this.out.println("Pong");
			break;
		case NEW:
			try {
				String msg = this.in.readLine();
				System.out.println(msg);
				this.in.readLine();
				//TODO END ??
			} catch (IOException e) {
				// TODO 
				e.printStackTrace();
			}
			
			break;
		case LOGIN:
			try {
				//User Name
				boolean uN = this.in.readLine().equals("test");
				//PW
				boolean pW = this.in.readLine().equals("test");
				
				//
				if(uN && pW){
					this.out.println("Yes");
				} else{
					this.out.println("NO");
				}	
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			

		default:
			break;

		}
	}

}
