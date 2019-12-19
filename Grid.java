package battleships;

public class Grid {
	private static final int size = 10;
	private static String waves = "~";
	
    public Grid() {

    }
    public void makeGrid() {
        String[][] grid = new String[size][size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                grid[i][j] = waves;
            }
        }
        
    }
}