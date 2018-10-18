package Model;

public class EnemiesKilledDecorator extends WinConditionDecorator {

	public EnemiesKilledDecorator(WinCondition decoratee) {
		super(decoratee);
		// TODO Auto-generated constructor stub
	}

	//Maybe make condition protected?
	@Override
	public boolean hasWon(Dungeon map) {
		if(map.numEnemies() == 0 && this.getCondition().hasWon(map)) {
			return true;
		}
		return false;
	}

}
