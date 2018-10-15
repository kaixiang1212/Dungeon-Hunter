package Model.Tile;

public class OpenedDoor extends Tile {

	@Override
	public boolean isReachable(EntityType type) {
		return true;
	}

	@Override
	public Type getType() {
		return Type.OpenedDoor;
	}

}
