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
	//Rethink the below code: Do you really need to grab the grid?
	//You can make API methods to simply check if there exists an agent
	//The grid guarantees everything stored at a valid key is agent!
	
	/*public void use(Dungeon dungeon) {
		Player player = dungeon.getPlayer();
		Point playerPos = dungeon.getPlayerPos();
		Map<Point, ComputerAgent> agentMap = dungeon.getAgentGrid();
		if (player.getDirection().equals("Right")) {
			Point check = new Point(playerPos.x + 1, playerPos.y);
			if (agentMap.get(check) instanceof ComputerAgent) {
				//agentMap.get(check).takeDamage(this.damage);
				//TODO: revamp combat with no healthpoints
			}
		}
		numUses--;
	}*/
	
	public boolean isStackable() {
		return false;
	}
}


