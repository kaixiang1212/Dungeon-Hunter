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


	public int getnumUses() {
		return this.numUses;
	}

	// Agent takes damage from this weapon
	public void attack(ComputerAgent a) {
		a.takeDamage(this.damage);
		numUses--;
	}

	public void attack(Map<Point, ComputerAgent> agentMap, Point playerPos) {
		// TODO Auto-generated method stub
		
	}
}
