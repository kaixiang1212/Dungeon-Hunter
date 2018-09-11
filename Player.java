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

	public void pickupWeapon(Weapon w) {
		this.weapon = w;
	}

	// Observe current weapon state then process interaction
	public void attack(ComputerAgent a) {

		// weapon is fist: agent attacks player
		if(weapon instanceof Fist) {
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

	// Reduce hitpoints, check and process death.
	public void takeDamage(int damage) {
		this.healthPoints = this.healthPoints - damage;
		if(this.healthPoints <= 0) {
			System.out.println("Player has died");
		}
	}
	
	public void addStatus(Potion p) {
		int check = 0;
		for (Potion a: status) {
			if (a.getName().equals(p.getName())) {
				status.remove(a);
				status.add(p);
				check = 1;
			}
		}
		if (check == 0) {
			status.add(p);
		}
	}

	
}