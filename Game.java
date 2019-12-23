package battleships;

import java.awt.Point;
import java.util.*;

public class Game  {
	public static boolean gameOver = false;
	public static boolean playerWin;
	public static int playernbr = 2;
	public static char t;
	public static final int rowSize = 10;
	public static final int colSize = 10;
	public String name = null;
	public int score = 0;
	public int hitrate = 0;
	public int id = 0;
	public Player player1 = new Player(name, id, score, hitrate);
	public Player player2 = new Player(name, id, score, hitrate);
	public LinkedList<Player> players = new LinkedList<Player>();
	//public String[][] grid = new String [rowSize][colSize];
	
	static Scanner scan = new Scanner(System.in);
	
    public Game(String name) {
        this.name = name;
    }
	
    public Game() {

	}
    
	public String getName() {
        return name;
    }
	
	public void runGame() {
		Game game = new Game();
		System.out.println("Welcome to Battle Ships!");
		System.out.println("========================");
		System.out.println();
		
		System.out.println("Player 1, please enter your name.");
		name = scan.next();
		player1.setName(name);
		player1.setID(1);
		players.add(player1);
		
		System.out.println("Player 2, please enter your name.");
		name = scan.next();
		player2.setName(name);
		player2.setID(2);
		players.add(player2);
		
		//creates n players
		//for (int i = 1; i <= playernbr; i++) {
		//	game.newPlayer(i);
		//	}
		
		//places ships for players
		for (Player p: players) {
			p.placeShips();
		}
		
		while (!gameOver) {
			attack(player1);
			attack(player2);
		}
	}
    
	public void newPlayer(int nbr) {
		
		String name = null;
		int score = 0;
		int hitrate = 0;
		int id = 0;
		Player player = new Player(name, id, score, hitrate);
		players.add(player);
		
		System.out.println("Player " + nbr + ", please enter your name: ");
		name = scan.next();
		player.setName(name);
		System.out.println();
		System.out.println("Welcome " + name + "!");
		System.out.println();
	}

	public void playerTurn() {
		while (!gameOver) {
			
		}
		
	}
	
	public void attack(Player player) {
		boolean HIT = true;
		boolean WIN = true;
		boolean AGAIN = true;
			
		if (player.getID() == 1) {
			System.out.println("Your turn to attack, " + player1.getName());		
			
			System.out.println("Please enter the coordinates you would like to attack (enter 'x y'): ");
			Point attack = new Point(scan.nextInt(), scan.nextInt());
			
			if (player2.attacked(attack) == AGAIN) {		
				System.out.println();
				System.out.println("Please enter the coordinates you would like to attack (enter 'x y'): ");
				attack = new Point(scan.nextInt(), scan.nextInt());
			}
			else if (player2.attacked(attack) == HIT) {
				System.out.println("You hit a ship at grid " + attack);
				while (player2.attacked(attack) == HIT) {
					player2.hit(attack);
					System.out.println("You hit a ship at grid " + attack);
					System.out.println();
					System.out.println("Please enter the coordinates you would like to attack (enter 'x y'): ");
					attack = new Point(scan.nextInt(), scan.nextInt());
				} 
			}			
			else if (player2.attacked(attack) == WIN) {
				gameOver = true;
			}
			System.out.println("You missed.");
			
			
		} else if (player.getID() == 2) {
			
		}
	}
}