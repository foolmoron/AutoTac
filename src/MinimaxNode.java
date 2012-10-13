import java.util.Collections;
import java.util.Vector;

/*
 * Momin Khan 05/09/2011
 * MinimaxNode - Represents a possible future state of the game.  Contains a game state 
 * and computes the heuristic value associated with the state.
 */
public class MinimaxNode implements Comparable<MinimaxNode> {
	private Vector<MinimaxNode> children = new Vector<MinimaxNode>();
	private char[][] state = null;
	private int stateStatus;
	private char icon;
	public int move;
	private int bestMove = 9999;
	public int value;
	private int height;
	private int length;
	private boolean terminal = false;

	public MinimaxNode(char[][] gameState, int score, int move, char c) {
		icon = c;
		height = gameState.length;
		length = gameState[0].length;
		state = new char[height][length];
		int count = 0;
		for (int i = 0; i < height; i++)
			for (int j = 0; j < length; j++) {
				state[i][j] = gameState[i][j];
				if (state[i][j] != 0)
					count++;
			}
		if (move != 9999) {
			this.move = move;
			int x = move / 100;
			int y = move % 100;
			state[y][x] = c;

			stateStatus = TicTacToe.game.checkGameStatus(state, move, false,
					count + 1);
			if (stateStatus != 4)
				terminal = true;

			if (terminal) {
				if (stateStatus == 5)
					value = 0;
				else if (icon == 'X')
					value = 1000000;
				else if (icon == 'O')
					value = -1000000;
			} else
				value = score + stateScore();
		}
	}

	public int getMoveByScore(int score) {
		for (MinimaxNode m : children) {
			if (m.value == score)
				return m.move;
		}
		return 9999;
	}

	public void setBestMove(int bestMove) {
		this.bestMove = bestMove;
	}

	public int getBestMove() {
		return bestMove;
	}

	public int heuristicValue() {
		return value;
	}

	private int stateScore() {
		int score = 0;
		int xPos = move / 100;
		int yPos = move % 100;
		char[] line = new char[height];
		// check horizontal
		score += lineScore(state[yPos]);
		// check vertical
		for (int y = 0; y < height; y++) {
			line[y] = state[y][xPos];
		}
		score += lineScore(line);
		// check bslash
		int a = yPos;
		int b = xPos;
		while (!(a == 0 || b == 0)) {
			a--;
			b--;
		}
		try {
			for (int i = 0; i < height && i < length; i++)
				line[i] = state[a + i][b + i];
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		// check fslash
		a = yPos;
		b = xPos;
		while (!(a == (height - 1) || b == 0)) {
			a++;
			b--;
		}
		try {
			for (int i = 0; i < height && i < length; i++)
				line[i] = state[a - i][b + i];
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		return score;
	}

	public static int lineScore(char[] line) {
		char X = 'X';
		char O = 'O';
		char last = 'e';
		int len = line.length;
		int i = 0;
		int combo = 0;
		int score = 0;
		int borders = 0;

		while (i < len) {
			if (line[i] == X) {
				combo++;
				if (last == O || i == 0)
					borders++;
				if (i == len - 1 || line[i + 1] == O) {
					borders++;
					score += combo * (2 - borders);
					combo = 0;
					borders = 0;
				}
			} else if (line[i] == O) {
				combo--;
				if (last == X || i == 0)
					borders++;
				if (i == len - 1 || line[i + 1] == X) {
					borders++;
					score += combo * (2 - borders);
					combo = 0;
					borders = 0;
				}
			} else if (line[i] == 0) {
				score += combo * (2 - borders);
				combo = 0;
				borders = 0;
			}
			last = line[i];
			i++;
		}
		return score;
	}

	public boolean isTerminal() {
		return terminal;
	}

	public Vector<MinimaxNode> getChildren() {
		return children;
	}

	public Vector<MinimaxNode> getChildren(char currentIcon, int score,
			int depth) {
		for (int y = 0; y < height; y++)
			for (int x = 0; x < length; x++)
				if (state[y][x] == 0)
					children.add(new MinimaxNode(state, score, (x * 100 + y),
							currentIcon));
		if (depth < 2) {
			if (currentIcon == 'X')
				Collections.sort(children);
			else Collections.sort(children, Collections.reverseOrder());
		}
		return children;
	}

	public int compareTo(MinimaxNode m) {
		return this.value - m.value;
	}

	public String toString() {
		String s = "";
		for (int y = 0; y < height; y++) {
			s += "\n";
			for (int x = 0; x < length; x++) {
				s += state[y][x] + " ";
			}
		}
		return s;
	}
}
