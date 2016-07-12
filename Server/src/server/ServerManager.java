package server;

import java.util.ArrayList;

public class ServerManager {

	public static final boolean debug = false;
	public ArrayList<String> messages;

	private int acticConnects;
	private int finishConnects;

	public ServerManager() {
		this.messages = new ArrayList<String>();
		this.finishConnects = 0;
		this.acticConnects = 0;
	}

	public synchronized void onConnect() {
		this.acticConnects++;
	}

	public synchronized void onFinish() {
		this.acticConnects--;
		this.finishConnects++;
	}

	public int getActivConnects() {
		return this.acticConnects;
	}

	public int getFinishConnects() {
		return this.finishConnects;
	}
}
