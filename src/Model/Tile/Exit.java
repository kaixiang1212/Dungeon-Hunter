package Model.Tile;

import javafx.scene.image.Image;

public class Exit extends Tile {
	
	public Exit() {
		super(new Image("assets/tileassets/exit.png"));
	}

	@Override
	public Type getType() {
		return Type.Exit;
	}

}
