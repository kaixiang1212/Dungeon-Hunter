package Model.Tile;

import javafx.scene.image.Image;

public class DefaultTile extends Tile {

	@Override
	public Image getImage() {
		return new Image("assets/tileassets/default.png");
	}

	public String toString() {
		return "Default Tile";
	}


}
