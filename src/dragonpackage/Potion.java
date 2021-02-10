package dragonpackage;

public class Potion extends Item{
	protected String name;
	
	public Potion() {		
		name = "potion";
	}
	

	@Override
	public void use() {
		player.setHealthpoints(10);
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	

	

}
