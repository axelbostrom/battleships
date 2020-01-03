package battleships;

import java.util.*;

public class Game  {
	public static boolean gameOver = false;
	public String name = null;
	public int score = 0;
	public int hitrate = 0;
	public int id = 0;
	public LinkedList<Player> players = new LinkedList<Player>();
	
	static Scanner scan = new Scanner(System.in);
	
	public Game() {
	}
	
    public Game(String name) {
        this.name = name;
    }
    
	public String getName() {
        return name;
    }
	
	public void runGame(int playernbr) {
		Constants.printEmpty();
		System.out.println("Welcome to Battle Ships!");
		System.out.println("========================");
		System.out.println();
		
		//Playernbr 1, launch 1 player and AI
		if (playernbr == 1) {
			newPlayer(playernbr);
			CPlayer();
		}
		//Playernbr 2, launch 2 players, no AI
		else if (playernbr == 2) {
			newPlayer(playernbr);
		}
		
		//start game
		play();
		
		return;
	}
	
	public void CPlayer() {
		System.out.println("HEEEJ");
	}
   
	public void newPlayer(int playernbr) {	
		for (int i = 1; i <= playernbr; i++) {
			String name = null;
			int score = 0;
			int hitrate = 0;
			int id = 0;
			int health = 0;
			Player player = new Player(name, health, id, score, hitrate);
			players.add(player);	
			System.out.println("Player " + i + ", please enter your name: ");
			name = scan.next();
			player.setName(name);
			player.setID(i);
			System.out.println();
			Constants.printEmpty();
			System.out.println("Welcome " + player.getName() + "!");
			player.makeGrid();
			Constants.printEmpty();
		}
	}
	
    public void play() {
        int i = 0;
        int j = 1;
        int size = players.toString().length();
        Player player = null;
        while (!gameOver) {
        	while (players.get(0).getHealth() > 0 && players.get(1).getHealth() > 0) {
	        	try {
        		players.get(i++ % size).turnToPlay(players.get(j++ % size));
                player = (players.get(0).getHealth() < players.get(1).getHealth()) ?
                        players.get(1):
                        players.get(0);
	        	}
	        	catch (IndexOutOfBoundsException e) {   	
	        	}
	        	gameOver = true;
        	}
        System.out.println("Congrats, " + player.getName() + " you won!");   
        }
    }
}