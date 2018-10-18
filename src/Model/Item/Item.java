package Model.Item;

import Model.Dungeon;
import Model.Paintable;

public abstract class Item extends Paintable {

	private int quantity;
	
	public Item() {
		this.quantity = 1;
	}
	
	public abstract void use(Dungeon map);
	
	public abstract boolean isStackable();
	
	public void stack() {
		this.quantity++;
	}
	public int getQuantity() {
		return this.quantity;
	}
	public void subQuantity() {
		this.quantity--;
	}
	public boolean isPotion() {
		return this instanceof Potion;
	}
	public boolean isMeleeWeapon() {
		return this instanceof MeleeWeapon;
	}
	public boolean isLitBomb() {
		return this instanceof LitBomb;
	}
	public boolean isTreasure() {
		return this instanceof Treasure;
	}
}


