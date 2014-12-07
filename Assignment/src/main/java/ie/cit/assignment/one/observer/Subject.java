package ie.cit.assignment.one.observer;

public interface Subject {

	public void registerOberver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObservers(Product product, String availability);
	
}
