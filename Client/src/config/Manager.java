package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import client.ServerConector;

public class Manager {

	/**
	 * Datei Pfad
	 */
	private final String filePath = "ServerList.txt";

	public ArrayList<ServerConector> knownServer = new ArrayList<ServerConector>();
	public ServerConector server;

	public Manager() {
		this.readInServer();
	}

	/**
	 * Liest die Datei ein ServerList.txt ein und Added Server die noch nicht da sind
	 * falls datei nicht exestiert wird createServerListFile() auferufen
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
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Legt eine neue ServerList.txt an
	 */
	private void createServerListFile() {
		File file = new File(this.filePath);

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
			FileWriter writer = new FileWriter(new File(this.filePath),true);
			writer.write(newServer.toString() + "\n");
			
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
