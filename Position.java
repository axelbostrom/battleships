package battleships;
import java.awt.Point;

public class Position {
	private Point from;
	private Point to;
	private int numCols = 10;
	private int numRows = 10;
	
	public Position(Point from, Point to) throws ArrayIndexOutOfBoundsException {
		if (
				from.getX() > numCols || from.getX() < 0
				|| from.getY() > numRows || from.getY() < 0
				|| to.getX() > numCols || to.getX() < 0
				|| to.getY() > numRows || to.getY() < 0) {
			 
		}
		this.from = from;
		this.to = to;
	}
	
	public Point getFrom() {
		return from;
	}
	
	public Point getTo() {
		return to;
	}
	
}