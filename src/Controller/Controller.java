package Controller;

import Model.*;
import org.junit.Test;

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
		String move = "1";
		Direction dir;
		for(int i = 0; i < 100; i++) {
			//Take in player input
			  // Reading from System.in

			while(!tempBadMethodChangeMeValidMove(move)) {
				System.out.println("Enter your move (wasd): ");
				String n = reader.next();
				System.out.println(n);
			}

			dir = tempBadMethodChangeMeGetInputDirection(move);
			update(d, dir);
			
			
		}
		reader.close();
		
	}

	private Direction tempBadMethodChangeMeGetInputDirection(String moveInput) {
		if (moveInput.equals("w")) {
			return Direction.UP;
		} else if (moveInput.equals("a")) {
			return Direction.LEFT;
		} else if (moveInput.equals("s")) {
			return Direction.DOWN;
		} else if (moveInput.equals("d")) {
			return Direction.RIGHT;
		}

		return null;
	}

	private boolean tempBadMethodChangeMeValidMove(String move) {
	    String[] valids = {"w", "a", "s", "d"};
		for (String v : valids) {
			if (move.equals(v)) {
				return true;
			}
		}
		return false;
	}

	@Test

	/**
	 * Updates state of the dungeon
	 * @param d
	 * @param move
	 */
	public void update(Dungeon d, Direction move) {
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
