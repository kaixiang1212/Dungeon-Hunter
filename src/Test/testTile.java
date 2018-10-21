package Test;

import Model.Tile.Wall;
import Model.Tile.Door;
import Model.Tile.Exit;
import Model.Tile.Pit;
import Model.Tile.Switch;
import Model.Tile.Tile;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class testTile {

    @Test
    public void CanCloneTile() throws CloneNotSupportedException {
        Tile test1 = new Wall();
        assertTrue(test1.clone() instanceof Wall);
        test1 = new Pit();
        assertTrue(test1.clone() instanceof Pit);
        test1 = new Door();
        assertTrue(test1.clone() instanceof Door);
        test1 = new Exit();
        assertTrue(test1.clone() instanceof Exit);
        test1 = new Switch();
        assertTrue(test1.clone() instanceof Switch);
    }

}
