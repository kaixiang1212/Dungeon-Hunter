package Test;

import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import Model.Dungeon;
import Model.Player;
import Model.ComputerAgent.ComputerAgent;
import Model.ComputerAgent.Hound;
import Model.ComputerAgent.Hunter;

public class testHoundChase {
	Dungeon testDun;
	Player testPlayer;

	@Before public void initTest() {
		testDun = new Dungeon(5);
		testPlayer = new Player();
	}
	@Test
	public void testHoundReflect() {
		ComputerAgent hunter = new Hunter();
		ComputerAgent hound = new Hound();
		
		testDun.placePlayer(testPlayer, new Point(1,3));
		testDun.placeComputerAgent(hunter, new Point(1,5));
		testDun.placeComputerAgent(hound, new Point (2,1));
		testDun.updateAgents();
		assertEquals(hound.getPos(), new Point(1,1));
	}
	
}
