package Model.Tile;

import java.awt.Point;

import Model.Dungeon;
import javafx.scene.image.Image;

public class DefaultTile extends Tile {

//	@Override
//	public Type getType() {
//		return Type.Default;
//	}
	
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("assets/tileassets/default.png");
	}



}
