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
		double xDist = posX - currPosX;
		double yDist = posY - currPosY;
		Point newPos1 = null;
		Point newPos2 = null;

		if(xDist >= yDist) {

			newPos1 = new Point((int)currPosX+1, (int)currPosY);
			newPos2 = new Point((int)currPosX-1, (int)currPosY);
				if(posX > currPosX && map.isValidMove(newPos1) && !map.isAgentExist(newPos1)) {					
					return newPos1;
				}
				else if(map.isValidMove(newPos2) && !map.isAgentExist(newPos2)) {
					
					return newPos2;
				}
		}
		else if(yDist > xDist) {
			newPos1 = new Point((int)currPosX, (int)currPosY+1);
			newPos2 = new Point((int)currPosX, (int)currPosY-1);
			if(posY > currPosX && map.isValidMove(newPos1) && !map.isAgentExist(newPos1)) {
				return newPos1;
			}
			else if (map.isValidMove(newPos2) && !map.isAgentExist(newPos2)) {
				return newPos2;
			}
		}				
		return currPos;
		
		
	}

}
