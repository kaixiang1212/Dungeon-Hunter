package Model;

import Model.Item.Item;
import Model.Item.Key;

import java.util.ArrayList;

public class PlayerInventory {

	private ArrayList<Item> items;
	private int maxCapacity;
	
	public PlayerInventory() {
		items = new ArrayList<>();
		maxCapacity = 3;
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
		if(items.size() >= index+1) {
			return items.get(index);
		}
		return null;
	}
	public void removeItem(Item i) {
		items.remove(i);
	}
	public boolean isEmpty() {
		if (items.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Key> getKeys(){
		ArrayList<Key> keys = new ArrayList<>();
		for (Item item : items) {
			if (item instanceof Key) keys.add((Key )item);
		}
		return keys;
	}
	
	public int getNumItems() {
		return items.size();
	}
	public String toString() {
		if(items.size() == 0) {
			return "None";
		}
		StringBuilder sb = new StringBuilder();
		for(Item i:items) {
			sb.append(i.toString() + "\n");
			
		}
		String desc = new String(sb);
		return desc;
	}
}


