package Model;

import java.awt.Point;

/**
 * Represents a bomb item which when lit by player
 * will kill everythin in a 3x3 radius from where it was lit
 * and dropped.
 *
 */
public class Bomb extends Item {
	
	public Bomb() {
		super();
	}
	
	/**
	 * Determines if bombs can be stacked in inventory
	 * @return Returns true
	 */
	@Override
	public boolean isStackable() {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * If quantity is above 0, minus quantity and place
	 * lit bomb item on player position it was used.
	 * Where quantity of bombs is 0, remove bomb from player inventory and hand.
	 * @param Dungeon map being played
	 */
	public void use(Dungeon map) {
		if (this.getQuantity() > 0) {
			this.subQuantity();
			Player player = map.getPlayer();
		    Point location = map.getPlayerPos();
			
		    map.placeItem(new LitBomb(location), location);
		    
			if (this.getQuantity() == 0) {
		    	player.getInventory().removeItem(this);
		    	player.removeHeld();
		    }
		}
	}
	

}
