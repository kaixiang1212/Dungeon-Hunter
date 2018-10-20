package Model.Item;

import Model.Dungeon;
import Model.Player;
import Model.Item.Item;
import javafx.scene.image.Image;

public class Treasure extends Item {
	
	public Treasure() {
		super();
	}

	@Override
	public void use(Dungeon map) {
		
	}
	
	public void pickedUp(Player player) {
		player.getInventory().storeItem(this);
	}

	@Override
	public boolean isStackable() {
		return true;
	}
	public String toString() {
		return "Treasure x" + getQuantity();
	}

	@Override
	public Image getImage() {
		return new Image("assets/itemassets/treasure.png");
	}
	public boolean equals(Object o) {
		if(o instanceof Treasure) {
			return true;
		}
		return false;
	}

}
