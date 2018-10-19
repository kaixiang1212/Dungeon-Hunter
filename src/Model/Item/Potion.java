package Model.Item;

import Model.Player;

/*
 * abstract class that contains information of all potions
 */
public abstract class Potion extends Item {

	private int duration;
	
	public Potion(int duration) {
		super();
		this.duration = duration;
	}
	
	public void pickedUp(Player player) {
		player.addStatus(this);
	}
	
	public int getDuration() {
		return this.duration;
	}
	
	public void reduceDuration() {
		this.duration--;
	}

	/*
	 * determines if potions are stackable
	 * @return false
	 */
	@Override
	public boolean isStackable() {
		return false;
	}
	
	/*
	 * determines if potion is Hover
	 * @return True/False
	 */
	public boolean isHover() {
		return this instanceof Hover;
	}
	
	/*
	 * determines if potion is Invincibility
	 * @return True/False
	 */
	public boolean isInvinc() {
		return this instanceof Invincibility;

	}
}