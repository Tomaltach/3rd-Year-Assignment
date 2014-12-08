package ie.cit.assignment.five;

public class DriverFive {

	public static void main(String[] args) {
		init();
	}
	public static void init() {
		try {
			Caller.init();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
