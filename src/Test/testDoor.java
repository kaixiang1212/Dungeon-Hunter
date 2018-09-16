package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;


import Model.ComputerAgent;
import Model.Door;
import Model.Dungeon;
import Model.Hunter;
import Model.Key;
import Model.Player;
import Model.Tile.TileType;

class testDoor {

	Dungeon basic = new Dungeon(3);
	Player player = new Player();
	ComputerAgent enemy = new Hunter();

	@Test
	public void testClosedDoorObstructPlayer() {
		basic.placePlayer(player, new Point(1, 1));
		basic.placeDoorKey(new Point(2, 1), new Point(3, 3));
		basic.updatePlayer("d");
		assertEquals(new Point(1, 1), basic.getPlayerPos());
	}
	
	@Test
	public void testClosedDoorObstructEnemy() {
		basic.placePlayer(player, new Point(1, 1));
		basic.placeDoorKey(new Point(2, 1), new Point(3, 3));
		basic.placeComputerAgent(enemy, new Point(3, 1));
		basic.updateAgents();
		System.out.println(basic.isValidMove(new Point(2, 1)));
		assertEquals(new Point(3, 2), enemy.getPos());
	}
	
	@Test
	public void testUnlockDoor() {
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
		basic.placePlayer(player, new Point(1, 1));
		basic.placeDoorKey(new Point(2, 2), new Point(2, 1));
		assertFalse(basic.isValidMove(new Point(2, 2)));
		player.pickup(new Key(1));
		Door door = (Door )basic.getTileGrid().get(new Point(2, 2));
		door.unlockDoor(player.getKeys());
		assertTrue(basic.isValidMove(new Point(2, 2)));
	}

}
