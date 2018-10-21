package Model;

import java.awt.Point;

import javafx.scene.image.Image;

public abstract class Paintable implements Cloneable {

	public abstract void place(Dungeon d, Point point);
	
	public abstract void remove(Dungeon d, Point point);
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public abstract Image getImage();
}
