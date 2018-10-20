package Model.Item;

import java.awt.Point;

import Model.Dungeon;
import javafx.scene.image.Image;

public class Sword extends MeleeWeapon {

	public Sword() {
		super(5);
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
	public String toString() {
		return "Sword x" + getnumUses();
	}

//	@Override
//	public ItemType getType() {
//		return ItemType.Sword;
//	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("assets/itemassets/sword.png");
	}

}

