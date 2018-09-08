package Model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Dungeon {
    public static final int MAX_SIZE = 20;
    private Map<Point, Tile> tileGrid;

    public Dungeon(int size) {
        if (size > MAX_SIZE) {
            System.err.println("Max size is " + MAX_SIZE + " You tried " + size);
            System.exit(1);

        }

        if (size < 1) {
             System.err.println("Min size is 1 You tried " + size);
             System.exit(1);
        }
        this.tileGrid = initTileGrid(size);
    }

    private HashMap<Point, Tile> initTileGrid(int size) {

        if (size < 1) {
            // TODO: refine this error checking
            System.err.println("tried to make a dungeon grid size less than 1");
            System.exit(1);
        }

        HashMap<Point, Tile> ret = new HashMap<Point, Tile>();
        int[] edges = {-1, 0, size+1, size+2};
        // Set the dungeons walls on left/right sides
        for(int i = -1; i <= size + 2; i++) {
            for (int j : edges) {
                ret.put(new Point(i, j), new Tile(Tile.TileType.INVINCIBLE_WALL));
            }
        }

        // Set the dungeons walls on remaining top/bottom sides
        for (int i : edges) {
            for (int j = 1; j <= size; j++) {
                ret.put(new Point(i, j), new Tile(Tile.TileType.INVINCIBLE_WALL));
            }
        }

        return ret;
    }


    public Tile.TileType pointTileType(Point location) {
        Tile local = tileGrid.get(location);
        if (local == null) {
            return null;
        }

        return local.getType();
    }
}
