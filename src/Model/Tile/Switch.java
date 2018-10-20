package Model.Tile;

import java.awt.Point;

import Model.Dungeon;
import Model.ComputerAgent.ComputerAgent;
import javafx.scene.image.Image;

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
	public Image getImage() {
		return new Image("assets/tileassets/switch.png");
	}
	
	@Override
	public void place(Dungeon d, Point p) {
		d.placeTile(this, p);
		d.addSwitch(this, p);
	}

	public String toString() {
		return "Switch";
	}
	
}
