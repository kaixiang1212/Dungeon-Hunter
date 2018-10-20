package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import Controller.Direction;
import Model.*;
import Model.ComputerAgent.Boulder;
import Model.ComputerAgent.ComputerAgent;
import Model.ComputerAgent.Strategist;
import Model.Item.*;
import org.junit.Before;
import org.junit.Test;

import Model.Tile.Pit;
import Model.Tile.Wall;

public class testItemInteract {

	Dungeon dungeon;
	Player player;
	Point topL;


	@Before public void initTest() {
		dungeon = new Dungeon(20);
		player = new Player();
		topL = new Point (1, 1);
	}
	
	//TEST POTION AND SWORD FIGHT
	
	//test to make sure player with invincibility can kill enemies
	@Test 
	public void testInviPot() {

		// Setup
		Potion invi = new Invincibility();
		ComputerAgent ca = new Strategist();
		dungeon.placeComputerAgent(ca, topL);
		dungeon.placePlayer(player, topL);

		// Tests
		assertFalse(player.deathStatus());
		assertTrue(dungeon.isAgentExist(topL));
		player.pickup(invi);
		player.fight(dungeon);
		assertFalse(player.deathStatus());
		assertFalse(dungeon.isAgentExist(topL));
	}
	
	@Test
	public void testInviPotDuration() {
		Potion invi = new Invincibility();
		player.pickup(invi);
		dungeon.placePlayer(player, new Point(1, 1));
		dungeon.updatePlayer(Direction.RIGHT);
		assertTrue(invi.getDuration() == 19);
	}
	
	@Test
	public void testInviDisappear() {
		Potion invi = new Invincibility();
		player.pickup(invi);
		dungeon.placePlayer(player, new Point(1, 1));
		assertTrue(player.isInvinc());
		for (int i = 0; i < 10; i++) {
			dungeon.updatePlayer(Direction.RIGHT);
		}
		for (int i = 0; i < 10; i++) {
			dungeon.updatePlayer(Direction.LEFT);
		}
		assertFalse(player.isInvinc());
	}
	
	//test to make sure player with hover can be on pit tile
	@Test
	public void testHoverPot() {

		// Setup
		Potion hover = new Hover();
		player.pickup(hover);
		Point playerPos = new Point (2,1);

		// Tests
		dungeon.placeTile(new Pit(), topL);
		dungeon.placePlayer(player, playerPos);
		assertFalse(player.deathStatus());
		assertTrue(player.isHover());
		dungeon.updatePlayer(Direction.LEFT);
		assertEquals(dungeon.getPlayerPos(), topL);
		assertFalse(player.deathStatus());
	}
	
	@Test
	public void testHoverPotDuration() {
		Potion hover = new Hover();
		player.pickup(hover);
		dungeon.placePlayer(player, new Point(1, 1));
		dungeon.updatePlayer(Direction.RIGHT);
		assertTrue(hover.getDuration() == 999);
	}
	
	//test to make sure sword breaks when durability hits 0 and that collision when player has sword kills enemies
	@Test
	public void testSwordAttack() {
		dungeon.placePlayer(player, topL);
		assertFalse(player.deathStatus());
		player.pickup(new Sword());
		player.selectItem(0);
		for (int i = 0; i < 5; i++) {
			ComputerAgent ca = new Strategist();
			dungeon.placeComputerAgent(ca, topL);
			player.fight(dungeon);
			assertFalse(player.deathStatus());
			assertFalse(dungeon.isAgentExist(topL));
		}
		ComputerAgent ca = new Strategist();
		dungeon.placeComputerAgent(ca, topL);
		player.fight(dungeon);
		assertTrue(player.deathStatus());
		assertTrue(dungeon.isAgentExist(topL));
	}
	
	//TEST ARROW USE
	
	//test that shot arrows can kill enemies to the right and that stacking works
	@Test
	public void testArrowAttackRight() {
		ComputerAgent ca = new Strategist();
		Point aPos = new Point(2,1);
		dungeon.placePlayer(player, topL);
		dungeon.placeComputerAgent(ca, aPos);
		player.pickup(new Arrow());
		player.pickup(new Arrow());
		player.selectItem(0);
		assertTrue(player.getHeld().getQuantity() == 2);
		player.useItem(dungeon);
		assertFalse(dungeon.isAgentExist(aPos));
		assertTrue(player.getHeld().getQuantity() == 1);
	}
	
