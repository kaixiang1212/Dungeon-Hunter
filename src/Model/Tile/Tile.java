package Model.Tile;

import java.awt.Point;

import Model.Dungeon;
import Model.Paintable;
import javafx.scene.image.Image;

public abstract class Tile extends Paintable {

	public boolean isReachable(EntityType type) {
		return true;
	}
	
//	public abstract Type getType();
	
//	public boolean isType(Type tiletype) {
//		return getType().equals(tiletype);
//	}
	@Override
	public void place(Dungeon d, Point point) {
		// TODO Auto-generated method stub
		d.placeTile(this, point);
	}
}