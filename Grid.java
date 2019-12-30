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
    	int shipSize = 0;
    	int shipLives = 0;
    	boolean shipSunk = false;
    	Position position = null;	
    	String nbr = shipNbr(shipnbr);
	
		Ship ship = new Ship(shipSize, nbr, shipLives, shipSunk, position);
		ships.add(ship);
		while (!isShipPlacementLegal) {
            try {
            	
            	System.out.println("Please enter the starting coordinates for your " + nbr +" ship (x y).");
            	Point from = new Point(scan.nextInt(), scan.nextInt());
            	             
            	System.out.println("Please enter the ending coordinates for your " + nbr +" ship (x y).");
                Point to = new Point(scan.nextInt(), scan.nextInt());

                while((Utils.distanceBetweenPoints(from, to) > 5)) {
                    System.out.printf("Your ship cannot be longer than 5 units. Try again.");
                    System.out.println("Please enter the starting coordinates for your " + nbr + " ship (x y): ");
                    from = new Point(scan.nextInt(), scan.nextInt());
                    
                    System.out.println("Please enter the ending coordinates for your " + nbr + " ship (x y): ");
                    to = new Point(scan.nextInt(), scan.nextInt());
                }
                position = new Position(from, to);
                
            	while (isPositionOccupied(position)) {
            		 System.out.printf("A ship already exists in that position. Try again.");
                     System.out.println("Please enter the starting coordinates for your " + nbr + " ship (type 'x y'): ");                 
                     from = new Point(scan.nextInt(), scan.nextInt());
                     
                     System.out.println("Please enter the ending coordinates for yout " + nbr + " ship (type 'x y'): ");
                     to = new Point(scan.nextInt(), scan.nextInt());
                     position = new Position(from, to);
            	}
            	
            	/*while(isShipTooClose(position)) {
            		System.out.println("Kommer jag hit?");
            	}
            	*/
            	
                shipSize = (int) Utils.distanceBetweenPoints(from, to);
                ship.setShipnbr(nbr);
                ship.setPosition(position);
                ship.setShipSize(shipSize);
                ship.setShipLives(shipSize);
        		drawShips(position);
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
    
    private void drawShips(Position position) {
       Point from = position.getFrom();
       Point to = position.getTo();

       for(int i = (int) from.getY(); i <= to.getY(); i++) {
           for(int j = (int) from.getX(); j <= to.getX(); j++) {
               grid[i][j] = "X";
           }
       }
       printMap(grid);
    }
	
    public boolean isPositionOccupied(Position position) {
    	System.out.println("Check");
        boolean isOccupied = false;
    	
    	for(Ship s: ships) {
    		System.out.println("Test");
    		if(position == s.getPosition()) {
    			isOccupied = true;
    			break;
    		}
    	}
    	return isOccupied;
    }
    
    //Too add class that prevents players from placing ships to close to other ships
   /* public boolean isShipTooClose(Position position) {
    	System.out.println("Kommer jag hit???");
    	boolean tooClose = false;
    	Point from = position.getFrom();
    	Point to = position.getTo();
    	
    	
    	for(int i = (int) from.getX() - 1; i < to.getX(); i++) {
    		System.out.println("1a for loop");
    		for(int j = (int) from.getY() - 1; j < to.getY(); j++) {
    			System.out.println("2a for loop");
    			if (grid[i][j] == "X") {
    				System.out.println("if satsen");
    				return tooClose;
    			}	
    		}   		
    	}
    	for (int k = (int) to.getX() + 1; k > from.getX(); k++) {
    		System.out.println("3e for loopen");
    		for(int l = (int) to.getY() +1; l > from.getY(); l++) {
    			System.out.println("4e for loopen");
    			if (grid[k][l] == "X") {
    				System.out.println("2a if satsen");
    				return tooClose;
    			}
    		}
    	}
    	return tooClose;
    }*/
    
    
    public Ship targetShip(Point point) {
        boolean isHit = false;
        Ship hitShip = null;
        for(int i = 0; i < ships.toString().length(); i++) {
            Ship ship = ships.get(i);
            if(ship.getPosition() != null) {
                if(Utils.isPointBetween(point, ship.getPosition())) {
                    isHit = true;
                    hitShip = ship;
                    break;
                }

            }
        }
        final String result = isHit ? "@":"O";
        updateShipOnBoard(point, result);
        printMap(grid);

        return (isHit) ? hitShip : null;
    }
    
    private void updateShipOnBoard(Point point, final String result) {
        int x = (int) point.getX() - 1;
        int y = (int) point.getY() - 1;
        grid[y][x] = result;
    }
}