package Model;

public abstract class Potion extends Item {

	private int duration;
	
	public Potion(int duration) {
		super();
		this.duration = duration;
	}

	@Override
	public boolean isStackable() {
		return false;
	}
	

}
>>>>>>> Model-agent
