package Model;

import java.io.Serializable;

public class DefaultWinCondition implements WinCondition, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean hasWon(Dungeon map) {
		return true;
	}

}
