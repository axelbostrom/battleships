package battleships;

import java.awt.Point;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Grid {
	private Scanner scan = new Scanner(System.in);
	private String[][] playerGrid = new String[Constants.gridSize][Constants.gridSize];
	private String[][] attackGrid = new String[Constants.gridSize][Constants.gridSize];
	private List<Ship> ships = new ArrayList<>();
	private List<Point> pointList = new ArrayList<>();
	private List<Point> checkList = new ArrayList<>();

	// launches two grids, one for player to place ships and one to attack
	public Grid() {
		for (int i = 0; i < Constants.gridSize; i++) {
			for (int j = 0; j < Constants.gridSize; j++) {
				playerGrid[i][j] = Constants.WAT_SYM;
				attackGrid[i][j] = Constants.WAT_SYM;
			}
		}
	}

	// Prints map depending on map (playerGrid or attackGrid
	public void printGrid(String[][] grid) {
		System.out.println();
		System.out.print("   ");
		for (int i = 0; i < Constants.gridSize; i++) {
			System.out.print(i + "  ");
		}
		System.out.println();
		for (int i = 0; i < Constants.gridSize; i++) {
			System.out.print((i) + "  ");
			for (int j = 0; j < Constants.gridSize; j++) {
				System.out.print(grid[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// for printing playerGrid from Player.java
	public void printPlayerGrid() {
		printGrid(playerGrid);
	}

	// for printing attackGrid from Player.java
	public void printAttackGrid() {
		printGrid(attackGrid);
	}

	// calculating player life
	public int countX() {
		int hp = 0;
		for (int i = 0; i < Constants.gridSize; i++) {
			for (int j = 0; j < Constants.gridSize; j++) {
				if (playerGrid[i][j] == Constants.SHIP_SYM) {
					hp++;
				}
			}
		}
		return hp;
	}

	// places ships, called x times depended on game settings
	public void placeShips(int shipnbr) {
		boolean inputCorrect = true;
		int shipSize = 0;
		int shipLives = 0;
		Position position = null;
		String nbr = Utils.shipNbr(shipnbr);
		Ship ship = new Ship(shipSize, shipLives, position);
		ships.add(ship);
		printGrid(playerGrid);
		while (inputCorrect) {
			try {
				System.out.println("Please enter the starting coordinates for your " + nbr + " ship (x y):");
				Point from = new Point(scan.nextInt(), scan.nextInt());
				System.out.println("Please enter the ending coordinates for your " + nbr + " ship (x y):");
				Point to = new Point(scan.nextInt(), scan.nextInt());
				position = new Position(from, to);
				while (checkPoints(from, to) || (isOutsideGrid(from, to)) || isIllegalForm(from, to)
						|| isShipWrongWay(from, to) || isShipTooBig(from, to)) {
					System.out.println("Incorrect input, try again.");
					System.out.println("Please enter the starting coordinates for your " + nbr + " ship (x y):");
					from = new Point(scan.nextInt(), scan.nextInt());
					System.out.println("Please enter the ending coordinates for your " + nbr + " ship (x y):");
					to = new Point(scan.nextInt(), scan.nextInt());
					position = new Position(from, to);
				}
				shipSize = (int) Utils.distanceBetweenPoints(from, to);
				ship.setPosition(position);
				ship.setShipSize(shipSize);
				ship.setShipLives(shipSize);
				drawShips(from, to, playerGrid);
				inputCorrect = false;
			} catch (InputMismatchException e) {
				System.out.println("Incorrect input. Only use numbers. Try again.");
				System.out.println();
				scan.nextLine();
			}
		}
	}

	// computer places ships
	public void placeComputerShips(int shipnbr) {
		int shipSize = 0;
		int shipLives = 0;
		Position position = null;
		Ship ship = new Ship(shipSize, shipLives, position);
		ships.add(ship);
		Point from = new Point(Utils.getRandomPoint());
		Point to = new Point(Utils.getRandomPoint());
		while (isIllegalForm(from, to) || checkPoints(from, to) || isShipWrongWay(from, to) || isShipTooBig(from, to)) {
			from = Utils.getRandomPoint();
			to = Utils.getRandomPoint();
		}
		position = new Position(from, to);
		shipSize = (int) Utils.distanceBetweenPoints(from, to);
		ship.setPosition(position);
		ship.setShipSize(shipSize);
		ship.setShipLives(shipSize);
		drawShips(from, to, playerGrid);
	}

	// checks if ship is bigger than five
	public boolean isShipTooBig(Point from, Point to) {
		if (Utils.distanceBetweenPoints(from, to) > 5)
			return true;
		return false;
	}

	// prevents placement where from is bigger than to
	public boolean isShipWrongWay(Point from, Point to) {
		if (from.getX() > to.getX() || from.getY() > to.getY())
			return true;
		return false;
	}

	// checks if ship is 2x2, 3x3, 4x4
	public boolean isIllegalForm(Point from, Point to) {
		if (to.getX() - from.getX() > 0 && to.getY() - from.getY() > 0)
			return true;
		return false;
	}

	// checks if attempted to place ship outside grid
	public boolean isOutsideGrid(Point from, Point to) {
		if (from.getX() > 9 || from.getX() < 0 || from.getY() > 9 || from.getY() < 0 || to.getX() > 9 || to.getX() < 0
				|| to.getY() > 9 || to.getY() < 0)
			return true;
		return false;
	}

	// checks if ship is attempted to be placed ontop/1 grid too close
	public boolean isPointFreeForPlacement(Point from, Point to) {
		for (Point p : pointList)
			if (p.equals(from) || p.equals(to))
				return true;
		return false;
	}

	// add ships to playerGrid
	private void drawShips(Point from, Point to, String[][] grid) {
		if ((int) from.getY() <= to.getY() && from.getX() <= to.getX()) {
			for (int i = (int) from.getX(); i <= to.getX(); i++) {
				for (int j = (int) from.getY(); j <= to.getY(); j++) {
					grid[j][i] = Constants.SHIP_SYM;
				}
			}
		} else {
			for (int i = (int) from.getX(); i >= to.getX(); i++) {
				for (int j = (int) from.getY(); j >= to.getY(); j++) {
					grid[j][i] = Constants.SHIP_SYM;
				}
			}
		}
		addPoint(from, to);
	}

	// attacking, returns null if miss, true if hit/sunk
	public Ship targetShip(Point point, String name) {
		Ship hitShip = null;
		for (Ship s : ships) {
			if (s.getPosition() != null) {
				if (Utils.isPointEqual(point, s.getPosition()) || Utils.isPointBetween(point, s.getPosition())) {
					s.reduceShipLives();
					if (s.isShipSunk() == true) {
						System.out.println(name + " targets position (" + (int) point.getX() + ", " + (int) point.getY()
								+ ") and...");
						System.out.println("HITS!");
						System.out.println("Good job! You sunk a ship!");
						System.out.println();
						hitShip = s;
						updateShipOnBoard(point, Constants.HIT_SYM);
						return hitShip;
					}
					System.out.println(
							name + " targets position (" + (int) point.getX() + ", " + (int) point.getY() + ") and...");
					System.out.println("HITS!");
					System.out.println();
					updateShipOnBoard(point, Constants.HIT_SYM);
					hitShip = s;
					return hitShip;
				}
			}
		}
		System.out.println(name + " targets position (" + (int) point.getX() + ", " + (int) point.getY() + ") and...");
		System.out.println("misses...");
		System.out.println();
		updateShipOnBoard(point, Constants.MISS_SYM);
		return hitShip;
	}

	// updates playerGrid with 1 or 0, 1 for hit 0 for miss
	private void updateShipOnBoard(Point point, String result) {
		int x = (int) point.getX();
		int y = (int) point.getY();
		attackGrid[y][x] = result;
	}

	// checks if attempted placement points interfere with points in pointList
	public boolean checkPoints(Point from, Point to) {
		for (int i = (int) from.getX(); i <= (int) to.getX(); i++) {
			for (int j = (int) from.getY(); j <= (int) to.getY(); j++) {
				Point point = new Point(i, j);
				checkList.add(point);
			}
		}
		for (Point p : pointList) {
			for (Point c : checkList) {
				if (p.equals(c)) {
					checkList.clear();
					return true;
				}
			}
		}
		checkList.clear();
		return false;
	}

	// adds points around each point to prevent from too close placement
	public List<Point> addPoint(Point from, Point to) {
		for (int i = (int) from.getX(); i <= (int) to.getX(); i++) {
			for (int j = (int) from.getY(); j <= (int) to.getY(); j++) {
				var point = List.of(new Point(i, j), new Point(i + 1, j), new Point(i, j + 1), new Point(i - 1, j),
						new Point(i, j - 1), new Point(i - 1, j - 1), new Point(i + 1, j - 1), new Point(i - 1, j + 1),
						new Point(i + 1, j + 1));
				pointList.addAll(point);
			}
		}
		return pointList;
	}
}