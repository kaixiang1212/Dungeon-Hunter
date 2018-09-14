package itemDesign;

public class Treasure extends Item {
	int amount;
	
	public Treasure(boolean stackable, int amount) {
		super(true);
		this.amount = amount;
	}
}
