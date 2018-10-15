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

	Dungeon testDun;
	Player testPlayer;
	Boulder testBoulder;

	@Before public void initTest() {

		testDun= new Dungeon(3);
		testPlayer = new Player();
		testBoulder = new Boulder(null);
	} // no @After required



	//Pushes boulder into empty square, attempts to push into map boundary, fails
	@Test
	public void testPushInvuln() {
		testDun.placePlayer(testPlayer, new Point(1,1));
		testDun.placeComputerAgent(testBoulder, new Point(2,1));
		testDun.updatePlayer(Direction.RIGHT);
		assertEquals(testDun.getPlayerPos(), new Point(2,1));
		assertEquals(testBoulder.getPos(), new Point(3,1));
		testDun.updatePlayer(Direction.RIGHT);
		assertEquals(testDun.getPlayerPos(), new Point(2,1));
	}
	//Boulder disappears if pushed into pit!
	@Test
	public void testPushIntoPit() {
		testDun.placeTile(new Pit(), new Point(3,1));
		testDun.placePlayer(testPlayer, new Point(1,1));
		testDun.placeComputerAgent(testBoulder, new Point(2,1));
		testDun.updatePlayer(Direction.RIGHT);
		assertEquals(testDun.getAgent(new Point(3,1)), null);
	}
	//Push boulder into destructable wall, fails
	@Test
	public void testPushWall() {
		testDun.placePlayer(testPlayer, new Point(1,1));
		testDun.placeComputerAgent(testBoulder, new Point(2,1));
		testDun.placeTile(new Wall(), new Point(3,1));
		testDun.updatePlayer(Direction.RIGHT);
		assertEquals(testBoulder.getPos(), new Point(2,1));
		assertEquals(testDun.getPlayerPos(), new Point(1,1));
	}
	@Test
	public void testPushBoulder() {
		testDun.placePlayer(testPlayer, new Point(1,1));
		testDun.placeComputerAgent(testBoulder, new Point(2,1));
		testDun.placeComputerAgent(new Boulder(null), new Point(3,1));
		testDun.updatePlayer(Direction.RIGHT);
		assertEquals(testBoulder.getPos(), new Point(2,1));
	
	}
	//Push boulder into closed door, fails
	@Test
	public void testPushClosedDoor() {
		testDun.placePlayer(testPlayer, new Point(1,1));
		testDun.placeComputerAgent(testBoulder, new Point(2,1));
		testDun.placeTile(new Door(), new Point(3,1));
		testDun.updatePlayer(Direction.RIGHT);
		assertEquals(new Point(2, 1), testBoulder.getPos());
		assertEquals(testDun.getPlayerPos(), new Point(1,1));
	}
}
