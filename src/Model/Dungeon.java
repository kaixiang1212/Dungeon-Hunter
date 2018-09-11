package Model;

//import org.jetbrains.annotations.Contract;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Dungeon {
    public static final int MAX_SIZE = 20;


    private Point topLeft;
    private Point bottomRight;

    private Map<Point, Tile> tileGrid;
    private Map<Point, Agent> agentGrid;
    private Point playerPosition;
    //private Map<Point, Pickups> pickGrid;

    public Dungeon(int size) throws IllegalArgumentException{
        if (size > MAX_SIZE || size < 1) {
            throw new IllegalArgumentException("Dungeon constructor size param 1-20. Received " + size);
        }

        this.tileGrid = initTileGrid(size);

        this.topLeft = new Point(0, 0);
        this.bottomRight = new Point(size+1, size+1);
    }
    //@Contract(pure = true)
    public Map<Point, Tile> getTileGrid() {
        return tileGrid;
    }

    //@Contract(pure = true)
    public Point getTopLeft() {
        return topLeft;
    }

    //@Contract(pure = true)
    public Point getBottomRight() {
        return bottomRight;
    }

    //@Contract(pure = true)
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


    //@Contract(pure = true)
    public Tile.TileType pointTileType(Point location) {
        Tile local = tileGrid.get(location);
        if (local == null) {
            return null;
        }

        return local.getType();
    }

    public boolean placeTile(Tile.TileType aTile, Point myPoint) throws IllegalArgumentException {

        // Cannot place invincible wall
        if(aTile == Tile.TileType.INVINCIBLE_WALL) {
            return false;
        }

        int aX = myPoint.x;
        int aY = myPoint.y;

        int top = topLeft.y;
        int left = topLeft.x;
        int bot = bottomRight.y;
        int right = bottomRight.x;

        if (aX < left || aX > right ||
            aY > bot || aY < top) {
            throw new IllegalArgumentException("Placement out of bounds");
        }

        if (tileGrid.get(myPoint) == null) {

            tileGrid.put(myPoint, new Tile(aTile));
            return true;
        }

        return false;
    }
    public void placeAgent(Agent a, Point agentPoint) {
    	agentGrid.put(agentPoint, a);
    }
    public void placePlayer(Player p, Point playerStart) {
    	playerPosition = playerStart;
    }
    public void updateAgents() {
    	for(Agent a: agentGrid.values()) {
    		a.move(playerPosition, agentGrid);
    	}
    }
}