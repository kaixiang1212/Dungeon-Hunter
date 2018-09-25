package Model;
import java.awt.Point;

/*
 * Abstract class which contains use method for all ranged weapons
 */
public abstract class RangedWeapon extends Item {
	
	public RangedWeapon() {
		super();
	}

	
	/*
	 * Depending on direction, checks tiles to see if weapon will be blocked or hit and kill certain agents
	 * removed from inventory when player runs out
	 * @param map Dungeon that contains all entities
	 */
	public void use(Dungeon map) {
		if (this.getQuantity() > 0) {
			this.subQuantity();
			Player player = map.getPlayer();
		    Point location = map.getPlayerPos();

			switch (player.getDirection()) {
				case RIGHT:
					for (double newX = location.getX() + 1; newX < map.getTileGrid().size(); newX++) {
						location.setLocation(newX, location.getY());
						if (!map.isValidMoveArrow(location)) {
							return;
						} else if (map.isAgentExist(location)) {
							map.removeAgent(location);
							break;
						}
					}
					break;
				case LEFT:
					for (double newX = location.getX() - 1; newX > 0; newX--) {
						location.setLocation(newX, location.getY());
						if (!map.isValidMoveArrow(location)) {
							return;
						} else if (map.isAgentExist(location)) {
							map.removeAgent(location);
							break;
						}
					}
					break;
				case UP:
					for (double newY = location.getY() - 1; newY > 0; newY--) {
						location.setLocation(location.getX(), newY);
						if (!map.isValidMoveArrow(location)) {
							return;
						} else if (map.isAgentExist(location)) {
							map.removeAgent(location);
							break;
						}
					}
					break;
				case DOWN:
					for (double newY = location.getY() + 1; newY < map.getTileGrid().size(); newY++) {
						location.setLocation(location.getX(), newY);
						if (!map.isValidMoveArrow(location)) {
							return;
						} else if (map.isAgentExist(location)) {
							map.removeAgent(location);
							break;
						}
					}
					break;
			}

			if (this.getQuantity() == 0) {
		    	player.getInventory().removeItem(this);
		    	player.removeHeld();
		    }
		}
	}
}
