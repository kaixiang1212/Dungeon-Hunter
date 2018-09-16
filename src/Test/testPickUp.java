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
	
	//test to make sure pickup adds invincibility effect on player upon pickup
	@Test
	public void testHasInviPot() {
		Player player = new Player();
		Potion invi = new Invincibility();
		assertFalse(player.isInvinc());
		player.pickup(invi);
		assert(player.isInvinc());
		assertTrue(player.getInventory().isEmpty());
	}
	
	//test to make sure collision with invincibility potion calls pickup
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
	
	//test to make sure pickup adds hover effect on player
	@Test
	public void testHasHoverPot() {
		Player player = new Player();
		Potion hover = new Hover();
		assertFalse(player.isHover());
		player.pickup(hover);
		assert(player.isHover());
		assertTrue(player.getInventory().isEmpty());
	}
	
	//test to make sure collision with hover potion calls pickup
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
	
	//test to make sure pickup adds Treasure
	@Test
	public void testHasTreasure() {
		Player player = new Player();
		Treasure treasure = new Treasure();
		assert(player.getInventory().isEmpty());
		player.pickup(new Treasure());
		player.selectItem(0);
		assertFalse(player.getInventory().isEmpty());
		assertTrue(player.getHeld() instanceof Treasure);
	}
	
	//test to make sure collision with treasure calls pickup and that treasure can be stacked
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
		assertTrue(player.getHeld().getQuantity() == 1);
		player.pickup(treasure);
		assertTrue(player.getHeld().getQuantity() == 2);
	}
	
	//test to make sure pickup adds sword to inventory
	@Test
	public void testHasSword() {
		Player player = new Player();
		assert(player.getInventory().isEmpty());
		player.pickup(new Sword());
		player.selectItem(0);
		assertTrue(player.getHeld() instanceof Sword);
		assertFalse(player.getInventory().isEmpty());
	}
	
	//test to make sure player can only hold one sword at a time
	@Test
	public void testOnlyHaveOneSword() {
		Player player = new Player();
		Sword sword = new Sword();
		Sword sword2 = new Sword();
		player.pickup(sword);
		assertTrue(player.getInventory().getNumItems() == 1);
		player.pickup(sword2);
		assertTrue(player.getInventory().getNumItems() == 1);
	}
	
	//test to make sure collision with sword calls pickup
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
	
	//test to make sure player swaps out sword with new one if pickup is called and that Sword durability works
	@Test
	public void testOnlyHaveOneSwordChangingUses() {
		Player player = new Player();
		Sword sword = new Sword();
		Sword sword2 = new Sword();
		Dungeon dungeon = new Dungeon(3);
		ComputerAgent ca = new Strategist();
		Point point = new Point(1,1);
		dungeon.placeComputerAgent(ca, point);
		dungeon.placePlayer(player, point);
		player.pickup(sword);
		player.selectItem(0);
		assertTrue(player.getInventory().getNumItems() == 1);
		assertTrue(((Sword) player.getHeld()).getnumUses() == 5);
		player.useItem(dungeon);
		assertTrue(((Sword) player.getHeld()).getnumUses() == 4);
		player.pickup(sword2);
		player.selectItem(0);
		assertTrue(player.getInventory().getNumItems() == 1);
		assertTrue(((Sword) player.getHeld()).getnumUses() == 5);
	}
	
	//test to make sure pickup adds arrows to inventory and that they can be stacked
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
		assertFalse(player.getInventory().isEmpty());
	}
	
	//test to make sure collision calls pickup on arrows
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
	
	//test to make sure pickup adds bombs to inventory
	@Test
	public void testHasBomb() {
		Player player = new Player();
		assert(player.getInventory().isEmpty());
		player.pickup(new Bomb());
		player.selectItem(0);
		assertTrue(player.getHeld() instanceof Bomb);
	}
	
	//test to make sure collision with bomb calls pickup and that bombs are stackable
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
		assertTrue(player.getHeld().getQuantity() == 1);
		dungeon.placeItem(bomb, point);
		dungeon.updatePlayer("a");
		assertTrue(player.getHeld().getQuantity() == 2);
	}
	
	//test to make sure player can't pickup lit bomb
	@Test
	public void testCantHaveLitBomb() {
		Player player = new Player();
		Point point = new Point(1,1);
		assert(player.getInventory().isEmpty());
		player.pickup(new LitBomb(point));
		assert(player.getInventory().isEmpty());
	}
	
	//test to make sure collision doesn't pick up lit bomb
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
	
	//test that player can move through inventory
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
	
	//test to make sure that enemies don't affect items on the map
	@Test
	public void testItemInMapInteractionWithEntity() {
		Sword sword = new Sword();
		ComputerAgent ca = new Strategist();
		Dungeon dungeon = new Dungeon(3);
		Point iPos = new Point(1,1);
		assertFalse(dungeon.isItemExist(iPos));
		assertFalse(dungeon.isAgentExist(iPos));
		dungeon.placeItem(sword, iPos);
		assertTrue(dungeon.isItemExist(iPos));
		dungeon.placeComputerAgent(ca, iPos);
		assertTrue(dungeon.isAgentExist(iPos));
		assertTrue(dungeon.isItemExist(iPos));
	}

}