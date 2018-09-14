package Model;

import java.awt.Point;

public class StandardChaseBehaviour implements MoveBehaviour {

	/**
	 * Get the player's coordinate, determine its direction towards player
	 * then increment the Point axis by 1 (Move)
	 */
	@Override
	public Point move(Dungeon map, Point curr) {

		// Get player's coordinate
		Point player = map.getPlayerPos();

		// Determine direction to move
		double xDist = Math.abs(player.x - curr.x);
		double yDist = Math.abs(player.y - curr.y);
		Point newPos = null;

		// Prioritize the largest axis differences
		if(xDist >= yDist) {
			if(player.x > curr.x) {
				newPos = new Point(curr.x+1, curr.y);
			} else {
				newPos = new Point(curr.x-1, curr.y);
			}
			if (map.isValidMove(newPos)) return newPos;
		}
		if (!map.isValidMove(newPos)) {
			if (player.y > curr.y) newPos = new Point(curr.x, curr.y+1);
			else newPos = new Point(curr.x, curr.y-1);
			if (map.isValidMove(newPos)) return newPos;
		}
		if (!map.isValidMove(newPos)) {
			if (player.x > curr.x && !map.isValidMove(newPos)) newPos = new Point(curr.x+1, curr.y);
			else newPos = new Point(curr.x-1, curr.y);
			if (map.isValidMove(newPos)) return newPos;
		}
		// I too desperated to move
		if (!map.isValidMove(newPos)) newPos = new Point(curr.x, curr.y);
		return newPos;
	}
}