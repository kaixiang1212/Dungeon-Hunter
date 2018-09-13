package itemDesign;

public abstract class MeleeWeapon extends Item {
	int damage;
	int numUses;
	
	public MeleeWeapon(int damage, int numUses) {
		this.damage = damage;
		this.numUses = numUses;
	}
	
	@Override
	public boolean isMeleeWeapon() {
		return true;
	}
	
	public boolean isSword() {
		return false;
	}
	
	public boolean isFist() {
		return false;
	}
	
	public int getNumUses() {
		return this.numUses;
	}
	
	public void attack(ComputerAgent a) {
		a.takeDamage(this.damage);
		numUses--;
	}
}
