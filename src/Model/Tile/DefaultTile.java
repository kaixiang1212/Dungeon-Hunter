package Model.Tile;

import javafx.scene.image.Image;

public class DefaultTile extends Tile {
	
	public DefaultTile() {
		super(new Image("assets/tileassets/default.png"));
	}

	@Override
	public Type getType() {
		return Type.Default;
	}

}
