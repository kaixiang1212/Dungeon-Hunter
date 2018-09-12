package Model;

/**
 * At time of writing the intention of this class is to provide a
 * weapon when a Sword is not in existence. Motivation is to
 * provide a means of setting the weaponless state without relying
 * on a null value.
 */

public class Fist extends Weapon{
    public Fist() {
        super(0, 1);
    }
    
    @Override
    public boolean isFist() {
    	return true;
    }
}
