package Model;

public class DefaultNoWinCondition implements WinCondition {

	@Override
	public boolean hasWon(Dungeon map) {
		return false;
	}

}
