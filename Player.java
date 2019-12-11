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
}