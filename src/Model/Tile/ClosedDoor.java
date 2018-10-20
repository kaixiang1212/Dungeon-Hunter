package Model.Tile;

import java.awt.Point;

import Model.Dungeon;
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

//	@Override
//	public Type getType() {
//		return Type.ClosedDoor;
//	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("assets/tileassets/closeddoor.png");
	}



}
