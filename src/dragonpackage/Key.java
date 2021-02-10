package dragonpackage;

public class Key extends Item{
	protected String name = "key";
	
	@Override
	public void use() {
		// TODO Auto-generated method stub
		
	}

	public static Item newKey() {
		return new Key();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
