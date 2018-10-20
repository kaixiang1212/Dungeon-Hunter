package View;

import java.awt.Point;
import java.util.EnumMap;

import Model.Dungeon;
import Model.ComputerAgent.AgentType;
import Model.ComputerAgent.ComputerAgent;
import javafx.scene.image.Image;

public class AgentRenderer extends Renderer {
	

	public AgentRenderer(Dungeon dungeon) {
		super(dungeon);		
	}

	@Override
	public Image getImage(Point point) {
//		ComputerAgent comp = dungeon.getAgent(point);
//		if (comp != null) return images.get(comp.getType());
//		return null;
		return dungeon.proxygettiles(point, dungeon.getAgentGrid());
		
	}

}
