package Model.ComputerAgent;

import Controller.PredictiveChaseBehaviour;
import javafx.scene.image.Image;

public class Strategist extends ComputerAgent {

	public Strategist() {
		super(new PredictiveChaseBehaviour());
	}

	@Override
	public Image getImage() {
		return new Image("assets/agentassets/strategist.png");
	}
	
	public String toString() {
		return "Strategist";
	}
	
}
