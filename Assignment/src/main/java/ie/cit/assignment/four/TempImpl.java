package ie.cit.assignment.four;

import java.rmi.RemoteException;

public class TempImpl implements Temp {

	public SharedBuffer receiveBuffer(Service service) throws RemoteException {
		service.setBuffer(new SharedBuffer());
		return service.getBuffer();
	}
}
