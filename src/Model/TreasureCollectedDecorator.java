package Model;

public class TreasureCollectedDecorator extends WinConditionDecorator {

	@Override
	public boolean hasWon(Dungeon map) {
		if(!map.hasTreasure() && this.getCondition().hasWon(map)) {
			return true;
		}
		return false;
	}

	
}
