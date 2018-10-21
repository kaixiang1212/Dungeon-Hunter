package Model.Tile;

import Model.Player;
import Model.Item.Key;
import javafx.scene.image.Image;

public class ClosedDoor extends Tile implements FunctionalTile {
	
	private Door door;
	private int check = 0;
	
	public ClosedDoor(Door door) {
		this.door = door;
	}

	@Override
	public void doOperation(Player player) {
		for (Key key : player.getKeys()) {
			unlock(key);
			if (this.check == 1) {
				player.getInventory().removeItem(key);
			}
		}
	}
	
	public void unlock(Key key) {
		if (key.unlocks(door)) {
			door.unlock();
			this.check = 1;
		}
	}

	@Override
	public boolean isReachable(EntityType type) {
		return false;
	}

	@Override
	public Image getImage() {
		return new Image("assets/tileassets/closeddoor.png");
	}

	public String toString() {
		return "Closed Door";
	}

}
