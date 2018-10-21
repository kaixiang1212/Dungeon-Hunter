package Model.Tile;

import java.awt.Point;

import Model.Dungeon;
import Model.Paintable;

public abstract class Tile extends Paintable {

	public boolean isReachable(EntityType type) {
		return true;
	}
	
	@Override
	public void place(Dungeon d, Point point) {
		d.placeTile(this, point);
	}
	
	@Override
	public void remove(Dungeon d, Point point) {
		d.getTileGrid().replace(point, new DefaultTile());
	}
}