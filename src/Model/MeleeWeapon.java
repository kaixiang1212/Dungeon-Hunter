package Model;

public abstract class MeleeWeapon extends Item {

	int numUses;
	
	public MeleeWeapon(int numUses) {
		super();
		this.numUses = numUses;
	}

	public int getnumUses() {
		return this.numUses;
	}
}
