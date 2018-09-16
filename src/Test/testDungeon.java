package Test;

import Model.Tile;
import org.junit.Assert;
import org.junit.Test;

import Model.Dungeon;
import Model.Tile.TileType;

import java.awt.Point;

public class testDungeon {

    @Test
    public void newDungeonHasDoubleInvulnWalls () {
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
                        Assert.assertEquals (testDun.pointTileType(myPoint), TileType.INVINCIBLE_WALL);
                    } else {
                        Assert.assertEquals(testDun.pointTileType(myPoint), TileType.DEFAULT);
                    }

                    count++;

                }
            }

            /**
             * We want to make sure we checked the square area, including double layer wall, and that the tile
             * grid size matches the number of tiles taken up by the double-layer wall.
             */
            Assert.assertEquals (count, (size + 4) * (size + 4));
            Assert.assertEquals (testDun.getTileGrid().size(), count);
            /**
             * Make sure that the corners make sense. Tells us top-left, bottom right of screen is set correct
             *
             */
            myPoint.setLocation(0, 0);
            Assert.assertTrue(myPoint.equals(testDun.getTopLeft()));

            myPoint.setLocation(size+1, size+1);
            Assert.assertTrue(myPoint.equals(testDun.getBottomRight()));

        }
    }

    @Test
    public void newDungeonSizeLimits0To20() {
        Dungeon testDun;

        // Boundary case, min size 1, should throw illegal arg exception
        try {
            testDun = new Dungeon(0);
            assert (false);
        } catch (IllegalArgumentException e) {
            // This block is good
            Assert.assertTrue(e.getMessage().contains("1-20"));
            Assert.assertTrue(e.getMessage().contains(Integer.toString(0)));
        } catch (Exception e) {
            Assert.assertTrue(false);
        }

        // Boundary case, max size 20, should throw illegal arg exception
        try {
            testDun = new Dungeon(21);
            assert (false);
        } catch (IllegalArgumentException e) {
            // this block is good
            Assert.assertTrue(e.getMessage().contains("1-20"));
            Assert.assertTrue(e.getMessage().contains(Integer.toString(0)));
        } catch (Exception e) {
            assert (false);
        }

        // Boundary case, min size 20, should have a good Dungeon
        try {
            testDun = null;
            testDun = new Dungeon(1);
            Assert.assertNotNull(testDun);
        } catch (IllegalArgumentException e) {
            assert (false);
        } catch (Exception e) {
            assert (false);
        }

        // Boundary case, max size 20, should have a good Dungeon
        try {
            testDun = null;
            testDun = new Dungeon(20);
            Assert.assertNotNull(testDun);
        } catch (IllegalArgumentException e) {
            assert (false);
        } catch (Exception e) {
            assert (false);
        }
    }

    @Test
    public void PlaceTileOnEmptyLocation() {
        Dungeon testDun = new Dungeon(4);
        Point myPoint = new Point(1, 1);
        int startSize = testDun.getTileGrid().size();


        // Ensure Tile is empty
        Assert.assertEquals(testDun.getTileGrid().get(myPoint).getType(), Tile.TileType.DEFAULT);

        // Cannot place Invincible Wall
        Assert.assertFalse(testDun.placeTile(TileType.INVINCIBLE_WALL, myPoint));

        // Can place SWITCH
        Assert.assertTrue(testDun.placeTile(TileType.SWITCH, myPoint));
        Assert.assertEquals(testDun.getTileGrid().get(myPoint).getType(), TileType.SWITCH);
        Assert.assertEquals(testDun.getTileGrid().size(), startSize);

        myPoint.setLocation(1, 2);
        Assert.assertEquals(testDun.getTileGrid().get(myPoint).getType(), TileType.DEFAULT);
        Assert.assertTrue(testDun.placeTile(TileType.OPEN_DOOR, myPoint));
        Assert.assertEquals(testDun.getTileGrid().get(myPoint).getType(), TileType.OPEN_DOOR);
        Assert.assertEquals(testDun.getTileGrid().size(), startSize);

        myPoint.setLocation(1, 3);
        Assert.assertEquals(testDun.getTileGrid().get(myPoint).getType(), TileType.DEFAULT);
        Assert.assertTrue(testDun.placeTile(TileType.CLOSED_DOOR, myPoint));
        Assert.assertEquals(testDun.getTileGrid().get(myPoint).getType(), TileType.CLOSED_DOOR);
        Assert.assertEquals(testDun.getTileGrid().size(), startSize);

        myPoint.setLocation(1, 4);
        Assert.assertEquals(testDun.getTileGrid().get(myPoint).getType(), TileType.DEFAULT);
        Assert.assertTrue(testDun.placeTile(TileType.PIT, myPoint));
        Assert.assertEquals(testDun.getTileGrid().get(myPoint).getType(), TileType.PIT);
        Assert.assertEquals(testDun.getTileGrid().size(), startSize);

        myPoint.setLocation(2, 1);
        Assert.assertEquals(testDun.getTileGrid().get(myPoint).getType(), TileType.DEFAULT);
        Assert.assertTrue(testDun.placeTile(TileType.EXIT, myPoint));
        Assert.assertEquals(testDun.getTileGrid().get(myPoint).getType(), TileType.EXIT);
        Assert.assertEquals(testDun.getTileGrid().size(), startSize);
    }

    @Test
    public void CannotPlaceOutsideGridDimensions() {

        int[] sizes = {1, 5, 10, 20};
        Dungeon testDun;
        Point myPoint = new Point();

        // Good placement
        for (int size : sizes) {
            myPoint.setLocation(1,1);
            testDun = new Dungeon(size);
            try {
                testDun.placeTile(TileType.PIT, myPoint);
            } catch (Exception e) {
                assert (false);
            }

            int count = 0;
            for (int i = 0; i < size + 3; i++) {

                // top edge
                myPoint.setLocation(-1 + i, -1);
                try {
                    testDun.placeTile(TileType.PIT, myPoint);
                    assert (false);
                } catch (IllegalArgumentException e) {
                    //System.out.format("Good catch! x: %d y: %d\n", myPoint.x, myPoint.y);
                    Assert.assertTrue(e.getMessage().contains("of bounds"));
                } catch (Exception e) {
                    assert (false);
                }
                count++;

                // bottom edge
                myPoint.setLocation(-1 + i, size + 2);
                try {
                    testDun.placeTile(TileType.PIT, myPoint);
                    assert (false);
                } catch (IllegalArgumentException e) {
                    //System.out.format("Good catch! x: %d y: %d\n", myPoint.x, myPoint.y);
                    Assert.assertTrue(e.getMessage().contains("of bounds"));
                } catch (Exception e) {
                    assert (false);
                }
                count++;


                // left edge
                myPoint.setLocation(-1, 0 + i);
                try {
                    testDun.placeTile(TileType.PIT, myPoint);
                    assert (false);
                } catch (IllegalArgumentException e) {
                    //System.out.format("Good catch! x: %d y: %d\n", myPoint.x, myPoint.y);
                    Assert.assertTrue(e.getMessage().contains("of bounds"));
                } catch (Exception e) {
                    assert (false);
                }
                count++;

                // right edge
                myPoint.setLocation(size + 2, 0 + i);
                try {
                    testDun.placeTile(TileType.PIT, myPoint);
                    assert (false);
                } catch (IllegalArgumentException e) {
                    //System.out.format("Good catch! x: %d y: %d\n", myPoint.x, myPoint.y);
                    Assert.assertTrue(e.getMessage().contains("of bounds"));
                } catch (Exception e) {
                    assert (false);
                }
                count++;
            }
            //System.out.format("count: %d\n", count);
            Assert.assertEquals(count, (size + 3) * 4);
        }


        // TODO ? test placements that are beyond just the edges

    }
}

