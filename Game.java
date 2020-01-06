package battleships;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
	public LinkedList<Player> players = new LinkedList<Player>();
	static Scanner scan = new Scanner(System.in);

	public void runGame(int playernbr) throws IOException {
		Utils.printEmpty();
		System.out.println("Welcome to Battle Ships!");
		System.out.println("========================");
		System.out.println();

		// Playernbr 1, launch 1 player and AI
		if (playernbr == 1) {
			newPlayer(playernbr);
			newComputerPlayer(2);
			AIplay();
		}
		// Playernbr 2, launch 2 players, no AI
		else if (playernbr == 2) {
			newPlayer(playernbr);
			play();
		}
	}

	public void newPlayer(int playernbr) {
		for (int i = 1; i <= playernbr; i++) {
			boolean inputCorrect = true;
			String name = null;
			int hitrate = 0;
			int damagerate = 0;
			int id = 0;
			int health = 0;
			Player player = new Player(name, health, id, hitrate, damagerate);
			players.add(player);
			System.out.println("Player " + i + ", please enter your name: ");
			name = scan.next();
			player.setName(name);
			player.setID(i);
			System.out.println();
			Utils.printEmpty();
			System.out.println("Welcome " + player.getName() + "!");
			System.out.println("Would you like to place your own ships?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			while (inputCorrect) {
				try {
					int choice = scan.nextInt();
					while (choice < 1 || choice > 2) {
						System.out.println("Incorrect input. Try again.");
						choice = scan.nextInt();
					}
					switch (choice) {
					case 1:
						System.out.println(player.getName() + " get ready to place your ships.");
						player.makeGrid(1);
						Utils.printEmpty();
						inputCorrect = false;
						break;
					case 2:
						System.out.println(player.getName() + " the computer is placing your ships...");
						player.makeGrid(2);
						System.out.println("Your grid with ships placed!");
						player.printPlayerGrid();
						Utils.pressAnyKeyToContinue();
						Utils.printEmpty();
						inputCorrect = false;
						break;
					}

				} catch (InputMismatchException e) {
					System.out.println("Incorrect input. Only use numbers. Try again.");
					System.out.println();
					scan.nextLine();
				}
			}
		}
		System.out.println();
	}

	public void newComputerPlayer(int playernbr) {
		String name = "Mr Robot";
		int hitrate = 0;
		int damagerate = 0;
		int health = 0;
		Player player = new Player(name, health, playernbr, hitrate, damagerate);
		players.add(player);
		player.makeGrid(2);

	}

	public void play() throws IOException {
		int i = 0;
		int j = 1;
		Player player = null;
		Menu menu = new Menu();
		while (players.get(0).getHealth() > 0 && players.get(1).getHealth() > 0) {
			players.get(i).turnToPlay(players.get(j));
			players.get(j).turnToPlay(players.get(i));
			player = (players.get(0).getHealth() < players.get(1).getHealth()) ? players.get(1) : players.get(0);
		}
		System.out.println(player.getName() + " you have sunk all ships, you win!");
		if (player.getHitrate() >= menu.checkHighscoreList((int) player.getHitrate())) {
			System.out.println("Congratulations! You made the highscore list!");
			menu.saving((int) player.getHitrate(), player.getShots(), player.getName());
		}
		System.out.println("Returning to main menu.");
		System.out.println();
	}

	public void AIplay() {
		int i = 0;
		int j = 1;
		Player player = null;
		while (players.get(0).getHealth() > 0 && players.get(1).getHealth() > 0) {
			players.get(i).turnToPlay(players.get(j));
			players.get(j).ComputerTurnToPlay(players.get(i));
			player = (players.get(0).getHealth() < players.get(1).getHealth()) ? players.get(1) : players.get(0);
		}
		System.out.println(player.getName() + " you have sunk all ships, you win!");
		System.out.println("Returning to main menu.");
		System.out.println();
	}
}