package battleships;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Grid {

	protected String name;
	protected int score;
	protected int hitrate;
	protected int id;
	protected int health;
    public Scanner scan = new Scanner(System.in);
	private String[][] grid = new String[Constants.rowSize][Constants.colSize];
	//private String[][] attackGrid = new String[rowSize][colSize];
	protected int playerLife;
    private List<Ship> ships = new ArrayList<>();

    	
	//launches two grids, one for player to place ships and one to attack
    public Grid() {
        for(int i = 0; i < Constants.rowSize; i++) {
            for(int j = 0; j < Constants.rowSize; j++) {
                grid[i][j] = "~";
            }
        }    
    }
    
    public String shipNbr(int i) {
    	String first = "first";
    	String second = "second";
    	String third = "third";
    	String fourth = "fourth";
    	String fifth = "fifth";
    	
    	if (i == 0)
    		return first;
    	else if (i == 1)
    		return second;
    	else if (i == 2)
    		return third;
    	else if (i == 3)
    		return fourth;
    	else if (i == 5)
    		return fifth;
    	return null;  	
    }
    
	public void placeShips(int shipnbr) {
		boolean isShipPlacementLegal = false;
    	String shipName = null;
    	int shipSize = 0;
    	int shipLives = 0;
    	boolean shipSunk = false;
    	Position position = null;	
    	String nbr = shipNbr(shipnbr);
	
		Ship ship = new Ship(shipName, shipSize, shipLives, shipSunk, position);
		ships.add(ship);
		while (!isShipPlacementLegal) {
            try {
            	System.out.println("Please enter the name of your " + nbr + " ship.");
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
                
                if (isPositionOccupied(position, grid)) {
                	while (isPositionOccupied(position, grid)) {
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
                ship.setShipLives(shipSize);
        		drawShips(position, grid);
                isShipPlacementLegal = true;

            } catch(IndexOutOfBoundsException e) {
                System.out.println("Invalid coordinates - Outside board");
                
            }
		}
    }
	
	//Prints map with ships
    public void printMap(String[][] grid) {
    	
    	System.out.print("   ");

        for(int i = 0; i < Constants.rowSize; i++) {
            System.out.print(i + "  ");
        }

        System.out.println();

        for(int i = 0; i < Constants.rowSize; i++) {
            System.out.print((i) + "  ");
            for(int j = 0; j < Constants.rowSize; j++) {
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

}