package Model;

public class Arrow extends RangedWeapon{
	
	public Arrow() {
		super();
	}
	
	public boolean equals(Object o) {
		if(o instanceof Arrow) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isStackable() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
