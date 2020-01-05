package battleships;

public class Ship {
	private int shipSize;
	private int shipLives;
	private Position position;
	
	public Ship(int shipSize, int shipLives, Position position) {
		this.shipSize = shipSize;
		this.shipLives = shipLives;
		this.position = position;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
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

	public void reduceShipLives() {
		shipLives--;
	}
	
	public boolean isShipSunk() {
		if (shipLives == 0)
			return true;
		return false;
	}
}