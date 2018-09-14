package itemDesign;

public abstract class Potion extends Item {
	String name;
	int time;
	
	public Potion(String name, int time) {
		this.name = name;
		this.time = time;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isHover() {
		return this instanceof Hover;
	}
	
	public boolean isInvinc() {
		return this instanceof Invincibility;
	}
}
