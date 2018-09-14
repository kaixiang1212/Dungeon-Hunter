package Model;

public class Hover extends Potion{
	public Hover() {
		super(999);
	}
	
	@Override
	public boolean isHover() {
		return true;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Hover) {

			return true;
		}
		return false;
	}

	@Override
	public void use(Dungeon map) {
		// TODO Auto-generated method stub
		
	}
}