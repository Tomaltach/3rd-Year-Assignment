package ie.cit.assignment.two;

public class DriverTwo {
	static SharedBuffer buffer = new SharedBuffer();

	public static void main(String[] args) {
		init();
	}
	public static void init() {
		Producer producer = new Producer(buffer);
		producer.start();
		new Consumer(buffer).start();
	}
}
