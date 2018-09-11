package Model;

import java.awt.Point;

public class Player {

	private int healthPoints;
	private Weapon weapon;
	//private Potion potion;
	
	
	public Player() {
		this.healthPoints = 100;
		this.weapon = null;
		//this.potion = null;
	}

/*	public void move(Point playerPos) {
		
	}*/
	public void pickupWeapon(Weapon w) {
		this.weapon = w;
	}
	public void attack(Agent a) {
		weapon.attack(a);
	}
	public void damage(int damage) {
		this.healthPoints = this.healthPoints - damage;
		if(this.healthPoints <= 0) {
			System.out.println("Player has died");
		}
	}

	
}
