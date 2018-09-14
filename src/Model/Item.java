package itemDesign;

public class Item {
	
	private boolean stackable;
	public Item(boolean stackable) {
		this.stackable = stackable;
	}
	
	public void use(Dungeon dungeon) {
		return;
	}
	
	public boolean isStackable() {
		return this.stackable;
	}

	public boolean isMeleeWeapon() {
		return this instanceof MeleeWeapon;
	}
	
	public boolean isRangedWeapon() {
		return this instanceof RangedWeapon;
	}
	
	public boolean isPotion() {
		return this instanceof Potion;
	}
	
	public boolean isTreasure() {
		return this instanceof Treasure;
	}

}