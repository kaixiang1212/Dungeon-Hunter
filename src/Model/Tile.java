package Model;

import org.jetbrains.annotations.Contract;

import java.util.Objects;

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

    @Contract(pure = true)
    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return type == tile.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
