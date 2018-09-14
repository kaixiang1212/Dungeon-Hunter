package itemDesign;

import java.awt.Point;
import java.util.Map;

public abstract class RangedWeapon extends Item {
	int damage;
	int numUses;
	boolean stackable;
	
	public RangedWeapon(boolean stackable, int damage, int numUses) {
		super(true);
		this.damage = damage;
		this.numUses = numUses;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public int getUses() {
		return this.numUses;
	}
	
	public void addUses() {
		this.numUses++;
	}
	
	public void subUses() {
		this.numUses--;
	}
	
	public void use(Dungeon dungeon) {
		if (this.getUses() > 0) {
			this.subUses();
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
						agentMap.get(shot).takeDamage(this.damage);
					}
				}
			}
		}
	}

}
