package battleships;

public class Ship {
	private String shipName;
	private int shipSize;
	private int shipLives;
	private boolean shipSunk;
	private Position position;
    //private List<Ship> ship = new ArrayList<>();
	
	public Ship(String shipName, int shipSize, int shipLives, boolean shipSunk, Position position) {
		this.shipName = shipName;
		this.shipSize = shipSize;
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
	
	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
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
	
	public void shipWasSunk() {
		if (shipLives == 0) {
			shipSunk = true;
			System.out.println("Good job! You sunk the " + shipName + ".");
			return;
		}
		shipLives--;
	}
}