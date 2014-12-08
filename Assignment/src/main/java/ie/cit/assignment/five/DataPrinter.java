package ie.cit.assignment.five;

import java.util.Random;

public class DataPrinter extends Thread implements CallBack {
	private String name;
	private Caller caller;
	Random rand = new Random();

	public DataPrinter(String name, Caller caller) {
		this.name = name;
		this.caller = caller;
	}
	public void run() {
		ready();
	}
	public void callback(String data) {
		int waiting = randomWait();
		System.out.println(name + ": recieved " + data + waiting + "ms");
		ready();
	}
	public void ready() {
		caller.addToQueue(this);
	}
	private int randomWait() {
	    int waiting = rand.nextInt((2500 - 500) + 1) + 50;
		try {
			Thread.sleep(waiting);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return waiting;
	}
}
