package Model;

import java.awt.Point;
import java.util.Map;

public class Arrow extends Weapon{
	
	public Arrow() {
		super(100, 1);
	}
	
	/*
	 * Not sure how we're finding direction player is facing so implementation is just killing closest enemy
	 */
	@Override
	public void attack(Map<Point, ComputerAgent> agentMap, Point playerPos) {
		int x = playerPos.x;
		int y = playerPos.y;
		double minX = 999;
		double minY = 999;
		Point minPointX = new Point(0, 0);
		Point minPointY = new Point(0, 0);
		
		for (Point a: agentMap.keySet()) {
			if (!a.equals(playerPos)) {
				if (a.getX() == x) {
					double temp = Math.abs(x - a.getX());
					if (temp < minX) {
						minX = temp;
						minPointX = a;
					}
				} else if (a.getY() == y) {
					double temp = Math.abs(y - a.getY());
					if (temp < minY) {
						minY = temp;
						minPointY = a;
					}
				}
			}
		}
		if (minX <= minY) {
			agentMap.get(minPointX).takeDamage(this.damage);
		} else {
			agentMap.get(minPointY).takeDamage(this.damage);
		}
	}
}
