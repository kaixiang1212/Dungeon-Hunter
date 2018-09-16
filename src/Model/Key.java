package Model;

public class Key extends Item {
	
	private int code;
	
	public Key(int code) {
		this.code = code;
	}

	@Override
	public void use(Dungeon map) {
		Player player = map.getPlayer();
		player.getInventory().removeItem(this);

	}

	@Override
	public boolean isStackable() {
		return false;
	}
	
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

}
