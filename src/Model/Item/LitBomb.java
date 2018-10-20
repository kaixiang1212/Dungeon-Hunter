package Model.Item;

import Model.Dungeon;
import Model.Player;
import Model.Item.Item;
import javafx.scene.image.Image;

import java.awt.Point;

public class LitBomb extends Item {

	private int counter = 2;
	private Point location;
	
	public LitBomb(Point location) {
		super();
		this.location = location;
	}
	
	@Override
	public void pickedUp(Player player) {
		//Do nothing
	}
	
	@Override
	public boolean isStackable() {
		return false;
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

	@Override
	public Image getImage() {
		return new Image("assets/itemassets/litbomb.png");
	}


}
