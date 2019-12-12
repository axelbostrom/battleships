package battleships;

import java.util.*;
import java.io.*;

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
	
	public void addPlayer(Player c) {
		players.add(c);
	}
	
	public String toString() {
		return "Name: " + getName() + ". " + "\n"
				+ "Score: " +  getScore() + " points. " + "\n"
				+ "Hitrate: " + getHitrate() + " procent hitrate." + "\n";
	}
	
	public static void getPlayer(Player c) {
		for (DataObject player: players) {
			System.out.println(player);
			System.out.println();
		}

	}
}