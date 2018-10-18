package Model;

import Controller.StandardChaseBehaviour;
import javafx.scene.image.Image;

public class Hunter extends ComputerAgent {

	public Hunter() {
		super(new StandardChaseBehaviour());
		this.setImage(new Image("assets/agentassets/hunter.png"));
	}
	
	@Override
	public String toString() {
		return "hunter";
	}
}
