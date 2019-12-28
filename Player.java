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
		return "Name: " + getName() + ". " + "\n"
				+ "Score: " +  getScore() + " points. " + "\n"
				+ "Hitrate: " + getHitrate() + " procent hitrate." + "\n";
	}
	
	public String printName() {
		return getName();
	}
	
	public void makeGrid() {
		Grid grid = new Grid();
		System.out.println("The seas are currently empty, get ready to place your ships, " + getName());
		for (int i = 0; i < Constants.pshipAmount; i++) {
			grid.placeShips(i);
		}
	}
    
    public boolean action() {
    	
    	
    	return false;
    }
}