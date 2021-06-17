package cn.tcp.fp;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class Server {
	private String serverName = null;
	private int serverPort = 0;

	HashMap<String, String> account = new HashMap<String, String>();
	String username = "";
	String password = "";

	public Server(String name, int port) {
		serverName = name;
		serverPort = port;
	}

	public void start() {
		// set server address
		InetSocketAddress serverSocketAddress = new InetSocketAddress(serverName, serverPort);
		String localAddress = serverSocketAddress.getAddress().getHostAddress();

		// try-with-resources statement, following s will close serverSocket
		// automatically
		try (ServerSocket serverSocket = new ServerSocket()) {

			// Binds the ServerSocket to a specific address
			System.out.println("Bind server socket to " + localAddress + ":" + serverPort);
			serverSocket.bind(serverSocketAddress);
			System.out.println("Multithreading server binding success.");

			while (true) {
				// Accept new client's connection
				Socket socket = serverSocket.accept();
				// Create a thread to serve the client
				// Create a thread to execute ClientHandlingTask(socket)
				Thread thread = new Thread(new ClientHandlingTask(socket));
				thread.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Server shutdown.");
		}

	}

	private class ClientHandlingTask implements Runnable {
		private Socket clientSocket = null;

		public ClientHandlingTask(Socket socket) {
			clientSocket = socket;
		}

		@Override
		public void run() {
			// get the ip address and port number of the remote client
			InetSocketAddress clientSocketAddress = (InetSocketAddress) clientSocket.getRemoteSocketAddress();
			String clientAddress = clientSocketAddress.getAddress().getHostAddress();
			int clientPort = clientSocketAddress.getPort();
			System.out.println("Connecting to " + clientAddress + ":" + clientPort);
			String str = null;
			String[] strs = null;

			try {
				// get input/output stream to the client
				InputStream inputStream = clientSocket.getInputStream();
				OutputStream outputStream = clientSocket.getOutputStream();
				byte[] buf = new byte[1024];

				// length = 0 or -1 if inputStream has reached end-of-stream
				int length = inputStream.read(buf);
				String s = "";

				while (length > 0) {
					str = new String(buf, 0, length);
					strs = str.split(" ");
					System.out.print(str);
					boolean exist = account.containsKey(username);
					username = strs[3];
					password = strs[4];
					if (strs[0].charAt(0) == 'R') { // register
						if (exist == true) { // already existed, not available
							s = "1";
						} else {
							s = "2";
							account.put(username, password);
						}
					} else if (strs[0].charAt(0) == 'S') { // sign in
						if (exist == true) {
							if (password.equals(account.get(username)) == false) {
								s = "3";
							} else {
								s = "4";
							}
						} else { // account not exist
							s = "5";
						}
					}
					System.out.println(s);
					outputStream.write(s.getBytes(), 0, s.getBytes().length);
																			outputStream.flush();
					length = inputStream.read(buf);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// always close the resources before left
				try {
					clientSocket.close();
				} catch (IOException e2) {
				}
				System.out.println("Disconnecting to " + clientAddress + ":" + clientPort);
			}
		}
	}

	public static void main(String[] args) {
		// Default server address is 127.0.0.1:12000
		String serverName = "127.0.0.1";
		int serverPort = 12000;

		if (args.length >= 2) {
			serverName = args[0];
			try {
				serverPort = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
			}
		}

		Server server = new Server(serverName, serverPort);
		server.start();

	}

}
