package Model.Tile;

public class Switch extends Tile {
	
	private boolean pressed;

	public Switch() {
		super(Type.SWITCH);
		pressed = false;
	}
	
	public void trigger() {
		pressed = true;
	}
	
	public void untrigger() {
		pressed = false;
	}
	
	public boolean isActivated() {
		return pressed;
	}

}
