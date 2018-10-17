package Model.Item;

import Model.Dungeon;
import Model.Item.Item;
import Model.Player;

public class Key extends Item {
	
	private int code;
	
	public Key(int code) {
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
	
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

}
