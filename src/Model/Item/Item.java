package Model.Item;

import Model.Dungeon;
import Model.Player;
import Model.Paintable;

public abstract class Item extends Paintable {

	private int quantity;
	
	public Item() {
		this.quantity = 1;
	}
	
	public abstract void pickedUp(Player player);
	
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
		return isType(ItemType.Hover) || isType(ItemType.Invincibility);
	}
	public boolean isMeleeWeapon() {
		return isType(ItemType.Sword);
	}
	public boolean isLitBomb() {
		return isType(ItemType.LitBomb);
	}
	public boolean isTreasure() {
		return isType(ItemType.Treasure);
	}
	
	public abstract ItemType getType();
	
	public boolean isType(ItemType type) {
		return type.equals(getType());
	}
}


