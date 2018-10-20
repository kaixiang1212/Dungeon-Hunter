package Model.Tile;

import Model.Player;
import Model.Item.Key;
import javafx.scene.image.Image;

public class ClosedDoor extends Tile implements FunctionalTile {
	
	private Door door;
	
	public ClosedDoor(Door door) {
		this.door = door;
	}

	@Override
	public void doOperation(Player player) {
		for (Key key : player.getKeys()) {
			unlock(key);
		}
	}
	
	public void unlock(Key key) {
		if (key.unlocks(door)) door.unlock();
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
