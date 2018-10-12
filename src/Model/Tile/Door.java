package Model.Tile;

import Model.Item.Key;
import Model.Tile.Tile;

import java.util.ArrayList;


public class Door extends Tile {
	
	private static int counter = 0;
	private final int code;

	public Door() {
		super(Type.CLOSED_DOOR);
		this.code = counter++;
	}
	
	public Key generateKey() {
		return new Key(this.code);
	}
	
	public boolean unlockDoor(Key key) {
		if (key.getCode() == this.code) {
			super.setType(Type.OPEN_DOOR);
			return true;
		}
		return false;
	}
	
	public boolean unlockDoor(ArrayList<Key> keys) {
		for (Key key : keys) {
			if (unlockDoor(key)) return true;
		}
		return false;
	}
	

}
