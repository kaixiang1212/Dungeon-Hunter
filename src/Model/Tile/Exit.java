package Model.Tile;

import java.awt.Point;

import Model.Dungeon;
import javafx.scene.image.Image;

public class Exit extends Tile {

	@Override
	public Type getType() {
		return Type.Exit;
	}

	@Override
	public void place(Dungeon d, Point point) {
		// TODO Auto-generated method stub
		
	}

}
