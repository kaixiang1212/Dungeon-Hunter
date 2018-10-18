package Controller;

import java.awt.Point;
import Model.Dungeon;
import Model.Hunter;

public class HoundBehaviour extends MoveBehaviour{

	@Override
	public Point getTarget(Dungeon map, Point currPos) {
		return reflect(map, currPos);
	}
	
	private Point reflect(Dungeon map, Point currPos) {
		Point prediction;
		if ((prediction = houndReflect(map)) == null) {
			prediction = map.getPlayerPos();
		}
		return prediction;
	}
	
	private Point houndReflect(Dungeon map) {
		Point hold = map.getBottomRight();
		for (double i = 0; i < hold.getX(); i++) {
			for (double j = 0; j < hold.getY(); j++) {
				Point check = new Point();
				check.setLocation(i, j);
				if (map.isAgentExist(check)) {
					if (map.getAgent(check) instanceof Hunter) {
						double diffX = check.getX() - map.getPlayerPos().getX();
						double diffY = check.getY() - map.getPlayerPos().getY();
						double predictX = 0;
						double predictY = 0;
						if (diffX < map.getPlayerPos().getX()) {
							predictX = map.getPlayerPos().getX() - diffX;
						}
						if (diffY < map.getPlayerPos().getY()) {
							predictY = map.getPlayerPos().getY() - diffY;
						}
						Point prediction = new Point();
						prediction.setLocation(predictX, predictY);
						return prediction;
					}
				}
			}
		}
		return null;
	}
}
