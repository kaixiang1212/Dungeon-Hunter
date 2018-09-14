package itemDesign;

public abstract class RangedWeapon extends Item {
	int damage;
	int numUses;
	
	public RangedWeapon(int damage, int numUses) {
		this.damage = damage;
		this.numUses = numUses;
	}
	
	public int getDamage() {
		return this.damage;
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

}
