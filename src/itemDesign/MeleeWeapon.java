package itemDesign;

public abstract class MeleeWeapon extends Item {
	int damage;
	int numUses;
	
	public MeleeWeapon(int damage, int numUses) {
		this.damage = damage;
		this.numUses = numUses;
	}
	
	public int getDamage() {
		return this.damage;
	}
	public int getNumUses() {
		return this.numUses;
	}
	
	public boolean isFist() {
		return this instanceof Fist;
	}
	
	public boolean isSword() {
		return this instanceof Sword;
	}
	
	public void attack(ComputerAgent a) {
		a.takeDamage(this.damage);
		numUses--;
	}
}
