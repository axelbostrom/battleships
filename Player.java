package battleships;

import java.util.*;

public class Player {
	protected String name;
	protected int score;
	protected int hitrate;
	static LinkedList<Player> players = new LinkedList<Player>();
	
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

	public void addPlayer(Player c) {
		players.add(c);
	}
	
	public void addPlayer(String c) {
		addPlayer(new Player(c, score, hitrate));
	}
	
	public String toString() {
		return "Name: " + getName() + ". " + "\n"
				+ "Score: " +  getScore() + " points. " + "\n"
				+ "Hitrate: " + getHitrate() + " procent hitrate." + "\n";
	}
	
	public String printName() {
		return getName();
	}
	
	public static void getPlayer(Player c) {
		for (Player player: players) {
			System.out.println(player);
			System.out.println();
		}
	}
}