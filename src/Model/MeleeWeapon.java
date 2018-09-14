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
	public void use(Dungeon dungeon) {
		Player player = dungeon.getPlayer();
		Point playerPos = dungeon.getPlayerPos();
		Map<Point, ComputerAgent> agentMap = dungeon.getAgentGrid();
		if (player.getDirection().equals("Right")) {
			Point check = new Point(playerPos.x + 1, playerPos.y);
			if (agentMap.get(check) instanceof ComputerAgent) {
				agentMap.get(check).takeDamage(this.damage);
			}
		}
		numUses--;
	}
	public boolean isStackable() {
		return false;
	}
}


