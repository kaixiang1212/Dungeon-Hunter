package View;

import java.awt.Point;

import Model.Dungeon;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Renderer {
	
	protected Dungeon dungeon;
	
	public Renderer(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	public abstract Image getImage(Point point);
	
	public void render(Pane pane) {
		Point point;
		for(int y = 0; y < dungeon.getSize() + 2; y++) {
			for(int x = 0; x < dungeon.getSize() + 2; x++) {
				Image image = getImage(point = new Point(x, y));
				if (image != null) {
					setupImageView(pane, image, point);
				}
			}
		}
	}
	
	protected void setupImageView(Pane pane, Image img, Point point) {
		ImageView insertview = new ImageView(img);
		insertview.setFitHeight(32);
		insertview.setFitWidth(32);
		insertview.setLayoutX(point.x * 32);
		insertview.setLayoutY(point.y * 32);
		pane.getChildren().add(insertview);
	}
}


