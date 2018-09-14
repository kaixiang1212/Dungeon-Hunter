package Model;

//Implements item
public abstract class Potion implements Item{
	String name;
	int time;
	
	public Potion(String name, int time) {
		this.name = name;
		this.time = time;
	}
	
	public void use(Player p) {
		p.addStatus(this);
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isPotion() {
		return true;
	}
	
	public boolean isWeapon() {
		return false;
	}
	
	public boolean isArrow() {
		return isWeapon();
	}
	
	public boolean isInvinc() {
		return false;
	}
	
	public boolean isHover() {
		return false;
	}
}
