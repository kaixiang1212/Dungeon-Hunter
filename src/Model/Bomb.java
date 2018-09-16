package Model;

import java.awt.Point;

public class Bomb extends Item {
	
	public Bomb() {
		super();
	}
	
	@Override
	public boolean isStackable() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Bomb) {
			return true;
		}
		return false;
	}
	
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
