package Model;

public abstract class Weapon {
	int damage;
	int numUses;
	
	public Weapon(int damage, int numUses) {
		this.damage = damage;
		this.numUses = numUses;
	}

	public int getDamage() {
		return this.damage;
	}


	public int getnumUses() {
		return this.numUses;
	}

	// Agent takes damage from this weapon
	public void attack(ComputerAgent a) {
		a.takeDamage(this.damage);
		numUses--;
	}

}
