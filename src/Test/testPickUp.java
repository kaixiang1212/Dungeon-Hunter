package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Model.Arrow;
import Model.ComputerAgent;
import Model.Hover;
import Model.Hunter;
import Model.Invincibility;
import Model.MoveBehaviour;
import Model.Player;
import Model.Potion;
import Model.StandardChaseBehaviour;
import Model.Sword;
import Model.Treasure;

public class testPickUp {

	Player player = new Player();
	// Not Yet Implemented
	Potion invi = new Invincibility();
	Potion hover = new Hover();
	ComputerAgent ca = new Hunter();
	
	//Potions
	@Test
	public void testHasInviPot() {
		player.pickup(invi);
		assert(player.getStatus().contains(invi));
	}
	
	@Test
	public void testHasHoverPot() {
		player.pickup(hover);
		assert(player.getStatus().contains(hover));
	}
	
	@Test 
	public void testInviPot() {
		player.pickup(invi);
		player.takeDamage(100);
		assert(player.getHealth() == 100);

	}
	
	@Test
	public void testInviPotAttack() {
		player.pickup(invi);
		player.attack(ca);
		assert(ca.getHealth() <= 0);
		assert(player.getHealth() == 100);
	}
	
	// Weapons
	@Test
	public void testHasSword() {
		player.pickup(new Sword());
		assertTrue(player.getWeapon() instanceof Sword);
	}
	
	@Test
	public void testHasArrow() {
		player.pickup(new Arrow());
		assertTrue(player.getWeapon() instanceof Arrow);
	}
	
	@Test
	public void testSwordAttack() {
		player.pickup(new Sword());
		player.attack(ca);
		assert(ca.getHealth() <= 0);
		assert(player.getHealth() == 100);
	}
	
	//Doesn't use arrow separate attack code
	@Test
	public void testArrowAttack() {
		player.pickup(new Arrow());
		player.attack(ca);
		assert(ca.getHealth() <= 0);
		assert(player.getHealth() == 100);
	}
	
	
		/*
	 * @Test
	 * public void testHoverPot(){
		player.pickupPotion(hover);
		assertTrue(player.isImmunePit());
	}
	 */
	
	// Treasure	
	/*
	 * @Test
	 * public void testTreasure() {
		player.pickup(new Treasure());
		assert(player.getNumTreasure() == 1);
	}
	 */
	

	// Key
	// Bomb

}