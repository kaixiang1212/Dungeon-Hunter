package Model.Tile;

import java.awt.Point;

import Model.Dungeon;
import Model.Player;
import javafx.scene.image.Image;

public class UnfilledPit extends Tile implements FunctionalTile {

	@Override
	public boolean isReachable(EntityType type) {
		if (type == EntityType.Computer) return false;
		return true;
	}

	@Override
	public void doOperation(Player player) {
		player.fallsIntoPit();
	}

//	@Override
//	public Type getType() {
//		return Type.Pit;
//	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("assets/tileassets/pit.png");
	}



}
