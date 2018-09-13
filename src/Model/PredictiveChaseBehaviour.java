package Model;

import java.awt.Point;

import Model.Tile.TileType;

public class PredictiveChaseBehaviour implements MoveBehaviour {

	@Override
	public Point move(Dungeon map, Point currPos) {
		Point prediction = predict(map, currPos);
		// if strategist is on the prediction point, run towards player
		if (prediction.equals(currPos)) {
			System.out.println("Running Towards player");
			prediction = map.getPlayerPos();
		}
		Point player = map.getPlayerPos();
		int xDist = Math.abs(prediction.x - currPos.x);
		int yDist = Math.abs(prediction.y - currPos.y);
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
	
	
	/**
	 * Predict players next moves and return Point
	 * @param map the whole map
	 * @param currPos current position of Strategist
	 * @return the point of prediction (to move)
	 */
	private Point predict(Dungeon map, Point currPos) {
		Point prediction;
		if ((prediction = predictExit(map)) == null) {
			if ((prediction = predictTreasure(map)) == null) {
				// unable to predict
				prediction = map.getPlayerPos();
			}
		}
		return prediction;
	}
	
	
	/**
	 * Predict a point to move for win condition: EXIT
	 * @param map the whole map
	 * @return the path of player towards exit
	 */
	private Point predictExit(Dungeon map) {
		Point exit = getExitPoint(map);
		if (exit == null) return null;

		Point player = map.getPlayerPos();
		int predictX = (exit.x + player.x)/2;
		int predictY = (exit.y + player.y)/2;

		Point prediction = new Point(predictX, predictY);
		return prediction;
	}
	
	/**
	 * Get point of Exit if there is one
	 * @param map the whole map
	 * @return the point of EXIT, null if not exist
	 */
	private Point getExitPoint(Dungeon map) {
		for (int x=0;x<map.MAX_SIZE;x++) {
			for (int y=0;y<map.MAX_SIZE;y++) {
				Point temp = new Point(x, y);
				//System.out.println(temp.toString() + map.pointTileType(temp));
				if (map.pointTileType(temp) == TileType.EXIT) {
					return temp;
				}
			}
		}
		System.out.println("no exit found");
		return null;
	}

	/** Incomplete
	 * Get point of Treasure if there is one
	 * @param map the whole map
	 * @return the point of Treasure, null if not exist
	 */
	private Point predictTreasure(Dungeon map) {
		// To-Do
		return null;
	}
}
