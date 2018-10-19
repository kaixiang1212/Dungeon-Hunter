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

	@Override
	public AgentType getType() {
		return AgentType.Hunter;
	}

	@Override
	public void place(Dungeon d, Point point) {
		// TODO Auto-generated method stub
		
	}
}