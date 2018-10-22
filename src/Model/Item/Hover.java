package Model.Item;

import Model.Dungeon;
import javafx.scene.image.Image;

/*
 * Representation of Hover Potion which allows player to move onto Pits without dying
 */
public class Hover extends Potion {
	public Hover() {
		super(999);
	}
	
	/*
	 * Returns true when this class is called by item
	 * @return true
	 */
	@Override
	public boolean isHover() {
		return true;
	}
	
	/*
	 * Returns true or flase depending on if Hover is being compared to Hover
	 * @param Object to be compared to
	 * @return True/False
	 */
	public boolean equals(Object o) {
		if(o instanceof Hover) {
			return true;
		}
		return false;
	}

	@Override
	public void use(Dungeon map) {
	}

	@Override
	public Image getImage() {
		return new Image("assets/itemassets/hover.png");
	}

	public String toString() {
		return "Hover";
	}
}