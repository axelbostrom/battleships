package battleships;

import java.util.*;

public class Menu implements MenuItem {
	public Scanner scan = new Scanner(System.in);
	
	protected String title;
	List<MenuItem> items;
	public Menu(String title) {
		items = new ArrayList<>();
		this.title = title;
	}
	
    public void add(MenuItem item) {
        items.add(item);
    }

    public String getTitle() {
        return title;
    }
    
    public void execute() {
    	int counter = -1;
        MenuItem toExecute = null;
        System.out.println(getTitle());
        for (int i = 0; i < getTitle().length(); i++) {
        	String n = ("=");
        	System.out.print(n);
        }
        System.out.println();
        for(MenuItem item : items) {
        	counter++;
            if(item.getTitle().equals(this.getTitle())) {
                toExecute = item;
            }
            System.out.println(counter + ": " + item.getTitle());
        }
        int a = scan.nextInt();
        toExecute = items.get(a);
        toExecute.execute();
    }
    
    public static void main(String[] args) {
    	Menu mainMenu = new Menu("Main Menu");
    	Menu playGame = new Menu("Play");
    	Menu scoreBoard = new Menu("Scoreboard");
    	Game game = new Game();
    	System.out.println("Welcome to Battleships!");
    	System.out.println();
        mainMenu.add(new AbstractMenuItem("Exit game") {
	        public void execute() {
	        	System.exit(1);
	        }
        });
        mainMenu.add(new AbstractMenuItem("Play") {
	        public void execute() {
				Constants.printEmpty();
	        	playGame.execute();
	        }
        }); 
        mainMenu.add(new AbstractMenuItem("Scoreboard") {
	        public void execute() {
				Constants.printEmpty();
	        	scoreBoard.execute();
	        }
        });
        playGame.add(new AbstractMenuItem("Main menu") {
	        public void execute() {
	        	mainMenu.execute();
	        }
        });
        playGame.add(new AbstractMenuItem("Player vs computer") {
	        public void execute() {
	        	game.runGame(1);
	        	mainMenu.execute();
	        }
        });
        playGame.add(new AbstractMenuItem("Player vs player") {
	        public void execute() {
	        	game.runGame(2);
	        	mainMenu.execute();
	        }
        });
        mainMenu.execute();
    }
}