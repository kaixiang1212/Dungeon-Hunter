package Model;

/**
 * Agent encompasses entities within the dungeon
 * which have movement, health and interactions with other
 * entities.
 * @author Richard
 *
 */
public abstract class Agent {

	private int healthPoints;
	private Weapon weapon;
	private MoveBehaviour moveBehaviour;
	
	public Agent(int healthPoints, MoveBehaviour moveBehaviour, Weapon weapon) {
		this.healthPoints = healthPoints;
		this.moveBehaviour = moveBehaviour;
	}
	public int getHealth() {
		return this.healthPoints;
	}
	public void attack(Agent a) {
		weapon.attack(a);
	}
	
	public abstract void move();
}
