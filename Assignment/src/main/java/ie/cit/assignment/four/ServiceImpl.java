package ie.cit.assignment.four;

import java.rmi.RemoteException;

public class ServiceImpl implements Service {
	SharedBuffer buffer;
	
	public void setBuffer(SharedBuffer buffer) throws RemoteException {
		this.buffer = buffer;
	}
	public SharedBuffer getBuffer() throws RemoteException {
		return buffer;
	}
}
