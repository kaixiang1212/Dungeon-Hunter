package itemDesign;

import java.util.ArrayList;

public class PlayerInventory {

	private ArrayList<Item> items;
	private int maxCapacity;
	private int equipped;
	
	public PlayerInventory() {
		items = new ArrayList<>();
		maxCapacity = 5;
		equipped = 0;
	}
	
	public void storeItem(Item i) {
		if(i.isStackable() && items.contains(i)) {
			for(Item search: items) {
				if(search.equals(i)) {
					//need to fix this so it covers all stack
					((RangedWeapon) search).addUses();
					return;
				}
			}
		}
		else if(items.size() < maxCapacity) {
			items.add(i);
		}
		return;

	}
	
	public Item getItem() {
		return items.get(this.equipped);
	}
	
	public void moveThrough() {
		if (this.equipped == maxCapacity) {
			this.equipped = 0;
		} else {
			this.equipped++;
		}
	}
	
	public boolean isEmpty() {
		if (this.items.isEmpty()) {
			return true;
		}
		return false;
	}
	
	
}