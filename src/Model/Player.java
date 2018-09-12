package Model;
import java.util.ArrayList;

public class Player {

	private int healthPoints;
	private Weapon weapon;
	private ArrayList<Potion> status;
	
	public Player() {
		this.healthPoints = 100;
		this.weapon = new Fist();
	}
	
	/*
	 * generalised pickup function for potion and weapons
	 * add potion status to player
	 * weapon checks for arrow to stack or replaces weapon held
	 * @TODO: Think of how we are implementing Treasure
	 */
	public void pickup(Item i) {
		if (i instanceof Potion) {
			this.status.add((Potion) i);
		} else if (i instanceof Weapon) {
			if ((this.weapon instanceof Arrow) && (i instanceof Arrow)) {
				((Arrow) this.weapon).addUses();
			} else {
				this.weapon = (Weapon) i;
			}
		}
	}
	
	/*
	 * add potion effect to player
	 * if under the effect, refresh timer/moves
	 * @TODO: is arraylist the best option?
	 */
	public void addStatus(Potion p) {
		for (Potion a: status) {
			if (a.getName().equals(p.getName())) {
				status.remove(a);
				status.add(p);
				return;
			}
		}
		status.add(p);
	}

	/*
	 * Observe current weapon state then process interaction
	 * added fist to kill enemy if player has invincible status
	 */
	public void attack(ComputerAgent a) {
		// weapon is fist: agent attacks player unless player is invincible
		if(weapon instanceof Fist) {
			for (Potion p: status) {
				if (p instanceof Invincibility) {
					a.die();
					return;
				}
			}
			a.attack(this);
			return;
		}

		// weapon is NOT fist: player attacks ComputerAgent
		weapon.attack(a);
		if(this.weapon.getnumUses() <= 0) {
			this.weapon = new Fist();
		}
	}

	public Weapon getWeapon() {
		return this.weapon;
	}
	public int getHealth() {
		return this.healthPoints;
	}

	/*
	 * Check for invincibility
	 * Reduce hitpoints, check and process death.
	 * @TODO: Is checking status better or is invincibility influencing HP better?
	 */
	public void takeDamage(int damage) {
		for (Potion p: status) {
			if (p instanceof Invincibility) {
				return;
			}
		}
		this.healthPoints = this.healthPoints - damage;
		if(this.healthPoints <= 0) {
			System.out.println("Player has died");
		}
	}
	
}
