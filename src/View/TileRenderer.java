package View;

import java.awt.Point;
import java.util.EnumMap;

import Model.Dungeon;
import Model.Tile.Tile;
import javafx.scene.image.Image;

public class TileRenderer extends Renderer {



	public TileRenderer(Dungeon dungeon) {
		super(dungeon);

	}

	@Override
	public Image getImage(Point point) {
//		Tile tile = dungeon.getTile(point);;
//		if (tile != null) return images.get(tile.getType());
//		return null;
		return dungeon.proxygettiles(point, dungeon.getTileGrid());
	}

}
