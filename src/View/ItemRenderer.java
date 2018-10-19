package View;

import java.awt.Point;
import java.util.EnumMap;

import Model.Dungeon;
import Model.Item.Item;
import Model.Item.ItemType;
import javafx.scene.image.Image;

public class ItemRenderer extends Renderer {
	
	EnumMap<ItemType, Image> images;

	public ItemRenderer(Dungeon dungeon) {
		super(dungeon);
		images = new EnumMap<>(ItemType.class);
		images.put(ItemType.Arrow, new Image("assets/itemassets/arrow.png"));
		images.put(ItemType.Hover, new Image("assets/itemassets/hover.png"));
		images.put(ItemType.Invincibility, new Image("assets/itemassets/invincibility.png"));
		images.put(ItemType.Sword, new Image("assets/itemassets/sword.png"));
		images.put(ItemType.Treasure, new Image("assets/itemassets/treasure.png"));
		images.put(ItemType.Bomb, new Image("assets/itemassets/unlitbomb.png"));
		images.put(ItemType.Key, new Image("assets/itemassets/key.png"));
		images.put(ItemType.LitBomb, new Image("assets/itemassets/litbomb.png"));
	}

	@Override
	public Image getImage(Point point) {
		Item item = dungeon.getItem(point);
		if (item != null) return images.get(item.getType());
		return null;
	}

}
