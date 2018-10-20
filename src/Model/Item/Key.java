package Model.Item;

import java.awt.Point;

import Model.Dungeon;
import Model.Item.Item;
import Model.Tile.Door;
import javafx.scene.image.Image;
import Model.Player;

public class Key extends Item {
	
	private int code;
	
	public boolean unlocks(Door door) {
		return this.code == door.getCode();
	}
	
	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public void use(Dungeon map) {
		Player player = map.getPlayer();
		player.getInventory().removeItem(this);
	}
	
	public void pickedUp(Player player) {
		player.getInventory().storeItem(this);
	}

	@Override
	public boolean isStackable() {
		return false;
	}
	
	public int getCode() {
		return code;
	}
	public String toString() {
		return "Key";
	}

	@Override
	public Image getImage() {
		return new Image("assets/itemassets/key.png");
	}
	@Override
	public void place(Dungeon d, Point p) {
		d.placeItem(this, p);
		d.setKeyCode(this);
	}
}
