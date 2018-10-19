package Model;
import Controller.Direction;
import Model.Item.Item;
import Model.Item.Key;
import Model.Item.Potion;
import javafx.scene.image.Image;

import java.util.ArrayList;

import static Controller.Direction.RIGHT;

/*
 * Player class that holds; inventory, status effects, equipped item, and direction
 */
public class Player extends Paintable {

	private boolean isDead;
	private PlayerInventory inventory;
	private Item heldItem;
	private ArrayList<Potion> status;
	private Direction direction;
	
	public Player() {
		this.inventory = new PlayerInventory();
		this.isDead = false;
		this.heldItem = null;
		this.status = new ArrayList<Potion>();
		this.direction = RIGHT;
		this.setImage(new Image("assets/agentassets/player.png"));
	}
	
	/*
	 * adds Potion effect to status, ignores LitBombs and adds other items to inventory
	 * maybe pickup method refactored into items?
	 * @param i, The item being picked up
	 */
	public void pickup(Item i) {
		i.pickedUp(this);
	}
	/*
	 * select item in inventory and equip it
	 * @param index, Inventory index to get the item
	 */
	public Item selectItem(int index) {
		if(inventory.getItem(index) != null) {
			this.heldItem = inventory.getItem(index);
		}
		return this.heldItem;
	}
	/*
	 * returns item that's equipped
	 * @return heldItem
	 */
	public Item getHeld() {
		return this.heldItem;
	}
	/*
	 * unequip item
	 */
	public void removeHeld() {
		this.heldItem = null;
	}
	/*
	 * Checks if MeleeWeapon is equipped, then if player is invincible else the player dies
	 * @param map, Dungeon object that holds all entities
	 */
	public void fight(Dungeon map) {
		if (this.heldItem != null && this.heldItem.isMeleeWeapon()) {
			this.useItem(map);
		} else if (this.isInvinc()) {
			map.removeAgent(map.getPlayerPos());
		} else {
			this.isDead = true;
		}
	}
	/*
	 * Calls the use function for equipped item
	 * @param map, Dungeon object that holds all entities
	 */
	public void useItem(Dungeon map) {
		heldItem.use(map);
	}
	/*
	 * returns if player is dead or not
	 * @return isDead, true or false
	 */
	public boolean deathStatus() {
		return this.isDead;
	}
	/*
	 * adds potion effect to player status list
	 * @param p, Potion that is being added to status list
	 */
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
	/*
	 * returns player's direction
	 * @return direction, can be right,left,up or down
	 */
	public Direction getDirection() {
		return this.direction;
	}
	/*
	 * returns if player is invincible or not
	 */
	public boolean isInvinc() {
		for (Potion p: this.status) {
			if (p.isInvinc()) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * returns if player is hovering or not
	 */
	public boolean isHover() {
		for (Potion p: this.status) {
			if (p.isHover()) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * return arraylist of player status effect
	 * @return status
	 */
	public ArrayList<Potion> getStatus() {
		return this.status;
	}
	
	/*
	 * return player inventory
	 * @return inventory
	 */
	public PlayerInventory getInventory() {
		return this.inventory;
	}
	
	/*
	 * sets direction of player
	 * @param direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/*
	 * sets player death status to true
	 */
	public void die() {
		this.isDead = true;
	}
	
	public ArrayList<Key> getKeys(){
		return inventory.getKeys();
	}

	/*
	 * Called when player falls into pit
	 * Check if player has hover effect
	 * if yes, do nothing
	 * if no, player dies 
	 */
	public void fallsIntoPit() {
		if (isHover()) return;
		else die();
	}
}