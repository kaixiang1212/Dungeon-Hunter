package Model;

public class Hunter extends ComputerAgent {

	public Hunter() {
		super(new StandardChaseBehaviour());

	}
	
	@Override
	public String toString() {
		return "hunter";
	}
}
