package battleships;

import java.awt.*;
import javax.swing.*;

public class mapGui {
	
	public static void main(String[] args) {
		bsGUI();
	}
	public static int numRows = 10;
	public static int numCols = 10;
	public static char t;
	public static int p1Ships; //Player one ships
	public static int p2Ships; //Player two ships
	public static int c1Ships; //Computers ships
	public static Color col1 = Color.blue;
	public static Color col2 = Color.red;
	public static JButton[][] gridButton = new JButton[numRows][numCols];
	public static final int MOUSE_CLICKED = 1;
			
	public static void bsGUI() {
		JFrame frame = new JFrame();
		frame.setTitle("Battle ships 1337");
		frame.setSize(500, 500);
		Container panel = frame.getContentPane();
		panel.setLayout(new GridLayout(numRows, numCols, 5, 5));
		
		//Colour
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				gridButton[i][j] = new JButton();
				gridButton[i][j].setBackground(col1);
				panel.add(gridButton[i][j]);
			}
		}
		// Showing the Board
		frame.setVisible(true);
		}
	}