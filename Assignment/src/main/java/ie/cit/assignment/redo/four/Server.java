package ie.cit.assignment.redo.four;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Server extends JFrame {
	private static final long serialVersionUID = 9174746364660591137L;

	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private ServerSocket server;
	private Socket connection;
	
	public Server() {
		super("Chat Server");
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
		add(new JScrollPane(chatWindow));
		setSize(300, 150);
		setVisible(true);
	}
	
	// run sever
	public void startRunning() {
		try {
			server = new ServerSocket(6789, 10);
			while(true) {
				try {
					waitForConnection();
					setupStreams();
					whileChatting();
				} catch(EOFException e) {
					showMessage("\nSERVER: ERROR: Server ended the connection!");
				} finally {
					close();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void waitForConnection() throws IOException {
		showMessage("SERVER: Waiting for someone to connect!...\n");
		connection = server.accept();
		showMessage("SERVER: Now connected to " + connection.getInetAddress().getHostName());
	}
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\nSERVER: Streams are now setup!");
	}
	private void whileChatting() throws IOException {
		sendMessage("\nSERVER: You are now connected!");
		ableToType(true);
		do {
			try {
				message = (String) input.readObject();
				showMessage("\n" + message);
			} catch(ClassNotFoundException e) {
				showMessage("\nSERVER: ERROR: Connection dropped!");
			}
		} while(!message.equals("CLIENT - END"));
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
			output.writeObject("SERVER - " + message);
			output.flush();
			showMessage("\nSERVER - " + message);
		} catch(IOException e) {
			chatWindow.append("SERVER: ERROR: Something messed up sending message!");;
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
		showMessage("\nSERVER: Closing connections!...");
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
