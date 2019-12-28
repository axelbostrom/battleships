package battleships;

import java.util.*;

public class Game  {
	public static boolean gameOver = false;
	public static boolean playerWin;
	public static char t;
	public static int playernbr = 2;
	public String name = null;
	public int score = 0;
	public int hitrate = 0;
	public int id = 0;
	public LinkedList<Player> players = new LinkedList<Player>();
	
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
		
		//Creates ship
		for (int i = 1; i <= playernbr; i++) {
			game.newPlayer(i);
		}
		
		//makes grid and places ships for players
		for (Player p: players) {
			p.makeGrid();
		}
		
		while (!gameOver) {
			for (Player p: players) {
				game.attack(p);
			}
		}
	}
    
	public void newPlayer(int nbr) {	
		String name = null;
		int score = 0;
		int hitrate = 0;
		int id = 0;
		int health = 0;
		Player player = new Player(name, health, id, score, hitrate);
		players.add(player);
		
		System.out.println("Player " + nbr + ", please enter your name: ");
		player.setName(scan.next());
		player.setID(nbr);
		System.out.println();
		System.out.println("Welcome " + player.getName() + "!");
		System.out.println();
	}
	
	public void attack(Player player) {
		for (int i = 0; i < playernbr; i++) {
			
		}
		player.action();
	}
}