package Model;

public class EnemiesKilledDecorator extends WinConditionDecorator {

	//Maybe make condition protected?
	@Override
	public boolean hasWon(Dungeon map) {
		if(map.numEnemies() == 0 && this.getCondition().hasWon(map)) {
			return true;
		}
		return false;
	}

}
