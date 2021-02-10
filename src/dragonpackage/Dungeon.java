package dragonpackage;


public final class Dungeon {
	private static int roomX = 5; 
	private static int roomY = 5;
	
	private static boolean north = false;
	private static boolean east = false;
	private static boolean west = false;
	private static boolean south = false;
	
	//slumpad dungeon baserad p� de olika typerna rum
	//dessa slumpas i Room-klassen
	public static Room[][] newRandomDungeon(Player player){
        Room[][] dungeon = new Room[5][5];
        for (int i = 0; i < roomX; i++) {
            for (int j = 0; j < roomY; j++) {
                dungeon[i][j] = Room.randomRoom();   
            }
        }       
        //l�mnar ut en f�rdigkonstruerad dungeon
        player.setCurrentRoom(dungeon[0][0]);
        //fasta rumsv�rden f�r viktiga rum        
        dungeon[4][2] = Room.dragonChamber();
        dungeon[2][3] = Room.keyRoom();
        dungeon[1][0] = Room.swordRoom();
        System.out.println("You enter a dark dungeon that is eerily quiet.");
        movePlayer(player); 
        return dungeon;
	}

	
	public boolean roomExist(int x, int y) {
		return rowExist(x) && columnExist(y);
	}
	
	public boolean rowExist(int x) {
		return (x >= 0) && (x <=4);
	}
	
	public boolean columnExist(int y) {
		return (y >= 0) && (y <=4);
	}
	
	//en metod f�r grundlogiken i v�derstrecken
	public void playerMovement(Player player) {
		north = roomExist(player.getCurrentX(), player.getCurrentY() + 1);		
		east = roomExist(player.getCurrentX() + 1, player.getCurrentY());
		west = roomExist(player.getCurrentX() - 1, player.getCurrentY());
		south = roomExist(player.getCurrentX(), player.getCurrentY() - 1);
		movePlayer(player);		
	}
	
	//om spelaren hittar ett f�rem�l i ett rum s� l�ggs det in i inventory
	public void playerFindItem(Player player,Room[][] dungeon) {
		
		//kontrollerar om villkoret �r sant i rummet d�r spelaren f�r n�rvarande st�r.
		if(dungeon[player.getCurrentX()][player.getCurrentY()].isPotionInRoom() == true) {
			//l�gger til potion i inventory
			player.addItemToInventory(new Potion());
			dungeon[player.getCurrentX()][player.getCurrentY()].setPotionInRoom(false);
		}
		if(dungeon[player.getCurrentX()][player.getCurrentY()].isSwordInRoom() == true) {
			player.addItemToInventory(new Sword());
			dungeon[player.getCurrentX()][player.getCurrentY()].setSwordInRoom(false);
			player.setDamageDone(2);
			System.out.println("Your damage done is set to 2.");
		}
		if(dungeon[player.getCurrentX()][player.getCurrentY()].isKeyInRoom() == true) {
			player.addItemToInventory(new Key());
			dungeon[player.getCurrentX()][player.getCurrentY()].setKeyInRoom(false);
		}
		if(dungeon[player.getCurrentX()][player.getCurrentY()].isTreasureInRoom() == true) {
			player.addItemToInventory(new Treasure());
			dungeon[player.getCurrentX()][player.getCurrentY()].setTreasureInRoom(false);
		}
	}
	
		//anv�nds f�r att �pnna d�rrar, s� om spelaren
		//st�r p� en kordinat d�r d�rr finns, s� ska metoden k�ra
		//ej korrekt implementerad
	public void openDoor(Player player, Room[][] dungeon) {
		if(dungeon[player.getCurrentX()][player.getCurrentY()].isDoorInRoom() == true) {
			Door door = new Door('1', true);
			if(player.playerHasKeyInInventory()){
				door.setPosition('0');
				door.setLocked(false);
				narrative(player, dungeon);
				doBattle(player, dungeon[player.getCurrentX()][player.getCurrentY()].getMonster());
				if(dungeon[player.getCurrentX()][player.getCurrentY()].getMonster().isActive() == false) {
					player.playerWinGame();
				}else
					player.dieAndLoseGame();

			} else if(!player.playerHasKeyInInventory()) {
				System.out.println("You need to find the key to enter");
				player.setCurrentX(player.getCurrentX() - 1);
				player.setCurrentY(player.getCurrentY() - 1);
			}					
		}	
	}
		
