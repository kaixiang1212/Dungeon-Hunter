package Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import Model.ComputerAgent;
import Model.Dungeon;
import Model.Hunter;
import Model.Player;
import Model.Tile.TileType;

public class testStandardChase {
	Dungeon testDun;
	Player testPlayer;

	@Before
	public void initTest() {
	    testDun = new Dungeon(3);
	    testPlayer = new Player();
	}

	@Test
	public void testVanillaChase() {
		ComputerAgent enemy = new Hunter();

		testDun.placeComputerAgent(enemy, new Point(3, 1));
		testDun.placePlayer(testPlayer, new Point(1, 1));
		testDun.updateAgents();
		assertEquals(new Point(2,1), enemy.getPos());
		testDun.updateAgents();
		assertEquals(new Point(1,1), enemy.getPos());
	}

	@Test
	public void testObstacleChase() {
		ComputerAgent enemy = new Hunter();

		testDun.placeComputerAgent(enemy, new Point(3, 1));
		testDun.placePlayer(testPlayer, new Point(1, 1));
		testDun.placeTile(TileType.DESTRUCTABLE_WALL, new Point(2, 1));
		testDun.updateAgents();
		assertNotEquals(new Point(2,1), enemy.getPos());
		testDun.updateAgents();
		testDun.updateAgents();
		testDun.updateAgents();
		assertEquals(testDun.getPlayerPos(), enemy.getPos());
	}
}
