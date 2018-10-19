package Model.Item;

import java.awt.Point;

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

	@Override
	public ItemType getType() {
		return ItemType.Treasure;
	}

	@Override
	public void place(Dungeon d, Point point) {
		// TODO Auto-generated method stub
		
	}

}
