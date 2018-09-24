package Test;

import org.junit.Before;
import org.junit.Test;
import Model.Dungeon;
import Model.Player;


import static org.junit.Assert.assertEquals;

import java.awt.Point;


public class testPlayerMovement {
	Player testPlayer;
	Dungeon testDun;

	@Before public void initTest() {
		testPlayer = new Player();
		testDun = new Dungeon(3);
	}
	@Test
	public void testUp() {
		Point playerStart = new Point(2,2);

		testDun.placePlayer(testPlayer, playerStart);
		assertEquals(testDun.getPlayerPos(), playerStart);
		testDun.updatePlayer("w");
		assertEquals(testDun.getPlayerPos(), new Point(2,1));
	}
	
	@Test
	public void testDown() {
		Point playerStart = new Point(2,2);

		testDun.placePlayer(testPlayer, playerStart);
		testDun.updatePlayer("s");
		assertEquals(testDun.getPlayerPos(), new Point(2,3));
	}
	
	@Test
	public void testLeft() {
		Point playerStart = new Point(2,2);

		testDun.placePlayer(testPlayer, playerStart);
		testDun.updatePlayer("a");
		assertEquals(testDun.getPlayerPos(), new Point(1,2));
	}
	
	@Test
	public void testRight() {
		Point playerStart = new Point(2,2);

		testDun.placePlayer(testPlayer, playerStart);
		testDun.updatePlayer("d");
		assertEquals(testDun.getPlayerPos(), new Point(3,2));
	}
	
	@Test 
	public void testCoverAll() {
		Point playerStart = new Point(2,2);

		testDun.placePlayer(testPlayer, playerStart);
		testDun.updatePlayer("a");
		assertEquals(testDun.getPlayerPos(), new Point(1,2));
		testDun.updatePlayer("w");
		assertEquals(testDun.getPlayerPos(), new Point(1,1));
		testDun.updatePlayer("d");
		assertEquals(testDun.getPlayerPos(), new Point(2,1));
		testDun.updatePlayer("d");
		assertEquals(testDun.getPlayerPos(), new Point(3,1));
		testDun.updatePlayer("s");
		assertEquals(testDun.getPlayerPos(), new Point(3,2));
		testDun.updatePlayer("s");
		assertEquals(testDun.getPlayerPos(), new Point(3,3));
		testDun.updatePlayer("a");
		assertEquals(testDun.getPlayerPos(), new Point(2,3));
		testDun.updatePlayer("a");
		assertEquals(testDun.getPlayerPos(), new Point(1,3));
		testDun.updatePlayer("w");
		assertEquals(testDun.getPlayerPos(), new Point(1,2));
		testDun.updatePlayer("w");
		assertEquals(testDun.getPlayerPos(), new Point(1,1));
		testDun.updatePlayer("s");
		assertEquals(testDun.getPlayerPos(), new Point(1,2));
		testDun.updatePlayer("d");
		assertEquals(testDun.getPlayerPos(), new Point(2,2));
		
	}
}
