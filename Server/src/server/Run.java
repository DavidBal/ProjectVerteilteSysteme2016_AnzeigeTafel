package server;

public class Run {

	public static int SERVER_PORT = 4690;

	public static void main(String[] args) {

		
		if (args.length == 1) {
			if (args[0].equals("?")) {
				System.out.println("Change port by: Server.jar [Port]");
				return;
			}
		}
		
		if (args.length == 1) {
			SERVER_PORT = Integer.valueOf(args[0]);
		}


		Thread serverMain = new Thread(new ServerMain(SERVER_PORT));
		serverMain.start();
	}
}
