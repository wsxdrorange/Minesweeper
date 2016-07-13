import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GUI extends JFrame{
	public GUI()
	{
		super("Minesweeper by Rick");
		setSize(600, 600);
		setVisible(true);
		setResizable(false);
		createJMenuBar();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addButtons();
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
		game.add(difficulty);
		difficulty.add(easy);
		difficulty.add(medium);
		difficulty.add(hard);
		game.addSeparator();
		game.add(reset);
		game.addSeparator();
		game.add(exit);
	}
	public void addButtons()
	{
		JPanel panel1 = new JPanel(new BorderLayout());
		ImageIcon smiley = new ImageIcon(this.getClass().getResource("Images/smileyFace.jpg"));
		JButton smileyRestart = new JButton(smiley);
		panel1.add(smileyRestart);
		panel1.setVisible(true);
	}
}
