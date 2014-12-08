package ie.cit.assignment.five;

import java.util.LinkedList;
import java.util.Queue;

public class Caller {
	static CallBack callBack1;
	static CallBack callBack2;
	Queue<DataPrinter> queue = new LinkedList<DataPrinter>();
	
	public static void main(String[] args) throws InterruptedException {
		init();
	}
	public static void init() throws InterruptedException {
		Caller caller = new Caller();
		callBack1 = new DataPrinter("DP 1", caller);
		callBack2 = new DataPrinter("DP 2", caller);
		
		Thread t1 = new Thread((Runnable) callBack1);
		Thread t2 = new Thread((Runnable) callBack2);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		caller.register();
	}
	public void register() {
		int[] numbers = {1,2,3,4,5,6,7,8,9,0};
		for(int i=0; i<numbers.length; i++) {
			printerQueue(numbers[i]);
		}
	}
	public void printerQueue(int number) {
		String data = number + " produced by Caller. ";
		CallBack calling = queue.poll();
		calling.callback(data);
	}
	public void addToQueue(DataPrinter dataPrinter) {
		queue.add(dataPrinter);
	}
}
