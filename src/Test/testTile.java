package Test;



import Model.Tile.Type;
import Model.Tile.Tile;
import org.junit.Assert;
import org.junit.Test;

public class testTile {

    @Test
    public void CanMakeTile() {
        Tile test1 = new Tile(Type.INVINCIBLE_WALL);
        Assert.assertEquals(test1.getType(), Type.INVINCIBLE_WALL);
        test1 = new Tile(Type.DESTRUCTIBLE_WALL);
        Assert.assertEquals(test1.getType(), Type.DESTRUCTIBLE_WALL);
        test1 = new Tile(Type.PIT);
        Assert.assertEquals(test1.getType(), Type.PIT);
        test1 = new Tile(Type.OPEN_DOOR);
        Assert.assertEquals(test1.getType(), Type.OPEN_DOOR);
        test1 = new Tile(Type.CLOSED_DOOR);
        Assert.assertEquals(test1.getType(), Type.CLOSED_DOOR);

    }

    @Test
    public void CanSetTile() {
        Tile test1 = new Tile(Type.INVINCIBLE_WALL);
        Assert.assertEquals(test1.getType(), Type.INVINCIBLE_WALL);
        test1.setType(Type.EXIT);
        Assert.assertNotEquals(test1.getType(), Type.INVINCIBLE_WALL);
        Assert.assertEquals(test1.getType(), Type.EXIT);
    }

}
