package View;

import java.awt.Point;
import java.util.EnumMap;

import Model.Dungeon;
import Model.ComputerAgent.AgentType;
import Model.ComputerAgent.ComputerAgent;
import javafx.scene.image.Image;

public class AgentRenderer extends Renderer {
	
	EnumMap<AgentType, Image> images;

	public AgentRenderer(Dungeon dungeon) {
		super(dungeon);
		images = new EnumMap<>(AgentType.class);
		images.put(AgentType.Boulder, new Image("assets/agentassets/boulder.png"));
		images.put(AgentType.Coward, new Image("assets/agentassets/coward.png"));
		images.put(AgentType.Hunter, new Image("assets/agentassets/hunter.png"));
		images.put(AgentType.Strategist, new Image("assets/agentassets/strategist.png"));
		// TODO:
		images.put(AgentType.Hound, new Image("assets/agentassets/hound.png"));
		
	}

	@Override
	public Image getImage(Point point) {
		ComputerAgent comp = dungeon.getAgent(point);
		if (comp != null) return images.get(comp.getType());
		return null;
	}

}
