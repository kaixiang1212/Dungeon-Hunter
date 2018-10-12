package Model.Item;

import Model.Dungeon;
import Model.Item.Item;
import javafx.scene.image.Image;

public class Treasure extends Item {
	
	public Treasure() {
		super();
		this.setImage(new Image("assets/itemassets/treasure.png"));
	}

	@Override
	public void use(Dungeon map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isStackable() {
		return true;
	}

}
