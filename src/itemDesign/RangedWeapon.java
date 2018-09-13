package itemDesign;

public abstract class RangedWeapon extends Item {
	int damage;
	int numUses;
	
	public RangedWeapon(int damage, int numUses) {
		this.damage = damage;
		this.numUses = numUses;
	}
	
	@Override
	public boolean isRangedWeapon() {
		return true;
	}
	
	public int getUses() {
		return this.numUses;
	}
	
	public void addUses() {
		this.numUses++;
	}
	
	public void subUses() {
		this.numUses--;
	}
	
	public boolean isArrow() {
		return false;
	}
}
