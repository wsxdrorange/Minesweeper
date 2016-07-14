import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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

public class GUI extends JFrame implements ActionListener{
	private String difficulty;
	public GUI(String difficulty)
	{
		super("Minesweeper by Rick");
		this.difficulty = difficulty;
		setSize(600,600);
		setResizable(true);
		createJMenuBar();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	public void createJMenuBar()
	{
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
	public void addButons(int width, int height)
	{
		//Main Panel - holds all other panels
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		//Restart Button - Panel 1
		JPanel panel1 = new JPanel();
		//ImageIcon smiley = new ImageIcon(this.getClass().getResource("Images/smileyFace.png"));
		JButton smileyRestart = new JButton("Restart");
		panel1.add(smileyRestart);
		mainPanel.add(panel1);
		
		//Make Board - Panel 2
		JPanel panel2 = new JPanel(new GridLayout(width,height));
		JButton Butons[][] = new JButton[height][width];
		for (int i = 0; i < Butons.length; i++)
		{
			for (int j = 0; j < Butons[0].length; j++)
			{
				Butons[i][j] = new JButton();
				Butons[i][j].addActionListener(this);
				panel2.add(Butons[i][j]);
			}
		}
		mainPanel.add(panel2);
		
		//Add mainPanel to JFrame
		add(mainPanel);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("Exit"))
		{
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		else if (arg0.getActionCommand().equals("Easy"))
		{
			setDefaultCloseOperation(HIDE_ON_CLOSE);
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			Board test = new Board("Easy");
			setDefaultCloseOperation(3);
		}
		else if (arg0.getActionCommand().equals("Medium"))
		{
			setDefaultCloseOperation(HIDE_ON_CLOSE);
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			Board test = new Board("Medium");
			setDefaultCloseOperation(3);
		}
		else if (arg0.getActionCommand().equals("Hard"))
		{
			setDefaultCloseOperation(HIDE_ON_CLOSE);
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			Board test = new Board("Hard");
			setDefaultCloseOperation(3);
		}
		else if (arg0.getActionCommand().equals("Reset"))
		{
			setDefaultCloseOperation(HIDE_ON_CLOSE);
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			Board test = new Board(this.difficulty);
			setDefaultCloseOperation(3);
		}
	}
}
