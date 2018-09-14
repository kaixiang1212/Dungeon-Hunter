package Model;

//Anything that can be picked up
public abstract class Item {
	private boolean stackable;
	
	public boolean isPotion() { return this instanceof Potion};
	public boolean isMeleeWeapon() {return this instanceof MeleeWeapon};
	public boolean isRangedWeapon() {return this instanceof RangedWeapon};
	public boolean isStackable() { return this.stackable};
}
