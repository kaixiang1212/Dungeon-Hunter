package Model.Item;

import Model.Dungeon;
import Model.Item.Item;

import java.awt.Point;

public class LitBomb extends Item {

	private int counter = 2;
	private Point location;
	
	public LitBomb(Point location) {
		super();
		this.location = location;
	}
	
	@Override
	public boolean isStackable() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void use(Dungeon map) {
		if (this.counter == 0) {
			Point temp = this.location;
			temp.setLocation(location.getX()-1, location.getY()-1);
			for (double i = temp.getX(); i < temp.getX()+3; i++) {
				for (double j = temp.getY(); j < temp.getY()+3; j++) {
					Point remove = new Point();
					remove.setLocation(i, j);
					if (map.getPlayerPos().equals(remove)) {
						map.getPlayer().die();
					}
					if (map.isAgentExist(remove)) {
						map.removeAgent(remove);
					}
				}
			}
			map.removeItem(this.location);
		} else {
			this.counter--;
		}
	}
}
