package View;

import java.awt.Point;
import java.util.EnumMap;

import Model.Dungeon;
import Model.Item.Item;
import Model.Item.ItemType;
import javafx.scene.image.Image;

public class ItemRenderer extends Renderer {
	


	public ItemRenderer(Dungeon dungeon) {
		super(dungeon);

	}

	@Override
	public Image getImage(Point point) {
//		Item item = dungeon.getItem(point);
//		if (item != null) return images.get(item.getType());
//		return null;
		return dungeon.proxygettiles(point, dungeon.getItemGrid());
	}

}
