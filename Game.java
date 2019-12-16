package battleships;

import java.util.*;

public class Game implements GameItem {
	protected String name;
	public static char t;
	public static int p1Ships; //Player one ships
	public static int p2Ships; //Player two ships
	public static int c1Ships;
	public static String[][] grid1 = new String [Constants.rowSize][Constants.colSize];
	public static String[][] grid2 = new String [Constants.rowSize][Constants.colSize];
	public static String[][] grid3 = new String [Constants.rowSize][Constants.colSize];
	public static String[][] grid4 = new String [Constants.rowSize][Constants.colSize];
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
		System.out.println("Welcome to Battle Ships!");
		System.out.println();
		//Launches all methods for game
		
		//System.out.println("Board 1");
		launchMap(grid1);
		launchMap(grid2);
		//System.out.println();
		//System.out.println("Board 2");
		//gridMap(grid2);
		//Add player
		newPlayer();
		
		//Launch addShipss
		addShips(grid1);
		addShips(grid2);
		
		attack(grid1);
	}

	public static void launchMap(String[][] grid) {
		//Creates and fills grid with tilde
		//Prints top row of grid
		System.out.print("   ");
		
		if (grid1.toString().contains(" ~ ")) {
			grid2 = grid;
		}
			for(int i = 0; i < Constants.rowSize; i++) {
				System.out.print(i + "  ");
			}
			System.out.println();
			
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					if (grid[i][j] == " X ") {
						grid[i][j] = " O ";
					} else {
						grid[i][j] = " ~ ";
						if (j == 0)
							System.out.print(i + "|"+ grid[i][j]);
						else if (j == grid[i].length -1)
							System.out.print(grid[i][j] + "|" + i);
						else
							System.out.print(grid[i][j]);
					}
				}
				System.out.println();
		}
	}
	//Prints map with ships
    public static void printMap(String[][] grid) {

		System.out.print("   ");
		for(int i = 0; i < Constants.rowSize; i++) {
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
    }

	public static void addShips(String[][] grid) {
		//ArrayList<String> player1Ships= new ArrayList<String>();
		//ArrayList<String> player2Ships= new ArrayList<String>();
		//ArrayList<String> aiShips= new ArrayList<String>();
		p1Ships = 2;
		c1Ships = 2;
		
		for (Player player: Player.players) {
			System.out.println(player.printName() + " you can place your ships!");
			for (int i = 1; i <= p1Ships; i++) {
				System.out.println("Please enter the size of your " + i + " ship. (only 1-5)");
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
				if (((x > Constants.colSize) || (x < 0)) || ((y > Constants.rowSize) || (y < 0))) {
					System.out.println("Not able to place ships outside of the grid.");
					i--;
				}
				
				else if ((grid[x][y]== " X ")) {
					System.out.println("You cannot place a ship on top of another.");
					i--;
				} else {
					//add if ship is placed closer than one grid from another ship
					
					System.out.println("Would you like to place the ship horizontally or vertically?");
					System.out.println("Answer H or V.");
					String dir = scan.next();
					
					while(!(dir.equals("h")) && !(dir.equals("H")) && !(dir.equals("v")) && !(dir.equals("V"))) {
						System.out.println("Incorrect input, only use H or V.");
						dir = scan.next();
					}
					
					if (dir.equals("h") || dir.equals("H")) {
						for (int j = 0; j < size; j++) {
							if (grid[x][y+j] == " X " ) {
								System.out.println("You cannot place a ship on top of another.");
								i--;
							} else if (((x > Constants.colSize) || (x < 0)) || ((y+j > Constants.rowSize) || (y+j < 0))) {
								System.out.println("Not able to place ships outside of the grid.");
								i--;
							} else {
								grid[x][y+j] = " X ";
							}
						}
					}
					
					else if (dir.equals("v") || dir.equals("V")) {
						for (int j = 0; j < size; j++) {
							if (grid[x+j][y] == " X " ) {
								System.out.println("You cannot place a ship on top of another.");
								i--;
							} else if (((x+j > Constants.colSize) || (x+j < 0)) || ((y > Constants.rowSize) || (y < 0))) {
								System.out.println("Not able to place ships outside of the grid.");
								i--;
							} else {
								grid[x+j][y] = " X ";
							}
						}
					}
					printMap(grid);
				}
			}
		}
	}
	
	public static String newPlayer() {
		String name = null;
		int playernbr = 1;
		int hitrate = 0;
		int choice = 0;
		int score = 0;
		Player player = new Player(name, score, hitrate);
		
		System.out.println("Would you like to play vs a computer or a friend?");
		System.out.println("1. vs computer");
		System.out.println("2. vs friend");
		try {
			choice = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {
			while (choice < 1 || choice > 2)  {
			System.out.println("Incorrect input, please only use number 1 or 2.");
			System.out.println("Try again...");
			choice = Integer.parseInt(scan.nextLine());
			}
		}
		
		for (int i = 0; i < choice; i++) {
			if (choice == 2) {
				System.out.println("Player " + playernbr + ", enter your name: ");
				name = scan.next();
				player.addPlayer(name);	
				System.out.println("Welcome " + name + "!");
				System.out.println();
				playernbr++;
			} else if (choice == 1) {
				System.out.println("Please enter your name: ");
				name = scan.next();
				player.addPlayer(name);		
				System.out.println("Welcome " + name + "!");
				System.out.println();
			} else {
				System.out.println("Incorrect input, please only use number 1 or 2.");
				System.out.println("Try again...");
				newPlayer();
			}
		}
		return null;
	}

	public static void playerTurn() {
		
	}
	
	public static void attack(String[][] grid) {
			System.out.println("Player 1: Get ready!");
			System.out.println();
			System.out.println("Please enter x coordinate to attack: ");
			int x = scan.nextInt();
			System.out.println("Please enter y coordinate to attack: ");
			int y = scan.nextInt();
		
			if (grid[x][y] == " X ")
				hitAttack(x , y, grid);
			else if (grid[x][y] == " ~ ")
				missedAttack(x, y, grid);	
			else
				alreadyHit(x, y, grid);
	}

	public static void missedAttack(int x, int y, String[][] grid) {
		System.out.println("You missed!");
		grid[x][y] = " O ";
		printMap(grid);
	}
	
	public static void hitAttack(int x, int y, String[][] grid) {
		System.out.println("You hit a ship at grid: " + x + ", " + y);
		grid[x][y] = " @ ";
		printMap(grid);
		attack(grid);
	}
	
	public static void alreadyHit(int x, int y, String[][] grid) {
		System.out.println("You have already hit this position, try again.");
		printMap(grid);
		attack(grid);
	}
}