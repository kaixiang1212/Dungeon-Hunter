package itemDesign;

public abstract class Potion extends Item {
	int time;
	
	public Potion(int time, boolean stackable) {
		super(stackable);
		this.time = time;
		
	}
	
	public abstract void consume(Player p);
	
		public boolean isHover() {
		return this instanceof Hover;
	}
	
	public boolean isInvinc() {
		return this instanceof Invincibility;
	}

}
