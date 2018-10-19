package Model.ComputerAgent;

import Controller.HoundBehaviour;

public class Hound extends ComputerAgent {

	public Hound() {
		super(new HoundBehaviour());
	}

	@Override
	public AgentType getType() {
		return AgentType.Hound;
	}

	
}
