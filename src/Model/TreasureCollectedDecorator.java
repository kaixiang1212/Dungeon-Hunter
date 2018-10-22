package Model;

import Model.Item.Treasure;

public class TreasureCollectedDecorator extends WinConditionDecorator {

	public TreasureCollectedDecorator(WinCondition decoratee) {
		super(decoratee);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean hasWon(Dungeon map) {
		if(!map.containsItem(new Treasure()) && this.getCondition().hasWon(map)) {
			return true;
		}
		return false;
	}

	
}
