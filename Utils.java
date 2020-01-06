package battleships;

import java.awt.Point;

public class Utils {
	private Utils() {

	}

	// CHECK DISTANCE BETWEEN TWO POINTS
	public static double distanceBetweenPoints(Point from, Point to) {
		double x1 = from.getX();
		double y1 = from.getY();
		double x2 = to.getX();
		double y2 = to.getY();

		if (x2 - x1 == 0) {
			if (y2 == 0 || y1 == 0)
				return Point.distance(x1, y1, x2, y2) + 1;
		}

		if (y2 - y1 == 0) {
			if (x2 == 0 || x1 == 0)
				return Point.distance(x1, y1, x2, y2) + 1;
		}

		return Point.distance(x1, y1, x2, y2);
		// return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) + 1;
	}

	// CHECK IS POINT IS EQUAL TO POSITION
	public static boolean isPointEqual(Point point, Position position) {
		Point from = position.getFrom();
		Point to = position.getTo();
		if (point.equals(from) || point.equals(to))
			return true;
		return false;
	}

	// CHECK IF BOINT IS BETWEEN POINTS IN POSITION
	public static boolean isPointBetween(Point point, Position position) {
		Point from = position.getFrom();
		Point to = position.getTo();

		return from.getY() <= point.getY() && to.getY() >= point.getY() && from.getX() <= point.getX()
				&& to.getX() >= point.getX();
	}

	// GET RANDOM POINT FOR BOT
	public static Point getRandomPoint() {
		int xmin = 0;
		int xmax = 9;
		int x = (int) ((Math.random() * ((xmax - xmin) + 1)) + xmin);
		int y = (int) ((Math.random() * ((xmax - xmin) + 1)) + xmin);
		Point point = new Point(x, y);
		return point;
	}

	// PRINT EMPTY
	public static void printEmpty() {
		for (int i = 0; i < Constants.emptySpace; i++) {
			System.out.println("\n");
		}
	}

	// SHIP NUMBERS
	public static String shipNbr(int i) {
		switch (i) {
		case 0:
			String first = "first";
			return first;
		case 1:
			String second = "second";
			return second;
		case 2:
			String third = "third";
			return third;
		case 3:
			String fourth = "fourth";
			return fourth;
		}
		String fifth = "fifth";
		return fifth;
	}

	// PRESS ANY KEY..
	public static void pressAnyKeyToContinue() {
		System.out.println("Press Enter key to continue...");
		try {
			System.in.read();
		} catch (Exception e) {
		}
	}
}