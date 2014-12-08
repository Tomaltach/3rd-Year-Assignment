package ie.cit.assignment;

import ie.cit.assignment.five.DriverFive;
import ie.cit.assignment.four.DriverFour;
import ie.cit.assignment.one.factorymethod.Agency;
import ie.cit.assignment.one.factorymethod.Company;
import ie.cit.assignment.one.observer.Person;
import ie.cit.assignment.one.observer.Product;
import ie.cit.assignment.three.DriverThree;
import ie.cit.assignment.two.DriverTwo;

public class Main {

	public static void main(String[] args) {
		System.out.println("Lab 1:");
		callLab1();
		
		System.out.println("\nLab 2:");
		callLab2();
		
		System.out.println("\nLab 3:");
		callLab3();
		
		System.out.println("\nLab 4:");
		callLab4();

		System.out.println("\nLab 5:");
		callLab5();
	}
	private static void callLab1() {
		factoryCall();
		observerCall();
	}
	private static void callLab2() {
		mutlithread();
	}
	private static void callLab3() {
		distributed();
	}
	private static void callLab4() {
		polling();
	}
	private static void callLab5() {
		callback();
	}
	private static void factoryCall() {
		//Company is only looking for a software developer.
		Company company = new Agency();
		company.requirement("Software Developer");
	}
	private static void observerCall() {
		// create the people
		Person per1 = new Person("Edmond");
		Person per2 = new Person("Laura");
		
		// set product to unavailable
		Product bungie = new Product("Halo", "Game", "Not available");
		
		// add the customers to the observer list
		bungie.registerOberver(per1);
		bungie.registerOberver(per2);
		
		// change the availability
		bungie.setAvailability("Available");
	}
	private static void mutlithread() {
		DriverTwo.init();
	}
	private static void distributed() {
		DriverThree.init();
	}
	private static void polling() {
		DriverFour.init();
	}
	private static void callback() {
		DriverFive.init();
	}
}