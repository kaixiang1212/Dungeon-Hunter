package itemDesign;

import java.awt.Point;
import java.util.Map;

public class ArrowShot {
	
	private String direction;
	private int damage;
	private Map<Point, ComputerAgent> agentMap;
	private Point start;
	
	public ArrowShot(String direction, int damage, Map<Point, ComputerAgent> agentMap, Point start) {
		this.direction = direction;
		this.damage = damage;
		this.agentMap = agentMap;
		this.start = start;
	}
	
	public void fly() {
		Point shot = this.start;
		if (this.direction.equals("Right")) {
			for (double newX = shot.getX() + 1; newX < this.agentMap.size(); newX++) {
				shot.setLocation(newX, shot.getY());
				if (this.dungeon.isValidMove(shot)) {
					agentMap.put(shot, ArrowShot);
					//Check for attacks
					//if attacks end this function and remove ArrowShot
					//else remove ArrowShot and loop again
				}
				
			}
		}
	}
}
