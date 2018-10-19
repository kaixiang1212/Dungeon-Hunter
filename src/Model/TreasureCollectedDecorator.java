package Model;

public class TreasureCollectedDecorator extends WinConditionDecorator {

	public TreasureCollectedDecorator(WinCondition decoratee) {
		super(decoratee);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean hasWon(Dungeon map) {
		if(!map.hasTreasure() && this.getCondition().hasWon(map)) {
			return true;
		}
		return false;
	}

	
}
