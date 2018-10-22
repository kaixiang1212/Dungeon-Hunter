package Model;

public class EnemiesKilledDecorator extends WinConditionDecorator {

	public EnemiesKilledDecorator(WinCondition decoratee) {
		super(decoratee);
	}

	@Override
	public boolean hasWon(Dungeon map) {

		if(map.enemiesEliminated() && this.getCondition().hasWon(map)) {
			return true;
		}
		return false;
		
	}

}
