import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

/*
 * Momin Khan 05/09/2011
 * TicTacToe - starts each TTT game in the proper mode with new CPU players for each game.
 */
public class TicTacToe {
	public static MainWindow main;
	public static Game game;
	public static int wins = 0, losses = 0, draws = 0;
	public static boolean twoPlayer = false;
	public static boolean twoCPUs = false;
	public static int setdepth = 100;

	public static void main(String[] args) {
		if (args.length > 0)
			setdepth = Integer.parseInt(args[0]);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				main = new MainWindow();
				main.setLocationRelativeTo(null);
				main.setVisible(true);
			}
		});
	}

	public static void startGame(int length, int height, int inarow) {
		main.getGameCanvas().setCursor(main.xCursor);
		main.getGameCanvas().setPreferredSize(
				new Dimension(length * 50, height * 50));
		main.getGameCanvas().revalidate();
		boolean first = Math.random() < 0.50;
		game = new Game(length, height, inarow, first);
		char a, b;
		if (first) {
			a = 'X';
			b = 'O';
		} else {
			a = 'O';
			b = 'X';
		}
		if (twoPlayer)
			game.setPlayers(null, null);
		else if (twoCPUs)
			game.setPlayers(new CPU(game, a, first, setdepth!=100), new CPU(game, b, !first, setdepth!=100));
		else
			game.setPlayers(null, new CPU(game, b, !first, setdepth!=100));
		main.getPlayerMoveTimeLbl().setText("This move: ");
		main.getPlayerLastMoveLbl().setText("Last move: ");
		main.getPlayerAvgTimeLbl().setText("Avg time: ");
		main.getCpuMoveTimeLbl().setText("This move: ");
		main.getCpuLastMoveLbl().setText("Last move: ");
		main.getCpuAvgTimeLbl().setText("Avg time: ");
		updateLabels();
		game.play();
	}

	public static void updateLabels() {
		main.getPlayerWinsLbl().setText(
				"W \\ L \\ D: " + wins + " \\ " + losses + " \\ " + draws);
		main.getCPUWinsLbl().setText(
				"W \\ L \\ D: " + losses + " \\ " + wins + " \\ " + draws);

	}

	public static void insanity(int l, int w, int n) {
		while (true) {
			if (game == null || game.gameover())
				startGame(l, w, n);
		}
	}
}
