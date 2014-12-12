package ie.cit.assignment.redo.four;

import javax.swing.JFrame;

public class ServerTest {

	public static void main(String[] args) {
		Server ser;
		ser = new Server();
		ser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ser.startRunning();
	}
}
