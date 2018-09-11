package Model;

public class Treasure implements Item {
	private String name;
	
	public Treasure(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
