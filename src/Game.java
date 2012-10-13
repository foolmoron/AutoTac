import java.awt.Cursor;

import javax.swing.SwingUtilities;

/*
 * Momin Khan 05/09/2011
 * Game - Keeps track of current game state, win/loss/draw status, and manages player turns.
 */
public class Game {
	private char[][] gameState = null;
	private boolean gameover = false;
	private boolean draw = false;
	private boolean Xwin = false;
	private static final char X = 'X';
	private static final char O = 'O';
	private static final String[] XStrings = { "X WINS!!!", "~*X WINS*~",
			"A WINNER IS X", "X PROBABLY CHEATED!!", "WE SO X-ITED",
			"X-TREME!!!", "YOU JUST GOT X-ECUTED" };
	private static final String[] OStrings = { "O WINS!!!", "~*O WINS*~",
			"A WINNER IS O", "O PROBABLY CHEATED!!", "O SNAP", "O RLY?",
			"LET ME SEE YOUR O FACE" };
	private static final String[] DrawStrings = { "YOU BOTH LOSE!!",
			"I'M NOT MAD, JUST DISAPPOINTED", "IT'S A DRAW!!!",
			"~*DRAW GAME*~", "STALEMATE! EVERBODY LOSES!!!", "HOW BORING...",
			"CONGRATS ON WASTING YOUR TIME!!", "DO YOU NEED A TUTORIAL?",
			"UR DOIN IT RONG" };
	private int l = 0;
	private int h = 0;
	private int n = 0;
	private int moves = 0;
	private int lastMove;
	private boolean playerFirst;
	private char playerIcon;
	private CPU player;
	private char cpuIcon;
	private CPU cpu;
	public boolean playerTurn;
	private double playerLast, playerAvg, cpuLast, cpuAvg;
	private int playerMoves = 0, cpuMoves = 0;

	public Game(int length, int height, int inarow, boolean first) {
		l = length;
		h = height;
		n = inarow;
		playerFirst = first;
		playerTurn = first;
		gameState = new char[h][l];
		for (int i = 0; i < h; i++)
			for (int j = 0; j < l; j++)
				gameState[i][j] = 0;
		TicTacToe.main.gameEnabled = true;
		TicTacToe.main.getGameCanvas().reset();
		TicTacToe.main.getMessageLbl().setText("");
		TicTacToe.main.getGameInfoLbl().setText(
				l + " by " + h + " to " + n + " in-a-row");
		if (playerFirst) {
			playerIcon = 'X';
			TicTacToe.main.getGameCanvas().setCursor(TicTacToe.main.xCursor);
			cpuIcon = 'O';
			TicTacToe.main.getMessageLbl().setText("Player goes first");
		} else {
			playerIcon = 'O';
			TicTacToe.main.getGameCanvas().setCursor(TicTacToe.main.oCursor);
			cpuIcon = 'X';
			TicTacToe.main.getMessageLbl().setText("CPU goes first");
		}
		if (TicTacToe.twoCPUs)
			TicTacToe.main.getGameCanvas().setCursor(Cursor.getDefaultCursor());
	}

	public void play() {
		if (playerFirst && !TicTacToe.twoCPUs) {
			TicTacToe.main.getPlayerMoveTimeLbl().start();
			letThink(player);
			return;
		}
		int x = (int) (Math.random() * l);
		int y = (int) (Math.random() * h);
		if (playerFirst)
			playerMove(x * 100 + y);
		else
			cpuMove(x * 100 + y);
		// else if (playerFirst) {
		// TicTacToe.main.getPlayerMoveTimeLbl().start();
		// letThink(player);
		// return;
		// } else {
		// TicTacToe.main.getCpuMoveTimeLbl().start();
		// letThink(cpu);
		// return;
		// }
	}

	public void letThink(CPU c) {
		if (c == null) {
			return;
		}
		c.think(getGameState());
	}

	public void setPlayers(CPU a, CPU b) {
		player = a;
		cpu = b;
	}

	public char[][] getGameState() {
		return gameState;
	}

	public char getPlayerIcon() {
		return playerIcon;
	}

	public char getCpuIcon() {
		return cpuIcon;
	}

	public CPU getPlayer() {
		return player;
	}

	public CPU getCPU() {
		return cpu;
	}

	public int getLastMove() {
		return lastMove;
	}

	public int getL() {
		return l;
	}

	public int getH() {
		return h;
	}

	public int getN() {
		return n;
	}

	public boolean gameover() {
		return gameover;
	}

	public int getMoves() {
		return moves;
	}

	public void enable() {
		TicTacToe.main.gameEnabled = true;
	}

	public void disable() {
		TicTacToe.main.gameEnabled = false;
	}

	synchronized public void playerMove(int move) {
		if (getGameState()[move % 100][move / 100] != 0) {
			TicTacToe.main.getWarningLbl().setText("Choose a blank spot");
			return;
		}
		TicTacToe.main.getMessageLbl().setText("");
		TicTacToe.main.getPlayerMoveTimeLbl().stop();
		playerMoves++;
		makeMove(playerIcon, move);
		playerLast = TicTacToe.main.getPlayerMoveTimeLbl().getTime();
		TicTacToe.main.getPlayerLastMoveLbl().setText(
				"Last move:  " + playerLast);
		playerAvg = (playerAvg * (playerMoves - 1) + playerLast) / playerMoves;
		TicTacToe.main.getPlayerAvgTimeLbl().setText("Avg time:  " + playerAvg);
		playerTurn = !playerTurn;
		if (!gameover) {
			TicTacToe.main.getCpuMoveTimeLbl().start();
			letThink(getCPU());
		}
	}

