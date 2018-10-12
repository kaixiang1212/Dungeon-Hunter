package Model;


import Controller.*;
import javafx.scene.image.Image;

import java.awt.Point;

public class Coward extends ComputerAgent {
	
	/**
	 * courage represents state of running away or chasing player
	 * cowardBehaviour defines movement strategy of the coward at any point in time
	 */
	private boolean courage;
	private MoveBehaviour cowardBehavior;
	
	public Coward() {
		super(new StandardChaseBehaviour());
		cowardBehavior = new cowardFleeBehavior();
		courage = false;
		this.setImage(new Image("assets/agentassets/coward.png"));
	}
	
	/**
	 * Return true if player is nearby (absolute value of axis <= 2)
	 * @param map Dungeon map
	 * @return true if player is close to coward
	 */
	private boolean determineCourage(Dungeon map) {
		Point player = map.getPlayerPos();
		Point self = super.getPos();
		if (Math.abs(player.x - self.x) > 2 || Math.abs(player.y - self.y) > 2) return true;
		return false;
	}

	/**
	 * Moves the coward around the dungeon map based on its movement strategy and courage state
	 * @param Current dungeon map being played
	 * @return Newly calculated movement position in Point form
	 */
	@Override
	public Point move(Dungeon map) {
		courage = determineCourage(map);
		if (courage) super.move(map);
		else setPos(cowardBehavior.move(map, getPos()));
		return super.getPos();
	}

}
