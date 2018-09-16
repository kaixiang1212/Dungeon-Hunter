package Model;

import java.awt.Point;

public class Boulder extends ComputerAgent {

	public Boulder(MoveBehaviour moveBehaviour) {
		super(moveBehaviour);
	}
	public Point push(String playerDirection) {
		int currX = (int) this.getPos().getX();
		int currY = (int) this.getPos().getY();
		Point newPos = null;
		switch(playerDirection) {
			case "Left":
				newPos = new Point(currX-1, currY);
				this.setPos(newPos);
				break;
			case "Right":
				newPos = new Point(currX+1, currY);
				this.setPos(newPos);
				break;
			case "Up":
				newPos = new Point(currX, currY-1);
				this.setPos(newPos);
				break;
			case "Down":
				newPos = new Point(currX, currY+1);
				this.setPos(newPos);
				break;
		}
		return newPos;
	}

	
}
