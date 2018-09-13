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
		else if(items.size() < maxCapacity) {
			items.add(i);
		}
		return;

	}
	
	public void getItem(int index) {
		return items.get(index);
	}
	
	
}
