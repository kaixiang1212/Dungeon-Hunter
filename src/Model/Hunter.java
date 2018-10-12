package Model;

import Controller.StandardChaseBehaviour;
import javafx.scene.image.Image;

public class Hunter extends ComputerAgent {

	public Hunter() {
		super(new StandardChaseBehaviour());
		this.setImage(new Image("application/bee.png"));
	}
	
	@Override
	public String toString() {
		return "hunter";
	}
}
