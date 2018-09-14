package Model;

import java.awt.Point;
import java.util.Map;

/**
 * ComputerAgent encompasses non-player entities within the dungeon
 * which have movement, health and interactions with other
 * entities.
 * @author Richard
 *
 */
public abstract class ComputerAgent {

	private boolean isDead;
	private MoveBehaviour moveBehaviour;
	private Point pos;

	public ComputerAgent(int healthPoints, MoveBehaviour moveBehaviour) {
		this.moveBehaviour = moveBehaviour;
		this.isDead = false;
	}

	public Point move(Dungeon map) {
		this.pos = moveBehaviour.move(map, pos);
		return this.pos;
	}
	public void setPos(Point newPos) {
		this.pos = newPos;
	}
	public Point getPos() {
		return this.pos;
	}
}
