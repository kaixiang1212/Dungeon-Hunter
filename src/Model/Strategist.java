package Model;

public class Strategist extends Agent {

	public Strategist(int healthPoints, MoveBehaviour moveBehaviour, Weapon weapon) {
		super(100, new PredictiveChaseBehaviour(), new Staff());
	}

	
}
