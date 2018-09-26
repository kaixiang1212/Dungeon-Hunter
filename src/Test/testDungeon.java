package Test;


import Model.Tile;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import Model.Dungeon;
import Model.Tile.TileType;
import org.junit.rules.ExpectedException;

import java.awt.Point;
import java.util.regex.Matcher;

public class testDungeon {

    Dungeon testDun;

    @Rule public ExpectedException thrown = ExpectedException.none();

    @Test
    public void newDungeonHasDoubleInvulnWalls () {
        // Setup
        int[] sizes = {1, 5, 20};
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

    @Test(expected = Exception.class)
    public void newDungeonSize0() {

        // Boundary case, min size 1, should throw illegal arg exception
        testDun = new Dungeon(0);
    }


    @Test(expected = Exception.class)
    public void newDungeonSize21() {

        // Boundary case, max size 20, should throw illegal arg exception
        testDun = new Dungeon(21);
    }

    @Test(expected = Test.None.class)
    public void newDungeonSize1() {
        // Boundary case, min size 1, should have a good Dungeon
        testDun = new Dungeon(1);
    }

    @Test(expected = Test.None.class)
    public void newDungeonSize20() {
        // Boundary case, max size 20, should have a good Dungeon
        testDun = new Dungeon(20);
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
            testDun.placeTile(TileType.PIT, myPoint);

            int count = 0;
            for (int i = 0; i < size + 3; i++) {

                // top edge
                myPoint.setLocation(-1 + i, -1);
                thrown.expect(IllegalArgumentException.class);
                testDun.placeTile(TileType.PIT, myPoint);
                count++;

                // bottom edge
                thrown.expect(IllegalArgumentException.class);
                myPoint.setLocation(-1 + i, size + 2);
                testDun.placeTile(TileType.PIT, myPoint);
                count++;


                // left edge
                myPoint.setLocation(-1, 0 + i);
                thrown.expect(IllegalArgumentException.class);
                testDun.placeTile(TileType.PIT, myPoint);
                count++;

                // right edge
                myPoint.setLocation(size + 2, 0 + i);
                thrown.expect(IllegalArgumentException.class);
                //System.out.format("Good catch! x: %d y: %d\n", myPoint.x, myPoint.y);
                count++;
            }
            //System.out.format("count: %d\n", count);
            Assert.assertEquals(count, (size + 3) * 4);
        }


        // TODO ? test placements that are beyond just the edges

    }
}

