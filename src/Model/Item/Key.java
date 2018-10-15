package Model.Item;

import Model.Dungeon;
import Model.Item.Item;
import Model.Tile.Door;
import Model.Player;

public class Key extends Item {
	
	private static int counter = 0;
	private final int code;
	
	public Key() {
		this.code = counter++;
	}
	
	public Key(int doorCode) {
		this.code = doorCode;
	}
	
	public boolean unlocks(Door door) {
		return this.code == door.getCode();
	}

	@Override
	public void use(Dungeon map) {
		Player player = map.getPlayer();
		player.getInventory().removeItem(this);
	}

	@Override
	public boolean isStackable() {
		return false;
	}
	
	public int getCode() {
		return code;
	}
}
