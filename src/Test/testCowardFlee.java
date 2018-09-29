package Test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.awt.Point;


import Model.ComputerAgent;
import Model.Coward;
import Model.Dungeon;
import Model.Player;
import Model.Tile.Type;

public class testCowardFlee {


	Dungeon basicDungeon;
	ComputerAgent coward;
	Player player;

	@Before public void initTest() {
		this.basicDungeon = new Dungeon(6);
		this.coward = new Coward();
		this.player = new Player();
	}

	@Test
	public void testDirectChase() {

		basicDungeon.placeComputerAgent(coward, new Point(5, 1));
		basicDungeon.placePlayer(player, new Point(1,1));
		basicDungeon.updateAgents();
		assertEquals(new Point(4,1), coward.getPos());
		basicDungeon.updateAgents();
		assertEquals(new Point(3,1), coward.getPos());
	}

	@Test
	public void testDirectFlee() {

		basicDungeon.placeComputerAgent(coward, new Point(2, 1));
		basicDungeon.placePlayer(player, new Point(1,1));
		basicDungeon.updateAgents();

		assertEquals(new Point(3,1), coward.getPos());
		basicDungeon.updateAgents();
		assertEquals(new Point(4,1), coward.getPos());

		// Regain courage
		basicDungeon.updateAgents();
		assertEquals(new Point(3,1), coward.getPos());
	}

	@Test
	public void testObstacleChase() {

		basicDungeon.placeComputerAgent(coward, new Point(5, 1));
		basicDungeon.placePlayer(player, new Point(1,1));
		basicDungeon.placeTile(Type.DESTRUCTIBLE_WALL, new Point(4, 1));

		// Expected Moved down
		basicDungeon.updateAgents();
		assertEquals(new Point(5, 2), coward.getPos());

		// Expected Moved Right
		basicDungeon.updateAgents();
		assertEquals(new Point(4, 2), coward.getPos());
	}

	@Test
	public void testObstacleFlee() {

		basicDungeon.placeComputerAgent(coward, new Point(2, 1));
		basicDungeon.placePlayer(player, new Point(1,1));
		basicDungeon.placeTile(Type.DESTRUCTIBLE_WALL, new Point(3, 1));

		// Expected Moved down
		basicDungeon.updateAgents();
		assertEquals(new Point(2,2), coward.getPos());

		// Expected Move away
		basicDungeon.updateAgents();
		assertEquals(new Point(3,2), coward.getPos());
	}
}
