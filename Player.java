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
	
	public void addScore(int score) {
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
		System.out.println("The seas are currently empty, get ready to place your ships, player " + getID());
		for (int i = 0; i < Constants.pshipAmount; i++) {
			grid.placeShips(i);
		}
		setHealth(grid.countX());
		System.out.println(getHealth());
	}
    
    public void turnToPlay(Player opponent) {
        System.out.println("Player " + getID() +  " choose coordinates you want to hit (x y): ");
        Point point = new Point(scan.nextInt(), scan.nextInt());

        try { 
        	while(targetHistory.get(point) != null) {
                System.out.println("This position has already been tried, try again (x y); ");
                point = new Point(scan.nextInt(), scan.nextInt());
        	}
        } catch (NullPointerException e) {
        	System.out.println("TurnToPlay nullpointer");
        	attack(point, opponent);
        }
        attack(point, opponent);
    } 
    
    private void attack(Point point, Player opponent) {
    	Ship ship = opponent.grid.targetShip(point);		
        boolean isShipHit = ship != null;

        if(isShipHit) {
            ship.shipHit();
            health--;
        }
        targetHistory.put(point, isShipHit);
        System.out.printf("Player %d, targets (%d, %d)",
                id,
                (int)point.getX(),
                (int)point.getY());
        System.out.println("...and " + ((isShipHit) ? "HITS!" : "misses..."));
    }
}