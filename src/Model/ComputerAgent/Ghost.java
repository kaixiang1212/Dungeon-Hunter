package Model.ComputerAgent;

import Controller.GhostMoveBehaviour;
import Controller.MoveBehaviour;
import javafx.scene.image.Image;

public class Ghost extends ComputerAgent {

	public Ghost() {
		super(new GhostMoveBehaviour());
	}

	@Override
	public Image getImage() {
		return new Image("assets/agentassets/ghost.png");
	}
	
	public String toString() {
		return "Ghost";
	}

}
