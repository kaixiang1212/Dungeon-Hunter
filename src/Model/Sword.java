package Model;

public class Sword extends MeleeWeapon {

	public Sword() {
		super(5);
	}

	@Override
	public boolean isStackable() {
		return false;
	}
}

