package itemDesign;

public abstract class Weapon extends Item {
	int damage;
	int numUses;
	
	public Weapon(int damage, int numUses) {
		this.damage = damage;
		this.numUses = numUses;
	}
	
	@Override
	public boolean isWeapon() {
		return true;
	}
	
	public boolean isSword() {
		return false;
	}
	
	public boolean isArrow() {
		return false;
	}
}
