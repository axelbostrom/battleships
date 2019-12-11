package battleships;

import java.awt.*;
import javax.swing.*;

public class mapGui {
	
	public static void main(String[] args) {
		
	}
	public static char t;
	public static Color col1 = Color.blue;
	public static Color col2 = Color.DARK_GRAY;
	public static Color col3 = Color.red;
	public static JButton[][] gridButton = new JButton[Constants.rowSize][Constants.colSize];
	public static JPanel border;
	public static final int MOUSE_CLICKED = 1;
			
	public static void bsGUI() {
		JFrame frame = new JFrame();
		frame.setTitle("Battle ships 1337");
		frame.setSize(500, 500);
		Container panel = frame.getContentPane();
		panel.setLayout(new GridLayout(Constants.rowSize, Constants.colSize, 5, 5));
        
		for (int i = 0; i < Constants.colSize; i++) {
			for (int j = 0; j < Constants.colSize; j++) {
				gridButton[i][j] = new JButton();
				gridButton[i][j].setBackground(col1);
				panel.add(gridButton[i][j]);
			}
		}
		// Showing the Board
		frame.setVisible(true);
		}
	}