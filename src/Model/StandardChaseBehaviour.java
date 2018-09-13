package Model;

import java.awt.Point;
import java.util.Map;

public class StandardChaseBehaviour implements MoveBehaviour {

	@Override
	public Point move(Dungeon map, Point currPos) {
		Point player = map.getPlayerPos();
		double xDist = Math.abs(player.x - currPos.x);
		double yDist = Math.abs(player.y - currPos.y);
		Point newPos = null;
		if(xDist >= yDist) {
			if(player.x > currPos.x) {
				newPos = new Point(currPos.x+1, currPos.y+1);
				return newPos;
			}
			else {
				newPos = new Point(currPos.x-1, currPos.y);
			}
		}
		else if(yDist > xDist) {
			if(player.y > currPos.x) {
				newPos = new Point(currPos.x, currPos.y+1);
			}
			else {
				newPos = new Point(currPos.x, currPos.y-1);
			}
		}
				
		return newPos;
	}

}
