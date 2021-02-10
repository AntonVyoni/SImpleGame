package dragonpackage;

import java.util.Random;
import java.util.Scanner;

public class DungeonMaster {
	public static Random random = new Random();
	public static Scanner input = new Scanner(System.in);
	public static Player currentPlayer;
	public static Room[][] currentDungeon;

	//huvudmetod
	public static void main(String[] args) {
		
		mainMenu();

	}
	
	//menyfunktion med väldigt grundläggande menyval
	public static void mainMenu() {
		boolean selection = false;
		
		while(!selection) {
			System.out.println("Welcome to Dragon Treasure!");
			System.out.println("Make your selection:");
			System.out.println("1. New game     2. Exit");
			String in = input.nextLine();
			switch(in) {
			case "1":
				//anropar new game och sätter selection till true
				selection = true;
				newGame();				
				break;
			case "2":	
				//avslutar programmet
				input.close();
				System.exit(0);
				
			default:
				//om inget av menyvalet slås in
				System.out.println("Please choose one of the alternatives:");
				mainMenu();
			}
		}
	}
	//startar new game med current player och current dungeon som indata
	public static void newGame() {
		Dungeon dungeon = new Dungeon();
		currentPlayer = Player.newPlayerAdventurer();
		currentDungeon = Dungeon.newRandomDungeon(currentPlayer);
		dungeon.logic(currentPlayer, currentDungeon);
	}
	
	
	
	
	
}
