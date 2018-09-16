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
	public void testNoWinConditionAdjacent() {
		Dungeon basicDungeon = new Dungeon(3);
		ComputerAgent strategist = new Strategist();
		Player player = new Player();

		basicDungeon.placeComputerAgent(strategist, new Point(2, 2));
		basicDungeon.placePlayer(player, new Point(1,2));
		basicDungeon.updateAgents();
		assertEquals(strategist.getPos(), new Point(1, 2));


	}

	@Test
	public void testNoWinConditionDiag() {
		Dungeon basicDungeon = new Dungeon(3);
		ComputerAgent strategist = new Strategist();
		Player player = new Player();

		basicDungeon.placeComputerAgent(strategist, new Point(2, 2));
		basicDungeon.placePlayer(player, new Point(1,1));
		basicDungeon.updateAgents();
		assertEquals(strategist.getPos(), new Point(1, 2));
	}


	@Test
	public void testExitConditionDirect() {
		Dungeon basicDungeon = new Dungeon(3);
		ComputerAgent strategist = new Strategist();
		Player player = new Player();

		basicDungeon.placeComputerAgent(strategist, new Point(2, 2));
		basicDungeon.placePlayer(player, new Point(1, 1));
		basicDungeon.placeTile(TileType.EXIT, new Point(3,1));
		basicDungeon.updateAgents();
		assertEquals(strategist.getPos(),new Point(2, 1));
	}

	@Test
	public void testExitConditionDiag() {
		Dungeon basicDungeon = new Dungeon(4);
		ComputerAgent strategist = new Strategist();
		Player player = new Player();

		basicDungeon.placeComputerAgent(strategist, new Point(4, 3));
		basicDungeon.placePlayer(player, new Point(1, 1));
		basicDungeon.placeTile(TileType.EXIT, new Point(4,1));
		basicDungeon.updateAgents();
		assertEquals(strategist.getPos(),new Point(3, 3));
	}

	@Test
	public void testExitConditionBlock() {
		Dungeon basicDungeon = new Dungeon(4);
		ComputerAgent strategist = new Strategist();
		Player player = new Player();

		basicDungeon.placeComputerAgent(strategist, new Point(2, 1));
		basicDungeon.placePlayer(player, new Point(1, 1));
		basicDungeon.placeTile(TileType.EXIT, new Point(4,1));
		basicDungeon.updateAgents();
		assertEquals(strategist.getPos(),new Point(2, 1));
	}

	@Test
	public void testExitConditionDiagPrioritiseKillUp() {
		Dungeon basicDungeon = new Dungeon(4);
		ComputerAgent strategist = new Strategist();
		Player player = new Player();

		basicDungeon.placeComputerAgent(strategist, new Point(1, 2));
		basicDungeon.placePlayer(player, new Point(1, 1));
		basicDungeon.placeTile(TileType.EXIT, new Point(4,1));

		//kill
		basicDungeon.updateAgents();
		assertEquals(strategist.getPos(),new Point(1, 1));
	}

	@Test
	public void testExitConditionDiagPrioritiseKillRight() {
		Dungeon basicDungeon = new Dungeon(4);
		ComputerAgent strategist = new Strategist();
		Player player = new Player();

		basicDungeon.placeComputerAgent(strategist, new Point(3, 1));
		basicDungeon.placePlayer(player, new Point(4, 1));
		basicDungeon.placeTile(TileType.EXIT, new Point(4,4));

		//kill
		basicDungeon.updateAgents();
		assertEquals(strategist.getPos(),new Point(4, 1));
	}

	@Test
	public void testExitConditionDiagPrioritiseKillDown() {
		Dungeon basicDungeon = new Dungeon(4);
		ComputerAgent strategist = new Strategist();
		Player player = new Player();

		basicDungeon.placeComputerAgent(strategist, new Point(4, 3));
		basicDungeon.placePlayer(player, new Point(4, 4));
		basicDungeon.placeTile(TileType.EXIT, new Point(1,4));

		//kill
		basicDungeon.updateAgents();
		assertEquals(strategist.getPos(),new Point(4, 4));
	}

	// TODO: Consider adding more tests.
}
