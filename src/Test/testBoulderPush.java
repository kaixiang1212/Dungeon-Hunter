package Test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Model.Boulder;
import Model.Dungeon;
import Model.Player;
import Model.Tile.TileType;

public class testBoulderPush {

	Dungeon d = new Dungeon(3);
	Player p = new Player();
	Boulder b = new Boulder(null);
	
	//Pushes boulder into empty square, attempts to push into map boundary, fails
	@Test
	public void testBasicPush() {
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
}
