package Model;

//Extends Potion which implements Item
public class Invincibility extends Potion{
	public Invincibility() {
		super("Invincibility", 20);
	}
	
	@Override 
	public boolean isInvinc() {
		return true;
	}
}
