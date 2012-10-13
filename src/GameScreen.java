import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/*
 * Momin Khan 05/09/2011
 * GameScreen - Handles all the drawing of the game.  Draws a grid depending on its preferred
 * size setting, which should be set outside of the class. Looks at the state of the current 
 * game and draws the board accordingly.  Needs TicTacToe and Game to work.
 */
@SuppressWarnings("serial")
public class GameScreen extends JPanel {
	private int lastX = 0;
	private int lastY = 0;
	private int winDir = 4;
	private boolean lastMoved = false;
	private static final Color LASTMOVEGREEN = new Color(185, 255, 117);

	public GameScreen() {
		super();
	}

	public void reset() {
		lastMoved = false;
		winDir = 4;
		this.repaint();
	}

	public void setLastMove(int move) {
		lastX = move / 100;
		lastY = move % 100;
		lastMoved = true;
	}

	public void setWinMove(int direction) {
		winDir = direction;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = this.getPreferredSize().width / 50;
		int y = this.getPreferredSize().height / 50;

		if (lastMoved) {
			g.setColor(LASTMOVEGREEN);
			g.fillRect(lastX * 50, lastY * 50, 50, 50);
		}

		if (winDir != 4) {
			int l = TicTacToe.game.getL();
			int h = TicTacToe.game.getH();
			int a, b;
			switch (winDir) {
			case 0:
				for (int i = 0; i < l; i++)
					g.fillRect(i * 50, lastY * 50, 50, 50);
				break;
			case 1:
				for (int i = 0; i < h; i++)
					g.fillRect(lastX * 50, i * 50, 50, 50);
				break;
			case 2:
				a = lastY;
				b = lastX;
				while (!(a == 0 || b == 0)) {
					a--;
					b--;
				}
				try {
					for (int i = 0; i < l && i < h; i++)
						g.fillRect((b + i) * 50, (a + i) * 50, 50, 50);
				} catch (ArrayIndexOutOfBoundsException e){}
				break;
			case 3:
				a = lastY;
				b = lastX;
				while (!(a == (h - 1) || b == 0)) {
					a++;
					b--;
				}
				try {
					for (int i = 0; i < l && i < h; i++)
						g.fillRect((b + i) * 50, (a - i) * 50, 50, 50);
				} catch (ArrayIndexOutOfBoundsException e){}
				break;
			}
		}

		g.setColor(Color.BLACK);
		for (int i = 1; i < x; i++)
			g.fillRoundRect((i * 50) - 2, 0, 4, y * 50, 4, 4);
		for (int i = 1; i < y; i++)
			g.fillRoundRect(0, (i * 50) - 2, x * 50, 4, 4, 4);

		if (TicTacToe.game != null) {
			char[][] state = TicTacToe.game.getGameState();
			if (state != null)
				for (int i = 0; i < state.length; i++)
					for (int j = 0; j < state[0].length; j++) {
						if (state[i][j] == 'X')
							g.drawImage(MainWindow.xImg, (j * 50) + 5,
									(i * 50) + 5, null);
						else if (state[i][j] == 'O')
							g.drawImage(MainWindow.oImg, (j * 50) + 5,
									(i * 50) + 5, null);
					}
		}
	}
}
