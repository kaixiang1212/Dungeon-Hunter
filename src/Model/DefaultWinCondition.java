package Model;

public class DefaultWinCondition implements WinCondition {

	@Override
	public boolean hasWon(Dungeon map) {
		return true;
	}

}
