package battleships;

import java.util.*;

public class Player {
	protected String name;
	protected int score;
	protected int hitrate;
    private List<Ship> ships = new ArrayList<>();
    public Scanner scan = new Scanner(System.in);
	
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
		
    	String shipName = null;
    	int shipSize = 0;
    	int shipLives = 0;
    	boolean shipSunk = false;
    	Position position = null;
    	
    	Ship ship = new Ship(shipName, shipSize, shipLives, shipSunk, position);
		ArrayList<Ship> ships = new ArrayList<Ship>(ship.ships());
		System.out.println("The seas are currently empty, get ready to place your ships, " + getName());

    	for (int i = 1; i < 5; i++) {
    		int x;
    		int y;  		
    		String dir = null;
    		System.out.println("Please enter the name of your ship: ");
    		shipName = scan.next();
    		
    		
    		System.out.println("Please enter the starting x coordinate for your ship " + shipName);
    		x = scan.nextInt();
    		System.out.println("Please enter the starting y coordinate for your ship " + shipName);
    		y = scan.nextInt();
    		
    		while(x > 9 || x < 0 || y > 9 || y < 0) {
    			System.out.println("You can not place a ship outside the grid. Try again.");
        		System.out.println("Please enter the starting x coordinate for your ship " + shipName);
        		x = scan.nextInt();
        		System.out.println("Please enter the starting y coordinate for your ship " + shipName);
        		y = scan.nextInt();
    		}
    		
    		System.out.println("Please enter the size of your ship " + shipName);
    		shipSize = scan.nextInt();
    		while (shipSize > 5 || shipSize < 0) {
    			System.out.println(shipName + " cannot be longer than 5 units. Try again.");
        		shipSize = scan.nextInt();
    			}
    		shipLives = shipSize;
    		
    		System.out.println("Would you like to place " + shipName + " facing UP, DOWN, LEFT or RIGHT?");
    		System.out.println("Answer u, d, l or r.");
    		dir = scan.next();
    		
    		while (!(dir == "u") && !(dir == "U")
    				&& !(dir == "d") && !(dir == "D")
    				&& !(dir == "l") && !(dir == "L")
    				&& !(dir == "r") && !(dir == "R")) {
    			System.out.println("Incorrect input.");
        		System.out.println("Would you like to place + " + shipName + " facing UP, DOWN, LEFT or RIGHT?");
        		System.out.println("Answer u, d, l or r.");
        		dir = scan.next();
    		}
    		
    		
    		
    		}
    	
    		
    		addShip(shipName, shipSize, shipLives, shipSunk, position);
    }
}