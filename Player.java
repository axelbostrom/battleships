package battleships;

import java.util.*;
import java.awt.Point;

public class Player {
	protected String name;
	protected int score;
	protected int hitrate;
	public static int pshipSize = 5; // size of ships
	public static int pshipAmount = 2;
	protected int id;
	protected int health;
    private List<Ship> ships = new ArrayList<>();
    public Scanner scan = new Scanner(System.in);
	private static final int rowSize = 10;
	private static final int colSize = 10;
	private String[][] playerGrid = new String[rowSize][colSize];
	private String[][] attackGrid = new String[rowSize][colSize];
	protected int playerLife;
	
	public Player(String name, int health, int id, int score, int hitrate) {
		super();
		this.setName(name);
		this.setHealth(health);
		this.setID(id);
		this.setScore(score);
		this.setHitrate(hitrate);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
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
	
	public void placeShips() {
		makeGrid(playerGrid);
		makeGrid(attackGrid);
		System.out.println("The seas are currently empty, get ready to place your ships, " + getName());
		for (int i = 1; i <= pshipAmount; i++) {
			createShip();
		}
		playerLife = ships.toString().length();
	}
	
	public void createShip() {
		boolean isShipPlacementLegal = false;
    	String shipName = null;
    	int shipSize = 0;
    	int shipLives = 0;
    	boolean shipSunk = false;
    	Position position = null;	
		Ship ship = new Ship(shipName, shipSize, shipLives, shipSunk, position);
		ships.add(ship);
		
		while (!isShipPlacementLegal) {
            try {
            	System.out.println("Please enter the name of your ship.");
            	shipName = scan.next();
            	System.out.println("Please enter the starting coordinates for " + shipName + " (type 'x y'): ");
                Point from = new Point(scan.nextInt(), scan.nextInt());
                System.out.println("Please enter the ending coordinates for " + shipName + " (type 'x y'): ");
                Point to = new Point(scan.nextInt(), scan.nextInt());

                while((Utils.distanceBetweenPoints(from, to) > 5)) {
                    System.out.printf("Your ship cannot be longer than 5 units. Try again.");
                    System.out.println("Please enter the starting coordinates for " + shipName + " (type 'x y'): ");
                    from = new Point(scan.nextInt(), scan.nextInt());
                    System.out.println("Please enter the ending coordinates for " + shipName + " (type 'x y'): ");
                    to = new Point(scan.nextInt(), scan.nextInt());
                }
                position = new Position(from, to);
                
                if (isPositionOccupied(position, playerGrid)) {
                	while (isPositionOccupied(position, playerGrid)) {
                		 System.out.printf("A ship already exists in that position. Try again.");
                         System.out.println("Please enter the starting coordinates for " + shipName + " (type 'x y'): ");
                         from = new Point(scan.nextInt(), scan.nextInt());
                         System.out.println("Please enter the ending coordinates for " + shipName + " (type 'x y'): ");
                         to = new Point(scan.nextInt(), scan.nextInt());
                	}
            	}
                shipSize = (int) Utils.distanceBetweenPoints(from, to);
                ship.setShipName(shipName);
                ship.setPosition(position);
                ship.setShipSize(shipSize);
                ship.setShipLives(shipSize);
        		drawShips(position, playerGrid);
                isShipPlacementLegal = true;

            } catch(IndexOutOfBoundsException e) {
                System.out.println("Invalid coordinates - Outside board");
                
            }
        }
    }
	
	//launches two grids, one for player to place ships and one to attack
    public void makeGrid(String[][] grid) {

        for(int i = 0; i < rowSize; i++) {
            for(int j = 0; j < rowSize; j++) {
                grid[i][j] = "~";
            }
        }    
    }
	
	//Prints map with ships
    public void printMap(String[][] grid) {
    	
    	System.out.print("   ");

        for(int i = 0; i < rowSize; i++) {
            System.out.print(i + "  ");
        }

        System.out.println();

        for(int i = 0; i < rowSize; i++) {
            System.out.print((i) + "  ");
            for(int j = 0; j < rowSize; j++) {
                System.out.print(grid[i][j] + "  ");
            }
            System.out.println();
        }
    }
    
    private void drawShips(Position position, String[][] grid) {
        Point from = position.getFrom();
        Point to = position.getTo();
        
        for(int i = (int) from.getY(); i <= to.getY(); i++) {
            for(int j = (int) from.getX(); j <= to.getX(); j++) {
                grid[i][j] = "X";
            }
        }
        printMap(grid);
    }
	
    public boolean isPositionOccupied(Position position, String[][] grid) {
        boolean isOccupied = false;
    	Point from = position.getFrom();
    	Point to = position.getTo();
    	
    	for(int i = (int) from.getY(); i < to.getY(); i++) {
            for(int j = (int) from.getX(); j < to.getX(); j++) {
                if(grid[i][j] == "X") {
                	isOccupied = true;
                    break;
                }
            }
    	}
    	return isOccupied;
    }
    
    //Too add class that prevents players from placing ships to close to other ships
    public boolean isShipTooClose(Position position, String[][] grid) {
    	boolean tooClose = false;
    	Point from = position.getFrom();
    	Point to = position.getTo();
    	
    	for(int i = (int) from.getY(); i < to.getY(); i++) {
            for(int j = (int) from.getX(); j < to.getX(); j++) {
                if(grid[i][j] == "X") {
                    tooClose = true;
                    break;
                }
            }
    	}
    	return tooClose;
    }
    
    public boolean action() {
    	
    	return false;
    }
}