package Controller;

import java.awt.Point;

import Model.Dungeon;

public class NoMoveBehaviour extends MoveBehaviour {

	@Override
	public Point getTarget(Dungeon map, Point currPos) {
		// TODO Auto-generated method stub
		return currPos;
	}

}
