package Model;

//extends Weapon
public class Sword extends Weapon {

	public Sword() {
		super(100, 5);
	}
	
	@Override
	public boolean isSword() {
		return true;
	}
}
