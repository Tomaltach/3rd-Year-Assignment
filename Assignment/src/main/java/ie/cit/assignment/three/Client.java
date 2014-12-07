package ie.cit.assignment.three;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	public static void main(String[] args) {
		init();
	}
	public static void init() {
		try {
			Registry registry = LocateRegistry.getRegistry(2001);			
			Chat sStub;
			sStub = (Chat) registry.lookup("Chat");	
			
			System.out.println(sStub.message("Client", "Person"));
			System.out.println(sStub.message("Client", "Adding 9 + 8 = " + sStub.add(9, 8)));
		} catch(Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
