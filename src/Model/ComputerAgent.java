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

	private int healthPoints;
	private MoveBehaviour moveBehaviour;
	private Point pos;

	public ComputerAgent(int healthPoints, MoveBehaviour moveBehaviour) {
		this.healthPoints = healthPoints;
		this.moveBehaviour = moveBehaviour;
	}
	public int getHealth() {
		return this.healthPoints;
	}
	public void attack(Player a) {
		a.takeDamage(100, this);
	}
	public void takeDamage(int damage) {
		this.healthPoints = this.healthPoints - damage;
		if(this.healthPoints <= 0) {
			System.out.println("Ded");
		}
	}
	public Point move(Dungeon map) {
		this.pos = moveBehaviour.move(map, pos);
		if(this.pos.equals(map.getPlayerPos())) {
			this.attack(map.getPlayer());
		}
		return this.pos;
	}
	public void setPos(Point newPos) {
		this.pos = newPos;
	}
	public Point getPos() {
		return this.pos;
	}
}
