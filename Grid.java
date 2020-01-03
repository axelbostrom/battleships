package battleships;

import java.awt.Point;
import java.util.*;

public class Grid {
    private Scanner scan = new Scanner(System.in);
	private String[][] playerGrid = new String[Constants.gridSize][Constants.gridSize];
	private String[][] attackGrid = new String[Constants.gridSize][Constants.gridSize];
    private List<Ship> ships = new ArrayList<>();
    	
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
    	int shipSize = 0;
    	int shipLives = 0;
    	boolean shipSunk = false;
    	Position position = null;	
    	String nbr = shipNbr(shipnbr);	
		Ship ship = new Ship(shipSize, nbr, shipLives, shipSunk, position);
		ships.add(ship);  
		
    	System.out.println("Please enter the starting coordinates for your " + nbr +" ship (x y):");
    	int x1 = scan.nextInt();
    	int y1 = scan.nextInt();
    	Point from = new Point(y1, x1);
    	System.out.println("Please enter the ending coordinates for your " + nbr +" ship (x y):");
    	int x2 = scan.nextInt();
    	int y2 = scan.nextInt();    
        Point to = new Point(y2, x2);
        position = new Position(from, to);   
		while (isOutsideGrid(from, to)) {
			System.out.println("You cannot place ships outside the grid.");
        	System.out.println("Please enter the starting coordinates for your " + nbr +" ship (x y):");
        	x1 = scan.nextInt(); 
        	y1 = scan.nextInt();
        	from = new Point(y1, x1);
        	System.out.println("Please enter the ending coordinates for your " + nbr +" ship (x y):");
        	x2 = scan.nextInt();
        	y2 = scan.nextInt();    
            to = new Point(y2, x2);
            position = new Position(from, to); 
		}
		while (Utils.distanceBetweenPoints(from, to) > 5) {
			System.out.println("Ships cannot be longer than 5 units.");
        	System.out.println("Please enter the starting coordinates for your " + nbr +" ship (x y):");
        	x1 = scan.nextInt(); 
        	y1 = scan.nextInt();
        	from = new Point(y1, x1);
        	System.out.println("Please enter the ending coordinates for your " + nbr +" ship (x y):");
        	x2 = scan.nextInt();
        	y2 = scan.nextInt();    
            to = new Point(y2, x2);
            position = new Position(from, to); 
		}
		while (isPositionOccupied(position, playerGrid)) {
			System.out.println("You have to place ships atleast one grid from another.");
        	System.out.println("Please enter the starting coordinates for your " + nbr +" ship (x y):");
        	x1 = scan.nextInt(); 
        	y1 = scan.nextInt();
        	from = new Point(y1, x1);
        	System.out.println("Please enter the ending coordinates for your " + nbr +" ship (x y):");
        	x2 = scan.nextInt();
        	y2 = scan.nextInt();    
            to = new Point(y2, x2);
            position = new Position(from, to); 
		}
    	System.out.println("kommer jag hit=?");
        shipSize = (int) Utils.distanceBetweenPoints(from, to);
        ship.setShipnbr(nbr);
        ship.setPosition(position);
        ship.setShipSize(shipSize);
        ship.setShipLives(shipSize);
		drawShips(position, playerGrid);
	}
	
	//Prints map depending on map
    public void printMap(String[][] grid) {
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
    
    //for printing playerGrid
    public void playerGrid(String name) {   
    	System.out.println();
    	System.out.println(name + ", your ships.");
    	System.out.println("===============================");
    	printMap(attackGrid);
    }
    
    //for printing attackGrid
    public void attackGrid(String name) {   	
    	System.out.println();
    	System.out.println(name + ", your previous attacks.");
    	System.out.println("===============================");
    	printMap(attackGrid);
    }
    
    private void drawShips(Position position, String[][] grid) {
       Point from = position.getFrom();
       Point to = position.getTo();
       if ((int) from.getY() <=  to.getY() && from.getX() <= to.getX()) {
    	   for(int i = (int) from.getX(); i <= to.getX(); i++) {
    		   for(int j = (int) from.getY(); j <= to.getY(); j++) {
	               grid[i][j] = Constants.SHIP_SYM;
	           }
	       }
       } else if ((int) from.getY() >=  to.getY() && from.getX() >= to.getX()) {
	       for(int i = (int) from.getX(); i >= to.getX(); i++) {
	           for(int j = (int) from.getY(); j >= to.getY(); j++) {
	               grid[i][j] = Constants.SHIP_SYM;
	           }
	       }
       }
       printMap(playerGrid);
    }
    
    public boolean isOutsideGrid(Point from, Point to) {
    	boolean outside = false;
    	if (from.getX() > 9 || from.getX() < 0 ||
    			from.getY() > 9 || from.getY() < 0 ||
    			to.getX() > 9 || to.getX() < 0 ||
    			to.getY() > 9 || to.getY() < 0) {
    		outside = true;
    		return outside;
    	}
    	return outside;
    }
	
    public boolean isPositionOccupied(Position position, String[][] grid) {
        boolean isOccupied = false;
        Point from = position.getFrom();
        Point to = position.getTo();       
        first:
        for(int i = (int) from.getY() - 1; i <= (int) to.getY() - 1; i++) {
            for(int j = (int) from.getX() - 1; j <= (int) to.getX() - 1; j++) {
                try { 
                	if(grid[i][j] == Constants.SHIP_SYM) {
                		isOccupied = true;
                        break first;
                	}        
                }catch (ArrayIndexOutOfBoundsException e ) {
                	//Do nothing
                }
            }
        }     
        second:
        for(int i = (int) from.getY() + 1; i <= (int) to.getY() + 1; i++) {
            for(int j = (int) from.getX() + 1; j <= (int) to.getX() + 1; j++) {
                try {
                	if(grid[i][j] == Constants.SHIP_SYM) {
                		isOccupied = true;
                        break second;
                	}          
                } catch (ArrayIndexOutOfBoundsException e) {
                	//Do nothing
                }
            }
        }
        return isOccupied;
    }
    
    public Ship targetShip(Point point) {
        Ship hitShip = null;
        for(int i = 0; i < ships.toString().length(); i++) {
        	Ship ship = ships.get(i); 	
            if(ship.getPosition() != null) {
                if(Utils.isPointBetween(point, ship.getPosition())) {
                    hitShip = ship;
                    updateShipOnBoard(point, Constants.HIT_SYM, attackGrid);
                    printMap(attackGrid);
                    break;
                }
                updateShipOnBoard(point, Constants.MISS_SYM, attackGrid);
                printMap(attackGrid);
                return hitShip;
            }
        }
        return hitShip;
    }
    
    private void updateShipOnBoard(Point point, final String result, String[][] grid) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        grid[x][y] = result;
    }
}