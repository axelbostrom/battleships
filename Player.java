package battleships;

import java.util.*;
import java.awt.Point;

public class Player {
	protected String name;
	protected int score;
	protected int hitrate;
	protected int damagerate;
	protected int opponenthealth;
	protected int hit;
	protected int miss;
	protected int id;
	protected int health;
    public Scanner scan = new Scanner(System.in);
    private Map<Point, Boolean> targetHistory = new HashMap<>();
    private Grid grid = new Grid();
	
	public Player(String name, int health, int id, int score, int hitrate, int damagerate) {
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
	
	public int getDamagerate() {
		return damagerate;
	}

	public void setDamagerate(int damagerate) {
		this.damagerate = damagerate;
	}
	
	public void makeGrid() {
		System.out.println("The seas are currently empty, get ready to place your ships.");
		for (int i = 0; i < Constants.pshipAmount; i++) {
			grid.placeShips(i);
		}
		setHealth(grid.countX());
	}
    
    public void turnToPlay(Player opponent) {
    	boolean action = true;
    	opponenthealth = opponent.grid.countX();
    	System.out.println(getName() + " your turn.");
    	String f = "Your previous attacks...";
    	while (action == true) {
    		if (opponent.getHealth() == 0 || getHealth() == 0)
    			break;
    		System.out.println(f);
            for (int i = 0; i < f.length(); i++) {
            	String n = ("=");
            	System.out.print(n);
            }
            opponent.grid.printAttackGrid();
            System.out.println();
        	if (hit != 0) {
        		System.out.println("Your hitrate is: " + getHitrate() + "%");
        		System.out.println("Your damagerate is: " + getDamagerate() + "%");
        	}
        	System.out.println();
	        System.out.println("Choose the coordinates you want to attack (x y): ");
	        Point point = new Point(scan.nextInt(), scan.nextInt());
        	while(targetHistory.get(point) != null) {
                System.out.println("This position has already been tried, try again (x y); ");
                point = new Point(scan.nextInt(), scan.nextInt());
        	}
	        action = attack(point, opponent);
    	}
    } 
    
    private boolean attack(Point point, Player opponent) {
    	boolean action = false;
    	String name = getName();
        boolean isShipHit = opponent.grid.targetShip(point, name) != null;
        if (isShipHit) {
        	System.out.println("TRÄFF");
        	hit++;
        	hitPercentage();
        	damagePercentage();
            opponent.health--;
            action = true;
        }
        else {
        	System.out.println("MISS!!");
        	miss++;
        	hitPercentage();
        	damagePercentage();
        }
        targetHistory.put(point, isShipHit);
        return action;
    }
    
    public int hitPercentage() {
		if (hit != 0) {
			if(miss == 0) {
				hitrate = 100;
				setHitrate(hitrate);
				return hitrate;
			}
			hitrate = hit/miss*100;
			setHitrate(hitrate);
			return hitrate;
		}
		return hitrate;
    }
    
    public int damagePercentage() {
    	if (hit != 0) {
    		damagerate = hit/opponenthealth*100;
    		setDamagerate(damagerate);
    	}
		return damagerate;
    }
}