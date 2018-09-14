package itemDesign;

public class Arrow extends RangedWeapon{
	
	public Arrow() {
		super(true,100,1);
	}
	
	public boolean equals(Object o) {
		if(o instanceof Arrow) {
			Arrow a = (Arrow) o;
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
