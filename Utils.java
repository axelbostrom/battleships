package battleships;

import java.awt.Point;

public class Utils {
	private Utils() {
		
	}
	
	//Distance between two points, i.e shiplength
    public static double distanceBetweenPoints(Point from, Point to) {
        double x1 = from.getX();
        double y1 = from.getY();
        double x2 = to.getX();
        double y2 = to.getY();

        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2)) + 1;
    }
    
    public static boolean isPointEqual(Point point, Position position) {
        Point from = position.getFrom();
        Point to = position.getTo();
        System.out.println("kommer jag hit??");
        if (point.equals(from) || point.equals(to))
        	return false;
        return true;
    }
    
    //Is point between boolean
    public static boolean isPointBetween(Point point, Position position) {
        Point from = position.getFrom();
        Point to = position.getTo();

        return from.getY() <= point.getY()
                && to.getY() >= point.getY()
                && from.getX() <= point.getX()
                && to.getX() >= point.getX();
    }
}