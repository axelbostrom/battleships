package battleships;

import java.io.*;
import java.util.*;

public class Menu implements MenuItem {
	protected String title;
	private Scanner scan = new Scanner(System.in);
	private LinkedList<String> highscoreList = new LinkedList<String>();
	private List<MenuItem> items;
	
	public Menu() {
		
	}
	
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
		boolean inputCorrect = true;
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
    	while (inputCorrect) {
			try {
	           int a = scan.nextInt();
	           toExecute = items.get(a);
	           toExecute.execute();
	           inputCorrect = false;
			}
	        catch (InputMismatchException e) {
	        	System.out.println("Incorrect input. Only use numbers. Try again.");
	        	scan.nextLine();
	        }
    	}
    }
    
    public void launchMenu() {
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
    
    public void highScoreList() throws FileNotFoundException, IOException {
		InputStream highscore = new FileInputStream ("/eclipse-workspace/Sänksakepp/src/battleships/highscore.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(highscore));
	    while (reader.ready()) {
	    	String line = reader.readLine();
	    	if(!line.equals("")) {
	    		highscoreList.add(line);
	    	}
	    }
	    reader.close();
    }
    
	public int checkHighscoreList(int shots) {
		int scorePlace = 1;
		for(String s : highscoreList) {
	        String[] splitLine = s.split(":");
	        int recordShots = Integer.parseInt(splitLine[2].trim());
	        if(shots <= recordShots) {
	        	return scorePlace;
	        }
	        scorePlace++;
		}
		return 0;
	}
}