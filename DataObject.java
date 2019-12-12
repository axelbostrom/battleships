package battleships;

public class DataObject {
	protected String name;
	protected int score;
	protected int hitrate;
	
	public DataObject() {
		
	}
	
	public DataObject(String name, int score, int hitrate) {
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

	public int getHitrate() {
		return hitrate;
	}

	public void setHitrate(int hitrate) {
		this.hitrate = hitrate;
	}
}