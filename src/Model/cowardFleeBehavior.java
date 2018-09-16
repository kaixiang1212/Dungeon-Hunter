package Model;

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
	 * @param map the reference map
	 * @return the next tile across, or current pos if no others availale
	 */
	private Point getNewPos(Point playerPos, Point curr, Dungeon map) {
		Point retval = null;

		int xDist = Math.abs(playerPos.x - curr.x);
		int yDist = Math.abs(playerPos.y - curr.y);

		if(xDist >= yDist) {
			if(playerPos.x > curr.x) {
				retval = new Point(curr.x-1, curr.y);
			} else {
				retval = new Point(curr.x+1, curr.y);
			}
			if (map.isValidMove(retval)) return retval;
		}

		if (!map.isValidMove(retval)) {
			if (playerPos.y > curr.y) retval = new Point(curr.x, curr.y-1);
			else retval = new Point(curr.x, curr.y+1);
			if (map.isValidMove(retval)) return retval;
		}

		if (!map.isValidMove(retval)) {
			if (playerPos.x > curr.x && !map.isValidMove(retval)) retval = new Point(curr.x-1, curr.y);
			else retval = new Point(curr.x+1, curr.y);
			if (map.isValidMove(retval)) return retval;
		}

		return retval;
	}

	@Override
	public Point getTarget(Dungeon map, Point currPos) {
		// TODO Auto-generated method stub
		return null;
	}
}
