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
    
    public void execute() throws IOException {
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
	        catch (InputMismatchException | IndexOutOfBoundsException e) {
	        	System.out.println("Incorrect input. Try again."); 
	        	scan.nextLine();
	        }
    	}
    }
    
    public void launchMenu() throws IOException {
		highScoreList();
    	Menu mainMenu = new Menu("Main Menu");
    	Menu playGame = new Menu("Play");
    	Menu scoreBoard = new Menu("Scoreboard");
    	Menu returnMenu = new Menu("Return");
    	Game game = new Game();
    	System.out.println("Welcome to Battleships!");
    	System.out.println();
        mainMenu.add(new AbstractMenuItem("Exit game") {
	        public void execute() {
	        	Constants.printEmpty();
	        	System.exit(1);
	        }
        });
        mainMenu.add(new AbstractMenuItem("Play") {
	        public void execute() throws IOException {
				Constants.printEmpty();
	        	playGame.execute();
	        }
        }); 
        mainMenu.add(new AbstractMenuItem("Scoreboard") {
	        public void execute() throws IOException {
	        	Constants.printEmpty();
	        	scoreBoard.execute();
	        }
        });
        playGame.add(new AbstractMenuItem("Main menu") {
	        public void execute() throws IOException {
	        	Constants.printEmpty();
	        	mainMenu.execute();
	        }
        });
        playGame.add(new AbstractMenuItem("Player vs computer") {
	        public void execute() throws IOException {
	        	Constants.printEmpty();
	        	game.runGame(1);
	        	mainMenu.execute();
	        }
        });
        playGame.add(new AbstractMenuItem("Player vs player") {
	        public void execute() throws IOException {
	        	Constants.printEmpty();
	        	game.runGame(2);
	        	mainMenu.execute();
	        }
        });
        scoreBoard.add(new AbstractMenuItem("Main menu") {
	        public void execute() throws IOException {
	        	Constants.printEmpty();
	        	mainMenu.execute();
	        }
        });
        scoreBoard.add(new AbstractMenuItem("Show highscore") {
	        public void execute() throws IOException {
	        	Constants.printEmpty();
	        	System.out.println("Top 10 players");
	        	System.out.println("==============");
	    		for(String s : highscoreList) {
	    	    	System.out.println(s);
	    	    }
	    		System.out.println();
	    		returnMenu.execute();
	        }
        });
        returnMenu.add(new AbstractMenuItem("Main menu") {
	        public void execute() throws IOException {
	        	Constants.printEmpty();
	        	mainMenu.execute();
	        	
	        }
        });
        mainMenu.execute();
    }
    
    public void highScoreList() throws IOException {
		InputStream highscore = new FileInputStream ("C:\\Users\\Greattech\\eclipse-workspace\\battleship\\src\\battleships\\highscore.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(highscore));
	    while (reader.ready()) {
	    	String line = reader.readLine();
	    	if(!line.equals("")) {
	    		highscoreList.add(line);
	    	}
	    }
	    reader.close();
    }
    
	public int checkHighscoreList(float hitrate) {
		int scorePlace = 1;
		for(String s : highscoreList) {
	        String[] splitLine = s.split(":");
	        int recordShots = Integer.parseInt(splitLine[2].trim());
	        if(hitrate <= recordShots) {
	        	return scorePlace;
	        }
	        scorePlace++;
		}
		return 0;
	}
	
	public void saving(int hitrate, String playerName) throws IOException {
		highScoreList();
		LinkedList<String> placeholder = new LinkedList<String>();
		int checkShots = checkHighscoreList(hitrate);
		int i = checkShots + 1;
		for(String s : highscoreList) {
			if(checkShots + 48 > s.charAt(0)) {
				placeholder.add(s);
				continue;
			}
			if(checkShots + 48 == s.charAt(0)) {
				placeholder.add(checkShots + ". Name: " + playerName + ", Hitrate: " + hitrate);
				continue;
			}
			String[] splitLine = s.split(":");
			placeholder.add(i + ". Name:" + splitLine[1] + ":" + splitLine[2]);
			i++;
		}
		highscoreList.clear();
		for(String s : placeholder) {
			highscoreList.add(s);
		}
		
		for(String s : highscoreList) {
			System.out.println(s);
		}
		
		OutputStream save = new FileOutputStream ("C:\\Users\\Greattech\\eclipse-workspace\\battleship\\src\\battleships\\highscore.txt", false);
		saveHighscore(save);
	}
	
	public void saveHighscore(OutputStream os) throws IOException {
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
		for(String s : highscoreList) {
			outputStreamWriter.append(s + "\n");
		}
		outputStreamWriter.close();
	}
}