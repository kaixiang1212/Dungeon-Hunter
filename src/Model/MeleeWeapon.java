package Model;

import java.awt.Point;
import java.util.Map;

/*
 * Abstract class which contains use method for all melee weapons
 */
public abstract class MeleeWeapon extends Item {

	int numUses;
	
	public MeleeWeapon(int numUses) {
		super();
		this.numUses = numUses;
	}

	/*
	 * returns number of uses weapon has left
	 * @return numUses
	 */
	public int getnumUses() {
		return this.numUses;
	}
	
	/*
	 * Removes enemy that is on the same point player is, reduces number of uses, if uses is zero then it is removed form inventory
	 * @param map, Dungeon that holds all entities
	 */
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
	
	/*
	 * Determines if melee weapons can be stacked
	 * @return false
	 */
	public boolean isStackable() {
		return false;
	}
}


