package Model.Tile;

import java.awt.Point;

import Model.Dungeon;
import javafx.scene.image.Image;

public class Exit extends Tile {

	@Override
	public Image getImage() {
		return new Image("assets/tileassets/exit.png");
	}

	public boolean equals(Object o) {
		
		if(o instanceof Exit) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "Exit";
	}
}
