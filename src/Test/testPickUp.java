package Test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Model.Arrow;
import Model.Bomb;
import Model.Dungeon;
import Model.Hover;
import Model.Invincibility;
import Model.LitBomb;
import Model.Player;
import Model.Potion;
import Model.Sword;
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
	public void testPickUpInviPot() {
		Player player = new Player();
		Potion invi = new Invincibility();
		Dungeon dungeon = new Dungeon(3);
		Point point = new Point(1,1);
		Point iPoint = new Point(2,1);
		dungeon.placePlayer(player, point);
		assertEquals(dungeon.getPlayerPos(), point);
		dungeon.placeItem(invi, iPoint);
		assertTrue(dungeon.isItemExist(iPoint));
		assertFalse(player.isInvinc());
		dungeon.updatePlayer("d");
		assertEquals(dungeon.getPlayerPos(), iPoint);
		assertFalse(dungeon.isItemExist(iPoint));
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
	public void testPickUpHoverPot() {
		Player player = new Player();
		Potion hover = new Hover();
		Dungeon dungeon = new Dungeon(3);
		Point point = new Point(1,1);
		Point iPoint = new Point(2,1);
		dungeon.placePlayer(player, point);
		assertEquals(dungeon.getPlayerPos(), point);
		dungeon.placeItem(hover, iPoint);
		assertTrue(dungeon.isItemExist(iPoint));
		assertFalse(player.isInvinc());
		dungeon.updatePlayer("d");
		assertEquals(dungeon.getPlayerPos(), iPoint);
		assertFalse(dungeon.isItemExist(iPoint));
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
	public void testPickUpTreasure() {
		Player player = new Player();
		Treasure treasure = new Treasure();
		Dungeon dungeon = new Dungeon(3);
		Point point = new Point(2,1);
		Point iPoint = new Point(1,1);
		dungeon.placePlayer(player, point);
		assertEquals(dungeon.getPlayerPos(), point);
		dungeon.placeItem(treasure, iPoint);
		assertTrue(dungeon.isItemExist(iPoint));
		assertFalse(player.isInvinc());
		dungeon.updatePlayer("a");
		assertEquals(dungeon.getPlayerPos(), iPoint);
		assertFalse(dungeon.isItemExist(iPoint));
		player.selectItem(0);
		assert(player.getHeld() instanceof Treasure);
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
	public void testPickUpSword() {
		Player player = new Player();
		Sword sword = new Sword();
		Dungeon dungeon = new Dungeon(3);
		Point point = new Point(1,1);
		Point iPoint = new Point(2,1);
		dungeon.placePlayer(player, point);
		assertEquals(dungeon.getPlayerPos(), point);
		dungeon.placeItem(sword, iPoint);
		assertTrue(dungeon.isItemExist(iPoint));
		assertTrue(player.getInventory().isEmpty());
		dungeon.updatePlayer("d");
		assertEquals(dungeon.getPlayerPos(), iPoint);
		assertFalse(dungeon.isItemExist(iPoint));
		player.selectItem(0);
		assert(player.getHeld() instanceof Sword);
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
	public void testPickUpArrow() {
		Player player = new Player();
		Arrow arrow = new Arrow();
		Dungeon dungeon = new Dungeon(3);
		Point point = new Point(1,1);
		Point iPoint = new Point(2,1);
		dungeon.placePlayer(player, point);
		assertEquals(dungeon.getPlayerPos(), point);
		dungeon.placeItem(arrow, iPoint);
		assertTrue(dungeon.isItemExist(iPoint));
		assertTrue(player.getInventory().isEmpty());
		dungeon.updatePlayer("d");
		assertEquals(dungeon.getPlayerPos(), iPoint);
		assertFalse(dungeon.isItemExist(iPoint));
		player.selectItem(0);
		assert(player.getHeld() instanceof Arrow);
		assertTrue(player.getHeld().getQuantity() == 1);
		dungeon.placeItem(arrow, point);
		dungeon.updatePlayer("a");
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
	public void testPickUpBomb() {
		Player player = new Player();
		Bomb bomb = new Bomb();
		Dungeon dungeon = new Dungeon(3);
		Point point = new Point(1,1);
		Point iPoint = new Point(2,1);
		dungeon.placePlayer(player, point);
		assertEquals(dungeon.getPlayerPos(), point);
		dungeon.placeItem(bomb, iPoint);
		assertTrue(dungeon.isItemExist(iPoint));
		assertTrue(player.getInventory().isEmpty());
		dungeon.updatePlayer("d");
		assertEquals(dungeon.getPlayerPos(), iPoint);
		assertFalse(dungeon.isItemExist(iPoint));
		player.selectItem(0);
		assert(player.getHeld() instanceof Bomb);
	}
	
	@Test
	public void testCantHaveLitBomb() {
		Player player = new Player();
		Point point = new Point(1,1);
		assert(player.getInventory().isEmpty());
		player.pickup(new LitBomb(point));
		assert(player.getInventory().isEmpty());
	}
	
	@Test
	public void testCantPickUpLitBomb() {
		Player player = new Player();
		Dungeon dungeon = new Dungeon(3);
		Point point = new Point(1,1);
		Point iPoint = new Point(2,1);
		dungeon.placePlayer(player, point);
		assertEquals(dungeon.getPlayerPos(), point);
		dungeon.placeItem(new LitBomb(iPoint), iPoint);
		assertTrue(dungeon.isItemExist(iPoint));
		assertTrue(player.getInventory().isEmpty());
		dungeon.updatePlayer("d");
		assertEquals(dungeon.getPlayerPos(), iPoint);
		assertTrue(dungeon.isItemExist(iPoint));
		assertTrue(player.getInventory().isEmpty());
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

}