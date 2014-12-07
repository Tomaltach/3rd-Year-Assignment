package ie.cit.assignment.four;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
	private static SharedBuffer buffer;
	
	public static void main(String[] args) {
		init();
	}
	public static void init() {
		try {
			LocateRegistry.createRegistry(2001); // creates registry each time
			Service obj = new ServiceImpl();            
			Service stub = (Service) UnicastRemoteObject.exportObject(obj, 0);
            
			Temp temp = new TempImpl();
			buffer = temp.receiveBuffer(stub);
            
            stub.setBuffer(buffer);
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry(2001); //must create from terminal each time
            registry.bind("Guesses", stub);
			
        	Producer producer = new Producer(stub.getBuffer());
			producer.start();
			
			System.err.println("Server ready");		
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
	}
}
