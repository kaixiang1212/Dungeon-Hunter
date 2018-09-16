package Model;
import java.util.ArrayList;

public class Player {

	private boolean isDead;
	private PlayerInventory inventory;
	private Item heldItem;
	private ArrayList<Potion> status;
	private String direction;
	
	public Player() {
		this.inventory = new PlayerInventory();
		this.isDead = false;
		this.heldItem = null;
		this.status = new ArrayList<Potion>();
		this.direction = "Right";
	}
	//How do we make item disappear? are we allowed to pass dungeon in to make it disappear!
	//Coupled with a move method?
	public void pickup(Item i) {
		if (i.isPotion()) {
			this.addStatus((Potion) i);
		} else if (i.isLitBomb()) {
			return;
		} else {
			inventory.storeItem(i);
		}
	}
	public void selectItem(int index) {
		this.heldItem = inventory.getItem(index);
	}
	public Item getHeld() {
		return this.heldItem;
	}
	public void removeHeld() {
		this.heldItem = null;
	}
	//Passes into use() if meleeWeapon equipped else player dies
	public void fight(Dungeon map) {
		if (this.heldItem != null && this.heldItem.isMeleeWeapon()) {
			this.useItem(map);
		} else if (this.isInvinc()) {
			map.removeAgent(map.getPlayerPos());
		} else {
			this.isDead = true;
		}
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
	public boolean deathStatus() {
		return this.isDead;
	}
	public void die() {
		this.isDead = true;
	}
	public void addStatus(Potion p) {
		for (Potion a: this.status) {
			if (a.equals(p)) {
				this.status.remove(a);
				this.status.add(p);
				return;
			}
		}
		this.status.add(p);
	}
	public String getDirection() {
		return this.direction;
	}
	public boolean isInvinc() {
		for (Potion p: this.status) {
			if (p.isInvinc()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isHover() {
		for (Potion p: this.status) {
			if (p.isHover()) {
				return true;
			}
		}
		return false;
	}
	public ArrayList<Potion> getStatus() {
		return this.status;
	}
	public PlayerInventory getInventory() {
		return this.inventory;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public void die() {
		this.isDead = true;
	}
}

	




	



