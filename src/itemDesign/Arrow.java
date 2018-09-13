package itemDesign;

public class Arrow extends RangedWeapon{
	
	public Arrow() {
		super(100, 0);
	}
	
	@Override
	public boolean isArrow() {
		return true;
	}
	
}
