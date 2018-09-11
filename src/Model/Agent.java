package Model;

/**
 * Agent encompasses non-player entities within the dungeon
 * which have movement, health and interactions with other
 * entities.
 * @author Richard
 *
 */
public abstract class Agent {

	private int healthPoints;
	private MoveBehaviour moveBehaviour;

	public Agent(int healthPoints, MoveBehaviour moveBehaviour, Weapon weapon) {
		this.healthPoints = healthPoints;
		this.moveBehaviour = moveBehaviour;
	}
	public int getHealth() {
		return this.healthPoints;
	}
	public void attack(Player a) {
		a.damage(100);
	}
	public void damage(int damage) {
		this.healthPoints = this.healthPoints - damage;
		if(this.healthPoints <= 0) {
			this.die();
		}
	}
	public void die() {
		System.out.println("Enemy agent has died\n");
	}
	public void move() {
		
	}
}
