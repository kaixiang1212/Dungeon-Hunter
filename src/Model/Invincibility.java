package Model;

public class Invincibility extends Potion{
	public Invincibility() {
		super(20);
	}
	
	public boolean equals(Object o) {
		if(o instanceof Invincibility) {
			return true;
		}
		return false;
	}

	@Override
	public void use(Dungeon map) {
		// TODO Auto-generated method stub
		
	}
}
