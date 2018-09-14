package Model;
import java.awt.Point;
import java.util.Map;

public abstract class RangedWeapon extends Item {
	int damage;
	boolean stackable;
	
	public RangedWeapon() {
		super();
	}

	
	//TODO: Rethink the below, try use map API methods to achieve without needing
	//to know the implementation of the hashmaps
	public void use(Dungeon dungeon) {
		if (this.getQuantity() > 0) {
			this.subQuantity();
			Point shot = dungeon.getPlayerPos();
			Map<Point, ComputerAgent> agentMap = dungeon.getAgentGrid();
			Map<Point, Tile> tileMap = dungeon.getTileGrid();
			String direction = dungeon.getPlayer().getDirection();
			if (direction.equals("Right")) {
				for (double newX = shot.getX() + 1; newX < agentMap.size(); newX++) {
					System.out.println(newX);
					shot.setLocation(newX, shot.getY());
					if (tileMap.get(shot) instanceof Tile) {
						return;
					} else if (agentMap.get(shot) instanceof ComputerAgent) {
						//agentMap.get(shot).takeDamage(this.damage);
						//TODO: implement no healthpoint fighting, you
						//can modify takeDamage to make it more basic
					}
				}
			}
		}
	}

}
