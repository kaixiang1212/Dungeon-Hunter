package Test;

import java.awt.Point;

import org.junit.Test;

import Model.ComputerAgent;
import Model.Dungeon;
import Model.Player;
import Model.Strategist;
import Model.Tile.TileType;

public class testPredictiveChase {

	Dungeon basicDungeon = new Dungeon(3);
	ComputerAgent strategist = new Strategist();
	Player player = new Player();
	
	@Test
	public void testNoWinCondition() {
		basicDungeon.placeComputerAgent(strategist, new Point(2, 2));
		basicDungeon.placePlayer(player, new Point(1,2));
		basicDungeon.updateAgents();
		System.out.println("Expected: [1,1]");
		System.out.println("Reality: " + strategist.getPos()+"\n");
	}
	
	@Test
	public void testExitCondition() {
		basicDungeon.placeComputerAgent(strategist, new Point(2, 2));
		basicDungeon.placePlayer(player, new Point(1, 1));
		basicDungeon.placeTile(TileType.EXIT, new Point(3,1));
		basicDungeon.updateAgents();
		System.out.println("Expected: [2,1]");
		System.out.println(strategist.getPos());
	}
}
