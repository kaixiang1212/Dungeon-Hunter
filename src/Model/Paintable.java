package Model;

import java.awt.Point;
import java.io.Serializable;

import javafx.scene.image.Image;

public abstract class Paintable implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	public abstract void place(Dungeon d, Point point) throws Exception;
	
	public abstract void remove(Dungeon d, Point point);
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public abstract Image getImage();
}
