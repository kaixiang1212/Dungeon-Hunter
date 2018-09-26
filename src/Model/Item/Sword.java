package Model.Item;

public class Sword extends MeleeWeapon {

	public Sword() {
		super(5);
	}

	@Override
	public boolean isStackable() {
		return false;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Sword) {
			return true;
		}
		return false;
	}
}

