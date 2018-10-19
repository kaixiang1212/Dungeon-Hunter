package View;

import java.awt.Point;

import Model.Dungeon;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class PlayerRenderer extends Renderer {

	private Image playerImage;

	public PlayerRenderer(Dungeon dungeon) {
		super(dungeon);
		this.playerImage = new Image("assets/agentassets/player.png");
	}


	@Override
	public Image getImage(Point point) {
		return playerImage;
	}


	@Override
	public void render(Pane pane) {
		if(dungeon.getPlayer() != null) {
			setupImageView(pane, this.playerImage, dungeon.getPlayerPos());
		}
	}

}
