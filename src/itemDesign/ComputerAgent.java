package itemDesign;

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

	public ComputerAgent(int healthPoints, MoveBehaviour moveBehaviour) {
		this.healthPoints = healthPoints;
		this.moveBehaviour = moveBehaviour;
	}
	public int getHealth() {
		return this.healthPoints;
	}
	public void attack(Player a) {
		if (a.isInvinc()) {
			this.takeDamage(this.healthPoints);
		} else {
			a.takeDamage(100);
		}
	}
	public void takeDamage(int damage) {
		this.healthPoints = this.healthPoints - damage;
		if(this.healthPoints <= 0) {
			this.die();
		}
	}
	public void die() {
		System.out.println("Enemy agent has died\n");
	}
	public void move(Point playerPos, Map<Point, ComputerAgent> agentPos) {
		moveBehaviour.move(playerPos, agentPos);
	}
}