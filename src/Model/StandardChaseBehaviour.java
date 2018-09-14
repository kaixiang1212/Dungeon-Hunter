package Model;

import java.awt.Point;

public class StandardChaseBehaviour implements MoveBehaviour {

	/**
	 * Get the player's coordinate, determine its direction towards player
	 * then increment the Point axis by 1 (Move)
	 */
	@Override
	public Point move(Dungeon map, Point currPos) {

		// Get player's coordinate
		Point player = map.getPlayerPos();
	
		// Determine direction to move
		double xDist = Math.abs(player.x - currPos.x);
		double yDist = Math.abs(player.y - currPos.y);
		Point newPos = null;

		// Increment Point axis by 1 (Move)
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
		return currPos;
		
		
	}

}
