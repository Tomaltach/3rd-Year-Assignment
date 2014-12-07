package ie.cit.assignment.one.factorymethod;

public class Agency implements Company {
	
	// Agency only puts forward applicants with a 2.1 or higher.
	private String results = "2.1+"; 
	
	// Agency requirements for applicants
	public Applicant requirement(String string) {
		return new Applicant(string, results);
	}
}
