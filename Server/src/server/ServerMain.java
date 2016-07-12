package server;

public class ServerMain {

	public static int SERVER_PORT = 4690;

	/**
	 * Server.jar [Port] or ? for Help
	 * 
	 * @param args
	 */
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

		ServerManager manager = new ServerManager();
		Thread serverMain = new Server(SERVER_PORT, manager);
		serverMain.start();

		CommandsExcuter cE = new CommandsExcuter(manager);
		cE.waitForCommand();

		try {
			serverMain.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
