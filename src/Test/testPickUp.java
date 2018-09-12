package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Model.Arrow;
import Model.ComputerAgent;
import Model.Fist;
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
	
	//Potions
	@Test
	public void testHasInviPot() {
		Player player = new Player();
		Potion invi = new Invincibility();
		assertFalse(player.getStatus().contains(invi));
		player.pickup(invi);
		assert(player.getStatus().contains(invi));
	}
	
	@Test
	public void testHasHoverPot() {
		Player player = new Player();
		Potion hover = new Hover();
		assertFalse(player.getStatus().contains(hover));
		player.pickup(hover);
		assert(player.getStatus().contains(hover));
	}
	
	@Test 
	public void testInviPot() {
		Player player = new Player();
		Potion invi = new Invincibility();
		assert(player.getHealth() == 100);
		player.takeDamage(50);
		assert(player.getHealth() == 50);
		player.pickup(invi);
		player.takeDamage(100);
		assert(player.getHealth() == 50);

	}
	
	@Test
	public void testInviPotAttack() {
		Player player = new Player();
		Potion invi = new Invincibility();
		player.pickup(invi);
		ComputerAgent ca = new Hunter();
		assert(ca.getHealth() == 100);
		player.attack(ca);
		assert(ca.getHealth() <= 0);
		assert(player.getHealth() == 100);
	}
	
	// Weapons
	@Test
	public void testHasSword() {
		Player player = new Player();
		assert(player.getWeapon() instanceof Fist);
		player.pickup(new Sword());
		assertTrue(player.getWeapon() instanceof Sword);
	}
	
	@Test
	public void testHasArrow() {
		Player player = new Player();
		assert(player.getWeapon() instanceof Fist);
		player.pickup(new Arrow());
		assertTrue(player.getWeapon() instanceof Arrow);
	}
	
	@Test
	public void testSwordAttack() {
		Player player = new Player();
		ComputerAgent ca = new Hunter();
		player.pickup(new Sword());
		assert(ca.getHealth() == 100);
		assert(player.getHealth() == 100);
		player.attack(ca);
		assert(ca.getHealth() <= 0);
		assert(player.getHealth() == 100);
	}
	
	//Doesn't use arrow separate attack code
	@Test
	public void testArrowAttack() {
		Player player = new Player();
		ComputerAgent ca = new Hunter();
		player.pickup(new Arrow());
		assert(ca.getHealth() == 100);
		assert(player.getHealth() == 100);
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