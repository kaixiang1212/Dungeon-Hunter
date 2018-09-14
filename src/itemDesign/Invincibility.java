package itemDesign;

public class Invincibility extends Potion{
	public Invincibility() {
		super("Invincibility", 20);
	}
	
	public boolean equals(Object o) {
		if(o instanceof Invincibility) {
			Invincibility a = (Invincibility) o;
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
