package Model;

import java.io.Serializable;

public class DefaultNoWinCondition implements WinCondition, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean hasWon(Dungeon map) {
		return false;
	}

}
