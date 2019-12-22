package battleships;

import java.awt.Point;
import java.util.*;

public class Game  {
	protected String name;
	public static boolean gameOver;
	public static boolean playerWin;
	public static int playernbr = 2;
	public static char t;
	public static int pshipSize = 5; // size of ships
	public static int pshipAmount = 2;
	public static final int rowSize = 10;
	public static final int colSize = 10;
	public static LinkedList<Player> players = new LinkedList<Player>();
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

		for (int i = 1; i <= playernbr; i++) {
			game.newPlayer(i);
			}
		
		//launches maps for all players
		placeShips();
	}
    
	public void newPlayer(int nbr) {
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
	}
	
	public static void placeShips() {
		for (Player p: players) {
			p.makeGrid();
			System.out.println("The seas are currently empty, get ready to place your ships, " + p.getName());
			for (int i = 1; i <= pshipAmount; i++) {
				p.createShip();
			}
		}
	}

	public void playerTurn() {
		while (!gameOver) {
			
		}
		
	}
	
	public static void attack() {
		
		System.out.println("Last player : " + players.getLast());
		
		while (!gameOver) {
			for (Player p : players) {
				System.out.println("Your turn to attack, " + p.getName());
				
				
				System.out.println("Please enter the X-coordinates you would like to attack: ");
				int x = scan.nextInt();
				System.out.println("Please enter the Y-coordinates you would like to attack: ");
				int y = scan.nextInt(); 
				
				
				while(x > 9 || x < 0 || y > 9 || y < 0) {
					System.out.println("You cannot attack coordinates outside the grid.");				
					System.out.println("Please enter the X-coordinates you would like to attack: ");
					x = scan.nextInt();
					System.out.println("Please enter the Y-coordinates you would like to attack: ");
					y = scan.nextInt(); 
					
				}
				
			}
		}
	}
	
	public void hitAttack(int x, int y, String[][] grid) {
		
	}
	
	public void alreadyHit(int x, int y, String[][] grid) {
		
	}
}