	//test that shot arrow can kill enemies to the left
	@Test
	public void testArrowAttackLeft() {
		ComputerAgent ca = new Strategist();
		Point pPos = new Point(2,1);
		dungeon.placePlayer(player, pPos);
		dungeon.placeComputerAgent(ca, topL);
		player.pickup(new Arrow());
		player.setDirection(Direction.LEFT);
		player.selectItem(0);
		player.useItem(dungeon);
		assertFalse(dungeon.isAgentExist(topL));
	}
	
	//test that shot arrow can kill enemies above
	@Test
	public void testArrowAttackUp() {
		ComputerAgent ca = new Strategist();
		Point pPos = new Point(1,2);
		dungeon.placePlayer(player, pPos);
		dungeon.placeComputerAgent(ca, topL);
		player.pickup(new Arrow());
		player.setDirection(Direction.UP);
		player.selectItem(0);
		player.useItem(dungeon);
		assertFalse(dungeon.isAgentExist(topL));
	}
	
	//test that shot arrow can kill enemies below
	@Test
	public void testArrowAttackDown() {
		ComputerAgent ca = new Strategist();
		Point aPos = new Point(1,2);
		dungeon.placePlayer(player, topL);
		dungeon.placeComputerAgent(ca, aPos);
		player.pickup(new Arrow());
		player.setDirection(Direction.DOWN);
		player.selectItem(0);
		player.useItem(dungeon);
		assertFalse(dungeon.isAgentExist(aPos));
	}
	
	//test that arrows can be blocked by obstacle tiles
	@Test
	public void testArrowBlock() {
		ComputerAgent ca = new Strategist();
		Point aPos = new Point(3,1);
		Point tPos = new Point(2,1);
		dungeon.placePlayer(player, topL);
		dungeon.placeComputerAgent(ca, aPos);
		dungeon.placeTile(new Wall(), tPos);
		player.pickup(new Arrow());
		player.selectItem(0);
		player.useItem(dungeon);
		assertTrue(dungeon.isAgentExist(aPos));
	}
	
	//test that arrows can be blocked by boulders and that boulders aren't destoryed
	@Test
	public void testArrowBlockBoulder() {
		ComputerAgent ca = new Strategist();
		ComputerAgent boulder = new Boulder(null);
		Point aPos = new Point(3,1);
		Point tPos = new Point(2,1);
		dungeon.placePlayer(player, topL);
		dungeon.placeComputerAgent(ca, aPos);
		dungeon.placeComputerAgent(boulder, tPos);
		player.pickup(new Arrow());
		player.selectItem(0);
		player.useItem(dungeon);
		assertTrue(dungeon.isAgentExist(tPos));
		assertTrue(dungeon.isAgentExist(aPos));
	}
	
	//TEST BOMB EXPLOSION
	
	//test that lit bombs can kill player and agents within a 3x3 square
	@Test
	public void testLitBombExplode() {
		dungeon = new Dungeon(4);
		Point bPos = new Point(2,2);
		LitBomb litBomb = new LitBomb(bPos);
		Point trPos = new Point(3,1);
		Point brPos = new Point(3,3);
		Point blPos = new Point(1,3);
		Point outPos = new Point(4,4);
		ComputerAgent ca = new Strategist();
		dungeon.placeComputerAgent(ca, topL);
		dungeon.placeComputerAgent(ca, brPos);
		dungeon.placeComputerAgent(ca, trPos);
		dungeon.placeComputerAgent(ca, blPos);
		dungeon.placeComputerAgent(ca, outPos);
		dungeon.placePlayer(player, bPos);
		litBomb.use(dungeon);
		litBomb.use(dungeon);
		litBomb.use(dungeon);
		assertFalse(dungeon.isAgentExist(topL));
		assertFalse(dungeon.isAgentExist(brPos));
		assertFalse(dungeon.isAgentExist(trPos));
		assertFalse(dungeon.isAgentExist(blPos));
		assertTrue(dungeon.isAgentExist(outPos));
		assertTrue(player.deathStatus());
	}
	
	//test that bombs can be used from inventory and that stacking works
	@Test
	public void testUseBomb() {
		dungeon = new Dungeon(4);
		Point bPos = new Point(2,2);
		Bomb bomb = new Bomb();
		player.pickup(bomb);
		player.pickup(bomb);
		player.selectItem(0);
		assertTrue(player.getHeld().getQuantity() == 2);
		dungeon.placePlayer(player, bPos);
		player.useItem(dungeon);
		assertTrue(player.getHeld().getQuantity() == 1);
		assertTrue(dungeon.isItemExist(bPos));
	}
}
