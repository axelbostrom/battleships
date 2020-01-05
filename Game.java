package battleships;

import java.util.*;

public class Game  {
	public LinkedList<Player> players = new LinkedList<Player>();
	
	static Scanner scan = new Scanner(System.in);
	
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
	
	/*public void CPlayer() {
		String name = "AI";
		int score = 0;
		int hitrate = 0;
		int id = 2;
		int health = 0;
		Player Cplayer = new Player(name, health, id, score, hitrate);
		players.add(Cplayer);
		
        int startmin = 0;
        int startmax = 5;
        int endmin = 5;
        int endmax = 9;
        int startrange = startmax - startmin;
        int endrange = endmax - endmin;
        for (int i = 1; i <= 2;) {
            int rand1 = new Random().nextInt((startmax - startmin) + 1) + startmin;
            int rand2 = new Random().nextInt((startmax - startmin) + 1) + startmin;
            int rand3 = new Random().nextInt((endmax - endmin) + 1) + endmin;
            int rand4 = new Random().nextInt((endmax - endmin) + 1) + endmin;
            if(Math.abs(rand1 - rand3) <= 5 && Math.abs(rand2 - rand4) <=5){
                if(!Grid.isPositionOccupied()) {
                    System.out.println(rand1);
                    System.out.println(rand2);
                    Point from = new Point(rand1, rand2);
                    System.out.println(rand3);
                    System.out.println(rand4);
                    Point to = new Point(rand3, rand4);
                    i++;
                }
            }
        }
    } */
   
	public void newPlayer(int playernbr) {	
		for (int i = 1; i <= playernbr; i++) {
			String name = null;
			int score = 0;
			int hitrate = 0;
			int damagerate = 0;
			int id = 0;
			int health = 0;
			Player player = new Player(name, health, id, score, hitrate, damagerate);
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
			players.get(i % players.toString().length()).turnToPlay(players.get(j % players.toString().length()));
			players.get(j % players.toString().length()).turnToPlay(players.get(i % players.toString().length()));
	        player = (players.get(0).getHealth() < players.get(1).getHealth()) ?
	                players.get(1):
	                players.get(0);
    		}
    	System.out.println(player.getName() + " you have sunk all ships, you win!");
    	System.out.println("Returning to main menu.");
    	System.out.println();
    }
}