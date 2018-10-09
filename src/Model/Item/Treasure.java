package Model.Item;

import Model.Dungeon;
import Model.Item.Item;

public class Treasure extends Item {
	
	public Treasure() {
		super();
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
