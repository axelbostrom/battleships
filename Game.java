package battleships;

import java.util.*;

public class Game  {
	private LinkedList<Player> players = new LinkedList<Player>();
	private Scanner scan = new Scanner(System.in);
	
	public Game() {

	}
	
	public void runGame(int playernbr) {
		Constants.printEmpty();
		System.out.println("Welcome to Battle Ships!");
		System.out.println("========================");
		System.out.println();
		
		//Playernbr 1, launch 1 player and AI
		if (playernbr == 1) {
			newPlayer(playernbr);
			//CPlayer();
		}
		//Playernbr 2, launch 2 players, no AI
		else if (playernbr == 2) {
			newPlayer(playernbr);
		}
		
		//start game
		play();
		return;
	}
   
	public void newPlayer(int playernbr) {	
		for (int i = 1; i <= playernbr; i++) {
			String name = null;
			int hitrate = 0;
			int damagerate = 0;
			int id = 0;
			int health = 0;
			Player player = new Player(name, health, id, hitrate, damagerate);
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
        Player player = null;
        while (players.get(0).getHealth() > 0 && players.get(1).getHealth() > 0) {
        	players.get(i).turnToPlay(players.get(j));
        	players.get(j).turnToPlay(players.get(i));
        	player = (players.get(0).getHealth() < players.get(1).getHealth()) ?
        			players.get(1):players.get(0);
        }
    	System.out.println(player.getName() + " you have sunk all ships, you win!");
    	System.out.println("Returning to main menu.");
    	System.out.println();
    }
}