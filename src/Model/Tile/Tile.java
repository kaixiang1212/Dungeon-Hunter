package Model.Tile;

import java.util.Objects;

public class Tile {


    private Type type;

    public Tile(Type aType) {
        this.type = aType;
    }

    //@Contract(pure = true)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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
    
    public boolean isType(Type type) {
    	return this.type.equals(type);
    }


}

