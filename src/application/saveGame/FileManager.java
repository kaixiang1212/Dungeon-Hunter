package application.saveGame;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Model.Dungeon;
import Model.Player;
import Model.Item.Key;
import Model.Tile.Door;

public class FileManager {

	public static void main(String args[]) {

		FileManager obj = new FileManager();

		Dungeon dungeon= new Dungeon(10);
		Door door = new Door();
		door.place(dungeon, new Point(3, 2));
		dungeon.placePlayer(new Player(), new Point(1, 1));
		Key aKey = new Key();
		try {
			aKey.place(dungeon, new Point(2, 2));
		} catch (Exception e) {
			e.printStackTrace();
		}

		obj.save(dungeon, "Saves/DoorUnlockKey");
		Dungeon dungeon2 = (Dungeon )obj.load("Saves/DoorUnlockKey");

	}

	public void save(Dungeon data, String fileName) {

		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		System.out.println("Saving Game...");
		
		try {

			fout = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(data);

			System.out.println("Game Saved");

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Dungeon load(String fileName) {
		System.out.println("Loading Game...");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Object dungeon = null;

		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			dungeon = ois.readObject();
			System.out.println("Game Loaded");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (Dungeon )dungeon;
	}
}
