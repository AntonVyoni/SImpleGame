package dragonpackage;

public class Sword extends Item {
		
	protected String name;
	public Sword() {
		name = "sword";
	}
	@Override
	public void use() {	
		player.setDamageDone(2);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
