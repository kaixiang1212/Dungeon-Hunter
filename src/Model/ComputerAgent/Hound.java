package Model.ComputerAgent;

import Controller.HoundBehaviour;
import javafx.scene.image.Image;

public class Hound extends ComputerAgent {

	public Hound() {
		super(new HoundBehaviour());
	}


	@Override
	public Image getImage() {
		return new Image("assets/agentassets/hound.png");
	}

	public String toString() {
		return "Hound";
	}
	
}
