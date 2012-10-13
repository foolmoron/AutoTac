import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

/*
 * Momin Khan 05/09/2011
 * MainWindow - Contains the main UI components and handles starting new games and setting
 * game mode options.  Needs TicTacToe and Game classes to work.
 */
/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class MainWindow extends javax.swing.JFrame {
	private JComboBox lengthCombo;
	private JButton startGameBtn;
	private JLabel newGameWarn;
	private JLabel newGameLbl4;
	private JComboBox inarowCombo;
	private JLabel newGameLbl3;
	private JLabel newGameLbl2;
	private JComboBox widthCombo;
	private JLabel newGameLbl1;
	private JDialog newGameDialog;
	private JMenu fileMenu;
	private JMenuBar mainMenu;
	private TimerLabel cpuMoveTimeLbl;
	private JMenuItem modeInsanityAlright;
	private JMenu modeInsanitySure;
	private JMenu modeInsanity;
	private JLabel cpuAvgTimeLbl;
	private JLabel cpuLastMoveLbl;
	private JLabel cpuWinsLbl;
	private JLabel playerWinsLbl;
	private JLabel playerAvgTimeLbl;
	private JLabel playerLastMoveLbl;
	private TimerLabel playerMoveTimeLbl;
	private JLabel about5;
	private JLabel jLabel1;
	private JLabel about3;
	private JLabel about2;
	private JLabel about1;
	private JDialog aboutDialog;
	private JMenuItem helpAbout;
	private JMenu helpMenu;
	private JMenuItem fileExit;
	private JMenu modeMenu;
	private JMenuItem fileModeTwoCPU;
	private JMenuItem fileModeTwoPlayer;
	private JMenuItem fileModeOriginal;
	private JMenuItem fileNewGame;
	private JSeparator fileSep1;
	private ColorLabel messageLbl;
	private JButton newGameBtn;
	private FlashingLabel warningLbl;
	private JLabel gameInfoLbl;
	private JPanel cpuStatsPanel;
	private JPanel playerStatsPanel;
	private GameScreen gameCanvas;
	private JPanel gamePanel;
	private JScrollPane gameScrollPane;
	public boolean insanity = false;
	public boolean gameEnabled = false;
	public static Image xImg;
	public static Image oImg;
	public Cursor xCursor;
	public Cursor oCursor;

	public MainWindow() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			Toolkit tk = Toolkit.getDefaultToolkit();
			Graphics x, o;
			xImg = tk.getImage("xcursor.png");
			oImg = tk.getImage("ocursor.png");
			Point zero = new Point(15, 15);
			xCursor = tk.createCustomCursor(xImg, zero, "X Cursor");
			oCursor = tk.createCustomCursor(oImg, zero, "O Cursor");
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			GroupLayout thisLayout = new GroupLayout(
					(JComponent) getContentPane());
			getContentPane().setLayout(thisLayout);
			thisLayout
					.setVerticalGroup(thisLayout
							.createSequentialGroup()
							.addComponent(getMessageLbl(),
									GroupLayout.PREFERRED_SIZE, 28,
									GroupLayout.PREFERRED_SIZE)
							.addComponent(getGameScrollPane(), 0, 172,
									Short.MAX_VALUE)
							.addGroup(
									thisLayout
											.createParallelGroup()
											.addComponent(
													getCpuStatsPanelx(),
													GroupLayout.Alignment.LEADING,
													GroupLayout.PREFERRED_SIZE,
													92,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(
													getPlayerStatsPanelx(),
													GroupLayout.Alignment.LEADING,
													GroupLayout.PREFERRED_SIZE,
													92,
													GroupLayout.PREFERRED_SIZE)
											.addGroup(
													GroupLayout.Alignment.LEADING,
													thisLayout
															.createSequentialGroup()
															.addGap(12)
															.addComponent(
																	getWarningLbl(),
																	GroupLayout.PREFERRED_SIZE,
																	20,
																	GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(
																	LayoutStyle.ComponentPlacement.RELATED)
															.addComponent(
																	getGameInfoLbl(),
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(
																	LayoutStyle.ComponentPlacement.RELATED)
															.addComponent(
																	getNewGameBtn(),
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.PREFERRED_SIZE)
															.addGap(11))));
			thisLayout
					.setHorizontalGroup(thisLayout
							.createParallelGroup()
							.addGroup(
									GroupLayout.Alignment.LEADING,
									thisLayout
											.createParallelGroup()
											.addGroup(
													GroupLayout.Alignment.LEADING,
													thisLayout
															.createSequentialGroup()
															.addGroup(
																	thisLayout
																			.createParallelGroup()
																			.addComponent(
																					getWarningLbl(),
																					GroupLayout.Alignment.LEADING,
																					GroupLayout.PREFERRED_SIZE,
																					116,
																					GroupLayout.PREFERRED_SIZE)
																			.addComponent(
																					getGameInfoLbl(),
																					GroupLayout.Alignment.LEADING,
																					GroupLayout.PREFERRED_SIZE,
																					116,
																					GroupLayout.PREFERRED_SIZE))
															.addPreferredGap(
																	LayoutStyle.ComponentPlacement.RELATED)
															.addComponent(
																	getCpuStatsPanelx(),
																	0,
																	122,
																	Short.MAX_VALUE)
															.addPreferredGap(
																	LayoutStyle.ComponentPlacement.RELATED)
															.addComponent(
																	getPlayerStatsPanelx(),
																	0,
																	128,
																	Short.MAX_VALUE))
											.addComponent(
													getGameScrollPane(),
													GroupLayout.Alignment.LEADING,
													0, 384, Short.MAX_VALUE)
											.addComponent(
													getMessageLbl(),
													GroupLayout.Alignment.LEADING,
													0, 384, Short.MAX_VALUE))
							.addGroup(
									GroupLayout.Alignment.LEADING,
									thisLayout
											.createSequentialGroup()
											.addGap(7)
											.addComponent(getNewGameBtn(),
													GroupLayout.PREFERRED_SIZE,
													96,
													GroupLayout.PREFERRED_SIZE)
											.addContainerGap(281,
													Short.MAX_VALUE)));
			this.setPreferredSize(new java.awt.Dimension(400, 350));
			this.setMinimumSize(new java.awt.Dimension(400, 350));
			this.setTitle("AutoTac");
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent evt) {
					thisWindowClosing(evt);
				}
			});
			{
				mainMenu = new JMenuBar();
				setJMenuBar(mainMenu);
				mainMenu.setSize(378, 20);
				{
					fileMenu = new JMenu();
					mainMenu.add(fileMenu);
					mainMenu.add(getModeMenu());
					mainMenu.add(getHelpMenu());
					fileMenu.setText("File");
					fileMenu.add(getFileNewGame());
					fileMenu.add(getFileSep1());
					fileMenu.add(getFileExit());
				}
			}
			pack();
			this.setSize(400, 350);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	public JPanel getPlayerStatsPanel() {
		return playerStatsPanel;
	}

	public JPanel getCpuStatsPanel() {
		return cpuStatsPanel;
	}

	private JDialog getNewGameDialog() {
		if (newGameDialog == null) {
			newGameDialog = new JDialog(this);
			newGameDialog.setTitle("Start a new game");
			newGameDialog.setResizable(false);
			newGameDialog.getContentPane().setLayout(null);
			newGameDialog.setPreferredSize(new java.awt.Dimension(233, 161));
			newGameDialog
					.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			newGameDialog.getContentPane().add(getLengthCombo(), "Center");
			newGameDialog.getContentPane().add(getNewGameLbl1());
			newGameDialog.getContentPane().add(getJComboBox1(), "Center");
			newGameDialog.getContentPane().add(getNewGameLbl2());
			newGameDialog.getContentPane().add(getJLabel1());
			newGameDialog.getContentPane().add(getJComboBox1x(), "Center");
			newGameDialog.getContentPane().add(getNewGameLbl4());
			newGameDialog.getContentPane().add(getNewGameWarn());
			newGameDialog.getContentPane().add(getStartGameBtn());
			newGameDialog.setSize(233, 161);
		}
		return newGameDialog;
	}

	private JComboBox getLengthCombo() {
		if (lengthCombo == null) {
			ComboBoxModel lengthComboModel = new DefaultComboBoxModel(
					new String[] { "3", "4", "5", "6", "7", "8", "9", "10" });
			lengthCombo = new JComboBox();
			lengthCombo.setModel(lengthComboModel);
			lengthCombo.setBounds(30, 30, 43, 20);
		}
		return lengthCombo;
	}

	private JLabel getNewGameLbl1() {
		if (newGameLbl1 == null) {
			newGameLbl1 = new JLabel();
			newGameLbl1.setText("Grid Size");
			newGameLbl1.setBounds(79, 6, 53, 20);
		}
		return newGameLbl1;
	}

	private JComboBox getJComboBox1() {
		if (widthCombo == null) {
			ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(
					new String[] { "3", "4", "5", "6", "7", "8", "9", "10" });
			widthCombo = new JComboBox();
			widthCombo.setModel(jComboBox1Model);
			widthCombo.setBounds(98, 30, 43, 20);
		}
		return widthCombo;
	}

	private JLabel getNewGameLbl2() {
		if (newGameLbl2 == null) {
			newGameLbl2 = new JLabel();
			newGameLbl2.setText("by");
			newGameLbl2.setBounds(79, 32, 27, 16);
		}
		return newGameLbl2;
	}

	private JLabel getJLabel1() {
		if (newGameLbl3 == null) {
			newGameLbl3 = new JLabel();
			newGameLbl3.setText("units");
			newGameLbl3.setBounds(144, 32, 50, 16);
		}
		return newGameLbl3;
	}

	private JComboBox getJComboBox1x() {
		if (inarowCombo == null) {
			ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(
					new String[] { "3", "4", "5", "6", "7", "8", "9", "10" });
			inarowCombo = new JComboBox();
			inarowCombo.setModel(jComboBox1Model);
			inarowCombo.setBounds(160, 54, 43, 20);
		}
		return inarowCombo;
	}

	private JLabel getNewGameLbl4() {
		if (newGameLbl4 == null) {
			newGameLbl4 = new JLabel();
			newGameLbl4.setText("Symbols in-a-row to win");
			newGameLbl4.setBounds(18, 50, 142, 28);
		}
		return newGameLbl4;
	}

	private JLabel getNewGameWarn() {
		if (newGameWarn == null) {
			newGameWarn = new FlashingLabel();
			newGameWarn.setText("");
			newGameWarn.setBounds(1, 78, 232, 16);
			newGameWarn.setFont(new java.awt.Font("Segoe UI", 1, 12));
			newGameWarn.setForeground(new java.awt.Color(255, 0, 0));
		}
		return newGameWarn;
	}

	private JButton getStartGameBtn() {
		if (startGameBtn == null) {
			startGameBtn = new JButton();
			startGameBtn.setText("Start");
			startGameBtn.setBounds(82, 100, 38, 23);
			startGameBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
			startGameBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					startGameBtnActionPerformed(evt);
				}
			});
		}
		return startGameBtn;
	}

	private void gameCanvasMouseClicked(MouseEvent evt) {
		System.out.println("gameCanvas.mouseClicked=" + evt.getX() + ", "
				+ evt.getY() + "\n");
		if (!gameEnabled || TicTacToe.game == null || TicTacToe.twoCPUs)
			return;
		if (TicTacToe.game.playerTurn)
			TicTacToe.game.playerMove((evt.getX() / 50 * 100)
					+ (evt.getY() / 50));
		else if (TicTacToe.twoPlayer) {
			TicTacToe.game.cpuMove((evt.getX() / 50 * 100) + (evt.getY() / 50));
		} else {
			getWarningLbl().setText("It's not your turn!");
		}
		if (TicTacToe.twoPlayer) {
			if (getGameCanvas().getCursor().getName().equals("X Cursor"))
				getGameCanvas().setCursor(oCursor);
			else if (getGameCanvas().getCursor().getName().equals("O Cursor"))
				getGameCanvas().setCursor(xCursor);
		}
	}

	private void startGameBtnActionPerformed(ActionEvent evt) {
		System.out.println("startGameBtn.actionPerformed, event=" + evt);
		int l, w, n;
		l = Integer.parseInt((String) lengthCombo.getSelectedItem());
		w = Integer.parseInt((String) widthCombo.getSelectedItem());
		n = Integer.parseInt((String) inarowCombo.getSelectedItem());
		if (n > l && n > w) {
			newGameWarn.setText("Too many symbols for grid of that size!");
			return;
		}
		// if ((l * w > 70) && !TicTacToe.twoPlayer) {
		// newGameWarn.setText("Grid area for CPU game must be <= 70!");
		// return;
		// }
		TicTacToe.startGame(l, w, n);
		newGameDialog.dispose();
	}

	private void newGameBtnActionPerformed(ActionEvent evt) {
		System.out.println("newGameBtn.actionPerformed, event=" + evt);
		if (gameEnabled){
			getWarningLbl().setText("Game in progress!");
			return;
		}
		getNewGameDialog().setLocationRelativeTo(this);
		newGameWarn.setText("");
		getNewGameDialog().setVisible(true);
	}

	private void fileSwapCursorActionPerformed(ActionEvent evt) {
		System.out.println("fileSwapCursor.actionPerformed, event=" + evt);
		if (getGameCanvas().getCursor().getName().equals("X Cursor"))
			getGameCanvas().setCursor(oCursor);
		else if (getGameCanvas().getCursor().getName().equals("O Cursor"))
			getGameCanvas().setCursor(xCursor);
	}

	private JScrollPane getGameScrollPane() {
		if (gameScrollPane == null) {
			gameScrollPane = new JScrollPane();
			getGameScrollPane().getHorizontalScrollBar().setUnitIncrement(30);
			getGameScrollPane().getVerticalScrollBar().setUnitIncrement(30);
			gameScrollPane.setViewportView(getGamePanel());
		}
		return gameScrollPane;
	}

	private JPanel getGamePanel() {
		if (gamePanel == null) {
			gamePanel = new JPanel();
			FlowLayout gamePanelLayout = new FlowLayout();
			gamePanel.setLayout(gamePanelLayout);
			gamePanel.setBackground(new java.awt.Color(255, 255, 255));
			gamePanel.add(getGameCanvas());
		}
		return gamePanel;
	}

	public GameScreen getGameCanvas() {
		if (gameCanvas == null) {
			gameCanvas = new GameScreen();
			gameCanvas.setBackground(new java.awt.Color(255, 255, 255));
			gameCanvas.setPreferredSize(new java.awt.Dimension(150, 150));
			gameCanvas.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					gameCanvasMouseClicked(evt);
				}
			});
		}
		return gameCanvas;
	}

	private JPanel getPlayerStatsPanelx() {
		if (playerStatsPanel == null) {
			playerStatsPanel = new JPanel();
			GroupLayout playerStatsPanelLayout = new GroupLayout(
					(JComponent) playerStatsPanel);
			playerStatsPanel.setBorder(new LineBorder(new java.awt.Color(0, 0,
					0), 1, false));
			playerStatsPanel.setLayout(playerStatsPanelLayout);
			playerStatsPanelLayout
					.setHorizontalGroup(playerStatsPanelLayout
							.createSequentialGroup()
							.addGroup(
									playerStatsPanelLayout
											.createParallelGroup()
											.addComponent(
													getCpuMoveTimeLbl(),
													GroupLayout.Alignment.LEADING,
													0, 120, Short.MAX_VALUE)
											.addGroup(
													GroupLayout.Alignment.LEADING,
													playerStatsPanelLayout
															.createParallelGroup()
															.addComponent(
																	getCpuAvgTimeLbl(),
																	GroupLayout.Alignment.LEADING,
																	0,
																	120,
																	Short.MAX_VALUE)
															.addComponent(
																	getCpuLastMoveLbl(),
																	GroupLayout.Alignment.LEADING,
																	0,
																	120,
																	Short.MAX_VALUE)
															.addComponent(
																	getCPUWinsLbl(),
																	GroupLayout.Alignment.LEADING,
																	0,
																	120,
																	Short.MAX_VALUE)))
							.addGap(6));
			playerStatsPanelLayout
					.setVerticalGroup(playerStatsPanelLayout
							.createSequentialGroup()
							.addComponent(getCpuMoveTimeLbl(),
									GroupLayout.PREFERRED_SIZE, 20,
									GroupLayout.PREFERRED_SIZE)
							.addComponent(getCpuLastMoveLbl(),
									GroupLayout.PREFERRED_SIZE, 16,
									GroupLayout.PREFERRED_SIZE)
							.addComponent(getCpuAvgTimeLbl(),
									GroupLayout.PREFERRED_SIZE, 16,
									GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(getCPUWinsLbl(),
									GroupLayout.PREFERRED_SIZE, 16,
									GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE,
									Short.MAX_VALUE));
		}
		return playerStatsPanel;
	}

	private JPanel getCpuStatsPanelx() {
		if (cpuStatsPanel == null) {
			cpuStatsPanel = new JPanel();
			GroupLayout cpuStatsPanelLayout = new GroupLayout(
					(JComponent) cpuStatsPanel);
			cpuStatsPanel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0),
					1, false));
			cpuStatsPanel.setLayout(cpuStatsPanelLayout);
			cpuStatsPanelLayout.setHorizontalGroup(cpuStatsPanelLayout
					.createParallelGroup()
					.addComponent(getPlayerMoveTimeLbl(),
							GroupLayout.Alignment.LEADING, 0, 120,
							Short.MAX_VALUE)
					.addComponent(getPlayerLastMoveLbl(),
							GroupLayout.Alignment.LEADING, 0, 120,
							Short.MAX_VALUE)
					.addComponent(getPlayerAvgTimeLbl(),
							GroupLayout.Alignment.LEADING, 0, 120,
							Short.MAX_VALUE)
					.addComponent(getPlayerWinsLbl(),
							GroupLayout.Alignment.LEADING, 0, 120,
							Short.MAX_VALUE));
			cpuStatsPanelLayout
					.setVerticalGroup(cpuStatsPanelLayout
							.createSequentialGroup()
							.addComponent(getPlayerMoveTimeLbl(),
									GroupLayout.PREFERRED_SIZE, 20,
									GroupLayout.PREFERRED_SIZE)
							.addComponent(getPlayerLastMoveLbl(),
									GroupLayout.PREFERRED_SIZE,
									GroupLayout.PREFERRED_SIZE,
									GroupLayout.PREFERRED_SIZE)
							.addComponent(getPlayerAvgTimeLbl(),
									GroupLayout.PREFERRED_SIZE,
									GroupLayout.PREFERRED_SIZE,
									GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(
									LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(getPlayerWinsLbl(),
									GroupLayout.PREFERRED_SIZE, 16,
									GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE,
									Short.MAX_VALUE));
		}
		return cpuStatsPanel;
	}

	public JLabel getGameInfoLbl() {
		if (gameInfoLbl == null) {
			gameInfoLbl = new JLabel();
			gameInfoLbl.setText("L by W to N in-a-row");
			gameInfoLbl.setFont(new java.awt.Font("Segoe UI", 1, 12));
			gameInfoLbl.setBounds(2, 32, 117, 15);
		}
		return gameInfoLbl;
	}

	public FlashingLabel getWarningLbl() {
		if (warningLbl == null) {
			warningLbl = new FlashingLabel();
			warningLbl.setFont(new java.awt.Font("Segoe UI", 1, 12));
			warningLbl.setForeground(new java.awt.Color(255, 0, 0));
			warningLbl.setBounds(0, 0, 120, 100);
		}
		return warningLbl;
	}

	private JButton getNewGameBtn() {
		if (newGameBtn == null) {
			newGameBtn = new JButton();
			newGameBtn.setText("New Game...");
			newGameBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
			newGameBtn.setBounds(0, 0, 100, 100);
			newGameBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					newGameBtnActionPerformed(evt);
				}
			});
		}
		return newGameBtn;
	}

	private void fileTestActionPerformed(ActionEvent evt) {
		System.out.println("fileTest.actionPerformed, event=" + evt);

	}

	public ColorLabel getMessageLbl() {
		if (messageLbl == null) {
			messageLbl = new ColorLabel();
			messageLbl.setHorizontalAlignment(SwingConstants.CENTER);
			messageLbl.setHorizontalTextPosition(SwingConstants.CENTER);
			messageLbl.setFont(new java.awt.Font("Segoe UI", 3, 18));
		}
		return messageLbl;
	}

	private void thisWindowClosing(WindowEvent evt) {
		System.out.println("this.windowClosing, event=" + evt);
		System.exit(0);
	}

	private void fileModeOriginalActionPerformed(ActionEvent evt) {
		System.out.println("fileModeOriginal.actionPerformed, event=" + evt);
		insanity = false;
		TicTacToe.twoPlayer = false;
		TicTacToe.twoCPUs = false;
		newGameBtnActionPerformed(evt);
	}

	private void fileModeTwoPlayerActionPerformed(ActionEvent evt) {
		System.out.println("fileModeTwoPlayer.actionPerformed, event=" + evt);
		insanity = false;
		TicTacToe.twoPlayer = true;
		TicTacToe.twoCPUs = false;
		newGameBtnActionPerformed(evt);
	}

	private void fileModeTwoCPUActionPerformed(ActionEvent evt) {
		System.out.println("fileModeTwoCPU.actionPerformed, event=" + evt);
		insanity = false;
		TicTacToe.twoPlayer = false;
		TicTacToe.twoCPUs = true;
		newGameBtnActionPerformed(evt);
	}

	private JSeparator getFileSep1() {
		if (fileSep1 == null) {
			fileSep1 = new JSeparator();
			fileSep1.setBounds(104, 119, 87, 8);
		}
		return fileSep1;
	}

	private JMenuItem getFileNewGame() {
		if (fileNewGame == null) {
			fileNewGame = new JMenuItem();
			fileNewGame.setText("New game...");
			fileNewGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					System.out.println("fileNewGame.actionPerformed, event="
							+ evt);
					newGameBtnActionPerformed(evt);
				}
			});
		}
		return fileNewGame;
	}

	private JMenuItem getFileModeOriginal() {
		if (fileModeOriginal == null) {
			fileModeOriginal = new JMenuItem();
			fileModeOriginal.setText("Player vs. CPU");
			fileModeOriginal.setBounds(55, 56, 87, 21);
			fileModeOriginal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					fileModeOriginalActionPerformed(evt);
				}
			});
		}
		return fileModeOriginal;
	}

	private JMenuItem getFileModeTwoPlayer() {
		if (fileModeTwoPlayer == null) {
			fileModeTwoPlayer = new JMenuItem();
			fileModeTwoPlayer.setText("Two Players");
			fileModeTwoPlayer.setBounds(55, 77, 87, 21);
			fileModeTwoPlayer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					fileModeTwoPlayerActionPerformed(evt);
				}
			});
		}
		return fileModeTwoPlayer;
	}

	private JMenuItem getFileModeTwoCPU() {
		if (fileModeTwoCPU == null) {
			fileModeTwoCPU = new JMenuItem();
			fileModeTwoCPU.setText("Two CPUs");
			fileModeTwoCPU.setBounds(55, 98, 87, 21);
			fileModeTwoCPU.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					fileModeTwoCPUActionPerformed(evt);
				}
			});
		}
		return fileModeTwoCPU;
	}

	private JMenu getModeMenu() {
		if (modeMenu == null) {
			modeMenu = new JMenu();
			modeMenu.setText("Mode");
			modeMenu.add(getFileModeOriginal());
			modeMenu.add(getFileModeTwoPlayer());
			modeMenu.add(getFileModeTwoCPU());
			modeMenu.add(getModeInsanity());
		}
		return modeMenu;
	}

	private JMenuItem getFileExit() {
		if (fileExit == null) {
			fileExit = new JMenuItem();
			fileExit.setText("Exit");
			fileExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					fileExitActionPerformed(evt);
				}
			});
		}
		return fileExit;
	}

	private void fileExitActionPerformed(ActionEvent evt) {
		System.out.println("fileExit.actionPerformed, event=" + evt);
		this.dispose();
		System.exit(0);
	}

	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getHelpAbout());
		}
		return helpMenu;
	}

	private JMenuItem getHelpAbout() {
		if (helpAbout == null) {
			helpAbout = new JMenuItem();
			helpAbout.setText("About...");
			helpAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					helpAboutActionPerformed(evt);
				}
			});
		}
		return helpAbout;
	}

	private void helpAboutActionPerformed(ActionEvent evt) {
		System.out.println("helpAbout.actionPerformed, event=" + evt);
		getAboutDialog().setLocationRelativeTo(this);
		getAboutDialog().setVisible(true);
	}

	private JDialog getAboutDialog() {
		if (aboutDialog == null) {
			aboutDialog = new JDialog(this);
			aboutDialog.getContentPane().setLayout(null);
			aboutDialog.setResizable(false);
			aboutDialog.setTitle("About AutoTac");
			aboutDialog.getContentPane().setBackground(
					new java.awt.Color(255, 255, 255));
			aboutDialog.getContentPane().add(getAbout1());
			aboutDialog.getContentPane().add(getAbout2());
			aboutDialog.getContentPane().add(getAbout3());
			aboutDialog.getContentPane().add(getJLabel1x());
			aboutDialog.getContentPane().add(getAbout5());
			aboutDialog.setSize(264, 254);
		}
		return aboutDialog;
	}

	private JLabel getAbout1() {
		if (about1 == null) {
			about1 = new JLabel();
			about1.setText("AutoTac Player v0.1");
			about1.setBounds(12, 6, 234, 36);
			about1.setHorizontalAlignment(SwingConstants.CENTER);
			about1.setFont(new java.awt.Font("Segoe UI", 3, 20));
		}
		return about1;
	}

	private JLabel getAbout2() {
		if (about2 == null) {
			about2 = new JLabel();
			about2.setText("by Momin Khan");
			about2.setBounds(12, 40, 234, 16);
			about2.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return about2;
	}

	private JLabel getAbout3() {
		if (about3 == null) {
			about3 = new JLabel();
			about3.setText("<HTML>Tools used:<BR>Java SE 6<BR>Eclipse Helios IDE<BR>Jigloo GUI editor</HTML>");
			about3.setBounds(12, 144, 105, 70);
		}
		return about3;
	}

	private JLabel getJLabel1x() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("<HTML>Credits:<BR>Some evaluation heuristics<BR>ideas - Internet<BR>Everything else - Momin<BR>Khan mbk6wm</HTML>");
			jLabel1.setBounds(115, 144, 147, 76);
			jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 10));
		}
		return jLabel1;
	}

	private JLabel getAbout5() {
		if (about5 == null) {
			about5 = new JLabel();
			about5.setText("<HTML>Select new game and insert your parameters to start playing.<BR>Launch program with a single int param to force max depth search.");
			about5.setBounds(24, 68, 210, 70);
			about5.setVerticalAlignment(SwingConstants.TOP);
		}
		return about5;
	}

	public TimerLabel getPlayerMoveTimeLbl() {
		if (playerMoveTimeLbl == null) {
			playerMoveTimeLbl = new TimerLabel();
			playerMoveTimeLbl.setText("This move: ");
			playerMoveTimeLbl.setBounds(1, 1, 120, 20);
		}
		return playerMoveTimeLbl;
	}

	public JLabel getPlayerLastMoveLbl() {
		if (playerLastMoveLbl == null) {
			playerLastMoveLbl = new JLabel();
			playerLastMoveLbl.setText("Last move: ");
			playerLastMoveLbl.setBounds(1, 21, 88, 16);
		}
		return playerLastMoveLbl;
	}

	public JLabel getPlayerAvgTimeLbl() {
		if (playerAvgTimeLbl == null) {
			playerAvgTimeLbl = new JLabel();
			playerAvgTimeLbl.setText("Avg time:  ");
			playerAvgTimeLbl.setBounds(1, 37, 120, 16);
		}
		return playerAvgTimeLbl;
	}

	public JLabel getPlayerWinsLbl() {
		if (playerWinsLbl == null) {
			playerWinsLbl = new JLabel();
			playerWinsLbl.setText("W \\ L \\ D: ");
			playerWinsLbl.setFont(new java.awt.Font("Segoe UI", 1, 12));
		}
		return playerWinsLbl;
	}

	public TimerLabel getCpuMoveTimeLbl() {
		if (cpuMoveTimeLbl == null) {
			cpuMoveTimeLbl = new TimerLabel();
			cpuMoveTimeLbl.setText("This move: ");
			cpuMoveTimeLbl.setBounds(1, 1, 120, 20);
		}
		return cpuMoveTimeLbl;
	}

	public JLabel getCPUWinsLbl() {
		if (cpuWinsLbl == null) {
			cpuWinsLbl = new JLabel();
			cpuWinsLbl.setText("W \\ L \\ D: ");
			cpuWinsLbl.setFont(new java.awt.Font("Segoe UI", 1, 12));
		}
		return cpuWinsLbl;
	}

	public JLabel getCpuLastMoveLbl() {
		if (cpuLastMoveLbl == null) {
			cpuLastMoveLbl = new JLabel();
			cpuLastMoveLbl.setText("Last move: ");
			cpuLastMoveLbl.setBounds(1, 21, 88, 16);
		}
		return cpuLastMoveLbl;
	}

	public JLabel getCpuAvgTimeLbl() {
		if (cpuAvgTimeLbl == null) {
			cpuAvgTimeLbl = new JLabel();
			cpuAvgTimeLbl.setText("Avg time:  ");
			cpuAvgTimeLbl.setBounds(1, 37, 120, 16);
		}
		return cpuAvgTimeLbl;
	}

	private JMenu getModeInsanity() {
		if (modeInsanity == null) {
			modeInsanity = new JMenu();
			modeInsanity.setText("Insanity");
			modeInsanity.add(getModeInsanitySure());
		}
		return modeInsanity;
	}

	private JMenu getModeInsanitySure() {
		if (modeInsanitySure == null) {
			modeInsanitySure = new JMenu();
			modeInsanitySure.setText("Are you sure?");
			modeInsanitySure.add(getModeInsanityAlright());
		}
		return modeInsanitySure;
	}

	private JMenuItem getModeInsanityAlright() {
		if (modeInsanityAlright == null) {
			modeInsanityAlright = new JMenuItem();
			modeInsanityAlright.setText("Alright...");
			modeInsanityAlright.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					modeInsanityAlrightActionPerformed(evt);
				}
			});
		}
		return modeInsanityAlright;
	}

	private void modeInsanityAlrightActionPerformed(ActionEvent evt) {
		System.out.println("modeInsanityAlright.actionPerformed, event=" + evt);
		TicTacToe.twoPlayer = false;
		TicTacToe.twoCPUs = true;
		insanity = true;
		newGameBtnActionPerformed(evt);
	}
}
