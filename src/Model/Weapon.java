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
	public void attack(Agent a) {
		a.damage(this.damage);
		numUses--;
	}
	
}
