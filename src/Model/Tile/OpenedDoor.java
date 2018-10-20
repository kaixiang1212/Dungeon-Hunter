package Model.Tile;

import javafx.scene.image.Image;

public class OpenedDoor extends Tile {

	@Override
	public Image getImage() {
		return new Image("assets/tileassets/opendoor.png");
	}

	public String toString() {
		return "Open Door";
	}


}
