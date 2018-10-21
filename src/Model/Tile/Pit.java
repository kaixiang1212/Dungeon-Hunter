package Model.Tile;

import Model.Player;
import javafx.scene.image.Image;

public class Pit extends Tile implements FunctionalTile {
	
	private boolean isFilled;
	
	public Pit() {
		isFilled = false;
	}
	
	public void filledWithBoulder() {
		isFilled = true;
	}

	@Override
	public boolean isReachable(EntityType type) {
		if (isFilled == false && type == EntityType.Computer) return false;
		return true;
	}

	@Override
	public void doOperation(Player player) {
		if (isFilled == false) player.fallsIntoPit();
	}

	@Override
	public Image getImage() {
		return new Image("assets/tileassets/pit.png");
	}

	public String toString() {
		return "Pit";
	}


}
