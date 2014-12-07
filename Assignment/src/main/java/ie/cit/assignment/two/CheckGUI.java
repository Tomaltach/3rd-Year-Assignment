package ie.cit.assignment.two;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private boolean changeFlag = false;
	private JFrame frame;
	private JLabel guessLabel = new JLabel();
	private JLabel correctLabel = new JLabel();
	private JLabel wrongLabel = new JLabel();
	private Consumer consumer;
	private SharedBuffer buffer;
	private ArrayList<JCheckBox> checkboxes = new ArrayList<JCheckBox>();
	private int correctAnswers = 0;
	private int wrongAnswers = 0;
	private int numGuesses = 1;
	
  	public CheckGUI(SharedBuffer buffer, Consumer consumer) {
		this.buffer = buffer;
		this.consumer = consumer;
  		init();
	}
  	private void init() {
  		frame = new JFrame("Multi-threaded Window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setPreferredSize(new Dimension(500, 300));		

		JPanel panel = new JPanel(new BorderLayout());		
		JPanel panel1 = new JPanel(new GridLayout(0,1));
			
		guessLabel.setText("# of Guesses: " + buffer.getCount() + "/5");
		correctLabel.setText("# of Correct: " + buffer.getCorrect());
		wrongLabel.setText("# of Wrong: " + buffer.getWrong());

		JPanel panel2 = new JPanel(new GridLayout(0,1));
		
		panel1.add(guessLabel);
		panel1.add(correctLabel);
		panel1.add(wrongLabel);
		panel2.add(checkboxes());
		panel.add(panel1, BorderLayout.WEST);
		panel.add(panel2, BorderLayout.EAST);
		frame.add(panel);
		
		frame.pack();
		frame.setVisible(true);

		buffer.getRandom();
  	}
 	private JPanel checkboxes() {
 		JPanel panel = new JPanel(new GridLayout(2,1));
 		panel.add(checkbox(1));
 		panel.add(checkbox(2));
 		panel.add(checkbox(3));
 		panel.add(checkbox(4));
 		panel.add(checkbox(5));
 		panel.add(checkbox(6));
 		panel.add(checkbox(7));
 		panel.add(checkbox(8));
 		panel.add(checkbox(9));
 		panel.add(checkbox(10));
 		return panel;
 	}
	private JCheckBox checkbox(final int boxNumber) {
		final JCheckBox cb = new JCheckBox("" + boxNumber);
		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cb.setEnabled(false);
				try {
					check(boxNumber);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		checkboxes.add(cb);
		return cb;
	}
	public void check(int boxNumber) throws InterruptedException {
		buffer.setGuess(boxNumber);
		consumer.check();
		setNew();
		if(changeFlag == true) {
			for(int i=0; i<checkboxes.size(); i++) {
				checkboxes.get(i).setEnabled(true);
				checkboxes.get(i).setSelected(false);
			}
			//consumer.reset();
			buffer.getRandom();
		}
		repaint();
	}
	public void setNew() {
		if(correctAnswers < buffer.getCorrect() || wrongAnswers < buffer.getWrong()) {
			changeFlag = true;
		} else {
			changeFlag = false;
		} 
		numGuesses = buffer.getCount();
		correctAnswers = buffer.getCorrect();
		wrongAnswers = buffer.getWrong();
	}
	public void repaint() {
		guessLabel.setText("# of Guesses: " + numGuesses + "/5");
		correctLabel.setText("# of Correct: " + correctAnswers);
		wrongLabel.setText("# of Wrong: " + wrongAnswers);
		
		frame.repaint();
	}
}
