package Controller;

import Model.Dungeon;

import java.awt.Point;

public class cowardFleeBehavior extends MoveBehaviour {

	/**
	 * Implements a run-away behaviour for the coward
	 * @param map reference mapp
	 * @param curr current location from whence there is a flee
	 * @return the tile that running towards
	 */
	public Point move(Dungeon map, Point curr) {
		Point playerPos = map.getPlayerPos();
		Point newPos = getNewPos(playerPos, curr, map);
		return newPos;
	}


	/**
	 * Prioritising left, then right, then up, then down.
	 * @param playerPos the assumed player position
	 * @param curr the assumed coward position
	 * @param map the reference Dungeon
	 * @return the next tile across, or current pos if no others availale
	 */
	private Point getNewPos(Point playerPos, Point curr, Dungeon map) {
		Point retval;

		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				if ((x == 0 && y == 0) || (x != 0 && y != 0)) continue;

				if (curr.y > playerPos.y && y == -1) continue;
				if (curr.x > playerPos.x && x == -1) continue;

				retval = new Point(x + curr.x, y + curr.y);
				if (retval.x > map.getSize()|| retval.y > map.getSize()) continue;


				if (map.isValidMoveAgent(retval) && !closerToPlayer(curr, retval, playerPos)) return retval;
			}
		}
		// No point Found
		return curr;
	}
	
	/**
	 * Check the intended point to move is closer to player than the original point
	 * @param curr Current position of Agent
	 * @param retval Intended position of Agent
	 * @param player Player's location
	 * @return true if intended position is closer to player, false otherwise
	 */
	private boolean closerToPlayer(Point curr, Point retval, Point player) {
		return getDistance(curr, player) > getDistance(retval, player);
	}
	
	/**
	 * Calculate distance between 2 points
	 * @param a Point a
	 * @param b Point 2
	 * @return return distance between a and b
	 */
	private int getDistance(Point a, Point b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}

	@Override
	public Point getTarget(Dungeon map, Point currPos) {
		return null;
	}
}
