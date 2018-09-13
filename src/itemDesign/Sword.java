package itemDesign;


public class Sword extends MeleeWeapon {

	public Sword() {
		super(100, 5);
	}
	
	@Override
	public boolean isSword() {
		return true;
	}
	
}
