package Model;

import java.util.ArrayList;

public class Door extends Tile {
	
	private int code = 0;

	public Door() {
		super(TileType.CLOSED_DOOR);
		code++;
	}
	
	public Key generateKey() {
		return new Key(this.code);
	}
	
	public boolean unlockDoor(Key key) {
		if (key.getCode() == this.code) {
			super.setType(TileType.OPEN_DOOR);
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
