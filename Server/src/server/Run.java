package server;

public class Run {

	public static final int SERVER_PORT = 4690;

	public static void main(String[] args) {
		Thread serverMain = new Thread(new ServerMain(SERVER_PORT));
		serverMain.start();
	}
}
