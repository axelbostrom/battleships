package battleships;

public class Ship {
	private int shipSize;
	private int shipLives;
	private String shipnbr;
	private boolean shipSunk;
	private Position position;
	
	public Ship(int shipSize, String shipnbr, int shipLives, boolean shipSunk, Position position) {
		this.shipSize = shipSize;
		this.shipnbr = shipnbr;
		this.shipLives = shipLives;
		this.shipSunk = false;
		this.position = position;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public String getShipnbr() {
		return shipnbr;
	}

	public void setShipnbr(String shipnbr) {
		this.shipnbr = shipnbr;
	}

	public int getShipSize() {
		return shipSize;
	}

	public void setShipSize(int shipSize) {
		this.shipSize = shipSize;
	}

	public int getShipLives() {
		return shipLives;
	}

	public void setShipLives(int shipLives) {
		this.shipLives = shipLives;
	}

	public boolean isShipSunk() {
		return shipSunk;
	}

	public void setShipSunk(boolean shipSunk) {
		this.shipSunk = shipSunk;
	}
	
	public void reduceShipLives() {
		shipLives--;
	}
	
	public boolean shipHit() {
		if (shipLives == 0)
			return true;
		return false;
	}
}