package Model.Tile;

import java.awt.Point;

import Model.Dungeon;
import javafx.scene.image.Image;

public class Wall extends Tile {

	@Override
	public boolean isReachable(EntityType type) {
		return false;
	}

//	@Override
//	public Type getType() {
//		return Type.Wall;
//	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("assets/tileassets/wall.png");
	}


}
