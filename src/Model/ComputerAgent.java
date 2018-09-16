package Model;

import java.awt.Point;

/**
 * ComputerAgent encompasses non-player entities within the dungeon
 * which have movement, health and interactions with other
 * entities.
 * @author Richard
 *
 */
public abstract class ComputerAgent {

	private MoveBehaviour moveBehaviour;
	private Point pos;

	public ComputerAgent(MoveBehaviour moveBehaviour) {
		this.moveBehaviour = moveBehaviour;
	}

	public Point move(Dungeon map) {
		setPos(moveBehaviour.move(map, pos));
		return this.pos;
	}
	public void setPos(Point newPos) {
		this.pos = newPos;
	}
	public Point getPos() {
		return this.pos;
	}
	
}





