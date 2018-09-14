package itemDesign;

import java.awt.Point;
import java.util.Map;

public abstract class MeleeWeapon extends Item {
	int damage;
	int numUses;
	boolean stackable;
	
	public MeleeWeapon(boolean stackable, int damage, int numUses) {
		super(false);
		this.damage = damage;
		this.numUses = numUses;
	}
	
	public int getDamage() {
		return this.damage;
	}
	public int getNumUses() {
		return this.numUses;
	}
	
	public boolean isFist() {
		return this instanceof Fist;
	}
	
	public boolean isSword() {
		return this instanceof Sword;
	}
	
	public void use(Dungeon dungeon) {
		Player player = dungeon.getPlayer();
		Point playerPos = dungeon.getPlayerPos();
		Map<Point, ComputerAgent> agentMap = dungeon.getAgentGrid();
		if (player.getDirection().equals("Right")) {
			Point check = new Point(playerPos.x + 1, playerPos.y);
			if (!agentMap.get(check).equals(null)) {
				agentMap.get(check).takeDamage(this.damage);
			}
		}
		numUses--;
	}
}
