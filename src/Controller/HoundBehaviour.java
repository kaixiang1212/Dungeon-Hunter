package Controller;

import java.awt.Point;
import Model.Dungeon;
import Model.ComputerAgent.Hunter;

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
		for (double i = 0; i < map.getSize(); i++) {
			for (double j = 0; j < map.getSize(); j++) {
				Point check = new Point();
				check.setLocation(i, j);
				if (map.isAgentExist(check)) {
					if (map.getAgent(check) instanceof Hunter) {
						double diffX = Math.abs(check.getX() - map.getPlayerPos().getX());
						double diffY = Math.abs(check.getY() - map.getPlayerPos().getY());
						double predictX = check.getX();
						double predictY = check.getY();
						if (check.getX() < map.getPlayerPos().getX()) {
							predictX = map.getPlayerPos().getX() + diffX;
							if (predictX >= map.getSize()) {
								predictX = map.getSize() - 1;
							}
						} else if (check.getX() > map.getPlayerPos().getX()) {
							predictX = map.getPlayerPos().getX() - diffX;
							if (predictX <= 0) {
								predictX = 1;
							}
						}
						if (check.getY() < map.getPlayerPos().getY()) {
							predictY = map.getPlayerPos().getY() + diffY;
							if (predictY >= map.getSize()) {
								predictY = map.getSize() - 1;
							}
						} else if (check.getY() > map.getPlayerPos().getY()) {
							predictY = map.getPlayerPos().getY() - diffY;
							if (predictY <= 0) {
								predictY = 1;
							}
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
