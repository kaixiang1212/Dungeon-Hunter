package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;

import Model.Arrow;
import Model.Bomb;
import Model.ComputerAgent;
import Model.Dungeon;
import Model.Hover;
import Model.Invincibility;
import Model.LitBomb;
import Model.Player;
import Model.Potion;
import Model.Strategist;
import Model.Sword;
import Model.Tile.TileType;

public class testItemInteract {
	
	//TEST POTION AND SWORD FIGHT
	
	//test to make sure player with invincibility can kill enemies
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
	
	//test to make sure player with hover can be on pit tile
	@Test
	public void testHoverPot() {
		Player player = new Player();
		Potion hover = new Hover();
		player.pickup(hover);
		Dungeon dungeon = new Dungeon(3);
		Point pitPos = new Point(1,1);
		Point playerPos = new Point (2,1);
		dungeon.placeTile(TileType.PIT, pitPos);
		dungeon.placePlayer(player, playerPos);
		assertFalse(player.deathStatus());
		assertTrue(player.isHover());
		dungeon.updatePlayer("a");
		assertFalse(player.deathStatus());
		assertEquals(dungeon.getPlayerPos(), pitPos);
		//TODO: Implement pit valid move check
	}
	
	//test to make sure sword breaks when durability hits 0 and that collision when player has sword kills enemies
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
	
	//test that shot arrows can kill enemies to the right and that stacking works
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
	
	//test that shot arrow can kill enemies above
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
	
	//test that shot arrow can kill enemies below
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
	
	//test that arrows can be blocked by obstacle tiles
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
	
	//test that lit bombs can kill player and agents within a 3x3 square
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
		assertTrue(player.deathStatus());
	}
	
	//test that bombs can be used from inventory and that stacking works
	@Test
	public void testUseBomb() {
		Dungeon dungeon = new Dungeon(4);
		Player player = new Player();
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
