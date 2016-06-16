package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import client.ServerConector;

public class ManagerClient {

	/**
	 * Datei Pfad
	 */
	private final static String filePath = "ServerList.txt";

	/**
	 * Alle bekannten Server
	 */
	public ArrayList<ServerConector> knownServer = new ArrayList<ServerConector>();
	
	/**
	 * Server der momentan angesprochen wird
	 */
	public ServerConector server;

	/**
	 * Legt einen neue Manager für den Client an
	 */
	public ManagerClient() {
		this.readInServer();
	}

	/**
	 * Liest die Datei ein ServerList.txt ein und Added Server die noch nicht da sind
	 * falls datei nicht exestiert wird createServerListFile() aufgerufen
	 */
	private void readInServer() {
		try {
			BufferedReader file = new BufferedReader(new FileReader(new File(filePath)));

			String serverIP = "";
			int serverPort = 0;
			String name = "";

			String[] data = new String[3];

			data[0] = file.readLine();
			while (data[0] != null) {
				if (!data[0].startsWith("//")) {

					data = data[0].split(":");

					name = data[0];
					serverIP = data[1];
					serverPort = Integer.valueOf(data[2]);

					ServerConector newServer = new ServerConector(serverIP, serverPort, name);

					if (!knownServer.contains(newServer)) {
						knownServer.add(newServer);
						System.out.println(knownServer.get(knownServer.size() - 1).toString());
					}

				}
				data[0] = file.readLine();
			}

			file.close();
		} catch (FileNotFoundException e) {
			this.createServerListFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Legt eine neue ServerList.txt an
	 */
	private void createServerListFile() {
		File file = new File(ManagerClient.filePath);

		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Addet einen Neuen Server zur ServerList.txt und Addet ihn zu den knownServer
	 * @param newServer
	 */
	public void addAServer(ServerConector newServer) {
		
		if(this.knownServer.contains(newServer))
			return;
		
		this.knownServer.add(newServer);
		try {
			FileWriter writer = new FileWriter(new File(ManagerClient.filePath),true);
			writer.write(newServer.toString() + "\n");
			
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Verändert der Server wo die Daten hingesandt werden.
	 * @param server
	 */
	public void changeMainServer(ServerConector server){
		this.server = server;
	}
}
