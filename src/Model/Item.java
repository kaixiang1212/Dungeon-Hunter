package Model;

public abstract class Item {

	private int quantity;
	
	public Item() {
		this.quantity = 1;
	}
	
	public abstract void use(Dungeon map);
	
	public abstract boolean isStackable();
	
	public void stack() {
		this.quantity++;
	}
	
}


