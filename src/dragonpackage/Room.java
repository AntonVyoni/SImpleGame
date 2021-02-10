package dragonpackage;

import java.util.Random;

public class Room {
	private Door doors;
	private Item item;
	private Monster monster;
	private String desc;
	//boolean monsterInRoom;
	//boolean DragonChamber;
	private boolean swordInRoom;
	private boolean potionInRoom;
	private boolean keyInRoom;
	private boolean treasureInRoom;
	private boolean doorInRoom;
//konstruktor med boleanska värden som säger om ett av de olika föremålen finns i rummet
	private Room(String desc, Monster monster,boolean swordInRoom, boolean potionInRoom, boolean keyInRoom, boolean treasureInRoom, boolean doorInRoom) {
		this.desc = desc;
		this.monster = monster;
		this.swordInRoom = swordInRoom;
		this.potionInRoom = potionInRoom;
		this.keyInRoom = keyInRoom;
		this.treasureInRoom = treasureInRoom;
		this.doorInRoom = doorInRoom;				
	}	
	//slumpmässiga rum, innehåller potions
	public static Room randomRoom() {
		Random random = new Random();
		String desc = null;
		int randomResult = random.nextInt(4) + 1;
		
		switch(randomResult) {
		
		//här ska småmonster finnas där monsterInRoom = true
			case 1:
				desc = "A clay brick room with a broken prison cell.";
				
			break;
			case 2:
				desc = "An ominus hallway with close to no lighting.";
	
			break;
			case 3:
				desc = "A small corridor with walls that seem to get closer the further in you get.";
	
			break;
			case 4:
				desc = "A dark room with a wall that has caved in.";
	
				break;
		}
		return new Room(desc, Monster.randomMonster(),false,true,false,false,false);
	}
	
	//ett fast rum som alltid måste finnas
	public static Room dragonChamber() {
		String desc = "You unlock the door and enter..."+"\nA massive room smelling of sulfur. You hear something big breathing";
		Monster monster;
		monster = Monster.dragon();   
		return new Room(desc, monster,false,true,false,true,true);
		
	}
	//ett fast rum som alltid måste finnas
	//eftersom det har nyckeln till drakrummet
	public static Room keyRoom() {
		String desc = "A small dark room. You see something shining in the corner..." ;
		return new Room(desc,Monster.bunny(),false,false,true,false,false);
	}
	
	//rum med svärd i.
	public static Room swordRoom() {
		String desc = "A big dark room. You see something on the floor";
		return new Room(desc,Monster.bunny(),true,false,false,false,false);
	}

	
	
	//getters och setters
	@Override
	public String toString() {
		return desc;
	}
	public Monster getMonster() {
		return monster;
	}
	public void setMonster(Monster monster) {
		this.monster = monster;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Door getDoors() {
		return doors;
	}

	public void setDoors(Door doors) {
		this.doors = doors;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}


	public boolean isSwordInRoom() {
		return swordInRoom;
	}

	public void setSwordInRoom(boolean swordInRoom) {
		this.swordInRoom = swordInRoom;
	}

	public boolean isPotionInRoom() {
		return potionInRoom;
	}

	public void setPotionInRoom(boolean potionInRoom) {
		this.potionInRoom = potionInRoom;
	}

	public boolean isKeyInRoom() {
		return keyInRoom;
	}

	public void setKeyInRoom(boolean keyInRoom) {
		this.keyInRoom = keyInRoom;
	}

	public boolean isTreasureInRoom() {
		return treasureInRoom;
	}

	public void setTreasureInRoom(boolean treasureInRoom) {
		this.treasureInRoom = treasureInRoom;
	}

	public boolean isDoorInRoom() {
		return doorInRoom;
	}

	public void setDoorInRoom(boolean doorInRoom) {
		this.doorInRoom = doorInRoom;
	}

	
}
