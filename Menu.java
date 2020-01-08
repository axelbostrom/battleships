package battleships;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu implements MenuItem {
	protected String title;
	private Scanner scan = new Scanner(System.in);
	private List<MenuItem> items;

	public Menu() {
	}

	public Menu(String title) {
		items = new ArrayList<>();
		this.title = title;
	}

	public void add(MenuItem item) {
		items.add(item);
	}

	public String getTitle() {
		return title;
	}

	public void execute() throws IOException {
		boolean inputCorrect = true;
		int counter = -1;
		MenuItem toExecute = null;
		System.out.println(getTitle());
		for (int i = 0; i < getTitle().length(); i++) {
			String n = ("=");
			System.out.print(n);
		}
		System.out.println();
		for (MenuItem item : items) {
			counter++;
			if (item.getTitle().equals(this.getTitle())) {
				toExecute = item;
			}
			System.out.println(counter + ": " + item.getTitle());
		}
		while (inputCorrect) {
			try {
				int a = scan.nextInt();
				toExecute = items.get(a);
				toExecute.execute();
				inputCorrect = false;
			} catch (InputMismatchException | IndexOutOfBoundsException e) {
				System.out.println("Incorrect input. Try again.");
				scan.nextLine();
			}
		}
	}

	public void launchMenu() throws IOException {
		Highscore list = new Highscore();
		list.highScoreList();
		Menu mainMenu = new Menu("Main Menu");
		Menu playGame = new Menu("Play");
		Menu scoreBoard = new Menu("Scoreboard");
		Menu returnMenu = new Menu("Return");
		Game game = new Game();
		System.out.println("Welcome to Battleships!");
		System.out.println();
		mainMenu.add(new AbstractMenuItem("Exit game") {
			public void execute() {
				Utils.printEmpty();
				System.exit(1);
			}
		});
		mainMenu.add(new AbstractMenuItem("Play") {
			public void execute() throws IOException {
				Utils.printEmpty();
				playGame.execute();
			}
		});
		mainMenu.add(new AbstractMenuItem("Scoreboard") {
			public void execute() throws IOException {
				Utils.printEmpty();
				scoreBoard.execute();
			}
		});
		playGame.add(new AbstractMenuItem("Main menu") {
			public void execute() throws IOException {
				Utils.printEmpty();
				mainMenu.execute();
			}
		});
		playGame.add(new AbstractMenuItem("Player vs computer") {
			public void execute() throws IOException {
				Utils.printEmpty();
				game.runGame(1);
				mainMenu.execute();
			}
		});
		playGame.add(new AbstractMenuItem("Player vs player") {
			public void execute() throws IOException {
				Utils.printEmpty();
				game.runGame(2);
				mainMenu.execute();
			}
		});
		scoreBoard.add(new AbstractMenuItem("Main menu") {
			public void execute() throws IOException {
				Utils.printEmpty();
				mainMenu.execute();
			}
		});
		scoreBoard.add(new AbstractMenuItem("Show highscore") {
			public void execute() throws IOException {
				Utils.printEmpty();
				list.printHighscore();
				returnMenu.execute();
			}
		});
		returnMenu.add(new AbstractMenuItem("Main menu") {
			public void execute() throws IOException {
				Utils.printEmpty();
				mainMenu.execute();
			}
		});
		mainMenu.execute();
	}
}
