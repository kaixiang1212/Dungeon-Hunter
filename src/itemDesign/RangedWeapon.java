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
			Point shot = dungeon.getPlayer().getPlayerPos();
			Map<Point, ComputerAgent> agentMap = dungeon.getAgentMap();
			String direction = dungeon.getPlayer().getDirection();
			if (direction.equals("Right")) {
				for (double newX = shot.getX() + 1; newX < agentMap.size(); newX++) {
					shot.setLocation(newX, shot.getY());
					if (dungeon.isValidMove(shot)) {
						agentMap.put(shot, RangedWeapon);
						//Check for attacks
						//if attacks end this function and remove ArrowShot
						//else remove ArrowShot and loop again
					}
				
				}
			}
		}
	}

}
