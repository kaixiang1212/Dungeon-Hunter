package Model;

import Model.Tile.Exit;

public class ExitWinDecorator extends WinConditionDecorator {

	public ExitWinDecorator(WinCondition decoratee) {
		super(decoratee);
	}

	/**
	 * Return true where:
	 * 1. Decorated condition is satisfied
	 * 2. Tile which player is standing on is a homogenous exit (all exits defined as equal substitutes to win)
	 */
	@Override
	public boolean hasWon(Dungeon map) {
		return this.getCondition().hasWon(map) && map.getTile(map.getPlayerPos()).equals(new Exit());
	}

}
