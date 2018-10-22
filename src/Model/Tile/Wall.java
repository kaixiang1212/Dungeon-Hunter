package Model.Tile;

import javafx.scene.image.Image;

public class Wall extends Tile {

	@Override
	public boolean isReachable(EntityType type) {
		return false;
	}

	@Override
	public Image getImage() {
		return new Image("assets/tileassets/wall.png");
	}

	public String toString() {
		return "Wall";
	}
}
