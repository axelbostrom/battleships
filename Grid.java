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
	private String[][] playerGrid = new String[Constants.rowSize][Constants.colSize];
	private String[][] attackGrid = new String[Constants.rowSize][Constants.colSize];
	protected int playerLife;
    private List<Ship> ships = new ArrayList<>();
    	
	//launches two grids, one for player to place ships and one to attack
    public Grid() {
        for(int i = 0; i < Constants.rowSize; i++) {
            for(int j = 0; j < Constants.rowSize; j++) {
                playerGrid[i][j] = "~";
                attackGrid[i][j] = "~";
            }
        }    
    }
    
	public int countX() {
		int hp = 0;
		for(int i = 0; i < Constants.rowSize; i++) {
			for(int j = 0; j < Constants.rowSize; j++) {
				if (playerGrid[i][j]== "X") {
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
    	case 5:
        	String fifth = "fifth";
    		return fifth;
    	}
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
            	while (isPositionOccupied(position, playerGrid)) {
            		 System.out.println("A ship already exists in that position. Try again.");
                     System.out.println("Please enter the starting coordinates for your " + nbr + " ship (type 'x y'): ");                 
                     from = new Point(scan.nextInt(), scan.nextInt());          
                     System.out.println("Please enter the ending coordinates for yout " + nbr + " ship (type 'x y'): ");
                     to = new Point(scan.nextInt(), scan.nextInt());
                     position = new Position(from, to);
            	}         	
                shipSize = (int) Utils.distanceBetweenPoints(from, to);
                ship.setShipnbr(nbr);
                ship.setPosition(position);
                ship.setShipSize(shipSize);
                ship.setShipLives(shipSize);
        		drawShips(position, playerGrid);
                isShipPlacementLegal = true;
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Invalid coordinates, cannot place ships outside of the grid. Try again.");           
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
       
       if ((int) from.getY() <=  to.getY() && from.getX() <= to.getX()) {
    	   for(int i = (int) from.getX(); i <= to.getX(); i++) {
    		   for(int j = (int) from.getY(); j <= to.getY(); j++) {
	               grid[i][j] = "X";
	           }
	       }
       } else if ((int) from.getY() >=  to.getY() && from.getX() >= to.getX()) {
	       for(int i = (int) from.getX(); i >= to.getX(); i++) {
	           for(int j = (int) from.getY(); j >= to.getY(); j++) {
	               grid[i][j] = "X";
	           }
	       }
       }
       printMap(playerGrid);
    }
	
    public boolean isPositionOccupied(Position position, String[][] grid) {
        boolean isOccupied = false;
        Point from = position.getFrom();
        Point to = position.getTo();       
        first:
        for(int i = (int) from.getY() - 1; i <= (int) to.getY() - 1; i++) {
            for(int j = (int) from.getX() - 1; j <= (int) to.getX() - 1; j++) {
                try { 
                	if(grid[i][j] == "X") {
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
                	if(grid[i][j] == "X") {
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
                    String result = "1";
                    updateShipOnBoard(point, result, attackGrid);
                    printMap(attackGrid);
                    break;
                }
                String result = "0";
                updateShipOnBoard(point, result, attackGrid);
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