package Model;

import org.junit.jupiter.api.Test;
import java.awt.Point;

public class testDungeon {

    @Test
    void newDungeonHasDoubleInvulnWallsSize20 () {
        int size = 20;
        Dungeon testDun = new Dungeon(size);

        Point myPoint = new Point();

        int count = 0;

        for (int row = -1; row <= size+2; row++) {
            for (int col = -1; col <= size+2; col++) {
                myPoint.setLocation(row, col);
                if (row < 1 || row > size ||
                    col < 1 || col > size) {
                    assert(testDun.pointTileType(myPoint) == Tile.TileType.INVINCIBLE_WALL);
                } else {
                    assert (testDun.pointTileType(myPoint) == null);
                }
                count++;
            }
            System.out.println();
        }

        assert (count == (size + 4) * (size + 4));
    }

    @Test
    void newDungeonSizeLimits0To20() {
        Dungeon testDun;

        // Boundary case, min size 1, should throw illigal arg exception
        try {
            testDun = new Dungeon(0);
            assert (false);
        } catch (IllegalArgumentException e) {
            // This block is good
        } catch (Exception e) {
            assert (false);
        }

        // Boundary case, max size 20, should throw illigal arg exception
        try {
            testDun = new Dungeon(21);
            assert (false);
        } catch (IllegalArgumentException e) {
            // this block is good
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
}

