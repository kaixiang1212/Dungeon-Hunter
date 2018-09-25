package Test;

import Model.Direction;
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
		testDun.updatePlayer(Direction.UP);
		assertEquals(testDun.getPlayerPos(), new Point(2,1));
	}
	
	@Test
	public void testDown() {
		Point playerStart = new Point(2,2);

		testDun.placePlayer(testPlayer, playerStart);
		testDun.updatePlayer(Direction.DOWN);
		assertEquals(testDun.getPlayerPos(), new Point(2,3));
	}
	
	@Test
	public void testLeft() {
		Point playerStart = new Point(2,2);

		testDun.placePlayer(testPlayer, playerStart);
		testDun.updatePlayer(Direction.LEFT);
		assertEquals(testDun.getPlayerPos(), new Point(1,2));
	}
	
	@Test
	public void testRight() {
		Point playerStart = new Point(2,2);

		testDun.placePlayer(testPlayer, playerStart);
		testDun.updatePlayer(Direction.RIGHT);
		assertEquals(testDun.getPlayerPos(), new Point(3,2));
	}
	
	@Test 
	public void testCoverAll() {
		Point playerStart = new Point(2,2);

		testDun.placePlayer(testPlayer, playerStart);
		testDun.updatePlayer(Direction.LEFT);
		assertEquals(testDun.getPlayerPos(), new Point(1,2));
		testDun.updatePlayer(Direction.UP);
		assertEquals(testDun.getPlayerPos(), new Point(1,1));
		testDun.updatePlayer(Direction.RIGHT);
		assertEquals(testDun.getPlayerPos(), new Point(2,1));
		testDun.updatePlayer(Direction.RIGHT);
		assertEquals(testDun.getPlayerPos(), new Point(3,1));
		testDun.updatePlayer(Direction.DOWN);
		assertEquals(testDun.getPlayerPos(), new Point(3,2));
		testDun.updatePlayer(Direction.DOWN);
		assertEquals(testDun.getPlayerPos(), new Point(3,3));
		testDun.updatePlayer(Direction.LEFT);
		assertEquals(testDun.getPlayerPos(), new Point(2,3));
		testDun.updatePlayer(Direction.LEFT);
		assertEquals(testDun.getPlayerPos(), new Point(1,3));
		testDun.updatePlayer(Direction.UP);
		assertEquals(testDun.getPlayerPos(), new Point(1,2));
		testDun.updatePlayer(Direction.UP);
		assertEquals(testDun.getPlayerPos(), new Point(1,1));
		testDun.updatePlayer(Direction.DOWN);
		assertEquals(testDun.getPlayerPos(), new Point(1,2));
		testDun.updatePlayer(Direction.RIGHT);
		assertEquals(testDun.getPlayerPos(), new Point(2,2));
		
	}
}
