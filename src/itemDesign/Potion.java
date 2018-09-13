package itemDesign;

public abstract class Potion extends Item {
	String name;
	int time;
	
	public Potion(String name, int time) {
		this.name = name;
		this.time = time;
	}
	
	@Override
	public boolean isPotion() {
		return true;
	}
	
	public boolean isInvinc() {
		return false;
	}
	
	public boolean isHover() {
		return false;
	}
	
	public String getName() {
		return this.name;
	}
}
