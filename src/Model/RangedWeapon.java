package Model;
import java.awt.Point;
import java.util.Map;

public abstract class RangedWeapon extends Item {
	
	public RangedWeapon() {
		super();
	}

	
	//TODO: Rethink the below, try use map API methods to achieve without needing
	//to know the implementation of the hashmaps
	public void use(Dungeon map) {
		if (this.getQuantity() > 0) {
			this.subQuantity();
			Player player = map.getPlayer();
		    Point location = map.getPlayerPos();
			if (player.getDirection().equals("Right")) {
				for (double newX = location.getX() + 1; newX < map.getTileGrid().size(); newX++) {
					System.out.println(newX);
					location.setLocation(newX, location.getY());
					if (!map.isValidMove(location)) {
						return;
					} else if (map.isAgentExist(location)) {
						map.removeAgent(location);
						break;
					}
				}
			}
			if (this.getQuantity() == 0) {
		    	player.getInventory().removeItem(this);
		    	player.removeHeld();
		    }
		}
	}

}
