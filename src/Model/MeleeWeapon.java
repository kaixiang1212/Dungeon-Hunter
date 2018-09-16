package Model;

import java.awt.Point;

public abstract class MeleeWeapon extends Item {

	int numUses;
	
	public MeleeWeapon(int numUses) {
		super();
		this.numUses = numUses;
	}

	public int getnumUses() {
		return this.numUses;
	}
	//Rethink the below code: Do you really need to grab the grid?
	public void use(Dungeon map) {
	    Player player = map.getPlayer();
	    Point playerPos = map.getPlayerPos();
	    map.removeAgent(playerPos);
	    this.numUses--;
	    if (this.numUses == 0) {
	    	player.getInventory().removeItem(this);
	    	player.removeHeld();
	    }
	}
	
	public boolean isStackable() {
		return false;
	}
}


