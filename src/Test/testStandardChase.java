package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.awt.Point;

import org.junit.Test;

import Model.ComputerAgent;
import Model.Dungeon;
import Model.Hunter;
import Model.Player;
import Model.Tile.TileType;

public class testStandardChase {

	Dungeon basicDungeon = new Dungeon(3);
	ComputerAgent enemy = new Hunter();
	Player player = new Player();
	
	@Test
	public void testVanillaChase() {
		basicDungeon.placeComputerAgent(enemy, new Point(3, 1));
		basicDungeon.placePlayer(player, new Point(1, 1));
		basicDungeon.updateAgents();
		assertEquals(new Point(2,1), enemy.getPos());
		basicDungeon.updateAgents();
		assertEquals(new Point(1,1), enemy.getPos());
	}

	@Test
	public void testObstacleChase() {
		basicDungeon.placeComputerAgent(enemy, new Point(3, 1));
		basicDungeon.placePlayer(player, new Point(1, 1));
		basicDungeon.placeTile(TileType.DESTRUCTABLE_WALL, new Point(2, 1));
		basicDungeon.updateAgents();
		assertNotEquals(new Point(2,1), enemy.getPos());
		basicDungeon.updateAgents();
		basicDungeon.updateAgents();
		basicDungeon.updateAgents();
		assertEquals(basicDungeon.getPlayerPos(), enemy.getPos());
	}
}
