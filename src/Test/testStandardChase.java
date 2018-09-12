package Test;

import java.awt.Point;

import org.junit.Test;

import Model.ComputerAgent;
import Model.Dungeon;
import Model.Hunter;
import Model.Player;

public class testStandardChase {

	Dungeon basicDungeon = new Dungeon(3);
	ComputerAgent a1 = new Hunter();
	Player p1 = new Player();
	Point pPos = new Point(3,3);
	Point aPos = new Point(1,1);
	
	@Test
	public void testVanillaChase() {
		basicDungeon.placeComputerAgent(a1, aPos);
		basicDungeon.placePlayer(p1, pPos);
		basicDungeon.updateAgents();
		System.out.print(a1.getPos());
		
	}
	
	
}
