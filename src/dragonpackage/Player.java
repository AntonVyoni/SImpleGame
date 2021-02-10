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
	//en arraylist som håller spelarens föremål
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
	

	
	
	
	//metod för att spelaren sak kunna ta skada
	//körs i doBattle();
	public void playerTakeDmgDuringCombat(Monster monster) {
		this.healthpoints -= monster.getDmg();
		if(healthpoints <= 0) {
			dieAndLoseGame();
		}
	}
	
	//förlustmetod
	public void dieAndLoseGame() {
		active = false;
		System.out.println("You have been defeated!");
		System.out.println("Game over");

		
	}
	//vinstmetod ska köras när spelaren hittat skatten
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
	//så sätts healthpoints till 10;
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
	
	//visar inventorys innehåll
	public void showItemsInInventory() {
		String item;
		System.out.println("Your inventory contains:");
		//loopar igenom alla matriselement
		for(int i = 0; i<items.length; i++) {
			//om det inte finns ett föremål på index "i", sätt det till empty slot
			if(items[i] == null) {
				continue;
			}else {
				//annars skriv ut föremålet på index "i" i matrisen
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
	
	//metod för att sätta in föremål i spelarens inventory
	public void addItemToInventory(Item item) {
		for(int i = 0; i < items.length; i++) {
			//lägger till item på den första platsen loopen hittar som är null
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
	
	//kontrollerar om spelaren har ett föremål av en specifik typ i sitt inventory
	public boolean playerHasPotionInInventory() {
		//går igenom spelarens föremål
		for(int i = 0; i < items.length; i++) {
			//om spelaren har föremål av typen item
			//sätts playerHasPotion till true
			if(items[i].getName().contains("potion")) {
				playerHasPotion = true;
				break;
			}else
				//om inte spelaren har potion sätt till false
				playerHasPotion = false;
		}
		return playerHasPotion;
	}
	//samma typ av metod som ovanstående
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
	//samma typ av metod som ovanstående
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
	//samma typ av metod som ovanstående
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
	
	
	
	//en färdigkonstruerad spelare som används när spealren väljer new game.
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
