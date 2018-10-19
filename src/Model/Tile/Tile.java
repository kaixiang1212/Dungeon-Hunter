package Model.Tile;

import javafx.scene.image.Image;

public abstract class Tile extends Paintable {
	
	private Image image;
	
	public Tile(Image image) {
		this.image = image;
	}

	public boolean isReachable(EntityType type) {
		return true;
	}
	
	public abstract Type getType();
	
	public boolean isType(Type tiletype) {
		return getType().equals(tiletype);
	}
	
	public Image getImage() {
		return this.image;
	}
	
}