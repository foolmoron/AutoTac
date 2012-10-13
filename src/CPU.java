/*
 * Momin Khan 05/09/2011
 * CPU - Represents an automated player.  Computes the next move in a separate thread using
 * a minimax algorithm with alpha/beta pruning.
 */
public class CPU implements Runnable {
	private int maxdepth = TicTacToe.setdepth;
	public boolean forcemaxdepth = false;
	private int mindepth = 4;
	private Game game;
	private char icon;
	private char otherIcon;
	private boolean ready = false;
	private Thread t;
	private int move;
	private char[][] state = null;
	private MinimaxNode minimaxTree = null;
	private boolean maximizing;
	private MinimaxNode lastSeen = null;
	public boolean kill = false;

	public CPU(Game game, char icon, boolean maxPlayer, boolean forceMaxDepth) {
		this.game = game;
		this.icon = icon;
		forcemaxdepth = forceMaxDepth;
		maximizing = maxPlayer;
		if (icon == 'X')
			otherIcon = 'O';
		else if (icon == 'O')
			otherIcon = 'X';
	}

	public long fact(long x) {
		if (x > 20)
			return Long.MAX_VALUE;
		if (x <= 1)
			return 1;
		else
			return x * fact(x - 1);
	}

	public long exp(long x, long e) {
		if (e == 0)
			return 1;
		if (e == 1)
			return x;
		if (x > 80 && e > 9)
			return Long.MAX_VALUE;
		return x * exp(x, e - 1);
	}

	public void think(char[][] gamestate) {
		if (t != null)
			t.interrupt();
		state = gamestate;

		if (!forcemaxdepth) {
			long area = (state.length * state[0].length) - game.getMoves();
			int depth = 2;
			/* factorial-based depth values (don't really work) */
			// System.out.println(fact(area) + " " + fact(area - depth));
			// for( ;fact(area)/fact(area-depth) < 1000000 && depth<10;
			// depth++);

			/* exponent-based depth values that work pretty well */
			// System.out.println(exp(area, depth));
			// for (; exp(area, depth) < 500000 && depth < 10; depth++) {
			// }
			//
			// maxdepth = depth - 1;

			/* hardcoded depth values */
			switch ((int)(area / 10)) {
			case 0:
				maxdepth = 10;
				break;
			case 1:
				maxdepth = 5;
				break;
			case 2:
				maxdepth = 4;
				break;
			case 3:
				maxdepth = 4;
				break;
			case 4:
				maxdepth = 3;
				break;
			case 5:
				maxdepth = 3;
				break;
			case 6:
				maxdepth = 2;
				break;
			case 7:
				maxdepth = 2;
				break;
			case 8:
				maxdepth = 2;
				break;
			case 9:
				maxdepth = 2;
				break;
			case 10:
				maxdepth = 2;
				break;
			}
		}

		System.out.println("depth= " + maxdepth);
		t = new Thread(this);
		t.start();
	}

	public void run() {
		ready = false;
		minimaxTree = new MinimaxNode(game.getGameState(), 0, 9999, otherIcon);
		int bestMove = alphabeta(minimaxTree, maxdepth, -10000000, 10000000,
				maximizing, icon);
		ready = true;
		makeMove(minimaxTree.getBestMove());
	}

	private int alphabeta(MinimaxNode m, int depth, int a, int b,
			boolean maxing, char icon) {
		if (kill)
			t.stop();
		if (depth == 0 || m.isTerminal()) {
			// System.out.println("\n" + depth + "  " + maxing + m + "h="
			// + m.heuristicValue() + "ab= " + m.heuristicValue() + "\n"
			// + m.getBestMove());
			return (depth + 1) * m.heuristicValue();
		}
		char nextIcon = 0;
		if (icon == 'X')
			nextIcon = 'O';
		else if (icon == 'O')
			nextIcon = 'X';
		if (maxing) {
			for (MinimaxNode child : m.getChildren(icon, m.value, maxdepth
					- depth)) {
				if (m.getBestMove() == 9999)
					m.setBestMove(child.move);
				int ab = alphabeta(child, depth - 1, a, b, !maxing, nextIcon);
				if (ab > a) {
					m.setBestMove(child.move);
					a = ab;
				}
				if (b <= a) {
					break;
				}
			}
			// System.out.println("\n" + depth + "  " + maxing + m + "h="
			// + m.heuristicValue() + "ab= " + a + "\n" + m.getBestMove());
			return a;
		} else {
			for (MinimaxNode child : m.getChildren(icon, m.value, maxdepth
					- depth)) {
				if (m.getBestMove() == 9999)
					m.setBestMove(child.move);
				int ab = alphabeta(child, depth - 1, a, b, !maxing, nextIcon);
				if (ab < b) {
					m.setBestMove(child.move);
					b = ab;
				}
				if (b <= a) {
					break;
				}
			}
			// System.out.println("\n" + depth + "  " + maxing + m + "h="
			// + m.heuristicValue() + "ab= " + b + "\n" + m.getBestMove());
			return b;
		}
	}

	public boolean isReady() {
		return ready;
	}

	public void makeMove(int move) {
		if (ready) {
			if (icon == game.getCpuIcon())
				game.cpuMove(move);
			else if (icon == game.getPlayerIcon())
				game.playerMove(move);
		}
	}
}
