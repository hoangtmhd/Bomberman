package entities.changeable.inactive.item;

import app.screens.PlayScreen;
import entities.EntityMapData;
import entities.changeable.inactive.InactiveEntity;
import graphics.Sprite;

import java.util.ArrayList;

public abstract class Item extends InactiveEntity {
    protected final ArrayList<ArrayList<EntityMapData>> mapData;

    public Item(int xUnit, int yUnit, Sprite sprite, ArrayList<ArrayList<EntityMapData>> mapData) {
        super(xUnit, yUnit, sprite);
        this.mapData = mapData;
    }

    public boolean hide() {
        int xItem = (int) hitBox.getX() / PlayScreen.SCALED_SIZE;
        int yItem = (int) hitBox.getY() / PlayScreen.SCALED_SIZE;
        int h = mapData.size() - yItem - 1;
        return (mapData.get(h).get(xItem) == EntityMapData.BRICK);
    }

    @Override
    public void entityRemove() {
        remove();
    }
}
