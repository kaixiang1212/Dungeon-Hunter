package Test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import Controller.Direction;
import Model.Boulder;
import Model.Dungeon;
import Model.Player;
import Model.Tile.Switch;

public class testWinCondition {
	
	Dungeon basic;
	Player player;
	Boulder testBoulder;

	@Before
	public void init() {
		basic = new Dungeon(5);
		player = new Player();
		testBoulder = new Boulder(null);
	}
	/**
	 * Test win condition when all switch is triggered
	 */
	@Test
	public void testPlaceBoulderOnSwitch() {
		basic.placeTile(new Switch(), new Point(3, 1));
		assertFalse(basic.winConditionSwitch());
		basic.placeComputerAgent(testBoulder, new Point(3, 1));
		basic.updateTile();
		assertTrue(basic.winConditionSwitch());
	}
	
	/**
	 * test 2 switch win condition
	 */
	@Test
	public void testTwoSwitch() {
		basic.placeTile(new Switch(), new Point(2, 1));
		basic.placeComputerAgent(testBoulder, new Point(2, 1));
		basic.placePlayer(player, new Point(1, 1));
		basic.placeTile(new Switch(), new Point(1, 3));
		basic.placeComputerAgent(testBoulder, new Point(1, 2));
		assertFalse(basic.winConditionSwitch());
		basic.updatePlayer(Direction.DOWN);
		basic.updateTile();
		assertTrue(basic.winConditionSwitch());
	}
	
	// TODO: implement other win condition tests

}
