package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import Controller.Direction;
import Model.*;
import Model.ComputerAgent.ComputerAgent;
import Model.ComputerAgent.Hunter;
import Model.Item.Key;
import Model.Tile.Door;

import org.junit.Before;
import org.junit.Test;

import Model.Tile.Wall;

public class testDoor {

	Dungeon basic;
	Player player;
	Door door1;
	Door door2;
	Key key1;
	Key key2;
	Point pointOne = new Point(1, 1);

	@Before
	public void initTest() {
		basic = new Dungeon(3);
		player = new Player();
		door1 = new Door();
		key1 = new Key();
		key1.setCode(door1.getCode());
		door2 = new Door();
		key2 = new Key();
		key2.setCode(door2.getCode());
	}
	
	@Test
	public void testClosedDoorNotValidMove() {
		basic.placeTile(new Door(), new Point(2, 1));
		assertFalse(basic.isValidMoveAgent(new Point(2, 1)));
		assertFalse(basic.isValidMoveBasic(new Point(2, 1)));
		assertFalse(basic.isValidMove(new Point(2, 1)));
		
	}

	@Test
	public void testClosedDoorObstructPlayer() {

		basic.placePlayer(player, new Point(1, 1));

		basic.placeTile(new Door(), new Point(2, 1));
		basic.placeItem(new Key(), new Point(3,3));
		basic.updatePlayer(Direction.RIGHT);
		
		assertEquals(new Point(1, 1), basic.getPlayerPos());
	}
	
	@Test
	public void testClosedDoorObstructEnemyShortestPathHorizontal() {
		Dungeon basic = new Dungeon(3);
		Player player = new Player();
		ComputerAgent enemy = new Hunter();

		basic.placePlayer(player, new Point(1, 1));

		//divide player and hunter with a closed door and walls.
		basic.placeTile(new Door(), new Point(2, 1));
		basic.placeTile(new Wall(), new Point(2, 2));
		basic.placeTile(new Wall(), new Point(2, 3));
		basic.placeComputerAgent(enemy, new Point(3, 1));

		assertFalse(basic.isValidMove(new Point(2, 1)));
		basic.updateAgents();

		assertEquals(new Point(3, 1), enemy.getPos());
	}

	@Test
	public void testClosedDoorObstructEnemyWithOptionHorizontal() {
		Dungeon basic = new Dungeon(3);
		Player player = new Player();
		ComputerAgent enemy = new Hunter();

		basic.placePlayer(player, new Point(1, 1));

		// divide player and hunter with a closed door and walls.
		basic.placeTile(new Wall(), new Point(2, 1));
		basic.placeTile(new Door(), new Point(2,2));
		basic.placeTile(new Wall(), new Point(2, 3));
		basic.placeComputerAgent(enemy, new Point(3, 2));

		assertFalse(basic.isValidMove(new Point(2, 1)));
		assertFalse(basic.isValidMove(new Point(2, 2)));
		assertTrue(basic.isValidMove(new Point(3, 1)));

		basic.updateAgents();

		assertEquals(new Point(3, 2), enemy.getPos());
	}

	@Test
	public void testClosedDoorObstructEnemyShortestPathVert() {
		Dungeon basic = new Dungeon(3);
		Player player = new Player();
		ComputerAgent enemy = new Hunter();

		basic.placePlayer(player, new Point(1, 1));

		//divide player and hunter with a closed door and walls.
		basic.placeTile(new Door(), new Point(1, 2));
		basic.placeTile(new Wall(), new Point(2, 2));
		basic.placeTile(new Wall(), new Point(3, 3));
		basic.placeComputerAgent(enemy, new Point(1, 3));

		assertFalse(basic.isValidMove(new Point(1, 2)));
		basic.updateAgents();

		assertEquals(new Point(1, 3), enemy.getPos());
	}

	@Test
	public void testClosedDoorObstructEnemyWithOptionVertical() {
		Dungeon basic = new Dungeon(3);
		Player player = new Player();
		ComputerAgent enemy = new Hunter();

		basic.placePlayer(player, new Point(1, 1));

		//divide player and hunter with a closed door and walls.
		basic.placeTile(new Wall(), new Point(1, 2));
		basic.placeTile(new Door(), new Point(2, 2));
		basic.placeTile(new Wall(), new Point(3, 2));
		basic.placeComputerAgent(enemy, new Point(2, 3));

		assertFalse(basic.isValidMove(new Point(1, 2)));
		assertFalse(basic.isValidMove(new Point(2, 2)));
		assertFalse(basic.isValidMove(new Point(3, 2)));
		assertTrue(basic.isValidMove(new Point(1, 3)));
		assertTrue(basic.isValidMove(new Point(3, 3)));

		basic.updateAgents();

		// Known test failure - likely source updateAgents.
		assertEquals(new Point(2, 3), enemy.getPos());
	}

	@Test
	public void testUnlockDoor() {
		assertTrue(key1.unlocks(door1));
	}
	
	@Test
	public void testUnlockDoorChangeState(){
		player.pickup(key1);
		assertEquals(door1.getType(), Type.ClosedDoor);
		door1.doOperation(player);
		assertEquals(door1.getType(), Type.OpenedDoor);
	}

	@Test
	public void testKeyOneCanNotUnlockDoorTwo() {
		assertFalse(key1.unlocks(door2));
		assertFalse(key2.unlocks(door1));
		assertTrue(key1.unlocks(door1));
		assertTrue(key2.unlocks(door2));
	}

	@Test
	public void testMoveableAfterUnlock() {
		basic.placeTile(door1, new Point(1, 1));
		player.pickup(key1);
		assertFalse(basic.isValidMove(new Point(1, 1)));
		door1.doOperation(player);
		assertTrue(basic.isValidMove(new Point(1, 1)));
	}
	
	@Test
	public void testKeyOneUnlocksDoorTwo() {
		basic.placeTile(door1, new Point(1, 1));
		basic.placeTile(door2, new Point(2, 2));
		player.pickup(key2);
		door1.doOperation(player);
		assertFalse(basic.isValidMove(new Point(1, 1)));
		assertFalse(basic.isValidMove(new Point(2, 2)));
		door2.doOperation(player);
		assertTrue(basic.isValidMove(new Point(2, 2)));	
	}
	
	@Test
	public void testPlayerPickupKey() {
		Point doorPoint = new Point(1, 1);
		basic.placeTile(door1, doorPoint);
		basic.placeItem(key1, new Point(2, 1));
		basic.placePlayer(player, new Point(3, 1));
		basic.updatePlayer(Direction.LEFT);
		basic.updatePlayer(Direction.LEFT);
		assertEquals(basic.getPlayerPos(), doorPoint);
	}

}
