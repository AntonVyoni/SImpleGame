package dragonpackage;

public class Door {
	private char position;
	private boolean locked;
	
	//konstruktor
	public Door(char position, boolean locked) {
		this.position = position;
		this.locked = locked;
	}
	
	//getters och setters
	public char getPosition() {
		return position;
	}
	public void setPosition(char position) {
		this.position = position;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
}
