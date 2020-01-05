package battleships;

import java.io.IOException;
import java.util.*;

public class Game  {
	public LinkedList<Player> players = new LinkedList<Player>();
	static Scanner scan = new Scanner(System.in);

	public void runGame(int playernbr) throws IOException {
		Constants.printEmpty();
		System.out.println("Welcome to Battle Ships!");
		System.out.println("========================");
		System.out.println();
		
		//Playernbr 1, launch 1 player and AI
		if (playernbr == 1) {
			newPlayer(playernbr);
			newComputerPlayer(2);
			AIplay();
		}
		//Playernbr 2, launch 2 players, no AI
		else if (playernbr == 2) {
			newPlayer(playernbr);
			play();
		}
		//return to main menu
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
	
	public void newComputerPlayer(int playernbr) {
		String name = "Mr Robot";
		int hitrate = 0;
		int damagerate = 0;
		int health = 0;
		Player player = new Player(name, health, playernbr, hitrate, damagerate);
		players.add(player);
		player.makeComputerGrid();
		
	}
	
    public void play() throws IOException {
        int i = 0;
        int j = 1;
        Player player = null;
        Menu menu = new Menu();
        while (players.get(0).getHealth() > 0 && players.get(1).getHealth() > 0) {
        	players.get(i).turnToPlay(players.get(j));
        	players.get(j).turnToPlay(players.get(i));
        	player = (players.get(0).getHealth() < players.get(1).getHealth()) ?
        			players.get(1):players.get(0);
        }
    	System.out.println(player.getName() + " you have sunk all ships, you win!");
    	if(player.getHitrate() > 0) {
			System.out.println("Congratulations! You made the highscore list!");
			menu.saving(player.getHitrate(), player.getName());
    	}
		System.out.println("Returning to main menu.");
		System.out.println();
    }
    
    public void AIplay() {
        int i = 0;
        int j = 1;
        Player player = null;
        while (players.get(0).getHealth() > 0 && players.get(1).getHealth() > 0) {
        	players.get(i).turnToPlay(players.get(j));
        	players.get(j).ComputerTurnToPlay(players.get(i));
        	player = (players.get(0).getHealth() < players.get(1).getHealth()) ?
        			players.get(1):players.get(0);
        }
    	System.out.println(player.getName() + " you have sunk all ships, you win!");
    	System.out.println("Returning to main menu.");
    	System.out.println();
    }
}