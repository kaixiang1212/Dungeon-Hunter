package Model;

import java.awt.Point;
import java.util.Scanner;

/**
 * Incomplete controller class which allows user to navigate menus and play chosen dungeons
 * @author Richard
 *
 */
public class Controller {

	/**
	 * Starts dungeon game, loops through certain amount of turns
	 * asking for player input.
	 * @param d Dungeon level to be played
	 */
	public void startGame(Dungeon d) {
		//Temporary game loop, allowing for 100 moves
		Scanner reader = new Scanner(System.in);
		for(int i = 0; i < 100; i++) {
			//Take in player input
			  // Reading from System.in
			System.out.println("Enter your move: ");
			String n = reader.next();
			System.out.println(n);
			update(d, n);
			
			
		}
		reader.close();
		
	}
	/**
	 * Updates state of the dungeon
	 * @param d
	 * @param move
	 */
	public void update(Dungeon d, String move) {
		d.updatePlayer(move);
		d.updateAgents();
	}
	
	public Dungeon createTestLevel() {
		Dungeon d = new Dungeon(5);
		Player player = new Player();
		d.placePlayer(player, new Point(1,1));
		ComputerAgent hunter = new Hunter();
		d.placeComputerAgent(hunter, new Point(5,5));
		d.placeItem(new Treasure(), new Point(2,1));
		return d;
		
	}
	public static void main(String[] args) {
		Controller control = new Controller();
		//Some way to pick a level or create one then pass it in as argument to start the game in dungeon
		Dungeon d = control.createTestLevel();
		control.startGame(d);
	}
	
}
