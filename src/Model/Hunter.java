package Model;

public class Hunter extends Agent {

	public Hunter() {
		super(100, new standardChaseBehaviour(), new Bow())
	}
	@Override
	public void move() {
		this.moveBehaviour.move();
		
	}

}
