package Test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.Boulder;
import Model.Dungeon;
import Model.Player;
import Model.Tile;
import Model.Tile.TileType;

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
		testDun.updatePlayer("d");
		assertEquals(testDun.getPlayerPos(), new Point(2,1));
		assertEquals(testBoulder.getPos(), new Point(3,1));
		testDun.updatePlayer("d");
		assertEquals(testDun.getPlayerPos(), new Point(2,1));
	}
	//Boulder disappears if pushed into pit!
	@Test
	public void testPushIntoPit() {
		testDun.placeTile(TileType.PIT, new Point(3,1));
		testDun.placePlayer(testPlayer, new Point(1,1));
		testDun.placeComputerAgent(testBoulder, new Point(2,1));
		testDun.updatePlayer("d");
		assertEquals(testDun.getAgent(new Point(3,1)), null);
	}
	//Push boulder into destructable wall, fails
	@Test
	public void testPushWall() {
		testDun.placePlayer(testPlayer, new Point(1,1));
		testDun.placeComputerAgent(testBoulder, new Point(2,1));
		testDun.placeTile(TileType.DESTRUCTABLE_WALL, new Point(3,1));
		testDun.updatePlayer("d");
		assertEquals(testBoulder.getPos(), new Point(2,1));
		assertEquals(testDun.getPlayerPos(), new Point(1,1));
	}
	@Test
	public void testPushBoulder() {
		testDun.placePlayer(testPlayer, new Point(1,1));
		testDun.placeComputerAgent(testBoulder, new Point(2,1));
		testDun.placeComputerAgent(new Boulder(null), new Point(3,1));
		testDun.updatePlayer("d");
		assertEquals(testBoulder.getPos(), new Point(2,1));
	
	}
	//Push boulder into closed door, fails
	@Test
	public void testPushClosedDoor() {
		testDun.placePlayer(testPlayer, new Point(1,1));
		testDun.placeComputerAgent(testBoulder, new Point(2,1));
		testDun.placeTile(TileType.CLOSED_DOOR, new Point(3,1));
		testDun.updatePlayer("d");
		assertEquals(new Point(2, 1), testBoulder.getPos());
		assertEquals(testDun.getPlayerPos(), new Point(1,1));
	}
}
