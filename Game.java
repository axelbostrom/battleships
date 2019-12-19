package battleships;

import java.util.*;

public class Game  {
	protected String name;
	public static int playernbr = 2;
	public static char t;
	public static int pshipSize = 5; // size of ships
	public static int pshipAmount = 2;
	public static final int rowSize = 10;
	public static final int colSize = 10;
	public static String[][] grid1 = new String [rowSize][colSize]; //p1 grid
	public static String[][] grid2 = new String [rowSize][colSize]; //p2 grid
	public static String[][] grid3 = new String [rowSize][colSize]; //p1 attack grid
	public static String[][] grid4 = new String [rowSize][colSize]; //p2 attack grid
	public static String[][][] gridArray = {grid1, grid2, grid3, grid4};
	public LinkedList<Player> players = new LinkedList<Player>();

	
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {		
		Game.runGame();
	}
	
    public Game(String name) {
        this.name = name;
    }
	
    public Game() {
		// TODO Auto-generated constructor stub
	}
    
	public String getName() {
        return name;
    }
	
	public static void runGame() {
		Game game = new Game();
		System.out.println("Welcome to Battle Ships!");
		System.out.println("========================");
		System.out.println();
		//Launches all methods for game

		for (int i = 0; i <= playernbr; i++) {
			for (int j = 1; j <= 2; j++) {
				i++;
				game.newPlayer(i, j);
			}
		}
		game.placeShips();
		//Launch addShipss
		System.out.println();
		
		//attack(grid1);
	}

	public static void launchMap(String[][] grid) {
		//Creates and fills grid with tilde
		//Prints top row of grid
		System.out.print("   ");
		for(int i = 0; i < rowSize; i++) {
			System.out.print(i + "  ");
		}
		System.out.println();

		//Middle grid
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = " ~ ";
				if (j == 0)
					System.out.print(i + "|"+ grid[i][j]);
				else if (j == grid[i].length -1)
					System.out.print(grid[i][j] + "|" + i);
				else
					System.out.print(grid[i][j]);
				}
			System.out.println();
			}
		//Bottom row
		
		System.out.print("   ");
		for(int i = 0; i < rowSize; i++) {
			System.out.print(i + "  ");
		}
		System.out.println();
	}
	
	//Prints map with ships
    public static void printMap(String[][] grid) {

		System.out.print("   ");
		for(int i = 0; i < rowSize; i++) {
			System.out.print(i + "  ");
		}
		System.out.println();

        //Middle section of Ocean Map
        for(int x = 0; x < grid.length; x++) {
            System.out.print(x + "|");

            for (int y = 0; y < grid[x].length; y++){
                System.out.print(grid[x][y]);
            }

            System.out.println("|" + x);
        }
		System.out.print("   ");
		for(int i = 0; i < rowSize; i++) {
			System.out.print(i + "  ");
		}
		System.out.println();
    }
    
    public void placeShips() {
		for(Player p : players) {
			for (int i = 1; i <= 5; i++) {
				p.createShip();
			}
		}
    }

	public static void addShips(String[][] grid) {
		
		System.out.println();
		for (int i = 1; i <= pshipAmount; i++) {
			System.out.println("Please enter the length of your " + i + " ship. (only 1-5)");
			int size = scan.nextInt();
			while (size > 5 || size < 0) {
				System.out.println("Please only use numbers between 1-5.");
				System.out.println("Try again: ");
				size = scan.nextInt();
			}
			
			System.out.println("Please enter X cooridinate for your " + i + " ship: ");
			int x = scan.nextInt();		
			System.out.println("Please enter Y cooridinate for your " + i + " ship: ");
			int y = scan.nextInt();
			
			
			//if ship is attempted to place outside of grid
			while (((x > colSize) || (x < 0)) || ((y > rowSize) || (y < 0)) || (grid[x][y]== " X ")) {
				if (grid[x][y]== " X ") {
					System.out.println("You cannot place a ship on top of another.");
					System.out.println();
				} else {
					System.out.println("Not able to place ships outside of the grid.");
					System.out.println();
				}
				System.out.println("Please enter X cooridinate for your " + i + " ship: ");
				x = scan.nextInt();		
				System.out.println("Please enter Y cooridinate for your " + i + " ship: ");
				y = scan.nextInt();
			}
				
			System.out.println("Would you like to place the ship horizontally or vertically?");
			System.out.println("Answer H or V.");
			String dir = scan.next();
			
			while (!(dir.equals("h")) && !(dir.equals("H")) && !(dir.equals("v")) && !(dir.equals("V"))) {
				System.out.println("Incorrect input, only use H or V.");
				dir = scan.next();
			}
			
			if (dir.equals("h") || dir.equals("H")) {
				for (int j = 0; j < size; j++) {
					while (grid[x][y+j] == " X " ) {
						System.out.println("You cannot place a ship on top of another.");
						System.out.println("Please enter X cooridinate for your " + i + " ship: ");
						x = scan.nextInt();	
						System.out.println("Please enter Y cooridinate for your " + i + " ship: ");
						y = scan.nextInt();
					}
					
					try  {
						grid[x][y+j] = " X ";
					} 
					catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("Not able to place ships outside of the grid.");
						System.out.println("Please enter X cooridinate for your " + i + " ship: ");
						x = scan.nextInt();		
						System.out.println("Please enter Y cooridinate for your " + i + " ship: ");
						y = scan.nextInt();
					}
				}
			}
			
			else if (dir.equals("v") || dir.equals("V")) {
				for (int j = 0; j < size; j++) {
					while (grid[x+j][y] == " X " ) {
						System.out.println("You cannot place a ship on top of another.");
						System.out.println("Please enter X cooridinate for your " + i + " ship: ");
						x = scan.nextInt();	
						System.out.println("Please enter Y cooridinate for your " + i + " ship: ");
						y = scan.nextInt();
					} while (((x+j > colSize) || (x+j < 0)) || ((y > rowSize) || (y < 0))) {
						System.out.println("Not able to place ships outside of the grid.");
						System.out.println("Please enter X cooridinate for your " + i + " ship: ");
						x = scan.nextInt();	
						System.out.println("Please enter Y cooridinate for your " + i + " ship: ");
						y = scan.nextInt();
					}
					grid[x+j][y] = " X ";
				}
			}
		}
		printMap(grid);
	}

	
	public void newPlayer(int nbr, int i) {
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
		
		//launches players maps
		launchMap(gridArray[i]);
		System.out.println();
	}

	public static void playerTurn() {
		
	}
	
	public static void attack(String[][] grid) {
		
	}

	public static void missedAttack(int x, int y, String[][] grid) {
		
	}
	
	public static void hitAttack(int x, int y, String[][] grid) {
		
	}
	
	public static void alreadyHit(int x, int y, String[][] grid) {
		
	}
}