package Model;

public class Hunter extends ComputerAgent {

	public Hunter() {
		super(100, new StandardChaseBehaviour());
	}
	public String toString() {
		return "hunter";
	}
}
