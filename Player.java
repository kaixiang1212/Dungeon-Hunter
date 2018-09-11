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
	 * generalised pickup function
	 */
	public void pickup(Item i) {
		if (i instanceof Potion) {
			this.status.add((Potion) i);
		} else if (i instanceof Weapon) {
			this.weapon = (Weapon) i;
		}
	}
	
	/*
	 * add potion effect to player
	 */
	public void addStatus(Potion p) {
		int check = 0;
		for (Potion a: status) {
			if (a.getName().equals(p.getName())) {
				status.remove(a);
				status.add(p);
				check = 1;
				break;
			}
		}
		if (check == 0) {
			status.add(p);
		}
	}

	/*
	 * Observe current weapon state then process interaction
	 * added fist to kill enemy if player has invincible status
	 */
	public void attack(ComputerAgent a) {

		int check = 0;
		// weapon is fist: agent attacks player unless player is invincible
		if(weapon instanceof Fist) {
			for (Potion p: status) {
				if (p instanceof Invincibility) {
					a.die();
					check = 1;
					break;
				}
			}
			if (check == 0) {
				a.attack(this);
			}
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

	// Reduce hitpoints, check and process death.
	public void takeDamage(int damage) {
		this.healthPoints = this.healthPoints - damage;
		if(this.healthPoints <= 0) {
			System.out.println("Player has died");
		}
	}
	
}
