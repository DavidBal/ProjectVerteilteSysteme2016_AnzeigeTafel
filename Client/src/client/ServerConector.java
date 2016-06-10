package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerConector {

	private String name = "";
	private int serverPort;
	private InetAddress serverIP;

	private Socket socket;

	private BufferedReader in;
	private PrintWriter out;

	private boolean connected;
	private boolean reachable;

	/**
	 * 
	 * @param serverIP
	 * @param serverPort
	 * @throws UnknownHostException
	 */
	public ServerConector(String serverIP, int serverPort) throws UnknownHostException {

		this.serverIP = InetAddress.getByName(serverIP);
		this.serverPort = serverPort;
		this.connected = false;
		this.reachable = false;

	}

	/**
	 * 
	 * @param serverIP
	 * @param serverPort
	 * @param name
	 * @throws UnknownHostException
	 */
	public ServerConector(String serverIP, int serverPort, String name) throws UnknownHostException {
		this.serverIP = InetAddress.getByName(serverIP);
		this.serverPort = serverPort;
		this.connected = false;
		this.reachable = false;
		this.name = name;

	}

	/**
	 * Connecting to the Server If everything goes right connected = TRUE
	 * 
	 * 
	 */
	public void connect() {
		try {
			this.socket = new Socket(this.serverIP, serverPort);
			this.connected = true;
			this.reachable = true;
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.out = new PrintWriter(socket.getOutputStream(), true);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.reachable = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void disconnect() {
		if (this.connected) {
			try {
				this.socket.close();
				this.connected = false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isConnected() {
		return this.connected;
	}

	public boolean isReachable() {
		if (this.connected == false) {
			this.ping();
		}
		return this.reachable;
	}

	/**
	 * Send a "Ping" to the Server and if he work correct the Server will return
	 * a "Pong"!
	 * 
	 * @return TRUE = Server Okay ; FALSE = Server fail
	 * 
	 */
	public boolean ping() {
		boolean ping = false;
		if (!this.connected) {
			this.connect();
			ping = true;
		}
		this.out.println("Ping");

		String tmp = "";
		try {
			tmp = this.in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Ping  :  " + tmp);

		if (ping) {
			this.disconnect();
		}

		if (tmp.equals("Pong")) {
			return true;
		}

		this.disconnect();
		return false;
	}

	public void sendNewMessage(String msg) {
		this.connect();

		this.out.println(ControllCalls.ControllCalls.NEW.toString());
		//
		this.out.println(msg);
		//
		this.out.println(ControllCalls.ControllCalls.END);

		this.disconnect();
	}

	// TODO
	/**
	 * 
	 */
	public void auth(String userName, String pw) {
		this.connect();

		this.out.println(ControllCalls.ControllCalls.LOGIN);
		this.out.println(userName);
		this.out.println(pw);
		this.out.println(ControllCalls.ControllCalls.END);

		try {
			System.out.println(this.in.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.disconnect();
	}

	@Override
	public String toString() {
		String tmp = this.name + ":" + this.serverIP.getHostName() + ":" + this.serverPort;
		return tmp;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof ServerConector) {
			ServerConector other = (ServerConector) o;
			
			if(this.name.equals(other.name)){
				if(this.serverIP.equals(other.serverIP)){
					if(this.serverPort == other.serverPort){
						return true;
					}
				}
			}
		}
		
		return false;
		
	}

}
