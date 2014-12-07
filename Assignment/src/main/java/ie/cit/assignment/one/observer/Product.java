package ie.cit.assignment.one.observer;

import java.util.ArrayList;

public class Product implements Subject {
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private String productName;
	private String productType;
	private String availability;
	
	public Product(String productName, String productType, String availability) {
		
		super();
		this.productName = productName;
		this.productType = productType;
		this.availability = availability;
	}
	
	public ArrayList<Observer> getObservers() {
		return observers;
	}
	
	public void setObservers(ArrayList<Observer> observers) {
		this.observers = observers;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductType() {
		return productType;
	}
	
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	public String isAvailability() {
		return availability;
	}
	
	public void setAvailability(String availability) {
		
		if(!(this.availability.equalsIgnoreCase(availability))) {
			this.availability = availability;
			notifyObservers(this, availability);
		}
	}
	
	public void registerOberver(Observer observer) {
		observers.add(observer);
	}
	
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers(Product product, String availability) {
		
		System.out.println("\nNotify Observers that Subject has changed availability.");
		
		// loop through the list of observers and update the objects
		for(Observer obs : observers) {
			obs.update(product, this.availability);
		}
	}
	
}
