package battleships;

import java.util.*;

public class Game {
	public static int numRows = 10;
	public static int numCols = 10;
	public static char t;
	public static int p1Ships; //Player one ships
	public static int p2Ships; //Player two ships
	public static int c1Ships; //Computers ships
	public static String[][] grid = new String [numRows][numCols];
	
	public static void main(String[] args) {
		runGame();
	}
	
	public static void runGame() {
		//Launches all methods for game
		
		System.out.println("Welcome to Battle Ships game!");
		System.out.println();
		
		//Launch gridmap
		gridMap();
		
		//Launch addShips
		addShips();
	}
	
	public static void gridMap() {
		System.out.print("  ");
		for(int i = 0; i < numRows; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = "  ";
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
			System.out.println();
			System.out.println("Please enter X cooridinate for your " + i + " ship: ");
			int x = scan.nextInt();		
			
			System.out.println();
			System.out.println("Please enter Y cooridinate for your " + i + " ship: ");
			int y = scan.nextInt();
			
			//if ship is attempted to place outside of grid
			if (((x > numCols) || (x < 0)) || ((y > numRows) || (y < 0))) {
				System.out.println();
				System.out.println("Not able to place ships outside of the grid.");
				i--;
			}
			//if ship is bigger than max ship length
			else if (((x > y) && (x - y > 4)) || ((y > x) && (y - x > 4))) {
				System.out.println();
				System.out.println("Your ship can not be larger than 4.");
				i--;
			}
			
			//if ship is placed closer than one grid from another ship
			
			else {
				grid[x][y] = " X ";
			}
			
		}
	}
}