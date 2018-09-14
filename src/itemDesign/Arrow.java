package itemDesign;

public class Arrow extends RangedWeapon{
	
	public Arrow() {
		super(100, 0, true);
	}
	
	@Override
	public boolean isArrow() {
		return true;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Arrow) {
			Arrow a = (Arrow) o;
			if(a.getDamage == this.damage) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	
}
