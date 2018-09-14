package itemDesign;

public abstract class Potion extends Item {
	String name;
	int time;
	boolean stackable;
	
	public Potion(boolean Stackable, String name, int time) {
		super(false);
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
