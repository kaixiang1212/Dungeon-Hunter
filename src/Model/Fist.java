package itemDesign;

/**
 * At time of writing the intention of this class is to provide a
 * weapon when a Sword is not in existence. Motivation is to
 * provide a means of setting the weaponless state without relying
 * on a null value.
 */

public class Fist extends MeleeWeapon{
    public Fist() {
        super(0, 1);
    }
    
	public boolean equals(Object o) {
		if(o instanceof Fist) {
			Fist a = (Fist) o;
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
