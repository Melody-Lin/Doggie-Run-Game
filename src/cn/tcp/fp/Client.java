package cn.tcp.fp;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Client extends Application {
	public static Stage mainStage;
	public static Scene mainScene;
	
	static FXMLLoader loader = null;
	HashMap<String, String> account = new HashMap<String, String>();

	static String serverName = "127.0.0.1";
	static int serverPort = 12000;

	static InputStream inputStream = null;
	static OutputStream outputStream = null;
	Socket clientSocket = null;

	@FXML
	private TextField _username;
	@FXML
	private TextField _password;
	@FXML
	private Label _notice1; // This account is already registered.
	@FXML
	private Label _notice2; // This account does not exist.
	@FXML
	private Label _notice3; // Password is incorrect.
	@FXML
	private Label _notice4; // Registration success.
	@FXML
	private Button _register;
	@FXML
	private Button _signin;

	String username = "";
	String password = "";

	@Override
	public void start(Stage primaryStage) throws IOException {
		mainStage = primaryStage;
		loader = new FXMLLoader(getClass().getResource("Login.fxml"));
		Parent main = loader.load();
		mainScene = new Scene(main);
		mainStage.setTitle("Login");
		mainStage.setScene(mainScene);
		mainStage.setResizable(false);
		mainStage.show();

	}

	public static void main(String[] args) throws Exception {
		if (args.length >= 2) {
			serverName = args[0];
			try {
				serverPort = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
			}
		}
		SocketAddress severSocketAddress = new InetSocketAddress(serverName, serverPort);

		try (Socket clientSocket = new Socket()) {
			// connect to server in the specific timeout 3000 ms
			System.out.println("Connect to server " + serverName + ":" + serverPort);
			clientSocket.connect(severSocketAddress, 3000);
			// get client address and port at local host
			InetSocketAddress socketAddress = (InetSocketAddress) clientSocket.getLocalSocketAddress();
			String clientAddress = socketAddress.getAddress().getHostAddress();
			int clientPort = socketAddress.getPort();
			System.out.println("Client " + clientAddress + ":" + clientPort);
			System.out.println("Connecting to server " + serverName + ":" + serverPort);

			try {
				inputStream = clientSocket.getInputStream();
				outputStream = clientSocket.getOutputStream();

				launch(args);

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			System.out.println("Connection shutdown");
		}

	}

	public void onRegisterPressed(ActionEvent e) throws IOException {
		username = _username.getText();
		password = _password.getText();
		String acct = "Username " + username + " Password " + password;
		acct = "Register " + acct + "\n";

		String s = null;
		try {
			outputStream.write(acct.getBytes(), 0, acct.getBytes().length);
			outputStream.flush();

			byte[] buf = new byte[1024];
			int length = inputStream.read(buf);
			s = new String(buf, 0, length);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		if (s.charAt(0) == '1') {
			System.out.println(s + ": This account is already registered.");
			_notice1.setVisible(true);
			_notice2.setVisible(false);
			_notice3.setVisible(false);
			_notice4.setVisible(false);
		} else if (s.charAt(0) == '2') {
			System.out.println(s + ": Registration success.");
			_notice4.setVisible(true);
			_notice1.setVisible(false);
			_notice2.setVisible(false);
			_notice3.setVisible(false);
		}

	}

	public void onSignInPressed(ActionEvent e) throws IOException {
		username = _username.getText();
		password = _password.getText();
		String acct = "Username " + username + " Password " + password;
		acct = "Sign_in " + acct + "\n";

		String s = null;
		try {
			outputStream.write(acct.getBytes(), 0, acct.getBytes().length);
			outputStream.flush();

			byte[] buf = new byte[1024];
			int length = inputStream.read(buf);
			s = new String(buf, 0, length);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		if (s.charAt(0) == '3') {
			System.out.println(s + ": Password is incorrect.");
			_notice3.setVisible(true);
			_notice1.setVisible(false);
			_notice2.setVisible(false);
			_notice4.setVisible(false);
		} else if (s.charAt(0) == '4') {
			System.out.println(s + ": Success.");
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Cover.fxml"));
			Parent cover = loader1.load();
			mainScene = new Scene(cover);
			mainStage.setScene(mainScene);
		} else if (s.charAt(0) == '5') {
			System.out.println(s + ": This account does not exist.");
			_notice2.setVisible(true);
			_notice1.setVisible(false);
			_notice3.setVisible(false);
			_notice4.setVisible(false);
		}
	}

}