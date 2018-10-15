package Model.Tile;

import java.awt.Point;

import Model.ComputerAgent;

public class Switch extends Tile {
	
	private boolean isTriggered;
	private Point point;
	
	public Switch() {
		isTriggered = false;
	}
	
	public void update(ComputerAgent agent) {
		if (agent != null && agent.isMoveable()) {
			isTriggered = true;
			return;
		}
		isTriggered = false;
	}
	
	public void setPoint(Point point) {
		this.point = point;
	}
	
	public Point getPoint() {
		return this.point;
	}
	
	public boolean isActivated() {
		return isTriggered;
	}

	@Override
	public Type getType() {
		return Type.Switch;
	}
	
}
