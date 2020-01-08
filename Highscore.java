package battleships;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Highscore {
	private LinkedList<String> highscoreList = new LinkedList<String>();
	private LinkedList<String> temp = new LinkedList<String>();

	// Loads highscorelist
	public void highScoreList() throws IOException {
		InputStream highscore = new FileInputStream(
				"/home/axebo861/eclipse-workspace/Sänkaskepp/src/battleships/highscore.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(highscore));
		while (reader.ready()) {
			String line = reader.readLine();
			if (!line.equals("")) {
				highscoreList.add(line);
			}
		}
		reader.close();
	}

	// checks and compares highscore
	public int checkHighscoreList(int hitrate, int shots, int time) {
		int place = 1;
		for (String s : highscoreList) {
			String[] splitLine = s.split(":");
			String[] extractNumber = splitLine[2].split(",");
			String[] extractShots = splitLine[3].split(",");
			String[] extractTime = splitLine[4].split(",");
			int hitrate2 = Integer.parseInt(extractNumber[0].trim());
			int shots2 = Integer.parseInt(extractShots[0].trim());
			int time2 = Integer.parseInt(extractTime[0].trim());
			if (hitrate <= hitrate2) {
				if(hitrate == hitrate2) {
					if (shots < shots2) {
						if (time > time2) {
							return place;
						}
						place++;
					}
					place++;
				}
				return place;
			}
			place++;
		}
		return 0;
	}

	public void savingHighscore(int hitrate, int shots, String playerName, int playertime) throws IOException {
		highScoreList();
		int place = checkHighscoreList(hitrate, shots, playertime);
		int i = place + 1;
		for (String s : highscoreList) {
			if (place + 48 > s.charAt(0)) {
				temp.add(s);
				continue;
			}
			String[] splitLine = s.split(":");
			if (place + 48 == s.charAt(0)) {
				temp.add(place + ". Name: " + playerName + ", Hitrate (Percent): " + hitrate + ", Shots fired: " + shots
						+ ", Time(sec): " + playertime);
				temp.add(i + ". Name:" + splitLine[1] + ":" + splitLine[2] + ":" + splitLine[3] + ":" + splitLine[4]);
				continue;
			}
			i++;
			if (i == 10)
				break;
			temp.add(i + ". Name:" + splitLine[1] + ":" + splitLine[2] + ":" + splitLine[3] + ":" + splitLine[4]);
		}
		highscoreList.clear();
		highscoreList = temp;
		printHighscore();
		OutputStream save = new FileOutputStream(
				"/home/axebo861/eclipse-workspace/Sänkaskepp/src/battleships/highscore.txt", false);
		saveHighscore(save);
	}

	public void saveHighscore(OutputStream os) throws IOException {
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
		for (String s : highscoreList) {
			outputStreamWriter.append(s + "\n");
		}
		outputStreamWriter.close();
	}

	public void printHighscore() {
		System.out.println();
		System.out.println("============================ Highscore ============================ ");
		for (String s : highscoreList) {
			System.out.println(s);
		}
		System.out.println("=================================================================== ");
		System.out.println();
	}
}