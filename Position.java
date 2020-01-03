package battleships;
import java.awt.Point;

public class Position {
	private Point from;
	private Point to;
	
	public Position(Point from, Point to) throws ArrayIndexOutOfBoundsException {
		if (
				from.getX() > Constants.gridSize || from.getX() < 0
				|| from.getY() > Constants.gridSize || from.getY() < 0
				|| to.getX() > Constants.gridSize || to.getX() < 0
				|| to.getY() > Constants.gridSize || to.getY() < 0) {
			 
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