	synchronized public void cpuMove(int move) {
		if (getGameState()[move % 100][move / 100] != 0) {
			TicTacToe.main.getWarningLbl().setText("Choose a blank spot");
			return;
		}
		TicTacToe.main.getCpuMoveTimeLbl().stop();
		cpuMoves++;
		makeMove(cpuIcon, move);
		cpuLast = TicTacToe.main.getCpuMoveTimeLbl().getTime();
		TicTacToe.main.getCpuLastMoveLbl().setText("Last move:  " + cpuLast);
		cpuAvg = (cpuAvg * (cpuMoves - 1) + cpuLast) / cpuMoves;
		TicTacToe.main.getCpuAvgTimeLbl().setText("Avg time:  " + cpuAvg);
		playerTurn = !playerTurn;
		if (!gameover) {
			TicTacToe.main.getPlayerMoveTimeLbl().start();
			letThink(getPlayer());
		}
	}

	synchronized private void makeMove(char cursorIcon, int move) {
		int xPos = move / 100;
		int yPos = move % 100;
		char cur = cursorIcon;
		gameState[yPos][xPos] = cur;
		moves++;
		lastMove = move;
		TicTacToe.main.getGameCanvas().setLastMove(lastMove);
		TicTacToe.main.getGameCanvas().setWinMove(
				checkGameStatus(getGameState(), lastMove, true, 0));
		TicTacToe.main.getGameCanvas().repaint();

		if (gameover) {
			int rand = (int) (Math.random() * 47);
			if (draw)
				TicTacToe.main.getMessageLbl().setText(
						DrawStrings[rand % DrawStrings.length]);
			else if (Xwin)
				TicTacToe.main.getMessageLbl().setText(
						XStrings[rand % XStrings.length]);
			else
				TicTacToe.main.getMessageLbl().setText(
						OStrings[rand % OStrings.length]);
			if (draw)
				TicTacToe.draws++;
			else if ((Xwin && playerFirst) || (!Xwin && !playerFirst)) {
				TicTacToe.wins++;
			} else
				TicTacToe.losses++;
			disable();
			TicTacToe.updateLabels();
			if (TicTacToe.main.insanity)
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						TicTacToe.startGame(l, h, n);
					}
				});
		}
	}

	public int checkGameStatus(char[][] currentState, int lastMove,
			boolean modify, int imaginarycount) {
		int x = lastMove / 100;
		int y = lastMove % 100;
		char[][] state = currentState;
		String xmatch = "";
		String omatch = "";
		for (int i = 0; i < n; i++) {
			xmatch += X;
			omatch += O;
		}
		String horiz = "";
		String vert = "";
		String bslash = "";
		String fslash = "";

		for (int i = 0; i < l; i++)
			horiz += state[y][i];
		for (int i = 0; i < h; i++)
			vert += state[i][x];
		int min = 0;
		int max = 0;
		if (l < h) {
			min = l;
			max = h;
		} else {
			min = h;
			max = l;
		}
		int a = y;
		int b = x;
		while (!(a == 0 || b == 0)) {
			a--;
			b--;
		}
		try {
			for (int i = 0; i < min; i++)
				bslash += state[a + i][b + i];
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		a = y;
		b = x;
		while (!(a == (h - 1) || b == 0)) {
			a++;
			b--;
		}
		try {
			for (int i = 0; i < min; i++)
				fslash += state[a - i][b + i];
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		if (horiz.contains(xmatch)) {
			if (modify) {
				gameover = true;
				Xwin = true;
			}
			return 0;
		} else if (vert.contains(xmatch)) {
			if (modify) {
				gameover = true;
				Xwin = true;
			}
			return 1;
		} else if (bslash.contains(xmatch)) {
			if (modify) {
				gameover = true;
				Xwin = true;
			}
			return 2;
		} else if (fslash.contains(xmatch)) {
			if (modify) {
				gameover = true;
				Xwin = true;
			}
			return 3;
		} else if (horiz.contains(omatch)) {
			if (modify) {
				gameover = true;
				Xwin = false;
			}
			return 0;
		} else if (vert.contains(omatch)) {
			if (modify) {
				gameover = true;
				Xwin = false;
			}
			return 1;
		} else if (bslash.contains(omatch)) {
			if (modify) {
				gameover = true;
				Xwin = false;
			}
			return 2;
		} else if (fslash.contains(omatch)) {
			if (modify) {
				gameover = true;
				Xwin = false;
			}
			return 3;
		} else if (moves == l * h) {
			if (modify) {
				gameover = true;
				draw = true;
			}
			return 5;
		} else if (!modify && imaginarycount == l * h)
			return 5;
		return 4;
	}

	private int reflectAcrossVert(int move, int maxX) {
		int x = move / 100;
		int y = move % 100;
		x = maxX - 1 - x;
		return x * 100 + y;
	}

	private int reflectAcrossHoriz(int move, int maxY) {
		int x = move / 100;
		int y = move % 100;
		y = maxY - 1 - y;
		return x * 100 + y;
	}

	private int reflectAcrossDiag(int move, int maxX, int maxY) {
		return (reflectAcrossVert(reflectAcrossHoriz(move, maxY), maxX));
	}

	private void printState() {
		for (int y = 0; y < h; y++) {
			System.out.println();
			for (int x = 0; x < l; x++) {
				System.out.print(gameState[y][x]);
			}
		}
	}
}
