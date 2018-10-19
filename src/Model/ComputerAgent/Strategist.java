package Model.ComputerAgent;

import java.awt.Point;

import Controller.PredictiveChaseBehaviour;
import Model.Dungeon;
import javafx.scene.image.Image;

public class Strategist extends ComputerAgent {

	public Strategist() {
		super(new PredictiveChaseBehaviour());
	}

	@Override
	public AgentType getType() {
		return AgentType.Strategist;
	}

	@Override
	public void place(Dungeon d, Point point) {
		// TODO Auto-generated method stub
		
	}
	
}
