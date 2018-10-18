package Model.Item;

import Model.Dungeon;
import Model.Player;
import Model.Item.Item;
import javafx.scene.image.Image;

public class Treasure extends Item {
	
	public Treasure() {
		super();
		this.setImage(new Image("assets/itemassets/treasure.png"));
	}

	@Override
	public void use(Dungeon map) {
		// TODO Auto-generated method stub
		
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

}
