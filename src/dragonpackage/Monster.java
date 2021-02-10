package dragonpackage;

public class Monster {
	
	private String name;
	private int healthPoints;
	private int dmg;
	private boolean active = true;
	
	//konstruktor
	public Monster(String name, int healthPoints, int dmg, boolean active) {
		this.name = name;
		this.dmg = dmg;
		this.healthPoints = healthPoints;
		this.active = active;

	}

	//metod för att  inaktivera monstret
	public void die() {		
		active = false;		
	}
	
	//metod för att ta skada tar in ett spelarobjekt 
	//där spelarens skada används som parameter för
	//hur mycket som ska dras bort från monstrets
	//hälsa
	public void takeDmg(Player player) {
		this.healthPoints -= player.getDamageDone();
		if(healthPoints <= 0) {
			die();
		}
	}
	
	
	//getters och setters
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getDmg() {
		return dmg;
	}


	public void setDmg(int dmg) {
		this.dmg = dmg;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}


	public static Monster dragon() {
		return new Monster("dragon", 18, 2, true);
	}
	
	public static Monster goblin() {
		return new Monster("goblin", 8, 1,  true);
	}
	
	public static Monster undead() {
		return new Monster("undead", 8, 1,  true);
	}
	
	public static Monster zombiePirate() {
		return new Monster("zombie pirate", 8, 1, true);
	}
	
	public static Monster bunny() {
		return new Monster("wounded bunny",1,0,true);
	}
	
	//slumpar fram de vanliga monstertyperna
	public static Monster randomMonster() {
		int random = DungeonMaster.random.nextInt(3) + 1;
		Monster monster = null;
		
		switch(random) {
		case 1:
			monster = goblin();
			break;
		case 2:
			monster = undead();
			break;
		case 3:
			monster = zombiePirate();
			break;
		}
		return monster;
	}
	
	

}
