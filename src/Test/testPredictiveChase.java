package Test;

import java.awt.Point;

import org.junit.Test;
import static org.junit.Assert.*;

import Model.ComputerAgent;
import Model.Dungeon;
import Model.Player;
import Model.Strategist;
import Model.Tile.TileType;

public class testPredictiveChase {

	@Test
	public void testNoWinCondition() {
		Dungeon basicDungeon = new Dungeon(3);
		ComputerAgent strategist = new Strategist();
		Player player = new Player();

		basicDungeon.placeComputerAgent(strategist, new Point(2, 2));
		basicDungeon.placePlayer(player, new Point(1,2));
		basicDungeon.updateAgents();
		assertEquals(strategist.getPos(), new Point(1, 1));
	}
	
	@Test
	public void testExitCondition() {
		Dungeon basicDungeon = new Dungeon(3);
		ComputerAgent strategist = new Strategist();
		Player player = new Player();

		basicDungeon.placeComputerAgent(strategist, new Point(2, 2));
		basicDungeon.placePlayer(player, new Point(1, 1));
		basicDungeon.placeTile(TileType.EXIT, new Point(3,1));
		basicDungeon.updateAgents();
		assertEquals(strategist.getPos(),new Point(2, 1));
	}
}
