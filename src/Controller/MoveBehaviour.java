package Controller;

import java.awt.Point;

import Model.Dungeon;
import Controller.MovementAlgo.AStarPathFinder;
import Controller.MovementAlgo.PathFinder;

public abstract class MoveBehaviour {
	
	private PathFinder pathFinder;
	
	public MoveBehaviour() {
		pathFinder = new AStarPathFinder();
	}
	
	public abstract Point getTarget(Dungeon map, Point currPos);
	
	public Point move(Dungeon map, Point currPos) {
		Point target = getTarget(map, currPos);
		if (target == null) return currPos;
		Point nextMove = pathFinder.findPath(map, currPos, target);
		if (nextMove == null) return currPos;
		return nextMove;
	}
	
	public void setPathFinder(PathFinder pf) {
		this.pathFinder = pf;
	}
}
