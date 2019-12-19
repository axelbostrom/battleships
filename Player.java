package battleships;

import java.util.*;
import java.awt.Point;

public class Player {
	protected String name;
	protected int score;
	protected int hitrate;
    private List<Ship> ships = new ArrayList<>();
    public Scanner scan = new Scanner(System.in);
	private static final int rowSize = 10;
	private static final int colSize = 10;
	private String[][] grid = new String[rowSize][colSize];
	
	public Player(String name, int score, int hitrate) {
		super();
		this.setName(name);
		this.setScore(score);
		this.setHitrate(hitrate);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void addScore(int score) {
	}

	public int getHitrate() {
		return hitrate;
	}

	public void setHitrate(int hitrate) {
		this.hitrate = hitrate;
	}
	
	public String toString() {
		return "Name: " + getName() + ". " + "\n"
				+ "Score: " +  getScore() + " points. " + "\n"
				+ "Hitrate: " + getHitrate() + " procent hitrate." + "\n";
	}
	
	public String printName() {
		return getName();
	}
	
	public void addShip(String a, int b, int c, boolean d, Position e) {
		addShip(new Ship(a, b, c, d, e));
	}
	
	public void addShip(Ship a) {
		ships.add(a);
	}
	
	public void createShip() {
		boolean isShipPlacementLegal = false;
    	String shipName = null;
    	int shipSize = 0;
    	int shipLives = 0;
    	boolean shipSunk = false;
    	Position position = null;

		Ship ship = new Ship(shipName, shipSize, shipLives, shipSunk, position);
		while (!isShipPlacementLegal) {
			try {
	    		System.out.println("Please enter the name of your ship: ");
	    		shipName = scan.next();
	    		System.out.println("Please enter the starting coordinate for your ship " + shipName + ", maximum length is 5 units.");
	    		System.out.println("Write for example '1 1'.");
	    		Point from = new Point(scan.nextInt(), scan.nextInt());
	    		System.out.println("Please enter the end coordinate.");
	    		Point to = new Point(scan.nextInt(), scan.nextInt());
	    		
	    		while (Utils.distanceBetweenPoints(from, to) > 5) {
	    			System.out.println("Incorrect size, your ship cannot be longer than 5 units. Try again.");
	    			System.out.println("Please enter your starting coordinate.");
	    			from = new Point(scan.nextInt(), scan.nextInt());
    	    		System.out.println("Please enter the end coordinate.");
    	    		to = new Point(scan.nextInt(), scan.nextInt());
	    		}
	    		position = new Position(from, to);

                if(!isPositionOccupied(position)) {
                    drawShips(position);
                    ship.setPosition(position);
                    isShipPlacementLegal = true;
                } else {
                    System.out.println("A ship in that position already exists - try again");
                }

		
			} catch(IndexOutOfBoundsException e) {
				System.out.println("Invalid coordinates - Outside board");
			}
		}
	}		
	
	//Prints map with ships
    public void printMap() {
    	
    	System.out.print("   ");

        for(int i = 0; i < rowSize; i++) {
            System.out.print(i + "  ");
        }

        System.out.println();

        for(int i = 0; i < rowSize; i++) {
            System.out.print((i) + "  ");
            for(int j = 0; j < rowSize; j++) {
            	if (grid[i][j] == null)
            		grid[i][j] = "~";
                System.out.print(grid[i][j] + "  ");
            }
            System.out.println();
        }
    }
    
    private void drawShips(Position position) {
        Point from = position.getFrom();
        Point to = position.getTo();
        for(int i = (int) from.getY() - 1; i < to.getY(); i++) {
            for(int j = (int) from.getX() - 1; j < to.getX(); j++) {
                grid[i][j] = "X";
            }
        }
        printMap();
    }
	
    public boolean isPositionOccupied(Position position) {
        boolean isOccupied = false;
        Point from = position.getFrom();
        Point to = position.getTo();

        outer:
        for(int i = (int) from.getY() - 1; i < to.getY(); i++) {
            for(int j = (int) from.getX() - 1; j < to.getX(); j++) {
                if(grid[i][j] == "X") {
                    isOccupied = true;
                    break outer;
                }
            }
        }
        return isOccupied;
    }
}