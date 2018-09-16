package Test;

import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

	@Test
	public void testClosedDoorObstructPlayer() {
		
		Dungeon basic = new Dungeon(3);
		Player player = new Player();
		
		basic.placePlayer(player, new Point(1, 1));
		basic.placeDoorKey(new Point(2, 1), new Point(3, 3));
		basic.updatePlayer("d");
		
		assertEquals(new Point(1, 1), basic.getPlayerPos());
	}
	
	@Test
	public void testClosedDoorObstructEnemy() {
		
		Dungeon basic = new Dungeon(3);
		Player player = new Player();
		
		ComputerAgent enemy = new Hunter();
		
		basic.placePlayer(player, new Point(1, 1));
		basic.placeDoorKey(new Point(2, 1), new Point(3, 3));
		basic.placeComputerAgent(enemy, new Point(3, 1));
		basic.updateAgents();
		//System.out.println(basic.isValidMove(new Point(2, 1)));
		assertEquals(new Point(3, 2), enemy.getPos());
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
