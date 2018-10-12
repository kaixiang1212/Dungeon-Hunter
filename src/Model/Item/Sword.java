package Model.Item;

import javafx.scene.image.Image;

public class Sword extends MeleeWeapon {

	public Sword() {
		super(5);
		this.setImage(new Image("assets/itemassets/sword.png"));
	}

	@Override
	public boolean isStackable() {
		return false;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Sword) {
			return true;
		}
		return false;
	}
}

