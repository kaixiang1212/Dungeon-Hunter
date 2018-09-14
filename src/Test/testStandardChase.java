package Test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import Model.ComputerAgent;
import Model.Dungeon;
import Model.Hunter;
import Model.Player;
import Model.Tile;

public class testStandardChase {

	Dungeon basicDungeon = new Dungeon(3);
	ComputerAgent a1 = new Hunter();
	Player p1 = new Player();
	Point pPos = new Point(3,3);
	Point aPos = new Point(1,1);
	
/*	@Test
	public void testVanillaChase() {
		basicDungeon.placeComputerAgent(a1, aPos);
		basicDungeon.placePlayer(p1, pPos);
		basicDungeon.updateAgents();
		assertEquals(new Point(2,3), a1.getPos());
		basicDungeon.updateAgents();
		assertEquals(new Point(2,2), a1.getPos());
		basicDungeon.updateAgents();
		assertEquals(new Point(1,2), a1.getPos());
		basicDungeon.updateAgents();
		assertEquals(new Point(1,1), a1.getPos());
	}*/
	@Test 
	public void testObstacleChase() {
		basicDungeon.placeComputerAgent(a1, aPos);
		basicDungeon.placePlayer(p1, pPos);
		basicDungeon.placeTile(Tile.TileType.DESTRUCTABLE_WALL, new Point(2,2));
		basicDungeon.updateAgents();
		System.out.println(a1.getPos());
		basicDungeon.updateAgents();
		System.out.println(a1.getPos());
		basicDungeon.updateAgents();
		System.out.println(a1.getPos());
	}
	
}
