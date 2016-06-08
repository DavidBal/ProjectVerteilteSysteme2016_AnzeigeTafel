package server;

import ControllCalls.ControllCalls;

public class Message {

	ControllCalls cc;
	String s;

	public Message(ControllCalls cc, String s) {
		this.s = s;
		this.cc = cc;
	}

	public String toString() {
		return cc.toString() + "|" + s + "|" + ControllCalls.END.toString();
	}

	public static Message stringToMessage(String message){
		Message m = null;
		
		String[] feld = message.split("|");
		
		ControllCalls.stringToControllCall(feld[0]);
		
		return m;
	}
}
