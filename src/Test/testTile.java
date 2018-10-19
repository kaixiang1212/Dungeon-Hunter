package Test;

import Model.Tile.Type;
import Model.Tile.Wall;
import Model.Tile.Door;
import Model.Tile.Exit;
import Model.Tile.Pit;
import Model.Tile.Switch;
import Model.Tile.Tile;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class testTile {

    @Test
    public void CanMakeTile() {
        Tile test1 = new Wall();
        assertEquals(test1.getType(), Type.Wall);
        test1 = new Pit();
        assertEquals(test1.getType(), Type.Pit);
        test1 = new Door();
        assertEquals(test1.getType(), Type.ClosedDoor);
        test1 = new Exit();
        assertEquals(test1.getType(), Type.Exit);
        test1 = new Switch();
        assertEquals(test1.getType(), Type.Switch);
    }

}
