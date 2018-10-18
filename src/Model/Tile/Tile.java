package Model.Tile;

import java.util.EnumMap;
import java.util.Objects;

import Model.Paintable;
import javafx.scene.image.Image;

public class Tile extends Paintable {


    private Type type;
    private EnumMap<Type, Image> images;

    public Tile(Type aType) {
        this.type = aType;
        images = new EnumMap<>(Type.class);
        this.images.put(Type.DEFAULT, new Image("assets/tileassets/default.png"));
        this.images.put(Type.INVINCIBLE_WALL, new Image("assets/tileassets/wall.png"));
        this.images.put(Type.OPEN_DOOR, new Image("assets/tileassets/opendoor.png"));
        this.images.put(Type.CLOSED_DOOR, new Image("assets/tileassets/closeddoor.png"));
        this.images.put(Type.EXIT, new Image("assets/tileassets/exit.png"));
        this.images.put(Type.PIT, new Image("assets/tileassets/pit.png"));
        this.images.put(Type.SWITCH, new Image("assets/tileassets/switch.png"));
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
    /**
     * Can create strategy pattern for image storage? in future of course
     */
    @Override
    public Image getImage() {
    	return this.images.get(this.type);
    }


}

