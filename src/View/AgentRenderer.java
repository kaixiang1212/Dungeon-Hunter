package View;

import java.awt.Point;
import java.util.EnumMap;

import Model.ComputerAgent;
import Model.Dungeon;
import javafx.scene.image.Image;

public class AgentRenderer extends Renderer {

	public AgentRenderer(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public Image getImage(Point point) {
		return null;
	}

}
