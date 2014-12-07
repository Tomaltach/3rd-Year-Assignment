package ie.cit.assignment.three;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Chat extends Remote {
	public String message(String calling, String name) throws RemoteException;
	public int add(int x, int y) throws RemoteException;
}
