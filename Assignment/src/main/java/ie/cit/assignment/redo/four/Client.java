package ie.cit.assignment.redo.four;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client extends JFrame {
	private static final long serialVersionUID = 1593186871516077879L;

	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String serverIP;
	private Socket connection;
	
	public Client(String host) {
		super("Client Messenger");
		serverIP = host;
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					sendMessage(event.getActionCommand());
					userText.setText("");
				}
			}
		);
		add(userText, BorderLayout.NORTH);
		chatWindow = new JTextArea();
		chatWindow.setEditable(false);
		add(new JScrollPane(chatWindow), BorderLayout.CENTER);
		setSize(300,150);
		setVisible(true);
	}

	// connect to server
	public void startRunning() {
		try {
			connectToServer();
			setUpStreams();
			whileChatting();
		} catch(EOFException e) {
			showMessage("\nCLIENT: ERROR: Client terminated connection!");
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	private void connectToServer() throws IOException {
		showMessage("CLIENT: Attempting connection...\n");
		connection = new Socket(InetAddress.getByName(serverIP), 6789);
		showMessage("CLIENT: Connected to: " + connection.getInetAddress().getHostName());
	}
	private void setUpStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\nCLIENT: Streams are good to go!");
	}
	private void whileChatting() throws IOException {
		ableToType(true);
		do {
			try {
				message = (String) input.readObject();
				showMessage("\n" + message);
			} catch(ClassNotFoundException e) {
				showMessage("\nCLIENT: ERROR: I don't know that object type!");
			}
		} while(!message.equals("SERVER - END"));
	}
	private void ableToType(final boolean b) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					userText.setEditable(b);
				}
			}
		);
	}
	private void sendMessage(String message) {
		try {
			output.writeObject("CLIENT - " + message);
			output.flush();
			showMessage("\nCLIENT - " + message);
		} catch(IOException e) {
			chatWindow.append("CLIENT: ERROR: Something messed up sending message!");;
		}
	}
	private void showMessage(final String string) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					chatWindow.append(string);
				}
			}
		);
		System.out.print(string);
	}
	private void close() {
		showMessage("\nCLIENT: Closing down now!...");
		ableToType(false);
		try {
			output.close();
			input.close();
			connection.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
