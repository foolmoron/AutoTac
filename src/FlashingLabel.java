import javax.swing.JLabel;

/*
 * Momin Khan 05/09/2011
 * FlashingLabel - When setText() is called, it flashes the text 3 times, then hold the
 * display for a while, then clears itself. Useful for warning messages. Runs in its own thread.   
 */
@SuppressWarnings("serial")
public class FlashingLabel extends JLabel implements Runnable {
	private Thread t = null;
	private String text;
	
	public FlashingLabel(){
		super();
	}
	
	public void setText(String s){
		text = s;
		super.setText(text);
		if (!text.equals("") && t == null){
			t = new Thread(this);
			t.start();
		}
	}
	
	public void run() {
		super.setText(text);
		try {
			Thread.sleep(200);
			super.setText("");
			Thread.sleep(200);
			super.setText(text);
			Thread.sleep(200);
			super.setText("");
			Thread.sleep(200);
			super.setText(text);
			Thread.sleep(1500);
			super.setText("");
			t = null;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

}
