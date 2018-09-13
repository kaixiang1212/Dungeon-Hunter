package itemDesign;

public class Treasure extends Item {
	int amount;
	
	public Treasure(int amount) {
		this.amount = amount;
	}
	
	@Override
	public boolean isTreasure() {
		return true;
	}
}
