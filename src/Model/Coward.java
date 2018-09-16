package Model;

import java.awt.Point;

public class Coward extends ComputerAgent {

	private boolean courage;
	private MoveBehaviour cowardBehavior;
	
	public Coward() {
		super(new StandardChaseBehaviour());
		cowardBehavior = new cowardFleeBehavior();
		courage = false;
	}
	
	/**
	 * Return true if player is nearby (absolute value of axis <= 2)
	 * @param map Dungeon map
	 * @return true if player is close to coward
	 */
	private boolean determineCourage(Dungeon map) {
		Point player = map.getPlayerPos();
		Point self = super.getPos();
		if (Math.abs(player.x - self.x) > 2 || Math.abs(player.y - self.y) > 2) return true;
		return false;
	}

	@Override
	public Point move(Dungeon map) {
		courage = determineCourage(map);
		if (courage) super.move(map);
		else setPos(cowardBehavior.move(map, getPos()));
		return super.getPos();
	}

}
