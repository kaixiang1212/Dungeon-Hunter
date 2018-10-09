package Model;

public abstract class WinConditionDecorator implements WinCondition {

	private WinCondition condition;
	
	public WinCondition getCondition() {
		return this.condition;
	}
}
