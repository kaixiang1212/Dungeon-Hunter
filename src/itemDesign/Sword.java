package itemDesign;


public class Sword extends MeleeWeapon {

	public Sword() {
		super(false, 100, 5);
	}
	
	public boolean equals(Object o) {
		if(o instanceof Sword) {
			Sword a = (Sword) o;
			if(a.getDamage() == this.damage) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
	
}
