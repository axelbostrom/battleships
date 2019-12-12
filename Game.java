package battleships;

import java.util.*;

<<<<<<< HEAD
public class Game implements GameItem {
	
	protected String name;
=======
public class Game {
>>>>>>> d63f6b4db8e8e2b0c25348a0ccf9af1249847edd
	public static char t;
	public static int p1Ships; //Player one ships
	public static int p2Ships; //Player two ships
	public static int c1Ships; //Computers ships
<<<<<<< HEAD
	public static String[] ships = new String[Constants.BATTLESHIP_SIZE];
	public static String[][] grid = new String [Constants.rowSize][Constants.colSize];
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {		
		Game demo = new Game();
		demo.runGame();
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
    
	public void runGame() {
		System.out.println("Welcome to Battle Ships!");
		System.out.println();
		//Launches all methods for game

		//gridMap();
		
		//Add player
		newPlayer();
		
		//Launch addShips
=======
	public static String[][] grid = new String [Constants.rowSize][Constants.colSize];
	public static String[] ships = new String[Constants.BATTLESHIP_SIZE];
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		runGame();
	}
	
	public static void runGame() {
		System.out.println("Welcome to Battle Ships!");
		System.out.println();
		//Launches all methods for game
		

		//gridMap();
		//Add player
		player();
		
		//Launch addShipss
>>>>>>> d63f6b4db8e8e2b0c25348a0ccf9af1249847edd
		addShips();
		
		attack();
	}
	
<<<<<<< HEAD
	public void gridMap() {
=======
	public static void gridMap() {
>>>>>>> d63f6b4db8e8e2b0c25348a0ccf9af1249847edd
		System.out.print("   ");
		for(int i = 0; i < Constants.rowSize; i++) {
			System.out.print(i + "  ");
		}
		System.out.println();
		
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
	}
	
<<<<<<< HEAD
	public void addShips() {
=======
	public static void addShips() {
		System.out.println();
>>>>>>> d63f6b4db8e8e2b0c25348a0ccf9af1249847edd
		p1Ships = 5;
		c1Ships = 5;
		
		for (int i = 1; i <= p1Ships; i++) {
			System.out.println("Please enter X cooridinate for your " + i + " ship: ");
			int x = scan.nextInt();		
			
			System.out.println("Please enter Y cooridinate for your " + i + " ship: ");
			int y = scan.nextInt();
			
			//if ship is attempted to place outside of grid
			if (((x > Constants.colSize) || (x < 0)) || ((y > Constants.rowSize) || (y < 0))) {
				System.out.println("Not able to place ships outside of the grid.");
				i--;
			}
			//if ship is bigger than max ship length
			//else if (((x > y) && (x - y > 4)) || ((y > x) && (y - x > 4))) {
			//	System.out.println("Your ship can not be larger than 4.");
			//	i--;
			//}
			
			else if ((grid[x][y]== " X ")) {
				System.out.println("You cannot place a ship on top of another.");
				i--;
			}
			//add if ship is placed closer than one grid from another ship
			
			else {
				grid[x][y] = " X ";
				System.out.println("Placed a ship at coordinates: x = " + x +", y = " + y + ".");
				System.out.println();
			}
		}
	}
	
<<<<<<< HEAD
	public String newPlayer() {
=======
	public static String player() {
>>>>>>> d63f6b4db8e8e2b0c25348a0ccf9af1249847edd
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
			System.out.println("Incorrect input, please only use number 1 or 2.");
			System.out.println("Try again...");
			System.out.println();
<<<<<<< HEAD
			newPlayer();
=======
			player();
>>>>>>> d63f6b4db8e8e2b0c25348a0ccf9af1249847edd
		}
		for (int i = 0; i < choice; i++) {
			if (choice == 2) {
				System.out.println("Player " + playernbr + ", enter your name: ");
				name = scan.next();
				player.setName(name);		
				System.out.println("Welcome " + name + "!");
				System.out.println();
				playernbr++;
			} else if (choice == 1) {
				System.out.println("Please enter your name: ");
				name = scan.next();
				player.setName(name);		
				System.out.println("Welcome " + name + "!");
				System.out.println();
			} else {
				System.out.println("Incorrect input... try again.");
				System.out.println();
<<<<<<< HEAD
				newPlayer();
=======
				player();
>>>>>>> d63f6b4db8e8e2b0c25348a0ccf9af1249847edd
			}
		}
		return null;
	}
	
<<<<<<< HEAD
	public void playerTurn() {
		
	}
	
	public void attack() {
=======
	public static void playerTurn() {
		
	}
	
	public static void attack() {
>>>>>>> d63f6b4db8e8e2b0c25348a0ccf9af1249847edd
		System.out.println("Player 1: Get ready!");
		System.out.println();
		System.out.println("Please enter x coordinate to attack: ");
		int x = scan.nextInt();
		System.out.println("Please enter y coordinate to attack: ");
		int y = scan.nextInt();
		
		if (grid[x][y] == " X ")
			hitAttack(x , y);
		else
			missedAttack(x, y);	
	}
	
<<<<<<< HEAD
	public void missedAttack(int x, int y) {
=======
	public static void missedAttack(int x, int y) {
>>>>>>> d63f6b4db8e8e2b0c25348a0ccf9af1249847edd
		System.out.println("You missed!");
		System.out.println("Player 2's turn.");
	}
	
<<<<<<< HEAD
	public void hitAttack(int x, int y) {
=======
	public static void hitAttack(int x, int y) {
>>>>>>> d63f6b4db8e8e2b0c25348a0ccf9af1249847edd
		System.out.println("You hit a ship at grid: " + x + ", " + y);
	}
}