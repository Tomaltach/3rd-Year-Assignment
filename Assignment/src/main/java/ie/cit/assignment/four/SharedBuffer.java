package ie.cit.assignment.four;

import java.io.Serializable;

public class SharedBuffer implements Serializable {
	private static final long serialVersionUID = 1L;
	private final int MAX_GUESSES = 6;
	private int random;
	private int guess;
	private int count = 1;
	private int correct;
	private int wrong;
	
	public int getRandom() {
		System.out.println("SharedBuffer - " + random);
		return random;
	}
	public void setRandom(int random) {
		this.random = random;
		System.out.println("SharedBuffer - " + random);
	}
	public int getGuess() {
		return guess;
	}
	public void setGuess(int guess) {
		this.guess = guess;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCorrect() {
		return correct;
	}
	public void setCorrect(int correct) {
		this.correct = correct;
	}
	public int getWrong() {
		return wrong;
	}
	public void setWrong(int wrong) {
		this.wrong = wrong;
	}
	public int getMAX_GUESSES() {
		return MAX_GUESSES;
	}	
}