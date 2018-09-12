package Model;

import java.awt.Point;
import java.util.Map;

public class StandardChaseBehaviour implements MoveBehaviour {

	@Override
	public Point move(Dungeon map, Point currPos) {
		Point playerPos = map.getPlayerPos();
		double posX = playerPos.getX();
		double posY = playerPos.getY();
		double currPosX = currPos.getX();
		double currPosY = currPos.getY();
		double xDist = Math.abs(posX - currPosX);
		double yDist = Math.abs(posY - currPosY);
		Point newPos = null;
		if(xDist >= yDist) {
			if(posX > currPosX) {
				newPos = new Point((int)currPosX+1, (int)currPosY);
				return newPos;
			}
			else {
				newPos = new Point((int)currPosX-1, (int)currPosY);
			}
		}
		else if(yDist > xDist) {
			if(posY > currPosX) {
				newPos = new Point((int)currPosX, (int)currPosY+1);
			}
			else {
				newPos = new Point((int)currPosX, (int)currPosY-1);
			}
		}
				
		return newPos;
	}

}
