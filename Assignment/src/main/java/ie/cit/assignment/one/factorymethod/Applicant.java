package ie.cit.assignment.one.factorymethod;

public class Applicant {
	
	private String string;
	private String results;
	
	public Applicant(String string, String results) {
		this.string = string;
		this.results = results;
		print();
	}
	
	public void print() {
		System.out.println("Job type wanted =\t" + string
						+ "\nGrade to qualify =\t" + results);
	}
}
