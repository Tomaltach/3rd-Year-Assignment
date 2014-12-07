package ie.cit.assignment.one.observer;

public class Person implements Observer {
	private String personName;
	
	public Person(String personName) {
		this.personName = personName;
	}	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public void update(Product product, String availability) {
		System.out.println(personName + ", Subject is now " + availability + ".");
	}
}
