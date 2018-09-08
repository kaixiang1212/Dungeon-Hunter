package Model;

import org.junit.jupiter.api.Test;
import java.awt.Point;

public class testDungeon {

    @Test
    void newDungeonHasDoubleInvulnWalls () {
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
}
