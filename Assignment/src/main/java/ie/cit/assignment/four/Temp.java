package ie.cit.assignment.four;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Temp extends Remote {
	public SharedBuffer receiveBuffer(Service service) throws RemoteException; 
}
