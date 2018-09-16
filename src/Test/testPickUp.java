package Test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import Model.Arrow;
import Model.Bomb;
import Model.ComputerAgent;
import Model.Dungeon;
import Model.Hover;
import Model.Hunter;
import Model.Invincibility;
import Model.LitBomb;
import Model.MoveBehaviour;
import Model.StandardChaseBehaviour;
import Model.Strategist;
import Model.Player;
import Model.Potion;
import Model.RangedWeapon;
import Model.Sword;
import Model.Tile;
import Model.Tile.TileType;
import Model.Treasure;

public class testPickUp {
	
	//PICKUP ITEMS
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
	public void testHasTreasure() {
		Player player = new Player();
		Treasure treasure = new Treasure();
		assert(player.getInventory().isEmpty());
		player.pickup(new Treasure());
		player.selectItem(0);
		assertTrue(player.getHeld() instanceof Treasure);
	}
	
	@Test
	public void testHasSword() {
		Player player = new Player();
		assert(player.getInventory().isEmpty());
		player.pickup(new Sword());
		player.selectItem(0);
		assertTrue(player.getHeld() instanceof Sword);
	}
	
	@Test
	public void testHasArrow() {
		Player player = new Player();
		assert(player.getInventory().isEmpty());
		player.pickup(new Arrow());
		player.selectItem(0);
		assertTrue(player.getHeld() instanceof Arrow);
		assertTrue(player.getHeld().getQuantity() == 1);
		player.pickup(new Arrow());
		assertTrue(player.getHeld().getQuantity() == 2);
	}
	
	@Test
	public void testHasBomb() {
		Player player = new Player();
		assert(player.getInventory().isEmpty());
		player.pickup(new Bomb());
		player.selectItem(0);
		assertTrue(player.getHeld() instanceof Bomb);
	}
	
	@Test
	public void testCantPickUpLitBomb() {
		Player player = new Player();
		Point point = new Point(1,1);
		assert(player.getInventory().isEmpty());
		player.pickup(new LitBomb(point));
		assert(player.getInventory().isEmpty());
	}
	
	@Test
	public void testMoveThroughInventory() {
		Player player = new Player();
		player.pickup(new Arrow());
		player.selectItem(0);
		assertTrue(player.getHeld() instanceof Arrow);
		player.pickup(new Sword());
		player.selectItem(1);
		assertTrue(player.getHeld() instanceof Sword);
	}
	
	//TEST POTION AND SWORD FIGHT
	
	@Test 
	public void testInviPot() {
		Player player = new Player();
		Potion invi = new Invincibility();
		ComputerAgent ca = new Strategist();
		Dungeon dungeon = new Dungeon(3);
		Point pPos = new Point(1,1);
		Point aPos = new Point(1,1);
		dungeon.placeComputerAgent(ca, aPos);
		dungeon.placePlayer(player, pPos);
		assertFalse(player.deathStatus());
		assertTrue(dungeon.isAgentExist(aPos));
		player.pickup(invi);
		player.fight(dungeon);
		assertFalse(player.deathStatus());
		assertFalse(dungeon.isAgentExist(aPos));
	}
	
	@Test
	public void testHoverPot() {
		Player player = new Player();
		Potion hover = new Hover();
		Dungeon dungeon = new Dungeon(3);
		Point pitPos = new Point(1,1);
		dungeon.placeTile(TileType.DESTRUCTABLE_WALL, pitPos);
		//TODO: Implement pit valid move check
	}
	
	@Test
	public void testSwordAttack() {
		Player player = new Player();
		Dungeon dungeon = new Dungeon(3);
		Point pPos = new Point(1,1);
		Point aPos = new Point(1,1);
		dungeon.placePlayer(player, pPos);
		assertFalse(player.deathStatus());
		player.pickup(new Sword());
		player.selectItem(0);
		for (int i = 0; i < 5; i++) {
			ComputerAgent ca = new Strategist();
			dungeon.placeComputerAgent(ca, aPos);
			player.fight(dungeon);
			assertFalse(player.deathStatus());
			assertFalse(dungeon.isAgentExist(aPos));
		}
		ComputerAgent ca = new Strategist();
		dungeon.placeComputerAgent(ca, aPos);
		player.fight(dungeon);
		assertTrue(player.deathStatus());
		assertTrue(dungeon.isAgentExist(aPos));
	}
	
