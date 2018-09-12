package Model;

import java.awt.Point;
import java.util.Map;

//Implements Item
public abstract class Weapon implements Item {
	int damage;
	int numUses;
	
	public Weapon(int damage, int numUses) {
		this.damage = damage;
		this.numUses = numUses;
	}

	public int getDamage() {
		return this.damage;
	}
	
	public void addUses() {
		this.numUses++;
	}
	
	public int getnumUses() {
		return this.numUses;
	}
	
	public boolean isPotion() {
		return false;
	}
	
	public boolean isWeapon() {
		return true;
	}
	
	public boolean isArrow() {
		return false;
	}
	
	public boolean isFist() {
		return false;
	}
	
	public boolean isSword() {
		return false;
	}

	// Agent takes damage from this weapon
	public void attack(ComputerAgent a) {
		a.takeDamage(this.damage);
		numUses--;
	}

	/*
	 * Attack for arrow
	 * Don't think this is good code design
	 */
	public void attack(Map<Point, ComputerAgent> agentMap, Point playerPos) {
		// TODO Auto-generated method stub
		numUses--;
	}
}
