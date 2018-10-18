package Model.Tile;

import javafx.scene.image.Image;

public class OpenedDoor extends Tile {
	
	public OpenedDoor() {
		super(new Image("assets/tileassets/opendoor.png"));
	}

	@Override
	public Type getType() {
		return Type.OpenedDoor;
	}

}
