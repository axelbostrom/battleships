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
	private String[][] playerGrid = new String[rowSize][colSize];
	private String[][] attackGrid = new String[rowSize][colSize];
	
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
			
			/* try {
				System.out.println("Please enter the name for your ship.");
				shipName = scan.next();
				System.out.println();
				System.out.println("Please enter the starting x-coordinate for your ship, " + shipName + ".");
				int x = scan.nextInt();
				System.out.println("Please enter the starting y-coordinate for your ship, " + shipName + ".");
				int y = scan.nextInt();
				
				while (((x > colSize) || (x < 0)) || ((y > rowSize) || (y < 0)) || (grid[x][y]== "X")) {
					if (grid[x][y]== "X") {
						System.out.println("You cannot place a ship on top of another.");
						System.out.println();
					} else {
						System.out.println("Not able to place ships outside of the grid.");
						System.out.println();
					}
					System.out.println("Please enter X cooridinate for " + shipName + ".");
					x = scan.nextInt();		
					System.out.println("Please enter Y cooridinate for " + shipName + ".");
					y = scan.nextInt();
				}
				System.out.println("Please enter the size of your ship, " + shipName);
				shipSize = scan.nextInt();
				
				while ((shipSize > 5) && (shipSize < 0)) {
					System.out.println("Incorrect size, the ship has to be between 1-5 units.");
					shipSize = scan.nextInt();				
				}
				
				System.out.println("Would you like to place your ship facing horizontally or vertically?");
				System.out.println("Answer H or V");
				String dir = scan.next();

				while (!(dir.equals("H")) 
						&& !(dir.equals("V"))) {
					System.out.println("Incorrect input, only use H or V");
					dir = scan.next();
						}
					

				} catch (InputMismatchException e) {
					
				} */
	
	//launches two grids, one for player to place ships and one to attack
    public void makeGrid() {

        for(int i = 0; i < rowSize; i++) {
            for(int j = 0; j < rowSize; j++) {
                playerGrid[i][j] = "~";
            }
        }
        
        for(int i = 0; i < rowSize; i++) {
            for(int j = 0; j < rowSize; j++) {
                attackGrid[i][j] = "~";
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
        
        /*if (from.getY() <=  to.getY()) {
	        for(int i = (int) from.getY(); i <= to.getY(); i++) {
	            for(int j = (int) from.getX(); j <= to.getX(); j++) {
	                grid[i][j] = "X";
	            }
	        }
        }
        else if (to.getY() <= from.getY()) {
	        for(int i = (int) to.getY(); i <= (int) from.getY(); i++) {
	            for(int j = (int) to.getX(); j <= from.getX(); j++) {
	                grid[i][j] = "X";
	            }
	        } 
        }*/
	
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
    
    public void checkGrid(int x, int y) {
    	
    }
}