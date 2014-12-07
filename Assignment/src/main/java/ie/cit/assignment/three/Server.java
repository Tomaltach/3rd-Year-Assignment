package ie.cit.assignment.three;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	public static void main(String[] args) {
		init();
	}
	public static void init() {
		try {		
			LocateRegistry.createRegistry(2001); // creates registry each time
			ChatMessenger obj = new ChatMessenger();
            Chat stub = (Chat) UnicastRemoteObject.exportObject(obj, 0);
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry(2001); //must create from terminal each time
            registry.bind("Chat", stub);
			System.err.println("Server ready");		
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
	}
}
