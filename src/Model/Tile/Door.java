package Model.Tile;

import java.awt.Point;

import Model.Dungeon;
import Model.Player;
import javafx.scene.image.Image;

public class Door extends Tile implements FunctionalTile {

	private static int counter = 0;
	private final int code;
	private Tile closedDoor;
	private Tile openedDoor;
	private Tile state;
	
	public Door() {
		this.code = counter++;
		this.closedDoor = new ClosedDoor(this);
		this.openedDoor = new OpenedDoor();
		this.state = closedDoor;
	}
	
	public int getCode() {
		return this.code;
	}
	
	protected void unlock() {
		this.state = this.openedDoor;
	}

	@Override
	public void doOperation(Player player) {
		if (state == openedDoor) return;
		((FunctionalTile )closedDoor).doOperation(player);
	}
	
	@Override
	public boolean isReachable(EntityType type) {
		return state.isReachable(type);
	}

	@Override
	public Image getImage() {
		return this.state.getImage();
	}
	@Override
	public void place(Dungeon d, Point p) {
		d.placeTile(this, p);
		d.addDoorCode(this);
	}
	
	public Tile getState() {
		return state;
	}

	public String toString() {
		return "Door";
	}
}
