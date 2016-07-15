import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GUI extends JFrame implements ActionListener {
	private String difficulty;
	private int BOARD_HEIGHT;
	private int BOARD_WIDTH;
	private int bombs;
	private int cellsChecked;
    private boolean checked [][];
	Cell playingBoard[][];
	JButton Butons[][];

	public GUI(String difficulty) {
		super("Minesweeper by Rick");
		this.difficulty = difficulty;
		setSize(600, 600);
		setResizable(true);
		createJMenuBar();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void createPlayingBoard(Cell[][] playingBoard) {
		this.playingBoard = playingBoard;
	}

	public void createJMenuBar() {
		//Display JMenu
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);

		//Game Menu
		JMenu game = new JMenu("Game");

		//Difficulty Submenu
		JMenu difficulty = new JMenu("Difficulty");
		JMenuItem easy = new JMenuItem("Easy");
		JMenuItem medium = new JMenuItem("Medium");
		JMenuItem hard = new JMenuItem("Hard");

		//Other Menu Items
		JMenuItem reset = new JMenuItem("Reset");
		JMenuItem exit = new JMenuItem("Exit");

		//Adding bars
		bar.add(game);

		//Difficulties
		game.add(difficulty);
		difficulty.add(easy);
		difficulty.add(medium);
		difficulty.add(hard);

		game.addSeparator();
		game.add(reset);
		game.addSeparator();
		game.add(exit);

		//ActionListeners
		exit.addActionListener(this);
		easy.addActionListener(this);
		medium.addActionListener(this);
		hard.addActionListener(this);
		reset.addActionListener(this);
	}

	public void addButons(int width, int height) {
		this.BOARD_WIDTH = width;
		this.BOARD_HEIGHT = height;

		//Main Panel - holds all other panels
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		//Restart Button - Panel 1
		JPanel panel1 = new JPanel();

		ImageIcon smiley = new ImageIcon(this.getClass().getResource("Images/smileyFaceSmaller.png"));
		JButton smileyRestart = new JButton(smiley);
		smileyRestart.addActionListener(this);
		smileyRestart.setActionCommand("SmileyRestart");
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.add(smileyRestart);
		mainPanel.add(panel1);

		//Make Board - Panel 2
		JPanel panel2 = new JPanel(new GridLayout(width, height));
		Butons = new JButton[height][width];
		for (int i = 0; i < Butons.length; i++) {
			for (int j = 0; j < Butons[0].length; j++) {
				Butons[i][j] = new JButton();
				Butons[i][j].addActionListener(this);
				panel2.add(Butons[i][j]);
			}
		}
		mainPanel.add(panel2);

		//Add mainPanel to JFrame
		add(mainPanel);
	}

	public JButton[][] getGUIBoard() {
		return this.Butons;
	}
	public void createBombCount(int bombs) {
		this.bombs = bombs;
	}
    public void createCheckedBoard(boolean [][] checked)
    {
        this.checked = checked;
    }
	public void checkWin() {
        if (this.cellsChecked + this.bombs == (BOARD_HEIGHT * BOARD_WIDTH)) {
            JFrame winningFrame = new JFrame();
            winningFrame.setSize(300,200);
            winningFrame.setResizable(false);


            //Panel to add to frame
            JPanel winningPanel = new JPanel();
            winningPanel.setLayout(new BorderLayout());

            //Win text
            JButton win = new JButton("You Win!");
            winningPanel.add(win, BorderLayout.CENTER);

            //add panel to winFrame and set Visible
            winningFrame.add(winningPanel);
            winningFrame.setVisible(true);
        }
    }
	public void revealAllBombs()
	{
		for (int i = 0; i < BOARD_HEIGHT; i++)
		{
			for (int j = 0; j < BOARD_WIDTH; j++)
			{
				if (playingBoard[i][j].toString().equals("-1"))
				{
					Butons[i][j].setText(playingBoard[i][j].toString());
				}
			}
		}
	}
	public void removeAllButton()
	{
		for (int i = 0; i < BOARD_HEIGHT; i++)
		{
			for (int j = 0; j < BOARD_WIDTH; j++)
			{
				Butons[i][j].removeActionListener(this);
			}
		}
	}
	public void recursiveCellOpener(int i, int j)
	{
		if (playingBoard[i][j].toString().equals("0"))
		{
			revealCell(i-1,j-1);
			revealCell(i-1,j);
			revealCell(i-1,j+1);
			revealCell(i+1,j);
			
			revealCell(i,j-1);
			revealCell(i,j+1);
			revealCell(i+1,j-1);
			revealCell(i+1,j+1);
		}
	}
	public void revealCell(int i, int j)
	{
		if (i < 0 || i >= playingBoard.length || j < 0 || j >= playingBoard[0].length)
		{
			return;
		}
		if (playingBoard[i][j].isRevealed())
		{
			return;
		}
		if (!(playingBoard[i][j].isBomb()))
		{
			playingBoard[i][j].setRevealed(true);
			Butons[i][j].setText(playingBoard[i][j].toString());
			recursiveCellOpener(i, j);
		}
		else
		{
			playingBoard[i][j].setRevealed(true);
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		for (int i = 0; i < BOARD_HEIGHT; i++) {
			for (int j = 0; j < BOARD_WIDTH; j++) {
				if (arg0.getSource().equals(Butons[i][j])) {
					Butons[i][j].removeActionListener(this);
					if (playingBoard[i][j].toString().equals("-1"))
					{
						Butons[i][j].setText(playingBoard[i][j].toString());
						revealAllBombs();
						removeAllButton();
					}
					else
					{
						Butons[i][j].setText(playingBoard[i][j].toString());
						recursiveCellOpener(i,j);
						break;
					}
                    if (checked[i][j] == false) {
                        this.cellsChecked++;
                        checkWin();
                        break;
                    }
				}
			}
		}
		if (arg0.getActionCommand().equals("Exit")) {
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		} else if (arg0.getActionCommand().equals("Easy")) {
			setDefaultCloseOperation(HIDE_ON_CLOSE);
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			Board test = new Board("Easy");
			setDefaultCloseOperation(3);
		} else if (arg0.getActionCommand().equals("Medium")) {
			setDefaultCloseOperation(HIDE_ON_CLOSE);
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			Board test = new Board("Medium");
			setDefaultCloseOperation(3);
		} else if (arg0.getActionCommand().equals("Hard")) {
			setDefaultCloseOperation(HIDE_ON_CLOSE);
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			Board test = new Board("Hard");
			setDefaultCloseOperation(3);
		} else if (arg0.getActionCommand().equals("Reset") || arg0.getActionCommand().equals("SmileyRestart")) {
			setDefaultCloseOperation(HIDE_ON_CLOSE);
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			Board test = new Board(this.difficulty);
			setDefaultCloseOperation(3);
		}
	}
}
