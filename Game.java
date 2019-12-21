package battleships;

import java.util.*;

public class Game  {
	protected String name;
	public static int playernbr = 2;
	public static char t;
	public static int pshipSize = 5; // size of ships
	public static int pshipAmount = 2;
	public static final int rowSize = 10;
	public static final int colSize = 10;
	public LinkedList<Player> players = new LinkedList<Player>();
	//public String[][] grid = new String [rowSize][colSize];
	
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {		
		Game.runGame();
	}
	
    public Game(String name) {
        this.name = name;
    }
	
    public Game() {

	}
    
	public String getName() {
        return name;
    }
	
	public static void runGame() {
		Game game = new Game();
		System.out.println("Welcome to Battle Ships!");
		System.out.println("========================");
		System.out.println();

		for (int i = 0; i <= playernbr; i++) {
			for (int j = 1; j <= 2; j++) {
				i++;
				game.newPlayer(i, j);
			}
		}
		game.placeShips();
	}
    
    public void placeShips() {
		for(Player p : players) {
			p.makeGrid();
			System.out.println("The seas are currently empty, get ready to place your ships, " + p.getName());
			for (int i = 1; i <= 5; i++) {
				p.createShip();
			}
		}
    }
    
	public void newPlayer(int nbr, int i) {
		String name = null;
		int score = 0;
		int hitrate = 0;
		Player player = new Player(name, score, hitrate);
		players.add(player);
		
		System.out.println("Player " + nbr + ", please enter your name: ");
		name = scan.next();
		player.setName(name);
		System.out.println();
		System.out.println("Welcome " + name + "!");
		System.out.println();
	
		placeShips();
	}

	public void playerTurn() {
		
	}
	
	public void attack(String[][] grid) {
		
	}

	public void missedAttack(int x, int y, String[][] grid) {
		
	}
	
	public void hitAttack(int x, int y, String[][] grid) {
		
	}
	
	public void alreadyHit(int x, int y, String[][] grid) {
		
	}
}