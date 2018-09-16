package Test;


import org.junit.jupiter.api.Test;

import Model.Tile;
import Model.Tile.TileType;

public class testTile {

    @Test
    void CanMakeTile() {
        Tile test1 = new Tile(Tile.TileType.INVINCIBLE_WALL);
        assert(test1.getType() == Tile.TileType.INVINCIBLE_WALL);
        test1 = new Tile(Tile.TileType.DESTRUCTABLE_WALL);
        assert(test1.getType() == Tile.TileType.DESTRUCTABLE_WALL);
        test1 = new Tile(Tile.TileType.PIT);
        assert(test1.getType() == Tile.TileType.PIT);
        test1 = new Tile(Tile.TileType.OPEN_DOOR);
        assert(test1.getType() == Tile.TileType.OPEN_DOOR);
        test1 = new Tile(Tile.TileType.CLOSED_DOOR);
        assert(test1.getType() == Tile.TileType.CLOSED_DOOR);
    }

    @Test
    void CanSetTile() {
        Tile test1 = new Tile(Tile.TileType.INVINCIBLE_WALL);
        assert(test1.getType() == Tile.TileType.INVINCIBLE_WALL);
        test1.setType(Tile.TileType.EXIT);
        assert(test1.getType() != Tile.TileType.INVINCIBLE_WALL);
        assert(test1.getType() == Tile.TileType.EXIT);
    }

}
