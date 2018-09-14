package itemDesign;

public abstract class Potion extends Item {
	int time;
	
	public Potion(int time, boolean stackable) {
		super(stackable);
		this.time = time;
		
	}
	
	public abstract void consume(Player p);
	
	
	

}
