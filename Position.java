package battleships;

public class Position {
	private int x;
	private int y;
	private int numCols = 10;
	private int numRows = 10;
	
	public Position(int x, int y) throws ArrayIndexOutOfBoundsException {
		if (
				x > numCols || x < 0
				|| y > numRows || y < 0
				|| x > numCols || x < 0
				|| y > numRows || y < 0) {
			 
		}
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}