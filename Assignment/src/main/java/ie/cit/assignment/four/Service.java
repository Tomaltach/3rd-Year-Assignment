package ie.cit.assignment.four;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
	public void setBuffer(SharedBuffer buffer) throws RemoteException;
	public SharedBuffer getBuffer() throws RemoteException;
}
