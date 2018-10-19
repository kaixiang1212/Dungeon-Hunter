package Model.Tile;

import Model.Player;

public class UnfilledPit extends Tile implements FunctionalTile {

	@Override
	public boolean isReachable(EntityType type) {
		if (type == EntityType.Computer) return false;
		return true;
	}

	@Override
	public void doOperation(Player player) {
		player.fallsIntoPit();
	}

	@Override
	public Type getType() {
		return Type.Pit;
	}

}
