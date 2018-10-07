package Test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import Controller.Direction;
import Model.Boulder;
import Model.Dungeon;
import Model.Player;


public class testSwitch {
	
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
	 * Test simple trigger switch
	 */
	@Test
	public void testSimpleTrigger() {
		basic.placeSwitch(new Point(3, 1));
		basic.placePlayer(player, new Point(1, 1));
		basic.placeComputerAgent(testBoulder, new Point(2, 1));
		assert(basic.winConditionSwitch() == false);
		basic.updatePlayer(Direction.RIGHT);
		assert(basic.getPlayerPos().equals(new Point(2,1)));
		assert(basic.winConditionSwitch() == true);
	}
	/**
	 * Test boulder triggered when boulder is place on it
	 */
	@Test
	public void testPlaceBoulderOnSwitch() {
		basic.placeSwitch(new Point(3, 1));
		basic.placeComputerAgent(testBoulder, new Point(3, 1));
		assert(basic.winConditionSwitch() == true);
	}
	
	/**
	 * Test simple untrigger switch
	 */
	@Test
	public void testSimpleUntrigger() {
		basic.placeSwitch(new Point(2, 1));
		basic.placeComputerAgent(testBoulder, new Point(2, 1));
		basic.placePlayer(player, new Point(1, 1));
		basic.updatePlayer(Direction.RIGHT);
		assert(basic.winConditionSwitch() == false);
	}
	
	/**
	 * test 2 switch
	 */
	@Test
	public void testTwoSwitch() {
		basic.placeSwitch(new Point(2, 1));
		basic.placeComputerAgent(testBoulder, new Point(2, 1));
		basic.placePlayer(player, new Point(1, 1));
		basic.placeSwitch(new Point(1, 3));
		basic.placeComputerAgent(testBoulder, new Point(1, 2));
		assert(basic.winConditionSwitch() == false);
		basic.updatePlayer(Direction.DOWN);
		assert(basic.winConditionSwitch() == true);
	}

}
