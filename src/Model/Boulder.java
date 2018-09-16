package Model;

import java.awt.Point;


/**
 * Represents boulder which can be pushed around the map
 * @author Richard
 *
 */
public class Boulder extends ComputerAgent {

	public Boulder(MoveBehaviour moveBehaviour) {
		super(moveBehaviour);
	}

	/**
	 * Pushed onto a Point which is in the direction of the player
	 * @param playerDirection Direction which player is facing (left,right,up,down)
	 * @return Point to update position of Boulder after pushing
	 */
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
