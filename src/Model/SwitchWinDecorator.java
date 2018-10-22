package Model;

public class SwitchWinDecorator extends WinConditionDecorator {

	public SwitchWinDecorator(WinCondition decoratee) {
		super(decoratee);
	}

	/**
	 * Return true in case where:
	 * 1. Conditions decorated are satisfied
	 * 2. All switches in dungeon are activated
	 */
	@Override
	public boolean hasWon(Dungeon map) {
		if(map.winConditionSwitch()) {
		}
		return this.getCondition().hasWon(map) && map.winConditionSwitch();
	}

}
