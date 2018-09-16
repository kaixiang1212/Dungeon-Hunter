package Model;

import java.awt.Point;

public class StandardChaseBehaviour extends MoveBehaviour {
	
	public StandardChaseBehaviour() {
		super();
	}


	public Point getTarget(Dungeon map, Point currPos) {
		return map.getPlayerPos();
	}

}

