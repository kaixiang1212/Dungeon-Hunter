package Test;



import Model.Tile;
import org.junit.Assert;
import org.junit.Test;

public class testTile {

    @Test
    public void CanMakeTile() {
        Tile test1 = new Tile(Tile.TileType.INVINCIBLE_WALL);
        Assert.assertEquals(test1.getType(), Tile.TileType.INVINCIBLE_WALL);
        test1 = new Tile(Tile.TileType.DESTRUCTABLE_WALL);
        Assert.assertEquals(test1.getType(), Tile.TileType.DESTRUCTABLE_WALL);
        test1 = new Tile(Tile.TileType.PIT);
        Assert.assertEquals(test1.getType(), Tile.TileType.PIT);
        test1 = new Tile(Tile.TileType.OPEN_DOOR);
        Assert.assertEquals(test1.getType(), Tile.TileType.OPEN_DOOR);
        test1 = new Tile(Tile.TileType.CLOSED_DOOR);
        Assert.assertEquals(test1.getType(), Tile.TileType.CLOSED_DOOR);

    }

    @Test
    public void CanSetTile() {
        Tile test1 = new Tile(Tile.TileType.INVINCIBLE_WALL);
        Assert.assertEquals(test1.getType(), Tile.TileType.INVINCIBLE_WALL);
        test1.setType(Tile.TileType.EXIT);
        Assert.assertNotEquals(test1.getType(), Tile.TileType.INVINCIBLE_WALL);
        Assert.assertEquals(test1.getType(), Tile.TileType.EXIT);
    }

}
