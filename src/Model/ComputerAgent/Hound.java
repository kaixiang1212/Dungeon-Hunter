package Model.ComputerAgent;

import java.awt.Point;

import Controller.HoundBehaviour;
import Model.Dungeon;

public class Hound extends ComputerAgent {

	public Hound() {
		super(new HoundBehaviour());
	}

	@Override
	public AgentType getType() {
		return AgentType.Hound;
	}

	@Override
	public void place(Dungeon d, Point point) {
		// TODO Auto-generated method stub
		
	}

	
}
