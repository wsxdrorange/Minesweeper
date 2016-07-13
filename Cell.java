
public class Cell {
	private int bombsNear;
	private boolean isBomb;
	public Cell(boolean isBomb)
	{
		this.bombsNear = -1;
		this.isBomb = true;
	}
	public Cell(int bombsNear)
	{
		this.bombsNear = bombsNear;
		this.isBomb = false;
	}
	public boolean isBomb()
	{
		return isBomb;
	}
	public String toString()
	{
		return Integer.toString(bombsNear);
	}
}
