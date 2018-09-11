package Model;

import java.awt.Point;

public class Player {

	private int healthPoints;
	private Weapon weapon;
	
	public Player() {
		this.healthPoints = 100;
		this.weapon = null;
	}

	public void pickupWeapon(Weapon w) {
		this.weapon = w;
	}
	public void attack(Agent a) {
		if(weapon == null) { //If no weapon, agent instead attack player :(
			a.attack(this);
			return;
		}
		weapon.attack(a);
		if(this.weapon.getnumUses() <= 0) {
			this.weapon = null; //Broken weapon! Delegation of deletion to the player or holder
		}
	}
	public Weapon getWeapon() {
		return this.weapon;
	}
	public int getHealth() {
		return this.healthPoints;
	}
	public void damage(int damage) {
		this.healthPoints = this.healthPoints - damage;
		if(this.healthPoints <= 0) {
			System.out.println("Player has died");
		}
	}

	
}
