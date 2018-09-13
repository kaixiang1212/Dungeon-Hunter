package itemDesign;
import java.util.ArrayList;

public class Player {

	private int healthPoints;
	private MeleeWeapon meleeWeapon;
	private RangedWeapon rangedWeapon;
	private ArrayList<Potion> status;
	private String direction;
	
	public Player() {
		this.healthPoints = 100;
		this.meleeWeapon = new Fist();
		this.rangedWeapon = new Arrow();
		this.status = new ArrayList<Potion>();
		this.direction = "Right";
	}
	
	/*
	 * generalised pickup function for potion and weapons
	 * add potion status to player
	 * weapon checks for arrow to stack or replaces weapon held
	 * @TODO: Think of how we are implementing Treasure
	 */
	public void pickup(Item i) {
		if (i.isPotion()) {
			this.addStatus((Potion) i);
		} else if (i.isRangedWeapon()) {
			this.rangedWeapon.addUses();
		} else if (i.isMeleeWeapon()) {
			this.meleeWeapon = (MeleeWeapon) i;
		}
	}
	
	/*
	 * add potion effect to player
	 * if under the effect, refresh timer/moves
	 * @TODO: is arraylist the best option?
	 */
	public void addStatus(Potion p) {
		for (Potion a: this.status) {
			if (a.getName().equals(p.getName())) {
				this.status.remove(a);
				this.status.add(p);
				return;
			}
		}
		this.status.add(p);
	}

	/*
	 * Observe current weapon state then process interaction
	 * added fist to kill enemy if player has invincible status
	 */
	public void attack(ComputerAgent a) {
		// weapon is fist: agent attacks player unless player is invincible
		if(this.meleeWeapon.isFist()) {
			if (this.isInvinc()) {
				a.takeDamage(a.getHealth());
				return;
			}
			a.attack(this);
			return;
		}

		// weapon is NOT fist: player attacks ComputerAgent
		this.meleeWeapon.attack(a);
		if(this.meleeWeapon.getNumUses() <= 0) {
			this.meleeWeapon = new Fist();
		}
	}
	
	/*
	 * @TODO: Implement arrow attack
	 */
	public void shoot() {
		if (this.rangedWeapon.getUses() > 0) {
			this.rangedWeapon.subUses();
		}
	}

	public MeleeWeapon getMeleeWeapon() {
		return this.meleeWeapon;
	}
	public RangedWeapon getRangedWeapon() {
		return this.rangedWeapon;
	}
	public int getHealth() {
		return this.healthPoints;
	}
	public ArrayList<Potion> getStatus() {
		return this.status;
	}
	public boolean isInvinc() {
		for (Potion p: this.status) {
			if (p.isInvinc()) {
				return true;
			}
		}
		return false;
	}
	public boolean isHover() {
		for (Potion p: this.status) {
			if (p.isHover()) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Check for invincibility
	 * Reduce hitpoints, check and process death.
	 * @TODO: Is checking status better or is invincibility influencing HP better?
	 */
	public void takeDamage(int damage) {
		if (this.isInvinc()) {
			return;
		}
		this.healthPoints = this.healthPoints - damage;
		if(this.healthPoints <= 0) {
			System.out.println("Player has died");
		}
	}
	
}
