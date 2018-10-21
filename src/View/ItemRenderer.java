package View;

import java.awt.Point;
import java.util.EnumMap;

import Model.Dungeon;
import Model.Item.Item;
import javafx.scene.image.Image;

public class ItemRenderer extends Renderer {
	


	public ItemRenderer(Dungeon dungeon) {
		super(dungeon);

	}

	@Override
	public Image getImage(Point point) {
		return dungeon.proxygettiles(point, dungeon.getItemGrid());
	}

}
