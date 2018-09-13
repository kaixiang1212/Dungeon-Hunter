package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import itemDesign.Arrow;
import itemDesign.ComputerAgent;
import itemDesign.Fist;
import itemDesign.Hover;
import itemDesign.Hunter;
import itemDesign.Invincibility;
import itemDesign.MoveBehaviour;
import itemDesign.Player;
import itemDesign.Potion;
import itemDesign.Sword;
import itemDesign.Treasure;

public class testPickUp {
	
	//Potions
	@Test
	public void testHasInviPot() {
		Player player = new Player();
		Potion invi = new Invincibility();
		assertFalse(player.isInvinc());
		player.pickup(invi);
		assert(player.isInvinc());
	}
	
	@Test
	public void testHasHoverPot() {
		Player player = new Player();
		Potion hover = new Hover();
		assertFalse(player.isHover());
		player.pickup(hover);
		assert(player.isHover());
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
		assert(player.getMeleeWeapon().isFist());
		player.pickup(new Sword());
		assertTrue(player.getMeleeWeapon().isSword());
	}
	
	@Test
	public void testHasArrow() {
		Player player = new Player();
		assert(player.getRangedWeapon().getUses() == 0);
		player.pickup(new Arrow());
		assertTrue(player.getRangedWeapon().getUses() == 1);
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
		player.shoot();
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