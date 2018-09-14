package itemDesign;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;


public class Player {

	private int healthPoints;
	private ArrayList<Potion> status;
	private String direction;
	private PlayerInventory inventory;
	
	public Player() {
		this.healthPoints = 100;
		this.status = new ArrayList<Potion>();
		this.direction = "Right";
		this.inventory = new PlayerInventory();
	}
	
	/*
	 * generalised pickup function for potion and weapons
	 * add potion status to player
	 * weapon checks for arrow to stack or replaces weapon held
	 * @TODO: Think of how we are implementing Treasure
	 */
	public void pickup(Item i) {
		if (i.isPotion()) {
			this.addStatus((Potion) i);
		} else {
			inventory.storeItem(i);
		}
	}
	
	/*
	 * add potion effect to player
	 * if under the effect, refresh timer/moves
	 * @TODO: is arraylist the best option?
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
	
	public int getHealth() {
		return this.healthPoints;
	}
	
	public String getDirection() {
		return this.direction;
	}
	
	public ArrayList<Potion> getStatus() {
		return this.status;
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

	/*
	 * Check for invincibility
	 * Reduce hitpoints, check and process death.
	 * @TODO: Is checking status better or is invincibility influencing HP better?
	 */
	public void takeDamage(int damage) {
		if (this.isInvinc()) {
			return;
		}
		this.healthPoints = this.healthPoints - damage;
		if(this.healthPoints <= 0) {
			System.out.println("Player has died");
		}
	}
	
	public void use(Dungeon dungeon) {
		Item equipped = this.inventory.getItem();
		equipped.use(dungeon);
	}
	
	public Item getEquipped() {
		return this.inventory.getItem();
	}
	
	public PlayerInventory getInventory() {
		return this.inventory;
	}
	
}
