package Model.Item;

import Model.Dungeon;
import Model.Item.Item;
import Model.Player;

import java.awt.Point;

/*
 * Abstract class which contains use method for all melee weapons
 */
public abstract class MeleeWeapon extends Item {

	int numUses;

	public MeleeWeapon(int numUses) {
		super();
		this.numUses = numUses;
	}
	
	public void pickedUp(Player player) {
		player.getInventory().storeItem(this);
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


