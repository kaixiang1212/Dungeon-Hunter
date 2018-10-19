package Model;

public abstract class WinConditionDecorator implements WinCondition {

	private WinCondition condition;
	
	public WinConditionDecorator(WinCondition decoratee) {
		this.condition = decoratee;
	}
	
	public WinCondition getCondition() {
		return this.condition;
	}
}
