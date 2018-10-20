package Model.ComputerAgent;

import java.awt.Point;

import Controller.StandardChaseBehaviour;
import Model.Dungeon;
import javafx.scene.image.Image;

public class Hunter extends ComputerAgent {

	public Hunter() {
		super(new StandardChaseBehaviour());
	}
	
	@Override
	public String toString() {
		return "hunter";
	}

//	@Override
//	public AgentType getType() {
//		return AgentType.Hunter;
//	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("assets/agentassets/hunter.png");
	}

}