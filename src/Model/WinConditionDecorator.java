package Model;

import java.io.Serializable;

public abstract class WinConditionDecorator implements WinCondition, Serializable {

	private static final long serialVersionUID = 1L;
	private WinCondition condition;
	
	public WinConditionDecorator(WinCondition decoratee) {
		this.condition = decoratee;
	}
	
	public WinCondition getCondition() {
		return this.condition;
	}
}
