package battleships;

import java.awt.Point;
import java.util.*;

public class Grid {
    private Scanner scan = new Scanner(System.in);
	private String[][] playerGrid = new String[Constants.gridSize][Constants.gridSize];
	private String[][] attackGrid = new String[Constants.gridSize][Constants.gridSize];
    private List<Ship> ships = new ArrayList<>();
    private List<Point> pointList = new ArrayList<>();
    	
	//launches two grids, one for player to place ships and one to attack
    public Grid() {
        for(int i = 0; i < Constants.gridSize; i++) {
            for(int j = 0; j < Constants.gridSize; j++) {
                playerGrid[i][j] = Constants.WAT_SYM;
                attackGrid[i][j] = Constants.WAT_SYM;
            }
        }    
    }
    
    //calculating player life
	public int countX() {
		int hp = 0;
		for(int i = 0; i < Constants.gridSize; i++) {
			for(int j = 0; j < Constants.gridSize; j++) {
				if (playerGrid[i][j] == Constants.SHIP_SYM) {
					hp++;	
				}
			}
		}
		return hp;
	}
    
	//naming ships instead of numbers
    public String shipNbr(int i) {	
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
    
    //places ships, called x times depended on game settings
	public void placeShips(int shipnbr) {
    	int shipSize = 0;
    	int shipLives = 0;
    	Position position = null;
    	String nbr = shipNbr(shipnbr);
		Ship ship = new Ship(shipSize, shipLives, position);
		ships.add(ship);  
		
    	System.out.println("Please enter the starting coordinates for your " + nbr +" ship (x y):");
    	Point from = new Point(scan.nextInt(), scan.nextInt());
    	System.out.println("Please enter the ending coordinates for your " + nbr +" ship (x y):");   
        Point to = new Point(scan.nextInt(), scan.nextInt());
        position = new Position(from, to);  
        while (isPointFreeForPlacement(from, to) || (isOutsideGrid(from, to)) || isShipTooBig(from, to)) {	
        	System.out.println("Please enter the starting coordinates for your " + nbr +" ship (x y):");
        	from = new Point(scan.nextInt(), scan.nextInt());
        	System.out.println("Please enter the ending coordinates for your " + nbr +" ship (x y):");   
            to = new Point(scan.nextInt(), scan.nextInt());
            position = new Position(from, to); 
        }
        shipSize = (int) Utils.distanceBetweenPoints(from, to);
        ship.setPosition(position);
        ship.setShipSize(shipSize);
        ship.setShipLives(shipSize);
		drawShips(from, to, playerGrid);
	}
	
	//Prints map depending on map (playerGrid or attackGrid
    public void printGrid(String[][] grid) {
    	System.out.println();
    	System.out.print("   ");
        for(int i = 0; i < Constants.gridSize; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
        for(int i = 0; i < Constants.gridSize; i++) {
            System.out.print((i) + "  ");
            for(int j = 0; j < Constants.gridSize; j++) {
                System.out.print(grid[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    //for printing attackGrid from Player.java
    public void printAttackGrid() {
    	printGrid(attackGrid);
    }
    
    //checks if ship is too big, eg 2x2, 3x3, 4x4
    public boolean isShipTooBig(Point from, Point to) {
    	if (Utils.distanceBetweenPoints(from, to) > 5 
    			|| ((to.getX() == from.getX() + 1) && (to.getY() == from.getY() + 1))
    			|| ((to.getX() == from.getX() + 2) && (to.getY() == from.getY() + 2))
    			|| ((to.getX() == from.getX() + 3) && (to.getY() == from.getY() + 3))
    			) {
    		System.out.println("The ship you tried to place is too big. Try again.");
    		return true;
    	}
    	return false;
    }
    
    //checks if attempted to place ship outside grid
    public boolean isOutsideGrid(Point from, Point to) {
    	if (from.getX() > 9 || from.getX() < 0 
    			|| from.getY() > 9 || from.getY() < 0 
    			|| to.getX() > 9 || to.getX() < 0 
    			|| to.getY() > 9 || to.getY() < 0) {
    		System.out.println("You can not place ships outside the grid. Try again.");
    		return true;
    	}
    	return false;
    }
    
    //checks if ship is attempted to be placed ontop/1 grid too close
    public boolean isPointFreeForPlacement(Point from, Point to) {
    	for (Point p: pointList) {
    		if (p.equals(from) || p.equals(to)) {
    			System.out.println("Cannot place ships on top of/too close to another ship! Try again.");
    			return true;
    		}
    	}
    	return false;
    }
    
    //add ships to playerGrid
    private void drawShips(Point from, Point to, String[][] grid) {
       if ((int) from.getY() <=  to.getY() && from.getX() <= to.getX()) {
    	   for(int i = (int) from.getX(); i <= to.getX(); i++) {
    		   for(int j = (int) from.getY(); j <= to.getY(); j++) {
	               grid[j][i] = Constants.SHIP_SYM;
	           }
	       }
       } else if ((int) from.getY() >=  to.getY() && from.getX() >= to.getX()) {
	       for(int i = (int) from.getX(); i >= to.getX(); i++) {
	           for(int j = (int) from.getY(); j >= to.getY(); j++) {
	               grid[j][i] = Constants.SHIP_SYM;
	           }
	       }
       }
       addPoint(from, to);
       printGrid(playerGrid);
    }
    
    //attacking, returns null if miss, true if hit/sunk
    public Ship targetShip(Point point, String name) {
        boolean isHit = false;
        Ship hitShip = null;
        for(int i = 0; i < ships.toString().length(); i++) {
        	Ship ship = ships.get(i);
            if(ship.getPosition() != null) {
	            if(!Utils.isPointEqual(point, ship.getPosition()) || Utils.isPointBetween(point, ship.getPosition())) {
	            	ship.reduceShipLives();
	            	if (ship.isShipSunk() == true) {
	            		System.out.println(name + " targets position (" + (int) point.getX() +", " + (int) point.getY() + ") and...");
	            		System.out.println("HITS!");
	            		System.out.println("Good job! You sunk a ship!");
	            		System.out.println();
		                isHit = true;
		                hitShip = ship;
		                break;
	            	}
            		System.out.println(name + " targets position (" + (int) point.getX() +", " + (int) point.getY() + ") and...");
            		System.out.println("HITS!");
            		System.out.println();
	                isHit = true;
	                hitShip = ship;
	                break;
	            }
	            else if (!Utils.isPointBetween(point, ship.getPosition())) {
            		System.out.println(name + " targets position (" + (int) point.getX() +", " + (int) point.getY() + ") and...");
            		System.out.println("misses...");
            		System.out.println();
	            	break;
	            }
            }
        }
        String result = (isHit) ? Constants.HIT_SYM : Constants.MISS_SYM;
        updateShipOnBoard(point, result);
        return (isHit) ? hitShip : null;
    }
    
    //updates playerGrid with 1 or 0, 1 for hit 0 for miss
    private void updateShipOnBoard(Point point, String result) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        attackGrid[y][x] = result;
    }
    
    //adds points around each point to prevent from too close placement
	public List<Point> addPoint(Point from, Point to) {
		for (int i = (int) from.getX(); i <= (int) to.getX(); i++) {
			for (int j = (int) from.getY(); j <= (int) to.getY(); j++) {
				var point = List.of(new Point(i, j), new Point(i+1, j), new Point(i, j+1), new Point(i-1, j), new Point(i, j-1), new Point(i-1, j-1), new Point(i+1, j-1), new Point(i-1, j+1),  new Point(i+1, j+1));
				pointList.addAll(point);
			}
		}
		return pointList;
	}
}