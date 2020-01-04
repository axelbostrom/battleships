package battleships;

import java.util.*;
import java.awt.Point;

public class Player {
	protected String name;
	protected int score;
	protected int hitrate;
	protected int id;
	protected int health;
    public Scanner scan = new Scanner(System.in);
    private Map<Point, Boolean> targetHistory = new HashMap<>();
    private Grid grid = new Grid();
	
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

	public int getHitrate() {
		return hitrate;
	}

	public void setHitrate(int hitrate) {
		this.hitrate = hitrate;
	}
	
	public String toString() {
		return "ID" + getID() + ". " + "\n"
				+ "Score: " +  getScore() + " points. " + "\n"
				+ "Hitrate: " + getHitrate() + " procent hitrate." + "\n"
				+ "HP: " + getHealth() + "  HP." + "\n";
	}
	
	public void makeGrid() {
		System.out.println("The seas are currently empty, get ready to place your ships.");
		for (int i = 0; i < Constants.pshipAmount; i++) {
			grid.placeShips(i);
		}
		setHealth(grid.countX());
	}
    
    public void turnToPlay(Player opponent) {
    	System.out.println(getName() + " your turn.");
    	boolean action = true;
    	String f = "Your previous attacks...";
    	while (action) {
    		if (opponent.getHealth() == 0)
    			break;
    		System.out.println(f);
            for (int i = 0; i < f.length(); i++) {
            	String n = ("=");
            	System.out.print(n);
            }
            opponent.grid.printAttackGrid();
	        System.out.println("Choose the coordinates you want to attack (x y): ");
	        Point point = new Point(scan.nextInt(), scan.nextInt());
        	while(targetHistory.get(point) != null) {
                System.out.println("This position has already been tried, try again (x y); ");
                point = new Point(scan.nextInt(), scan.nextInt());
        	}
	        action = attack(point, opponent, action);
    	}
    } 
    
    private boolean attack(Point point, Player opponent, boolean action) {
    	action = false;
    	String name = getName();
    	Ship ship = opponent.grid.targetShip(point, name);
        boolean isShipHit = ship != null;
        if(isShipHit) {
            ship.shipHit();
            opponent.health--;
            action = true;
        }     
        targetHistory.put(point, isShipHit);
        return action;
    }
}