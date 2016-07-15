
public class Cell {
	private int bombsNear;
	private boolean isBomb;
	private boolean revealed;
	public Cell(boolean isBomb)
	{
		this.bombsNear = -1;
		this.isBomb = true;
		revealed = false;
	}
	public Cell(int bombsNear)
	{
		this.bombsNear = bombsNear;
		this.isBomb = false;
		revealed = false;
	}
	public boolean isBomb()
	{
		return this.isBomb;
	}
	public void setRevealed(boolean revealed)
	{
		this.revealed = revealed;
	}
	public boolean isRevealed()
	{
		return this.revealed;
	}
	public String toString()
	{
		return Integer.toString(bombsNear);
	}
}
