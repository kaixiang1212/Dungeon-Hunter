package Model.ComputerAgent;

import Controller.PredictiveChaseBehaviour;
import javafx.scene.image.Image;

public class Strategist extends ComputerAgent {

	public Strategist() {
		super(new PredictiveChaseBehaviour());
	}

	@Override
	public AgentType getType() {
		return AgentType.Strategist;
	}
	
}
