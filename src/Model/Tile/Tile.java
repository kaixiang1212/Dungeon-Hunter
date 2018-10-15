package Model.Tile;

public abstract class Tile {

	public boolean isReachable(EntityType type) {
		return true;
	}
	
	public abstract Type getType();
	
	public boolean isType(Type tiletype) {
		return getType().equals(tiletype);
	}
	
	// render image maybe?
}