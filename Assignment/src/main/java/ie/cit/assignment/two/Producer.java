package ie.cit.assignment.two;

import java.util.Random;

public class Producer extends Thread {
	private SharedBuffer buffer;
	
	public Producer(SharedBuffer buffer) {
		this.buffer = buffer;
	}
	public void run() {
		try {
			while(true) {
				setRandom();
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void setRandom() throws InterruptedException {
		synchronized(buffer) {
			Random r = new Random();
			int rand = r.nextInt(10) + 1;
			buffer.setRandom(rand);
			System.err.println(rand);
			buffer.notify();
			buffer.wait();
		}
	}
}
