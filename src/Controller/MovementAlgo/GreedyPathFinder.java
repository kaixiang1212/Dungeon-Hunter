package Controller.MovementAlgo;

import java.awt.Point;
import java.lang.*;
import Model.Dungeon;

public class GreedyPathFinder implements PathFinder {

	@Override
	public Point findPath(Dungeon map, Point current, Point target) {
		int vertical = Math.abs(target.y - current.y);
		int horizontal = Math.abs(target.x - current.x);
		
		if(vertical > horizontal) {
			if(current.y > target.y) {
				return new Point(current.x, current.y - 1);
			}
			return new Point(current.x, current.y + 1);
		}
		
		else {
			if(current.x > target.x) {
				return new Point(current.x - 1, current.y);
			}
			return new Point(current.x + 1, current.y);
		}
		
		
	}

}
