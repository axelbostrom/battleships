package battleships;

public class Constants {
	//VALUES
	public static int pshipSize = 5; // size of ships
	public static int pshipAmount = 2; //ship amount
	public static int gridSize = 10; //gridsize
	public static int emptySpace = 25; //for printEmpty()
	
	//SYMBOLS
	public static String HIT_SYM = "1"; 
	public static String MISS_SYM = "0";
	public static String WAT_SYM = "~";
	public static String SHIP_SYM = "X";
	
	//PRINT EMPTY
	public static void printEmpty() {
		for (int i = 0; i < Constants.emptySpace; i++) {
			System.out.println("\n");
		}
	}
}