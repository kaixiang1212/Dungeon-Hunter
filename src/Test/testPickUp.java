package Test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import itemDesign.Arrow;
import itemDesign.ComputerAgent;
import itemDesign.Dungeon;
import itemDesign.Fist;
import itemDesign.Hover;
import itemDesign.Hunter;
import itemDesign.Invincibility;
import itemDesign.MoveBehaviour;
import itemDesign.Player;
import itemDesign.Potion;
import itemDesign.RangedWeapon;
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
		ca.attack(player);
		assert(ca.getHealth() <= 0);
		assert(player.getHealth() == 100);
	}
	
	// Weapons
	@Test
	public void testHasSword() {
		Player player = new Player();
		assert(player.getInventory().isEmpty());
		player.pickup(new Sword());
		assertTrue(player.getEquipped() instanceof Sword);
	}
	
	@Test
	public void testHasArrow() {
		Player player = new Player();
		assert(player.getInventory().isEmpty());
		player.pickup(new Arrow());
		assertTrue(player.getEquipped() instanceof Arrow);
		assertTrue(((RangedWeapon) player.getEquipped()).getUses() == 1);
		player.pickup(new Arrow());
		assertTrue(((RangedWeapon) player.getEquipped()).getUses() == 2);
	}
	
	@Test
	public void testMoveThroughInventory() {
		Player player = new Player();
		player.pickup(new Arrow());
		assertTrue(player.getEquipped() instanceof Arrow);
		player.pickup(new Sword());
		player.getInventory().moveThrough();
		assertTrue(player.getEquipped() instanceof Sword);
	}
	
	@Test
	public void testSwordAttack() {
		Player player = new Player();
		ComputerAgent ca = new Hunter();
		Dungeon dungeon = new Dungeon(3);
		Point pPos = new Point(1,1);
		Point aPos = new Point(2,1);
		player.pickup(new Sword());
		dungeon.placeComputerAgent(ca, aPos);
		dungeon.placePlayer(player, pPos);
		assert(ca.getHealth() == 100);
		assert(player.getHealth() == 100);
		player.use(dungeon);
		assert(ca.getHealth() <= 0);
		assert(player.getHealth() == 100);
	}
	
	//Doesn't use arrow separate attack code
	@Test
	public void testArrowAttack() {
		Player player = new Player();
		ComputerAgent ca = new Hunter();
		Dungeon dungeon = new Dungeon(4);
		Point pPos = new Point(1,1);
		Point aPos = new Point(3,1);
		player.pickup(new Arrow());
		dungeon.placeComputerAgent(ca, aPos);
		dungeon.placePlayer(player, pPos);
		assert(ca.getHealth() == 100);
		assert(player.getHealth() == 100);
		player.use(dungeon);
		System.out.println(ca.getHealth());
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