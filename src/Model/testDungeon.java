package Model;

import org.junit.jupiter.api.Test;
import java.awt.Point;

public class testDungeon {

    @Test
    void newDungeonHasDoubleInvulnWalls () {
        // Setup
        int[] sizes = {1, 5, 20};
        Dungeon testDun;
        Point myPoint = new Point();

        // test
        for (int size : sizes) {
            int count = 0;
            testDun = new Dungeon(size);
            for (int row = -1; row <= size + 2; row++) {
                for (int col = -1; col <= size + 2; col++) {
                    myPoint.setLocation(row, col);

                    if (row < 1 || row > size ||
                            col < 1 || col > size) {
                        assert (testDun.pointTileType(myPoint) == Tile.TileType.INVINCIBLE_WALL);
                    } else {
                        assert (testDun.pointTileType(myPoint) == null);
                    }

                    count++;

                }
            }

            /**
             * We want to make sure we checked the square area, including double layer wall, and that the tile
             * grid size matches the number of tiles taken up by the double-layer wall.
             */
            assert (count == (size + 4) * (size + 4));
            assert (testDun.getTileGrid().size() == (8*size + 16));
            /**
             * Make sure that the corners make sense. Tells us top-left, bottom right of screen is set correct
             *
             */
            myPoint.setLocation(0, 0);
            assert (myPoint.equals(testDun.getTopLeft()));

            myPoint.setLocation(size+1, size+1);
            assert (myPoint.equals(testDun.getBottomRight()));

        }
    }

    @Test
    void newDungeonSizeLimits0To20() {
        Dungeon testDun;

        // Boundary case, min size 1, should throw illegal arg exception
        try {
            testDun = new Dungeon(0);
            assert (false);
        } catch (IllegalArgumentException e) {
            // This block is good
            assert(e.getMessage().contains("1-20"));
            assert(e.getMessage().contains(Integer.toString(0)));
        } catch (Exception e) {
            assert (false);
        }

        // Boundary case, max size 20, should throw illegal arg exception
        try {
            testDun = new Dungeon(21);
            assert (false);
        } catch (IllegalArgumentException e) {
            // this block is good
            assert(e.getMessage().contains("1-20"));
            assert(e.getMessage().contains(Integer.toString(0)));
        } catch (Exception e) {
            assert (false);
        }

        // Boundary case, min size 20, should have a good Dungeon
        try {
            testDun = null;
            testDun = new Dungeon(1);
            assert (testDun != null);
        } catch (IllegalArgumentException e) {
            assert (false);
        } catch (Exception e) {
            assert (false);
        }

        // Boundary case, max size 20, should have a good Dungeon
        try {
            testDun = null;
            testDun = new Dungeon(20);
            assert (testDun != null);
        } catch (IllegalArgumentException e) {
            assert (false);
        } catch (Exception e) {
            assert (false);
        }
    }

    @Test
    void PlaceTileOnEmptyLocation() {
        Dungeon testDun = new Dungeon(4);
        Point myPoint = new Point(1, 1);
        int startSize = testDun.getTileGrid().size();

        // Ensure Tile is empty
        assert(testDun.getTileGrid().containsKey(myPoint) == false);

        // Cannot place Invincible Wall
        assert(testDun.placeTile(Tile.TileType.INVINCIBLE_WALL, myPoint) == false);

        // Can place SWITCH
        assert(testDun.placeTile(Tile.TileType.SWITCH, myPoint) == true);
        assert(testDun.getTileGrid().containsKey(myPoint) == true);
        assert(testDun.getTileGrid().get(myPoint).getType() == Tile.TileType.SWITCH);
        assert(testDun.getTileGrid().size() == startSize + 1);

        myPoint.setLocation(1, 2);
        assert(testDun.getTileGrid().containsKey(myPoint) == false);
        assert(testDun.placeTile(Tile.TileType.OPEN_DOOR, myPoint) == true);
        assert(testDun.getTileGrid().containsKey(myPoint) == true);
        assert(testDun.getTileGrid().get(myPoint).getType() == Tile.TileType.OPEN_DOOR);
        assert(testDun.getTileGrid().size() == startSize + 2);

        myPoint.setLocation(1, 3);
        assert(testDun.getTileGrid().containsKey(myPoint) == false);
        assert(testDun.placeTile(Tile.TileType.CLOSED_DOOR, myPoint) == true);
        assert(testDun.getTileGrid().containsKey(myPoint) == true);
        assert(testDun.getTileGrid().get(myPoint).getType() == Tile.TileType.CLOSED_DOOR);
        assert(testDun.getTileGrid().size() == startSize + 3);

        myPoint.setLocation(1, 4);
        assert(testDun.getTileGrid().containsKey(myPoint) == false);
        assert(testDun.placeTile(Tile.TileType.PIT, myPoint) == true);
        assert(testDun.getTileGrid().containsKey(myPoint) == true);
        assert(testDun.getTileGrid().get(myPoint).getType() == Tile.TileType.PIT);
        assert(testDun.getTileGrid().size() == startSize + 4);

        myPoint.setLocation(2, 1);
        assert(testDun.getTileGrid().containsKey(myPoint) == false);
        assert(testDun.placeTile(Tile.TileType.EXIT, myPoint) == true);
        assert(testDun.getTileGrid().containsKey(myPoint) == true);
        assert(testDun.getTileGrid().get(myPoint).getType() == Tile.TileType.EXIT);
        assert(testDun.getTileGrid().size() == startSize + 5);
    }
}

