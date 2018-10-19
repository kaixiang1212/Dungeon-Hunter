package Test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import Controller.Direction;
import Model.Dungeon;
import Model.Player;
import Model.ComputerAgent.Boulder;
import Model.Tile.Switch;


public class testSwitch {
	
	Dungeon basic;
	Player player;
	Boulder testBoulder;
	Switch sw;
	Point switchPoint;

	@Before
	public void init() {
		basic = new Dungeon(5);
		player = new Player();
		testBoulder = new Boulder(null);
	}

	/**
	 * Test initial switch is not activated
	 */
	@Test
	public void testInitSwitch() {
		basic.placeTile(new Switch(), switchPoint = new Point(1, 1));
		sw = (Switch )basic.getTile(switchPoint);
		assertFalse(sw.isActivated());
	}

	/**
	 * Test simple trigger switch
	 */
	@Test
	public void testSimpleTrigger() {
		basic.placeTile(new Switch(), switchPoint = new Point(1, 1));
		sw = (Switch )basic.getTile(switchPoint);
		assertFalse(sw.isActivated());
		basic.placeComputerAgent(new Boulder(null), new Point(1, 1));
		basic.updateTile();
		assertTrue(sw.isActivated());
	}

	/**
	 * Test simple untrigger switch
	 */
	@Test
	public void testSimpleUntrigger() {
		basic.placeTile(new Switch(), switchPoint = new Point(1, 1));
		sw = (Switch )basic.getTile(switchPoint);
		basic.placeComputerAgent(new Boulder(null), new Point(1, 1));
		basic.updateTile();
		assertTrue(sw.isActivated());
		basic.removeAgent(new Point(1, 1));
		basic.updateTile();
		assertFalse(sw.isActivated());
	}

//	/**
//	 * Test player pushes boulder to trigger
//	 */
//	@Test
//	public void testPlayerPushTrigger() {
//		
//	}
//	
//	/**
//	 * Test player pushes boulder to untrigger
//	 */
//	@Test
//	public void testPlayerPushUntrigger() {
//		
//	}

}
