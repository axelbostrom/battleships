package battleships;

import java.awt.Point;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Player {
	protected String name;
	protected float hitrate;
	protected float damagerate;
	protected float opponenthealth;
	protected long playertime;
	protected float hit = 0;
	protected float shots = 0;
	protected int id;
	protected int health;
	public Scanner scan = new Scanner(System.in);
	private Map<Point, Boolean> targetHistory = new HashMap<>();
	private Grid grid = new Grid();

	public Player(String name, int health, int id, int hitrate, int damagerate) {
		super();
		this.setName(name);
		this.setHealth(health);
		this.setID(id);
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

	public int getShots() {
		return (int) shots;
	}

	public int getHealth() {
		return health;
	}

	public void setShots(float shots) {
		this.shots = shots;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getHitrate() {
		return hitrate;
	}

	public void setHitrate(float hitrate) {
		this.hitrate = hitrate;
	}

	public float getDamagerate() {
		return damagerate;
	}

	public void setDamagerate(float damagerate) {
		this.damagerate = damagerate;
	}
	
	public void addPlayerTime(long t) {
		playertime = playertime + t;
	}

	public long getPlayerTime() {
		return playertime / 1000;
	}

	public void makeGrid(int c) {
		for (int i = 0; i < Constants.pshipAmount; i++) {
			if (c == 1)
				grid.placeShips(i);
			if (c == 2)
				grid.placeComputerShips(i);
		}
		setHealth(grid.countX());
		System.out.println(getName() + "'s grid.");
		grid.printPlayerGrid();
	}

	public void printPlayerGrid() {
		grid.printPlayerGrid();
	}

	public void turnToPlay(Player opponent) {
		long time = System.currentTimeMillis();
		boolean inputCorrect = true;
		boolean action = true;
		if (opponent.getHealth() == 0 || getHealth() == 0)
			return;
		opponenthealth = opponent.grid.countX();
		System.out.println(getName() + " your turn to attack.");
		String f = "Your previous attacks...";
		while (inputCorrect) {
			try {
				while (action) {
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
					System.out.println(getName() + " choose the coordinates you want to attack (x y): ");
					Point point = new Point(scan.nextInt(), scan.nextInt());
					while (targetHistory.get(point) != null) {
						System.out.println("This position has already been tried, try again (x y); ");
						point = new Point(scan.nextInt(), scan.nextInt());
					}
					action = attack(point, opponent);
					
				}
				addPlayerTime(System.currentTimeMillis() - time);
				inputCorrect = false;
			} catch (InputMismatchException e) {
				System.out.println("Incorrect input. Only use numbers. Try again.");
				System.out.println();
				scan.nextLine();
			}
		}
	}

	public void ComputerTurnToPlay(Player opponent) {
		boolean action = true;
		if (opponent.getHealth() == 0 || getHealth() == 0)
			return;
		opponenthealth = opponent.grid.countX();
		while (action) {
			if (opponent.getHealth() == 0 || getHealth() == 0)
				break;
			Point point = new Point(Utils.getRandomPoint());
			while (targetHistory.get(point) != null) {
				point = Utils.getRandomPoint();
			}
			action = attack(point, opponent);
		}
	}

	public boolean attack(Point point, Player opponent) {
		shots++;
		boolean action = false;
		String name = getName();
		boolean isShipHit = opponent.grid.targetShip(point, name) != null;
		if (isShipHit) {
			hit++;
			opponent.health--;
			action = true;
		}
		hitPercentage();
		damagePercentage();
		targetHistory.put(point, isShipHit);
		return action;
	}

	public float hitPercentage() {
		if (hit != 0) {
			hitrate = 100 * (hit / shots);
			setHitrate(hitrate);
			return hitrate;
		}
		return hitrate;
	}

	public float damagePercentage() {
		if (hit != 0) {
			damagerate = 100 * (hit / opponenthealth);
			setDamagerate(damagerate);
		}
		return damagerate;
	}
}