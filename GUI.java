import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GUI extends JFrame implements ActionListener{
	public GUI()
	{
		super("Minesweeper by Rick");
		setSize(600,600);
		setVisible(true);
		setResizable(false);
		createJMenuBar();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addButtons();
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
		game.add(difficulty);
		difficulty.add(easy);
		difficulty.add(medium);
		difficulty.add(hard);
		game.addSeparator();
		game.add(reset);
		game.addSeparator();
		game.add(exit);
		exit.addActionListener(this);
	}
	public void addButtons()
	{
		JPanel panel1 = new JPanel();
		ImageIcon smiley = new ImageIcon(this.getClass().getResource("Images/smileyFace.jpg"));
		JButton smileyRestart = new JButton(smiley);
		smileyRestart.setBounds(300,100,100,50);
		panel1.setBounds(50, 50, 300, 300);
		panel1.setLayout(null);
		panel1.add(smileyRestart);
		panel1.setVisible(true);
		add(panel1);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("Exit"))
		{
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	}
}
