package View;

import java.awt.Point;
import java.util.EnumMap;

import Model.Dungeon;
import Model.Tile.Tile;
import Model.Tile.Type;
import javafx.scene.image.Image;

public class TileRenderer extends Renderer {

	EnumMap<Type, Image> images;

	public TileRenderer(Dungeon dungeon) {
		super(dungeon);
        images = new EnumMap<>(Type.class);
        this.images.put(Type.Default, new Image("assets/tileassets/default.png"));
        this.images.put(Type.Wall, new Image("assets/tileassets/wall.png"));
        this.images.put(Type.OpenedDoor, new Image("assets/tileassets/opendoor.png"));
        this.images.put(Type.ClosedDoor, new Image("assets/tileassets/closeddoor.png"));
        this.images.put(Type.Exit, new Image("assets/tileassets/exit.png"));
        this.images.put(Type.Pit, new Image("assets/tileassets/pit.png"));
        this.images.put(Type.Switch, new Image("assets/tileassets/switch.png"));
	}

	@Override
	public Image getImage(Point point) {
		Tile tile = dungeon.getTile(point);;
		if (tile != null) return images.get(tile.getType());
		return null;
	}

}
