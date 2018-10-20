package Model.Item;

import Model.Dungeon;
import Model.Player;
import javafx.scene.image.Image;

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
	
	public void pickedUp(Player player) {
		player.getInventory().storeItem(this);
	}
	
	/**
	 * Determines if bombs can be stacked in inventory
	 * @return Returns true
	 */
	@Override
	public boolean isStackable() {
		return true;
	}
	

	public boolean equals(Object o) {
		if (o instanceof Bomb) {
			return true;
		}
		return false;
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
			Point location = new Point();
		    location.setLocation(map.getPlayerPos().getX(), map.getPlayerPos().getY());
			
		    map.placeItem(new LitBomb(location), location);
		    
			if (this.getQuantity() == 0) {
		    	player.getInventory().removeItem(this);
		    	player.removeHeld();
		    }
		}
	}
	public String toString() {
		return "Bomb";
	}

//	@Override
//	public ItemType getType() {
//		return ItemType.Bomb;
//	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("assets/itemassets/unlitbomb.png");
	}

	

}
