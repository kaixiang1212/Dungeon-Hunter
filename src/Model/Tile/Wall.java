package Model.Tile;

import javafx.scene.image.Image;

public class Wall extends Tile {

	@Override
	public boolean isReachable(EntityType type) {
		return false;
	}

	@Override
	public Type getType() {
		return Type.Wall;
	}

}
