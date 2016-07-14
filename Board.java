public class Board{
	private String difficulty;
	private int width;
	private int height;
	Cell [][] playingBoard;
	boolean [][] checked;
	GUI game;
	public Board()
	{
		width = 5;
		height = 5;
		difficulty = "Test";
		playingBoard = new Cell [height][width];
		checked = new boolean[height][width];
		setBoard();
		game = new GUI("Test");
		game.addButons(5, 5);
		game.createPlayingBoard(playingBoard);
		game.setVisible(true);
	}
	public Board(String difficulty)
	{
		game = new GUI(difficulty);
		setBoardDifficulty(difficulty,game);
		playingBoard = new Cell [height][width];
		checked = new boolean [height][width];
		setBoard();
		game.addButons(width,height);
		game.createPlayingBoard(playingBoard);
		game.setVisible(true);
	}
	public String getDifficulty()
	{
		return this.difficulty;
	}
	public int getBombCount()
	{
		if (this.difficulty.equals("Test"))
		{
			return 6;
		}
		if (this.difficulty.equals("Easy"))
		{
			return 10;
		}
		else if (this.difficulty.equals("Medium"))
		{
			return 40;
		}
		else if (this.difficulty.equals("Hard"))
		{
			return 99;
		}
		else
		{
			return 0;
		}
	}
	public void setBoard()
	{
		//SETS BOMBS
		boolean [][] bombs = new boolean [height][width];
		int count = 0;
		int y;
		int x;
		int maxBombs = getBombCount();
		while (count < maxBombs)
		{
			y = (int)Math.floor(Math.random() * (height));
			x = (int)Math.floor(Math.random() * (width));
			if(bombs[y][x] == false)
			{
				bombs[y][x] = true;
				playingBoard[y][x] = new Cell(true);
				count++;
			}
		}
		//Avoids null by presetting all values to 9
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				if (bombs[i][j] == false)
				{
					playingBoard[i][j] = new Cell(9);
				}
			}
		}
		//SETS NUMBERS
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				if (!bombs[i][j])
				{
					playingBoard[i][j] = new Cell(getBombsNearCell(i,j));
				}
			}
		}
	}
	public void setBoardDifficulty(String difficulty, GUI game)
	{
		this.difficulty = difficulty;
		if (this.difficulty.equals("Test"))
		{
			this.width = 5;
			this.height = 5;
		}
		if (this.difficulty.equals("Easy"))
		{
			this.width = 9;
			this.height = 9;
			//Set GUI size
		}
		else if (this.difficulty.equals("Medium"))
		{
			this.width = 16;
			this.height = 16;
			//Set GUI size
		}
		else if (this.difficulty.equals("Hard"))
		{
			this.width = 16;
			this.height = 30;
			//Set GUI size
		}
	}
	public void displayBoard()
	{
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				System.out.print(playingBoard[i][j].toString() + " ");
			}
			System.out.println();
		}
	}
	public int getBombsNearCell(int i,int j)
	{
		int bombsNear = 0;
		//Check Up
		if (i-1 >= 0)
		{
			if (playingBoard[i-1][j].isBomb())
			{
				bombsNear++;
			}
		}
		//Check Down
		if (i+1 < height)
		{
			if (playingBoard[i+1][j].isBomb())
			{
				bombsNear++;
			}
		}
		//Check Left
		if (j-1 >= 0)
		{
			if (playingBoard[i][j-1].isBomb())
			{
				bombsNear++;
			}
		}
		//Check Right
		if (j+1 < width)
		{
			if (playingBoard[i][j+1].isBomb())
			{
				bombsNear++;
			}
		}
		//Check UpRight
		if (j+1 < width && i-1 >= 0)
		{
			if (playingBoard[i-1][j+1].isBomb())
			{
				bombsNear++;
			}
		}
		//Check UpLeft
		if (j-1 >= 0 && i-1 >= 0)
		{
			if (playingBoard[i-1][j-1].isBomb())
			{
				bombsNear++;
			}
		}
		//Check DownLeft
		if (j-1 >= 0 && i+1 < height)
		{
			if (playingBoard[i+1][j-1].isBomb())
			{
				bombsNear++;
			}
		}
		//Check DownRight
		if (j+1 < width && i+1 < height)
		{
			if (playingBoard[i+1][j+1].isBomb())
			{
				bombsNear++;
			}
		}
		return bombsNear;
	}
}
