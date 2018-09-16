package Model;

import java.util.ArrayList;

public class PlayerInventory {

	private ArrayList<Item> items;
	private int maxCapacity;
	
	public PlayerInventory() {
		items = new ArrayList<>();
		maxCapacity = 5;
	}
	
	public void storeItem(Item i) {
		if(i.isStackable() && items.contains(i)) {
			for(Item search: items) {
				if(search.equals(i)) {
					search.stack();
					return;
				}
			}
		} 
		else if ((!i.isStackable()) && (items.contains(i))) {
			items.remove(i);
			items.add(i);
		}
		else if(items.size() < maxCapacity) {
			items.add(i);
		}
		return;
	}
	
	public Item getItem(int index) {
		return items.get(index);
	}
	public void removeItem(Item i) {
		this.items.remove(i);
	}
	public boolean isEmpty() {
		if (this.items.isEmpty()) {
			return true;
		}
		return false;
	}
	public int getNumItems() {
		return this.items.size();
	}
}


