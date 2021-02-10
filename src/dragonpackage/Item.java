package dragonpackage;

public abstract class Item {
	protected Player player;
	protected String name;
	
	//abstrakt metod som alla klasser som ärver från denna ska ha.
	public abstract void use();

	
	//getters och setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}



