package Model.ComputerAgent;

import Controller.StandardChaseBehaviour;
import javafx.scene.image.Image;

public class Hunter extends ComputerAgent {

	public Hunter() {
		super(new StandardChaseBehaviour());
	}
	
	@Override
	public String toString() {
		return "hunter";
	}

	@Override
	public AgentType getType() {
		return AgentType.Hunter;
	}
  
}