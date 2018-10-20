package Model.ComputerAgent;

import java.awt.Point;

import Controller.PredictiveChaseBehaviour;
import Model.Dungeon;
import javafx.scene.image.Image;

public class Strategist extends ComputerAgent {

	public Strategist() {
		super(new PredictiveChaseBehaviour());
	}

//	@Override
//	public AgentType getType() {
//		return AgentType.Strategist;
//	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("assets/agentassets/strategist.png");
	}

	
}
