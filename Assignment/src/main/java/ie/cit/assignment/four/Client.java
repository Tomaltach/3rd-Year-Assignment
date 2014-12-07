package ie.cit.assignment.four;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	public static void main(String[] args) {
		init();
	}
	public static void init() {
		try {
			Registry registry = LocateRegistry.getRegistry(2001);			
			Service sStub;
			sStub = (Service) registry.lookup("Guesses");
			new Consumer(sStub.getBuffer()).start();
		} catch(Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
