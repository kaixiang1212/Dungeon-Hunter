package Model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Dungeon {
    public static final int SIZE = 20;
    private Map<Point, Tile> grid;

    public Dungeon() {
        this.grid = init_grid(SIZE);
    }

    private HashMap<Point, Tile> init_grid(int size) {

        if (size < 1) {
            // TODO: refine this error checking
            System.err.println("tried to make a dungeon grid size less than 1");
            System.exit(1);
        }

        HashMap<Point, Tile> ret = new HashMap<Point, Tile>();
        int[] edges = {-1, 0, size-1, size};
        // Set the dungeons walls on left/right sides
        for(int i = -1; i <= size; i++) {
            for (int j : edges) {
                ret.put(new Point(i, j), new Tile(Tile.TileType.INVINCIBLE_WALL));
            }
        }

        // Set the dungeons walls on top/bottom sides
        for (int i : edges) {
            for (int j = 1; j < size-1; j++) {
                ret.put(new Point(i, j), new Tile(Tile.TileType.INVINCIBLE_WALL));
            }
        }

        return ret;
    }
}
