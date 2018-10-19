package Model.Item;

import Model.Dungeon;
import Model.Item.Item;
import Model.Tile.Door;
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
	public ItemType getType() {
		return ItemType.Key;
	}
}
