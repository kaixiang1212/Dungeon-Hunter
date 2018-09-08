package Model;

public class Tile {
    enum TileType {
        INVINCIBLE_WALL,
        DESTRUCTABLE_WALL,
        EXIT,
        CLOSED_DOOR,
        OPEN_DOOR,
        SWITCH,
        PIT
    }

    private TileType type;

    public Tile(TileType aType) {
        this.type = aType;
    }

}
