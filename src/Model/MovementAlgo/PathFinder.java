package Model.MovementAlgo;

import java.awt.Point;

import Model.Dungeon;

public interface PathFinder {
	public Point findPath(Dungeon map, Point current, Point target);
}
