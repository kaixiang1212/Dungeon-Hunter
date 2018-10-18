package Model;

import Controller.PredictiveChaseBehaviour;
import javafx.scene.image.Image;

public class Strategist extends ComputerAgent {

	public Strategist() {
		super(new PredictiveChaseBehaviour());
		this.setImage(new Image("assets/agentassets/strategist.png"));
	}

	
}
