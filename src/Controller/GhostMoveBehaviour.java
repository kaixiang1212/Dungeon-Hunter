package Controller;

import java.awt.Point;

import Controller.MovementAlgo.GreedyPathFinder;
import Model.Dungeon;

public class GhostMoveBehaviour extends MoveBehaviour {

	public GhostMoveBehaviour() {
		this.setPathFinder(new GreedyPathFinder());
	}
	@Override
	public Point getTarget(Dungeon map, Point currPos) {
		return map.getPlayerPos();
	}

}