	//visar vilka kordinater spelaren st�r p� och h�mtar beskrivningen f�r rummet
	public void narrative(Player player, Room[][] dungeon) {
		if(dungeon[player.getCurrentX()][player.getCurrentY()] == dungeon[4][2]) {
			System.out.println(
			        "                                                  .~))>>\n"+
			        "                                                 .~)>>\n"+
			        "                                               .~))))>>>\n"+
			        "                                             .~))>>             ___\n"+
			        "                                           .~))>>)))>>      .-~))>>\n"+
			        "                                         .~)))))>>       .-~))>>)>\n"+
			        "                                       .~)))>>))))>>  .-~)>>)>\n"+
			        "                   )                 .~))>>))))>>  .-~)))))>>)>\n"+
			        "                ( )@@*)             //)>))))))  .-~))))>>)>\n"+
			        "              ).@(@@               //))>>))) .-~))>>)))))>>)>\n"+
			        "            (( @.@).              //))))) .-~)>>)))))>>)>\n"+
			        "          ))  )@@*.@@ )          //)>))) //))))))>>))))>>)>\n"+
			        "       ((  ((@@@.@@             |/))))) //)))))>>)))>>)>\n"+
			        "      )) @@*. )@@ )   (\\_(\\-\\b  |))>)) //)))>>)))))))>>)>\n"+
			        "    (( @@@(.@(@ .    _/`-`  ~|b |>))) //)>>)))))))>>)>\n"+
			        "     )* @@@ )@*     (@)  (@) /\\b|))) //))))))>>))))>>\n"+
			        "   (( @. )@( @ .   _/  /    /  \\b)) //))>>)))))>>>_._\n"+
			        "    )@@ (@@*)@@.  (6///6)- / ^  \\b)//))))))>>)))>>   ~~-.\n"+
			        " ( @jgs@@. @@@.*@_ VvvvvV//  ^  \\b/)>>))))>>      _.     `bb\n"+
			        " ((@@ @@@*.(@@ . - | o |' \\ (  ^   \\b)))>>        .'       b`,\n"+
			        "   ((@@).*@@ )@ )   \\^^^/  ((   ^  ~)_        \\  /           b `,\n"+
			        "     (@@. (@@ ).     `-'   (((   ^    `\\ \\ \\ \\ \\|             b  `.\n"+
			        "       (*.@*              / ((((        \\| | |  \\       .       b `.\n"+
			        "                         / / (((((  \\    \\ /  _.-~\\     Y,      b  ;\n"+
			        "                        / / / (((((( \\    \\.-~   _.`\" _.-~`,    b  ;\n"+
			        "                       /   /   `(((((()    )    (((((~      `,  b  ;\n"+
			        "                     _/  _/      `\"\"\"/   /'                  ; b   ;\n"+
			        "                 _.-~_.-~           /  /'                _.'~bb _.'\n"+
			        "               ((((~~              / /'              _.'~bb.--~\n"+
			        "                                  ((((          __.-~bb.-~\n"+
			        "                                              .'  b .~~\n"+
			        "                                              :bb ,' \n"+
			        "                                              ~~~~\n"); 
			System.out.println(dungeon[player.getCurrentX()][player.getCurrentY()].getDesc());
		}else {
		System.out.print("[Y " + player.getCurrentY() + "]");
		System.out.print(":");
		System.out.println("[X " + player.getCurrentX() + "]");
		System.out.println(dungeon[player.getCurrentX()][player.getCurrentY()].getDesc());
		}
	}	
	
	public void printDragon(Player player, Room[][] dungeon) {

	}
	
	
		
