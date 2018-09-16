package Test;

import org.junit.Test;
import Model.Dungeon;
import Model.Player;

import static org.junit.Assert.assertEquals;

import java.awt.Point;


public class testPlayerMovement {

	@Test
	public void testUp() {
		Dungeon d = new Dungeon(3);
		Player p = new Player();
		Point playerStart = new Point(2,2);

		d.placePlayer(p, playerStart);
		assertEquals(d.getPlayerPos(), playerStart);
		d.updatePlayer("w");
		assertEquals(d.getPlayerPos(), new Point(2,1));
	}
	
	@Test
	public void testDown() {
		Dungeon d = new Dungeon(3);
		Player p = new Player();
		Point playerStart = new Point(2,2);

		d.placePlayer(p, playerStart);
		d.updatePlayer("s");
		assertEquals(d.getPlayerPos(), new Point(2,3));
	}
	
	@Test
	public void testLeft() {
		Dungeon d = new Dungeon(3);
		Player p = new Player();
		Point playerStart = new Point(2,2);

		d.placePlayer(p, playerStart);
		d.updatePlayer("a");
		assertEquals(d.getPlayerPos(), new Point(1,2));
	}
	
	@Test
	public void testRight() {
		Dungeon d = new Dungeon(3);
		Player p = new Player();
		Point playerStart = new Point(2,2);

		d.placePlayer(p, playerStart);
		d.updatePlayer("d");
		assertEquals(d.getPlayerPos(), new Point(3,2));
	}
	
	@Test 
	public void testCoverAll() {
		Dungeon d = new Dungeon(3);
		Player p = new Player();
		Point playerStart = new Point(2,2);

		d.placePlayer(p, playerStart);
		d.updatePlayer("a");
		assertEquals(d.getPlayerPos(), new Point(1,2));
		d.updatePlayer("w");
		assertEquals(d.getPlayerPos(), new Point(1,1));
		d.updatePlayer("d");
		assertEquals(d.getPlayerPos(), new Point(2,1));
		d.updatePlayer("d");
		assertEquals(d.getPlayerPos(), new Point(3,1));
		d.updatePlayer("s");
		assertEquals(d.getPlayerPos(), new Point(3,2));
		d.updatePlayer("s");
		assertEquals(d.getPlayerPos(), new Point(3,3));
		d.updatePlayer("a");
		assertEquals(d.getPlayerPos(), new Point(2,3));
		d.updatePlayer("a");
		assertEquals(d.getPlayerPos(), new Point(1,3));
		d.updatePlayer("w");
		assertEquals(d.getPlayerPos(), new Point(1,2));
		d.updatePlayer("w");
		assertEquals(d.getPlayerPos(), new Point(1,1));
		d.updatePlayer("s");
		assertEquals(d.getPlayerPos(), new Point(1,2));
		d.updatePlayer("d");
		assertEquals(d.getPlayerPos(), new Point(2,2));
		
	}
}
