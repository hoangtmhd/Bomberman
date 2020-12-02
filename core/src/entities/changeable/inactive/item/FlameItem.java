package entities.changeable.inactive.item;

import entities.EntityMapData;
import graphics.Sprite;

import java.util.ArrayList;

public class FlameItem extends Item {
    public FlameItem(int xUnit, int yUnit, ArrayList<ArrayList<EntityMapData>> mapData) {
        super(xUnit, yUnit, Sprite.powerup_flames, mapData);
    }
}
