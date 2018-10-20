package Model;

import java.awt.Point;

import javafx.scene.image.Image;

public abstract class Paintable implements Cloneable {

	
//	public Image getImage() {
//		return this.image;
//	}
//	
//	public void setImage(Image i) {
//		this.image = i;
//	}
	public abstract void place(Dungeon d, Point point);
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
