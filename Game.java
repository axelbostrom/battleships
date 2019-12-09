package sänkaskepp;

public class Game {
	public static int numRows = 10;
	public static int numCols = 10;
	public static char t;
	public static String[][] grid = new String [numRows][numCols];
	
	public static void main(String[] args) {
		System.out.println("Welcome to Battle Ships game!");
		System.out.println();
		
		gridMap();
	}
	
	public static void runGame() {
		System.out.println("KÖR");
	}
	
	public static void gridMap() {
		System.out.print("  ");
		for(int i = 0; i < numRows; i++) {
			System.out.print(i);
		}
		System.out.println();
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = " ";
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
}