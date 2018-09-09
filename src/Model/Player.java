package Model;

public class Player extends Agent {

	public Player() {
		MoveBehaviour moveBehaviour = new ControlledMoveBehaviour();
		super(100, moveBehaviour, null);
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	
}
