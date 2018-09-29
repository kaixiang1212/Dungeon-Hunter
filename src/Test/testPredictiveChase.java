package Test;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import Model.ComputerAgent;
import Model.Dungeon;
import Model.Player;
import Model.Strategist;
import Model.Tile.Type;

public class testPredictiveChase {

	Dungeon testDun;
	Player testPlayer;

	@Before public void initTest() {
		testDun = new Dungeon(3);
		testPlayer = new Player();
	}
	@Test
	public void testNoWinConditionAdjacent() {
		ComputerAgent strategist = new Strategist();

		testDun.placeComputerAgent(strategist, new Point(2, 2));
		testDun.placePlayer(testPlayer, new Point(1,2));
		testDun.updateAgents();
		assertEquals(strategist.getPos(), new Point(1, 2));


	}

	@Test
	public void testNoWinConditionDiag() {
		ComputerAgent strategist = new Strategist();

		testDun.placeComputerAgent(strategist, new Point(2, 2));
		testDun.placePlayer(testPlayer, new Point(1,1));
		testDun.updateAgents();
		assertEquals(strategist.getPos(), new Point(1, 2));
	}


	@Test
	public void testExitConditionDirect() {
		ComputerAgent strategist = new Strategist();

		testDun.placeComputerAgent(strategist, new Point(2, 2));
		testDun.placePlayer(testPlayer, new Point(1, 1));
		testDun.placeTile(Type.EXIT, new Point(3,1));
		testDun.updateAgents();
		assertEquals(strategist.getPos(),new Point(2, 1));
	}

	@Test
	public void testExitConditionDiag() {
		Dungeon basicDungeon = new Dungeon(4);
		ComputerAgent strategist = new Strategist();

		basicDungeon.placeComputerAgent(strategist, new Point(4, 3));
		basicDungeon.placePlayer(testPlayer, new Point(1, 1));
		basicDungeon.placeTile(Type.EXIT, new Point(4,1));
		basicDungeon.updateAgents();
		assertEquals(strategist.getPos(),new Point(3, 3));
	}

	@Test
	public void testExitConditionBlock() {
		Dungeon basicDungeon = new Dungeon(4);
		ComputerAgent strategist = new Strategist();

		basicDungeon.placeComputerAgent(strategist, new Point(2, 1));
		basicDungeon.placePlayer(testPlayer, new Point(1, 1));
		basicDungeon.placeTile(Type.EXIT, new Point(4,1));
		basicDungeon.updateAgents();
		assertEquals(strategist.getPos(),new Point(2, 1));
	}

	@Test
	public void testExitConditionDiagPrioritiseKillUp() {
		Dungeon basicDungeon = new Dungeon(4);
		ComputerAgent strategist = new Strategist();

		basicDungeon.placeComputerAgent(strategist, new Point(1, 2));
		basicDungeon.placePlayer(testPlayer, new Point(1, 1));
		basicDungeon.placeTile(Type.EXIT, new Point(4,1));

		//kill
		basicDungeon.updateAgents();
		assertEquals(strategist.getPos(),new Point(1, 1));
	}

	@Test
	public void testExitConditionDiagPrioritiseKillRight() {
		Dungeon basicDungeon = new Dungeon(4);
		ComputerAgent strategist = new Strategist();

		basicDungeon.placeComputerAgent(strategist, new Point(3, 1));
		basicDungeon.placePlayer(testPlayer, new Point(4, 1));
		basicDungeon.placeTile(Type.EXIT, new Point(4,4));

		//kill
		basicDungeon.updateAgents();
		assertEquals(strategist.getPos(),new Point(4, 1));
	}

	@Test
	public void testExitConditionDiagPrioritiseKillDown() {
		Dungeon basicDungeon = new Dungeon(4);
		ComputerAgent strategist = new Strategist();

		basicDungeon.placeComputerAgent(strategist, new Point(4, 3));
		basicDungeon.placePlayer(testPlayer, new Point(4, 4));
		basicDungeon.placeTile(Type.EXIT, new Point(1,4));

		//kill
		basicDungeon.updateAgents();
		assertEquals(new Point(3, 3), strategist.getPos());
	}

	// TODO: Consider adding more tests.
}