	//TEST ARROW USE
	@Test
	public void testArrowAttackRight() {
		Player player = new Player();
		ComputerAgent ca = new Strategist();
		Dungeon dungeon = new Dungeon(3);
		Point pPos = new Point(1,1);
		Point aPos = new Point(2,1);
		dungeon.placePlayer(player, pPos);
		dungeon.placeComputerAgent(ca, aPos);
		player.pickup(new Arrow());
		player.selectItem(0);
		player.useItem(dungeon);
		assertFalse(dungeon.isAgentExist(aPos));
	}
	
	@Test
	public void testArrowAttackLeft() {
		Player player = new Player();
		ComputerAgent ca = new Strategist();
		Dungeon dungeon = new Dungeon(3);
		Point pPos = new Point(2,1);
		Point aPos = new Point(1,1);
		dungeon.placePlayer(player, pPos);
		dungeon.placeComputerAgent(ca, aPos);
		player.pickup(new Arrow());
		player.setDirection("Left");
		player.selectItem(0);
		player.useItem(dungeon);
		assertFalse(dungeon.isAgentExist(aPos));
	}
	
	@Test
	public void testArrowAttackUp() {
		Player player = new Player();
		ComputerAgent ca = new Strategist();
		Dungeon dungeon = new Dungeon(3);
		Point pPos = new Point(1,2);
		Point aPos = new Point(1,1);
		dungeon.placePlayer(player, pPos);
		dungeon.placeComputerAgent(ca, aPos);
		player.pickup(new Arrow());
		player.setDirection("Up");
		player.selectItem(0);
		player.useItem(dungeon);
		assertFalse(dungeon.isAgentExist(aPos));
	}
	
	@Test
	public void testArrowAttackDown() {
		Player player = new Player();
		ComputerAgent ca = new Strategist();
		Dungeon dungeon = new Dungeon(3);
		Point pPos = new Point(1,1);
		Point aPos = new Point(1,2);
		dungeon.placePlayer(player, pPos);
		dungeon.placeComputerAgent(ca, aPos);
		player.pickup(new Arrow());
		player.setDirection("Down");
		player.selectItem(0);
		player.useItem(dungeon);
		assertFalse(dungeon.isAgentExist(aPos));
	}
	
	@Test
	public void testArrowBlock() {
		Player player = new Player();
		ComputerAgent ca = new Strategist();
		Dungeon dungeon = new Dungeon(3);
		Point pPos = new Point(1,1);
		Point aPos = new Point(3,1);
		Point tPos = new Point(2,1);
		dungeon.placePlayer(player, pPos);
		dungeon.placeComputerAgent(ca, aPos);
		dungeon.placeTile(TileType.DESTRUCTABLE_WALL, tPos);
		player.pickup(new Arrow());
		player.selectItem(0);
		player.useItem(dungeon);
		assertTrue(dungeon.isAgentExist(aPos));
	}
	
	//TEST BOMB EXPLOSION
	@Test
	public void testLitBombExplode() {
		Dungeon dungeon = new Dungeon(4);
		Player player = new Player();
		Point bPos = new Point(2,2);
		LitBomb litBomb = new LitBomb(bPos);
		Point tlPos = new Point(1,1);
		Point brPos = new Point(3,3);
		Point outPos = new Point(4,4);
		ComputerAgent ca = new Strategist();
		dungeon.placeComputerAgent(ca, tlPos);
		dungeon.placeComputerAgent(ca, brPos);
		dungeon.placeComputerAgent(ca, outPos);
		dungeon.placePlayer(player, bPos);
		litBomb.use(dungeon);
		litBomb.use(dungeon);
		litBomb.use(dungeon);
		assertFalse(dungeon.isAgentExist(tlPos));
		assertFalse(dungeon.isAgentExist(brPos));
		assertTrue(dungeon.isAgentExist(outPos));
	}

}