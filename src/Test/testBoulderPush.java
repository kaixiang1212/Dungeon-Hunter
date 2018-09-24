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

	Dungeon d;
	Player p;
	Boulder b;

	@Before public void initTest() {

		d = new Dungeon(3);
		p = new Player();
		b = new Boulder(null);
	} // no @After required



	//Pushes boulder into empty square, attempts to push into map boundary, fails
	@Test
	public void testPushInvuln() {
		d.placePlayer(p, new Point(1,1));
		d.placeComputerAgent(b, new Point(2,1));
		d.updatePlayer("d");
		assertEquals(d.getPlayerPos(), new Point(2,1));
		assertEquals(b.getPos(), new Point(3,1));
		d.updatePlayer("d");
		assertEquals(d.getPlayerPos(), new Point(2,1));		
	}
	//Boulder disappears if pushed into pit!
	@Test
	public void testPushIntoPit() {
		d.placeTile(TileType.PIT, new Point(3,1));
		d.placePlayer(p, new Point(1,1));
		d.placeComputerAgent(b, new Point(2,1));
		d.updatePlayer("d");
		assertEquals(d.getAgent(new Point(3,1)), null);
	}
	//Push boulder into destructable wall, fails
	@Test
	public void testPushWall() {
		d.placePlayer(p, new Point(1,1));
		d.placeComputerAgent(b, new Point(2,1));
		d.placeTile(TileType.DESTRUCTABLE_WALL, new Point(3,1));
		d.updatePlayer("d");
		assertEquals(b.getPos(), new Point(2,1));
		assertEquals(d.getPlayerPos(), new Point(1,1));
	}
	@Test
	public void testPushBoulder() {
		d.placePlayer(p, new Point(1,1));
		d.placeComputerAgent(b, new Point(2,1));
		d.placeComputerAgent(new Boulder(null), new Point(3,1));
		d.updatePlayer("d");
		assertEquals(b.getPos(), new Point(2,1));
	
	}
	//Push boulder into closed door, fails
	@Test
	public void testPushClosedDoor() {
		d.placePlayer(p, new Point(1,1));
		d.placeComputerAgent(b, new Point(2,1));
		d.placeTile(TileType.CLOSED_DOOR, new Point(3,1));
		d.updatePlayer("d");
		assertEquals(new Point(2, 1), b.getPos());
		assertEquals(d.getPlayerPos(), new Point(1,1));
	}
}
