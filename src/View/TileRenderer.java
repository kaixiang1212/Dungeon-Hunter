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
		return dungeon.proxygettiles(point, dungeon.getTileGrid());
	}

}
