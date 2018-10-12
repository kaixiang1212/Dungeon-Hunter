package Model;

import javafx.scene.image.Image;

public abstract class Paintable {

	private Image image;
	
	public Image getImage() {
		return this.image;
	}
	
	public void setImage(Image i) {
		this.image = i;
	}
}
