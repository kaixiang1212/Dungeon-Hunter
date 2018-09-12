package Model;

//Implements Item
public class Treasure implements Item {
	private String name;
	
	public Treasure(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isPotion() {
		return false;
	}
	
	public boolean isWeapon() {
		return false;
	}
	
	public boolean isArrow() {
		return isWeapon();
	}
}
