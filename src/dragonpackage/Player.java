package dragonpackage;

import java.util.ArrayList;

public class Player {
	
	private String name;
	private int healthpoints;
	private int damageDone;
	private int currentX;
	private int currentY;	
	private boolean active = true;
	private Room currentRoom;
	private boolean playerHasPotion;
	private boolean playerHasSword;
	private boolean playerHasKey;
	private boolean playerHasTreasure;
	//en arraylist som h�ller spelarens f�rem�l
	private Item[] items = new Item[16];
	//konstruktor
	private Player(String name, boolean active, int healthpoints, int damageDone, Item[] items) {
		this.name = name;
		this.active = active;
		this.healthpoints = healthpoints;
		this.damageDone = damageDone;
		//initialisering
		this.items = items;
		this.currentX = 0;
		this.currentY = 0;
	}
	

	
	
	
	//metod f�r att spelaren sak kunna ta skada
	//k�rs i doBattle();
	public void playerTakeDmgDuringCombat(Monster monster) {
		this.healthpoints -= monster.getDmg();
		if(healthpoints <= 0) {
			dieAndLoseGame();
		}
	}
	
	//f�rlustmetod
	public void dieAndLoseGame() {
		active = false;
		System.out.println("You have been defeated!");
		System.out.println("Game over");

		
	}
	//vinstmetod ska k�ras n�r spelaren hittat skatten
	public void playerWinGame() {
		System.out.println("You are victorious!");
		System.out.println("You escape the dungeon with your riches.");
	       System.out.println(
	               "                  _.--.\n"+
	               "              _.-'_:-'||\n"+
	               "          _.-'_.-::::'||\n"+
	               "     _.-:'_.-::::::'  ||\n"+
	               "   .'`-.-:::::::'     ||\n"+
	               "  /.'`;|:::::::'      ||_\n"+
	               " ||   ||::::::'      _.;._'-._\n"+
	               " ||   ||:::::'   _.-!oo @.!-._'-.\n"+
	               " \'.  ||:::::.-!() oo @!()@.-'_.||\n"+
	               "   '.'-;|:.-'.&$@.& ()$%-'o.'\\U||\n"+
	               "     `>'-.!@%()@'@_%-'_.-o _.|'||\n"+
	               "      ||-._'-.@.-'_.-' _.-o  |'||\n"+
	               "      ||=[ '-._.-\\U/.-'    o |'||\n"+
	               "      || '-.]=|| |'|      o  |'||\n"+
	               "      ||      || |'|        _| ';\n"+
	               "      ||      || |'|    _.-'_.-'\n"+
	               "      |'-._   || |'|_.-'_.-'\n"+
	               "      '-._'-.|| |' `_.-'\n"+
	               "           '-.||_/.-'\n");
		System.exit(0);
	}
	
	//kollar om spelaren har potions i sitt inventory, och om denna har det
	//s� s�tts healthpoints till 10;
	public void playerHeal() {
		for(int i = 0; i < items.length; i++) {
			if(items[i].getName().contains("potion")) {
				System.out.println("Your health has been restored!");
				this.healthpoints = 10;
				items[i] = null;
				break;
			}else if(!items[i].getName().contains("potion")) {
				System.out.println("you dont have any potions.");
			}
			else
				continue;
		}
	}
	
	//inventorymetoder
	
	//visar inventorys inneh�ll
	public void showItemsInInventory() {
		String item;
		System.out.println("Your inventory contains:");
		//loopar igenom alla matriselement
		for(int i = 0; i<items.length; i++) {
			//om det inte finns ett f�rem�l p� index "i", s�tt det till empty slot
			if(items[i] == null) {
				continue;
			}else {
				//annars skriv ut f�rem�let p� index "i" i matrisen
				item = items[i].toString();
				if(items[i].getName().contains("potion")) {
					System.out.println(items[i].getName());
				}
				if(items[i].getName().contains("sword")) {
					System.out.println(items[i].getName());
				}
				if(items[i].getName().contains("key")) {
					System.out.println(items[i].getName());
				}
			}
			
		}
		
	}
	
	//metod f�r att s�tta in f�rem�l i spelarens inventory
	public void addItemToInventory(Item item) {
		for(int i = 0; i < items.length; i++) {
			//l�gger till item p� den f�rsta platsen loopen hittar som �r null
			if(items[i] == null) {
				if(currentRoom.isPotionInRoom() == true) {
				items[i] = item;
				System.out.println("you found a "+item.getName()+"!");
				break;				
				} else if(currentRoom.isSwordInRoom() == true){
					items[i] = item;
					System.out.println("you found a "+item.getName()+"!");
					break;
				} else if(currentRoom.isKeyInRoom() == true){
					items[i] = item;
					System.out.println("you found a "+item.getName()+"!");
					break;
					
				} else if(currentRoom.isTreasureInRoom() == true){
					items[i] = item;
					System.out.println("you found a "+item.getName()+"!");
					break;
					
				} else
					System.out.println("");
				break;
			}
		}
	}
	
	//kontrollerar om spelaren har ett f�rem�l av en specifik typ i sitt inventory
	public boolean playerHasPotionInInventory() {
		//g�r igenom spelarens f�rem�l
		for(int i = 0; i < items.length; i++) {
			//om spelaren har f�rem�l av typen item
			//s�tts playerHasPotion till true
			if(items[i].getName().contains("potion")) {
				playerHasPotion = true;
				break;
			}else
				//om inte spelaren har potion s�tt till false
				playerHasPotion = false;
		}
		return playerHasPotion;
	}
	//samma typ av metod som ovanst�ende
	public boolean playerHasSwordInInventory() {
		for(int i = 0; i < items.length; i++) {
			if(items[i].getName().contains("sword")) {
				playerHasSword = true;
				break;
			}else
				playerHasSword = false;
		}
		return playerHasSword;
	}
	//samma typ av metod som ovanst�ende
	public boolean playerHasKeyInInventory() {
		for(int i = 0; i < items.length; i++) {
			if(items[i].getName().contains("key")) {
				playerHasKey = true;
				break;
			}else
				playerHasKey = false;
		}
		return playerHasKey;
	}
	//samma typ av metod som ovanst�ende
	public boolean playerHasTreasureInInventory() {
		for(int i = 0; i < items.length; i++) {
			if(items[i].getName().contains("treasure")) {
				playerHasTreasure = true;
				break;
			}else
				playerHasTreasure = false;
		}
		return playerHasTreasure;
	}
	
	
	
	//en f�rdigkonstruerad spelare som anv�nds n�r spealren v�ljer new game.
	public static Player newPlayerAdventurer() {
		return new Player("adventurer", true, 10, 1, new Item[16]);
	}
	
	
	//getters och setters
	

	public int getCurrentX() {
		return currentX;
	}


	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}


	public int getCurrentY() {
		return currentY;
	}


	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public int getHealthpoints() {
		return healthpoints;
	}


	public void setHealthpoints(int healthpoints) {
		this.healthpoints = healthpoints;
	}


	public int getDamageDone() {
		return damageDone;
	}


	public void setDamageDone(int damageDone) {
		this.damageDone = damageDone;
	}



	public Room getCurrentRoom() {
		return currentRoom;
	}


	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	


}
