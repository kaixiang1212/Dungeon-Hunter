package Test;

import static org.junit.Assert.*;

import java.awt.Point;

import Controller.Direction;
import Model.*;
import org.junit.Before;
import org.junit.Test;

import Model.Tile.Door;
import Model.Tile.Pit;
import Model.Tile.Wall;

public class testBoulderPush {

	Dungeon basic;
	Player player;
	Boulder boulder;

	@Before public void initTest() {

		basic= new Dungeon(3);
		player = new Player();
		boulder = new Boulder(null);
	} // no @After required



	//Pushes boulder into empty square, attempts to push into map boundary, fails
	@Test
	public void testPushInvuln() {
		basic.placePlayer(player, new Point(1,1));
		basic.placeComputerAgent(boulder, new Point(2,1));
		basic.updatePlayer(Direction.RIGHT);
		assertEquals(basic.getPlayerPos(), new Point(2,1));
		assertEquals(boulder.getPos(), new Point(3,1));
		basic.updatePlayer(Direction.RIGHT);
		assertEquals(basic.getPlayerPos(), new Point(2,1));
	}
	//Boulder disappears if pushed into pit!
	@Test
	public void testPushIntoPit() {
		basic.placeTile(new Pit(), new Point(3,1));
		basic.placePlayer(player, new Point(1,1));
		basic.placeComputerAgent(boulder, new Point(2,1));
		basic.updatePlayer(Direction.RIGHT);
		assertEquals(basic.getAgent(new Point(3,1)), null);
	}
	//Push boulder into destructable wall, fails
	@Test
	public void testPushWall() {
		basic.placePlayer(player, new Point(1,1));
		basic.placeComputerAgent(boulder, new Point(2,1));
		basic.placeTile(new Wall(), new Point(3,1));
		basic.updatePlayer(Direction.RIGHT);
		assertEquals(boulder.getPos(), new Point(2,1));
		assertEquals(basic.getPlayerPos(), new Point(1,1));
	}
	@Test
	public void testPushBoulder() {
		basic.placePlayer(player, new Point(1,1));
		basic.placeComputerAgent(boulder, new Point(2,1));
		basic.placeComputerAgent(new Boulder(null), new Point(3,1));
		basic.updatePlayer(Direction.RIGHT);
		assertEquals(boulder.getPos(), new Point(2,1));
	
	}
	//Push boulder into closed door, fails
	@Test
	public void testPushClosedDoor() {
		basic.placePlayer(player, new Point(1,1));
		basic.placeComputerAgent(boulder, new Point(2,1));
		basic.placeTile(new Door(), new Point(3,1));
		basic.updatePlayer(Direction.RIGHT);
		assertEquals(new Point(2, 1), boulder.getPos());
		assertEquals(basic.getPlayerPos(), new Point(1,1));
	}
}
