package Model.Item;

import Model.Dungeon;
import javafx.scene.image.Image;

/*
 * Representation of Invincibility Potion which allows player to not die except from pits
 */
public class Invincibility extends Potion {
	public Invincibility() {
		super(20);
	}
	
	/*
	 * Returns true/false if Invincibility is being compared to Invincibility 
	 * @param Object to be compared to
	 * @return True/false
	 */
	public boolean equals(Object o) {
		if(o instanceof Invincibility) {
			return true;
		}
		return false;
	}

	@Override
	public void use(Dungeon map) {
	}

	@Override
	public Image getImage() {
		return new Image("assets/itemassets/invincibility.png");
	}
	public String toString() {
		return "Invincible";
	}
}
