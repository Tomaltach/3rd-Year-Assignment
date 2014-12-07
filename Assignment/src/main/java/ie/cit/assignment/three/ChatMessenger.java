package ie.cit.assignment.three;

import java.rmi.RemoteException;

public class ChatMessenger implements Chat {

	public ChatMessenger() {}
	public String message(String calling, String name) throws RemoteException {
		return calling + ": message -> " + name;
	}
	public int add(int x, int y) throws RemoteException {
		return x + y;
	}
}
