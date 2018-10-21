package Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import Model.Dungeon;
import Model.Player;
import Model.ComputerAgent.ComputerAgent;
import Model.ComputerAgent.Hunter;
import Model.Tile.Wall;

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
		testDun.placeTile(new Wall(), new Point(2, 1));
		testDun.updateAgents();
		assertNotEquals(new Point(2,1), enemy.getPos());
		testDun.updateAgents();
		testDun.updateAgents();
		testDun.updateAgents();
		assertEquals(testDun.getPlayerPos(), enemy.getPos());
	}
}
