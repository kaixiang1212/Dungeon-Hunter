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


public class testSwitch {
	
	Dungeon basic;
	Player player;
	Boulder testBoulder;
	Switch switch1;
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
		basic.placeSwitch(switchPoint = new Point(1, 1));
		switch1 = (Switch )basic.getTile(switchPoint);
		assertFalse(switch1.isActivated());
	}

	/**
	 * Test simple trigger switch
	 */
	@Test
	public void testSimpleTrigger() {
		basic.placePlayer(player, new Point(1, 1));
		basic.placeComputerAgent(testBoulder, new Point(2, 1));
		basic.placeSwitch(switchPoint = new Point(3, 1));

		switch1 = (Switch )basic.getTile(switchPoint);
		// player pushes boulder to a switch
		basic.updatePlayer(Direction.RIGHT);
		assertTrue(switch1.isActivated());
	}

	/**
	 * Test simple untrigger switch
	 */
	@Test
	public void testSimpleUntrigger() {
		basic.placeSwitch(switchPoint = new Point(2, 1));
		basic.placeComputerAgent(testBoulder, switchPoint);
		basic.placePlayer(player, new Point(1, 1));
		// player pushes boulder off a switch
		basic.updatePlayer(Direction.RIGHT);
		
		switch1 = (Switch )basic.getTile(switchPoint);
		assertFalse(switch1.isActivated());
	}

	/**
	 * Test boulder triggered when boulder is place on it
	 */
	@Test
	public void testPlaceBoulderOnSwitch() {
		basic.placeSwitch(switchPoint = new Point(3, 1));
		// place boulder on top of switch
		basic.placeComputerAgent(testBoulder, switchPoint);
		switch1 = (Switch )basic.getTile(switchPoint);

		assertTrue(switch1.isActivated());
	}
	

}
