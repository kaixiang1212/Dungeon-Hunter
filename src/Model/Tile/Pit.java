package Model.Tile;

import java.awt.Point;

import Model.Dungeon;
import Model.Player;
import javafx.scene.image.Image;

public class Pit extends Tile implements FunctionalTile {
	
	private UnfilledPit unfilled;
	private Tile filled;
	private Tile state;
	
	public Pit() {
		unfilled = new UnfilledPit();
		filled = new DefaultTile();
		state = unfilled;
	}
	
	public void filledWithBoulder() {
		state = filled;
	}

	@Override
	public boolean isReachable(EntityType type) {
		return state.isReachable(type);
	}

	@Override
	public void doOperation(Player player) {
		if (state == unfilled) unfilled.doOperation(player);
	}

	@Override
	public Type getType() {
		return state.getType();
	}



}
