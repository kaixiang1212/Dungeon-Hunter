package itemDesign;

public class Hover extends Potion{
	public Hover() {
		super(false,"Hover", 999);
	}
	
	@Override
	public boolean isHover() {
		return true;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Hover) {
			Hover a = (Hover) o;
			if(a.getName() == this.name) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
}