package ie.cit.assignment.two;

public class Consumer extends Thread {
	private int count = 1;
	private int numCorrect = 0;
	private int numWrong = 0;
	private SharedBuffer buffer;
	
	public Consumer(SharedBuffer buffer) {
		this.buffer = buffer;
	}
	public void check() throws InterruptedException {
		if(buffer.getGuess() == buffer.getRandom()) {
			numCorrect++;
			reset();
			buffer.setCount(count);
			buffer.setCorrect(numCorrect);
			buffer.setWrong(numWrong);
		} else {
			count++;
			if(count >= buffer.getMAX_GUESSES()) {
				numWrong++;
				reset();
				buffer.setCount(count);
				buffer.setCorrect(numCorrect);
				buffer.setWrong(numWrong);
			} else {
				buffer.setCount(count);
				buffer.setCorrect(numCorrect);
				buffer.setWrong(numWrong);
			}
		}
	}
	public void reset() throws InterruptedException {
		synchronized(buffer) {
			count = 1;
			buffer.setCorrect(count);
			
			buffer.notify();
			buffer.wait();
		}
	}

	public void run() {
		new CheckGUI(buffer, this);
	}
}