	//flyttar spelaren och kollar s� att man inte g�r utanf�r spelets gr�nser
	public static void movePlayer(Player player) {		
		System.out.println("Which direction would you like to travel?");
		System.out.println("North (n), east (e), west (w) or south (s)");
		String choice = DungeonMaster.input.nextLine();		
		//om spelaren v�ljer ett av valen s�tts den kordinaten till nuvarande position plus 
		//eller minus ett.
			if(choice.equals("n") || choice.equals("N") && Dungeon.isNorth()) {
				player.setCurrentY(player.getCurrentY() + 1);
				System.out.println("North");
			}
			else if(choice.equals("e") || choice.equals("E") && Dungeon.isEast()) {
				player.setCurrentX(player.getCurrentX() + 1);
				System.out.println("East");
			}

			else if(choice.equals("w") || choice.equals("W") && Dungeon.isWest()) {
				player.setCurrentX(player.getCurrentX() - 1);
				System.out.println("West");
			}

			else if(choice.equals("s") || choice.equals("S") && Dungeon.isSouth()) {
				player.setCurrentY(player.getCurrentY() - 1);
				System.out.println("South");
			}
			else
				System.out.println("please pick one of the directions.");
								
			//kontrollsats s� spelaren inte kan g� genom v�ggarna
			//om spelaren f�rs�ker g� igenom en v�gg s�tts positionen
			//till f�reg�ende position
			if(player.getCurrentX() < 0) {
				player.setCurrentX(player.getCurrentX() + 1);
				System.out.println("You walked into a wall.");
			}
			else if(player.getCurrentX() > roomX - 1) {
				player.setCurrentX(player.getCurrentX() - 1);
				System.out.println("You walked into a wall.");
			}
			else if(player.getCurrentY() < 0) {
				player.setCurrentY(player.getCurrentY() + 1);
				System.out.println("You walked into a wall.");
			}
			else if(player.getCurrentY() > roomY - 1) {
				player.setCurrentY(player.getCurrentY() - 1);
				System.out.println("You walked into a wall.");
			}
	}
	
	//stridsmodul
	public static void doBattle(Player player, Monster monster) {		
		String choice;
		//medan b�de spelare och monster lever
		System.out.println("A " + monster.getName() + " jumps out of the shadows and prepares to attack!");
		while(player.isActive() && monster.isActive()) {						
			//meddelar spelarens h�lsopo�ng och monstrets h�lsopo�ng
			System.out.println(monster.getName() + "s HP: " + monster.getHealthPoints());
			System.out.println("Your HP: " + player.getHealthpoints());
			System.out.println("");
			//fr�gar vad spelaren vill g�ra
			System.out.println("What do you want to do?");
			System.out.println("1. attack 2.drink healing potion 3. show inventory");
			choice = DungeonMaster.input.nextLine();
			//om spelaren skriver in 1
			if(choice.equals("1")) {
			monster.takeDmg(player);
			System.out.println("You hit the monster for " + player.getDamageDone() + " damage");
					player.playerTakeDmgDuringCombat(monster);
					System.out.println("the monster counterattacks for " + monster.getDmg() + " damage");				
			}
			//
			else if (choice.equals("2")) {
					player.playerHeal();
				}
			else if(choice.equals("3")) {
					player.showItemsInInventory();
			}

			}			
			if(monster.getHealthPoints() <= 0) {
				monster.die();
				System.out.println("You defeat the " + monster.getName() + "!");
			}
			else
				System.out.println("please choose a correct option\n");
			
		}		
	
	
	//metod som s�tter spelare i combat om det finns monster i rummet
	//annars kan spelaren forts�tta fram�t.
	//spelar ocks� upp beskrivningen f�r rummet och vilka kordninater spelaren �r p�
	public void logic(Player player, Room[][] dungeon) {
		while(player.isActive()) {
			if(player.isActive() && dungeon[player.getCurrentX()][player.getCurrentY()].getMonster().isActive()) {
				narrative(player, dungeon);
				doBattle(player, dungeon[player.getCurrentX()][player.getCurrentY()].getMonster());
				openDoor(player, dungeon);
				playerFindItem(player, dungeon);
			}
			else if(player.isActive()) {
				narrative(player, dungeon);
				playerMovement(player);
				openDoor(player, dungeon);
				playerFindItem(player, dungeon);
			}
		}
	}

	//getters och setters
	public static boolean isNorth() {
		return north;
	}

	public static void setNorth(boolean north) {
		Dungeon.north = north;
	}

	public static boolean isEast() {
		return east;
	}

	public static void setEast(boolean east) {
		Dungeon.east = east;
	}

	public static boolean isWest() {
		return west;
	}

	public static void setWest(boolean west) {
		Dungeon.west = west;
	}

	public static boolean isSouth() {
		return south;
	}

	public static void setSouth(boolean south) {
		Dungeon.south = south;
	}
	
	
}
