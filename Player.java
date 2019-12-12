package battleships;

import java.util.*;

public class Player extends DataObject {
	protected String name;
	protected int score;
	protected int hitrate;
	static LinkedList<DataObject> players = new LinkedList<DataObject>();
	
	public Player(String name, int score, int hitrate) {
		super(name,score,hitrate);
		this.setName(name);
		this.setScore(score);
		this.setHitrate(hitrate);
	}
	
<<<<<<< HEAD
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

	public int getHitrate() {
		return hitrate;
	}

	public void setHitrate(int hitrate) {
		this.hitrate = hitrate;
	}
	
=======
>>>>>>> d63f6b4db8e8e2b0c25348a0ccf9af1249847edd
	public void addPlayer(Player c) {
		players.add(c);
	}
	
	public String toString() {
		return "Name: " + getName() + ". " + "\n"
				+ "Score: " +  getScore() + " points. " + "\n"
				+ "Hitrate: " + getHitrate() + " procent hitrate." + "\n";
	}
	
<<<<<<< HEAD
	public String printName() {
		return getName();
	}
	
=======
>>>>>>> d63f6b4db8e8e2b0c25348a0ccf9af1249847edd
	public static void getPlayer(Player c) {
		for (DataObject player: players) {
			System.out.println(player);
			System.out.println();
		}
<<<<<<< HEAD
=======

>>>>>>> d63f6b4db8e8e2b0c25348a0ccf9af1249847edd
	}
}