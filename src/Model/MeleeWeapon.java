package Model;

import java.awt.Point;
import java.util.Map;

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
	//You can make API methods to simply check if there exists an agent
	//The grid guarantees everything stored at a valid key is agent!
	public void use(Dungeon map) {
	    Player player = map.getPlayer();
	    Point playerPos = map.getPlayerPos();
	    map.removeAgent(playerPos);
	    this.numUses--;
	    if (this.numUses == 0) {
	    	player.getInventory().removeItem(this);
	    }
	}
	
	public boolean isStackable() {
		return false;
	}
}


