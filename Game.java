package battleships;

import java.util.*;

public class Game {
	public static char t;
	public static int p1Ships; //Player one ships
	public static int p2Ships; //Player two ships
	public static int c1Ships; //Computers ships
	//public static Player; 
	public static String[][] grid = new String [Constants.rowSize][Constants.colSize];
	
	public static void main(String[] args) {
		runGame();
	}
	
	public static void runGame() {
		//Launches all methods for game
		
		System.out.println("Welcome to Battle Ships game!");
		
		//Launch gridmap
		//gridMap();
		
		//Launch addShips
		//mapGui.bsGUI();
		addShips();
		
		attack();
	}
	
	public static void gridMap() {
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
	
	public static void addShips() {
		System.out.println();
		Scanner scan = new Scanner(System.in);
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
	
	public static void playerTurn() {
		
	}
	
	public static void attack() {
		Scanner scan = new Scanner(System.in);
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
	
	public static void missedAttack(int x, int y) {
		System.out.println("You missed!");
		System.out.println("Player 2's turn.");
	}
	
	public static void hitAttack(int x, int y) {
		System.out.println("You hit a ship at grid: " + x + ", " + y);
	}
}