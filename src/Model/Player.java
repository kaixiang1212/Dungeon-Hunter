package Model;

public class Player {

	private boolean isDead;
	private PlayerInventory inventory;
	private Item heldItem;
	
	public Player() {
		this.inventory = new PlayerInventory();
		this.isDead = false;
	}
	//How do we make item disappear? are we allowed to pass dungeon in to make it disappear!
	//Coupled with a move method?
	public void pickup(Item i) {
		inventory.storeItem(i);
	}
	public void selectItem(int index) {
		this.heldItem = inventory.getItem(index);
	}
	
	//Maybe pass in player itself as an argument to use, who is using it and where they are using it
	//Consider usage for:
	//Potion - Require player 
	//MeleeWeapons - Should be fine, when we move and have equipped sword (how do we know eqipped?)
	//RangedWeapons - Should be fine as above
	//Bomb - Fine, dungeon square works
	//Key - Find, move onto door, opens door no problem
	public void useItem(Dungeon map) {
		heldItem.use(map);
	}
	public void move();
	
}
