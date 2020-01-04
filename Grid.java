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
    
	public void placeShips(int shipnbr) {
		//boolean legalPlacement = false;
    	int shipSize = 0;
    	int shipLives = 0;
    	boolean shipSunk = false;
    	Position position = null;	
    	String nbr = shipNbr(shipnbr);	
		Ship ship = new Ship(shipSize, nbr, shipLives, shipSunk, position);
		ships.add(ship);  
		
    	System.out.println("Please enter the starting coordinates for your " + nbr +" ship (x y):");
    	Point from = new Point(scan.nextInt(), scan.nextInt());
    	System.out.println("Please enter the ending coordinates for your " + nbr +" ship (x y):");   
        Point to = new Point(scan.nextInt(), scan.nextInt());
        position = new Position(from, to);
        
        while (!isPointFreeForPlacement(position) || (isOutsideGrid(from, to)) 
        		|| (Utils.distanceBetweenPoints(from, to) > 5) 
        		|| ((to.getX() == from.getX() + 1) 
        		|| (to.getY() == from.getY() + 1))) {
        	
        	System.out.println("Please enter the starting coordinates for your " + nbr +" ship (x y):");
        	from = new Point(scan.nextInt(), scan.nextInt());
        	System.out.println("Please enter the ending coordinates for your " + nbr +" ship (x y):");   
            to = new Point(scan.nextInt(), scan.nextInt());
            position = new Position(from, to); 
        }
        shipSize = (int) Utils.distanceBetweenPoints(from, to);
        ship.setShipnbr(nbr);
        ship.setPosition(position);
        ship.setShipSize(shipSize);
        ship.setShipLives(shipSize);
		drawShips(position, playerGrid);
	}
	
	//Prints map depending on map
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
    
    public void printAttackGrid() {
    	printGrid(attackGrid);
    }
    
    public boolean isOutsideGrid(Point from, Point to) {
    	boolean outside = false;
    	if (from.getX() > 9 || from.getX() < 0 
    			||from.getY() > 9 || from.getY() < 0 
    			||to.getX() > 9 || to.getX() < 0 
    			|| to.getY() > 9 || to.getY() < 0) {
    		outside = true;
    		System.out.println("Cannot place ships outside the grid. Try again.");
    		return outside;
    	}
    	return outside;
    }
    
    public boolean isPointFreeForPlacement(Position position) {
    	Point f = position.getFrom();
    	Point t = position.getTo();
    	for (Point p: pointList) {
    		if (p.equals(f) || p.equals(t)) {
    			System.out.println("Cannot place ships on top of another ship! Try again.");
    			return false;
    		}
    	}
    	return true;
    }
    
    private void drawShips(Position position, String[][] grid) {
       Point from = position.getFrom();
       Point to = position.getTo();
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
    
    public Ship targetShip(Point point, String name) {
        boolean isHit = false;
        Ship hitShip = null;
        for(int i = 0; i < ships.toString().length(); i++) {
        	Ship ship = ships.get(i);
            if(ship.getPosition() != null) {
	            if(Utils.isPointBetween(point, ship.getPosition())) {
	            	ship.reduceShipLives();
	            	if (!ship.isShipSunk()) {
	            		System.out.println(name + " targets position (" + (int) point.getX() +", " + (int) point.getY() + ")...");
	            		System.out.println("HITS!");
	            		System.out.println("Good job! You sunk a ship!");
		                isHit = true;
		                hitShip = ship;
		                return hitShip;
	            	}
            		System.out.println(name + " targets position (" + (int) point.getX() +", " + (int) point.getY() + ")...");
            		System.out.println("HITS!");
	                isHit = true;
	                hitShip = ship;
	                break;
	            }
	            else if (!Utils.isPointBetween(point, ship.getPosition())) {
            		System.out.println(name + " targets position (" + (int) point.getX() +", " + (int) point.getY() + ")...");
            		System.out.println("MISS!");
            		String result = Constants.MISS_SYM;
                    updateShipOnBoard(point, result);
	            	return null;
	            }
            }
        }
        String result = Constants.HIT_SYM;
        updateShipOnBoard(point, result);
        printGrid(attackGrid);
        return (isHit) ? hitShip : null;
    }
    
    private void updateShipOnBoard(Point point, final String result) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        attackGrid[x][y] = result;
    }
    
	public List<Point> addPoint(Point from, Point to) {
		for (int i = (int) from.getX(); i <= (int) to.getX(); i++) {
			for (int j = (int) from.getY(); j <= (int) to.getY(); j++) {
				Point point = new Point(i, j);
				Point point1 = new Point(i+1, j);
				Point point2 = new Point(i, j+1);
				Point point3 = new Point(i+1, j+1);
				Point point4 = new Point(i-1, j);
				Point point5 = new Point(i, j-1);
				Point point6 = new Point(i-1, j-1);
				Point point7 = new Point(i+1, j-1);
				Point point8 = new Point(i-1, j+1);
				pointList.add(point);
				pointList.add(point1);
				pointList.add(point2);
				pointList.add(point3);
				pointList.add(point4);
				pointList.add(point5);
				pointList.add(point6);
				pointList.add(point7);
				pointList.add(point8);
			}
		}
		return pointList;
	}
}