package Model.Tile;

public class Wall extends Tile {

	@Override
	public boolean isReachable(EntityType type) {
		return false;
	}

}
