package Test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;


import Model.ComputerAgent;
import Model.Door;
import Model.Dungeon;
import Model.Hunter;
import Model.Key;
import Model.Player;
import Model.Tile.TileType;

public class testDoor {

	@Test
	public void testClosedDoorObstructPlayer() {
		Dungeon basic = new Dungeon(3);
		Player player = new Player();
		ComputerAgent enemy = new Hunter();

		basic.placePlayer(player, new Point(1, 1));
		basic.placeDoorKey(new Point(2, 1), new Point(3, 3));
		basic.updatePlayer("d");
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
		basic.placeComputerAgent(enemy, new Point(3, 1));

		assertFalse(basic.isValidMove(new Point(2, 1)));
		assertFalse(basic.isValidMove(new Point(2, 2)));
		assertTrue(basic.isValidMove(new Point(3, 1)));

		basic.updateAgents();

		assertEquals(new Point(3, 1), enemy.getPos());
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

		basic.updateAgents();

		// Known test failure - likely source updateAgents.
		assertEquals(new Point(1, 3), enemy.getPos());
	}

	@Test
	public void testUnlockDoor() {
		Dungeon basic = new Dungeon(3);
		Player player = new Player();
		ComputerAgent enemy = new Hunter();

		basic.placePlayer(player, new Point(1, 1));
		basic.placeDoorKey(new Point(2, 2), new Point(2, 1));

		player.pickup(new Key(1));
		// get the door to unlock
		Door door = (Door )basic.getTileGrid().get(new Point(2, 2));
		door.unlockDoor(player.getKeys());
	
		assert(basic.getTileGrid().get(new Point(2, 2)).isType(TileType.OPEN_DOOR));
	}
	
	@Test
	public void testMoveableAfterUnlock() {
		Dungeon basic = new Dungeon(3);
		Player player = new Player();

		basic.placePlayer(player, new Point(1, 1));
		basic.placeDoorKey(new Point(2, 2), new Point(2, 1));
		assertFalse(basic.isValidMove(new Point(2, 2)));
		player.pickup(new Key(1));
		Door door = (Door )basic.getTileGrid().get(new Point(2, 2));
		door.unlockDoor(player.getKeys());
		assertTrue(basic.isValidMove(new Point(2, 2)));
	}

}
