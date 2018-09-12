package Model;

//Extends Potion which implements Item
public class Hover extends Potion{
	public Hover() {
		super("Hover", 999);
	}
	
	@Override
	public boolean isHover() {
		return true;
	}
}
