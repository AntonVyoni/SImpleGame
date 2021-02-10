package dragonpackage;

public class Treasure extends Item{
	protected String name;
	@Override
	public void use() {
		name = "treasure";
		
	}
	
	//getters och setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	


	

		

}
