package Model;

import org.junit.jupiter.api.Test;
import java.awt.Point;

public class testDungeon {

    @Test
    void newDungeonHasDoubleInvulnWalls () {
        Dungeon testDun = new Dungeon();

        Point myPoint = new Point();

        int count = 0;

        for (int row = -1; row < 22; row++) {
            for (int col = -1; col < 22; col++) {
                myPoint.setLocation(row, col);
                if (row < 1 || row > 20 ||
                    col < 1 || col > 20) {
                    assert(testDun.pointTileType(myPoint) == Tile.TileType.INVINCIBLE_WALL);
                } else {
                    assert (testDun.pointTileType(myPoint) == null);
                }
                count++;
            }
        }

        assert (count == (24*24));
    }

}
