package Model.ComputerAgent;

import Controller.StandardChaseBehaviour;
import javafx.scene.image.Image;

public class Hunter extends ComputerAgent {

	public Hunter() {
		super(new StandardChaseBehaviour());
	}
	
	@Override
	public Image getImage() {
		return new Image("assets/agentassets/hunter.png");
	}
	
	public String toString() {
		return "Hunter";
	}
}