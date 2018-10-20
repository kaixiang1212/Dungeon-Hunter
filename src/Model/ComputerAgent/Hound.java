package Model.ComputerAgent;

import java.awt.Point;

import Controller.HoundBehaviour;
import Model.Dungeon;
import javafx.scene.image.Image;

public class Hound extends ComputerAgent {

	public Hound() {
		super(new HoundBehaviour());
	}

//	@Override
//	public AgentType getType() {
//		return AgentType.Hound;
//	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("assets/agentassets/hound.png");
	}


	
}
