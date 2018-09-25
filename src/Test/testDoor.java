package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import Controller.Direction;
import Model.*;
import org.junit.Before;
import org.junit.Test;

import Model.Tile.TileType;

public class testDoor {

	Dungeon basic;
	Player player;

	@Before public void initTest() {
		basic = new Dungeon(3);
		player = new Player();
	}

	@Test
	public void testClosedDoorObstructPlayer() {

		basic.placePlayer(player, new Point(1, 1));
		basic.placeDoorKey(new Point(2, 1), new Point(3, 3));
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
		basic.placeDoorKey(new Point(2, 1), new Point(3, 3));
		basic.placeTile(TileType.DESTRUCTABLE_WALL, new Point(2, 2));
		basic.placeTile(TileType.DESTRUCTABLE_WALL, new Point(2, 3));
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

		//divide player and hunter with a closed door and walls.
		basic.placeTile(TileType.DESTRUCTABLE_WALL, new Point(2, 1));
		basic.placeDoorKey(new Point(2, 2), new Point(3, 3));
		basic.placeTile(TileType.DESTRUCTABLE_WALL, new Point(2, 3));
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
		basic.placeDoorKey(new Point(1, 2), new Point(3, 3));
		basic.placeTile(TileType.DESTRUCTABLE_WALL, new Point(2, 2));
		basic.placeTile(TileType.DESTRUCTABLE_WALL, new Point(3, 3));
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
		basic.placeTile(TileType.DESTRUCTABLE_WALL, new Point(1, 2));
		basic.placeDoorKey(new Point(2, 2), new Point(3, 3));
		basic.placeTile(TileType.DESTRUCTABLE_WALL, new Point(3, 2));
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
		Dungeon basic = new Dungeon(3);
		Player player = new Player();

		basic.placePlayer(player, new Point(1, 1));
		basic.placeDoorKey(new Point(2, 2), new Point(2, 1));

		// get the door to unlock
		Door door = (Door )basic.getTile(new Point(2, 2));
		player.pickup(door.generateKey());
		door.unlockDoor(player.getKeys());
	
		assert(basic.getTile(new Point(2, 2)).isType(TileType.OPEN_DOOR));
	}
	
	@Test
	public void testMoveableAfterUnlock() {
		Dungeon basic = new Dungeon(3);
		Player player = new Player();

		basic.placePlayer(player, new Point(1, 1));
		basic.placeDoorKey(new Point(2, 2), new Point(2, 1));
		assertFalse(basic.isValidMove(new Point(2, 2)));
		Door door = (Door )basic.getTileGrid().get(new Point(2, 2));
		player.pickup(door.generateKey());
		door.unlockDoor(player.getKeys());
		assertTrue(basic.isValidMove(new Point(2, 2)));
	}
	
	@Test
	public void twoDoors() {
		Dungeon basic = new Dungeon(3);
		Player player = new Player();
		basic.placePlayer(player, new Point(1, 1));
		basic.placeDoorKey(new Point(2, 1), new Point(2, 3));
		basic.placeDoorKey(new Point(2, 3), new Point(3,2));
		Door doorOne = (Door )basic.getTile(new Point(2,1));
		Door doorTwo = (Door )basic.getTile(new Point(2,3));
		
		player.pickup(doorTwo.generateKey());
		doorOne.unlockDoor(player.getKeys());

		// door one with key 2 wrong key 
		assert(doorOne.isType(TileType.CLOSED_DOOR));
		// door two with key two
		doorTwo.unlockDoor(player.getKeys());
		assert(doorTwo.isType(TileType.OPEN_DOOR));
		
	}

}
