package itemDesign;

public class Item {
	
	public Item() {
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
