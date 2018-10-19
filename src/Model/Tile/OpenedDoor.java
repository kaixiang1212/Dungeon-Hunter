package Model.Tile;

import java.awt.Point;

import Model.Dungeon;
import javafx.scene.image.Image;

public class OpenedDoor extends Tile {

	@Override
	public Type getType() {
		return Type.OpenedDoor;
	}

	@Override
	public void place(Dungeon d, Point point) {
		// TODO Auto-generated method stub
		
	}

}
