package Model;

import java.awt.Point;

public class cowardFleeBehavior implements MoveBehaviour {

	/**
	 * Exact different from chasing
	 */
	public Point move(Dungeon map, Point curr) {

		Point player = map.getPlayerPos();
	
		// Determine direction to move
		double xDist = Math.abs(player.x - curr.x);
		double yDist = Math.abs(player.y - curr.y);
		
		Point newPos = null;

		// Increment Point axis by 1 (Move)
		if(xDist >= yDist) {
			if(player.x > curr.x) {
				newPos = new Point(curr.x-1, curr.y);
			} else {
				newPos = new Point(curr.x+1, curr.y);
			}
			if (map.isValidMove(newPos)) return newPos;
		}
	
		if (!map.isValidMove(newPos)) {
			if (player.y > curr.y) newPos = new Point(curr.x, curr.y-1);
			else newPos = new Point(curr.x, curr.y+1);
			if (map.isValidMove(newPos)) return newPos;
		}
	
		if (!map.isValidMove(newPos)) {
			if (player.x > curr.x && !map.isValidMove(newPos)) newPos = new Point(curr.x-1, curr.y);
			else newPos = new Point(curr.x+1, curr.y);
			if (map.isValidMove(newPos)) return newPos;
		}
		// I too desperate to move
		if (!map.isValidMove(newPos)) newPos = new Point(curr.x, curr.y);
	
		return newPos;
	}
}
