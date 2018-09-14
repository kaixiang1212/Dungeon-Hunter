package Model;

public class Player {

	private int healthPoints;
	private Weapon weapon;
	
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
	public void takeDamage(int damage, ComputerAgent a) {
		if(this.weapon instanceof Sword) {
			this.attack(a);
		}
		this.healthPoints = this.healthPoints - damage;
		if(this.healthPoints <= 0) {
			System.out.println("Player has died");
		}
	}

	
}
