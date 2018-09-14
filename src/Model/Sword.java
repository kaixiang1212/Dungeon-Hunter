package Model;

public class Sword extends MeleeWeapon {

	public Sword() {
		super(5);
	}

	@Override
	public void use(Dungeon map) {
		this.numUses--;
	}

	@Override
	public boolean isStackable() {
		return false;
	}
}

