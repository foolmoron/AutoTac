import java.awt.Color;

import javax.swing.JLabel;

/*
 * Momin Khan 05/09/2011
 * ColorLabel - When setText() is called, it displays the text in a looping rainbow display
 * until the text is set to the empty string. Runs as a separate thread.  
 */
@SuppressWarnings("serial")
public class ColorLabel extends JLabel implements Runnable {
	private static final Color R = new Color(255, 0, 0);
	private static final Color O = new Color(255, 127, 0);
	private static final Color Y = new Color(255, 235, 0);
	private static final Color G = new Color(0, 255, 0);
	private static final Color B = new Color(0, 0, 255);
	private static final Color I = new Color(111, 0, 255);
	private static final Color V = new Color(255, 20, 147);
	private static final Color[] colors = { R, O, Y, G, B, I, V };
	private boolean go = false;
	private Thread t = null;
	private String text;

	public ColorLabel() {
		super();
	}

	public void setText(String s) {
		text = s;
		super.setText(text);
		if (!text.equals("") && t == null) {
			go = true;
			t = new Thread(this);
			t.start();
		}
	}

	public void run() {
		super.setText(text);
		while (go && !text.equals(""))
			colors();
		t = null;
	}

	private void colors() {
		for (Color c : colors) {
			super.setForeground(c);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}