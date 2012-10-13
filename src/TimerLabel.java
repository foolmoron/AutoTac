import javax.swing.JLabel;

/*
 * Momin Khan 05/09/2011
 * TimerLabel - Label that keeps track of the time between start() and stop() calls.  Updates
 * displayed text continuously after start() is called.  Runs in its own thread.
 */
@SuppressWarnings("serial")
public class TimerLabel extends JLabel implements Runnable {
	private boolean go = false;
	private Thread t = null;
	private long start;
	private long end;
	private double time;

	public TimerLabel() {
		super();
	}

	public void start() {
		start = System.nanoTime();
			go = true;
		if (t == null) {
			this.repaint();
			t = new Thread(this);
			t.start();
		}
	}

	public void stop() {
		go = false;
	}

	public void run() {
		while (go) {
			try {
				end = System.nanoTime();
				time = (double) (end - start) / (double) 1000000000;
				this.setText("This move: " + time);
				this.repaint();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		end = System.nanoTime();
		time = (double) (end - start) / (double) 1000000000;
		this.setText("This move: " + time);
		t = null;
	}

	public double getTime() {
		return time;
	}
}
