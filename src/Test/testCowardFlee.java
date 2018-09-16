package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.awt.Point;


import Model.ComputerAgent;
import Model.Coward;
import Model.Dungeon;
import Model.Player;
import Model.Tile.TileType;

public class testCowardFlee {


	Dungeon basicDungeon = null;
	ComputerAgent coward = null;
	Player player = null;

	@Test
	public void testDirectChase() {
		this.basicDungeon = new Dungeon(6);
		this.coward = new Coward();
		this.player = new Player();

		basicDungeon.placeComputerAgent(coward, new Point(5, 1));
		basicDungeon.placePlayer(player, new Point(1,1));
		basicDungeon.updateAgents();
		assertEquals(new Point(4,1), coward.getPos());
		basicDungeon.updateAgents();
		assertEquals(new Point(3,1), coward.getPos());
	}
	
	@Test
	public void testDirectFlee() {
	    this.basicDungeon = new Dungeon(6);
		this.coward = new Coward();
		this.player = new Player();

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
		this.basicDungeon = new Dungeon(6);
		this.coward = new Coward();
		this.player = new Player();

		basicDungeon.placeComputerAgent(coward, new Point(5, 1));
		basicDungeon.placePlayer(player, new Point(1,1));
		basicDungeon.placeTile(TileType.DESTRUCTABLE_WALL, new Point(4, 1));

		// Expected Moved down
		basicDungeon.updateAgents();
		assertEquals(new Point(5, 2), coward.getPos());

		// Expected Moved Right
		basicDungeon.updateAgents();
		assertEquals(new Point(4, 2), coward.getPos());
	}
	
	@Test
	public void testObstacleFlee() {
		this.basicDungeon = new Dungeon(6);
		this.coward = new Coward();
		this.player = new Player();

		basicDungeon.placeComputerAgent(coward, new Point(2, 1));
		basicDungeon.placePlayer(player, new Point(1,1));
		basicDungeon.placeTile(TileType.DESTRUCTABLE_WALL, new Point(3, 1));

		// Expected Moved down
		basicDungeon.updateAgents();
		assertEquals(new Point(2,2), coward.getPos());

		// Expected Move away
		basicDungeon.updateAgents();
		assertEquals(new Point(3,2), coward.getPos());
	}
}
